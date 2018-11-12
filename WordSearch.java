import java.util.random;
import java.util.scanner;
import java.util.arraylist;
import java.io.file;
import java.io.filenotfoundexception;
public class WordSearch{
  private char[][]data;
  //the random seed used to produce this WordSearch
  private int seed;
  //a random object to unify random calls
  private Random randgen;
  //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
  private ArrayList<String>wordsToAdd;
  //all words that were successfully added get moved into wordsAdded.
  private ArrayList<String>wordsAdded;
  /**Initialize the grid to the size specified
  *and fill all of the positions with '_'
  *@param row is the starting height of the WordSearch
  *@param col is the starting width of the WordSearch
  *@param fileName is the file from which we are reading the words from
  */
  public WordSearch(int rows, int cols, String fileName) throws FileNotFoundException {
    File f = new file(fileName);
    Scanner in = new Scanner(f);
    String word = "";
    randgen = new Random();
    seed = randgen.nextInt();
    while(in.hasNext()){
      String word = in.next();
      wordsToAdd.add(word);
    }
    data = new char[rows][cols];
    for (int row = 0; row < data.length;row++){
      for (int col = 0; col < data[row].length;col++){
        data[row][col]='_';
      }
    }
    addAllWords();
  }
  public WordSearch(int rows, int cols, String fileName, int randSeed) throws FileNotFoundException {
    File f = new file(fileName);
    Scanner in = new Scanner(f);
    String word = "";
    seed = randSeed;
    while(in.hasNext()){
      String word = in.next();
      wordsToAdd.add(word);
    }
    data = new char[rows][cols];
    for (int row = 0; row < data.length;row++){
      for (int col = 0; col < data[row].length;col++){
        data[row][col]='_';
      }
    }
    addAllWords();
  }
  /**Set all values in the WordSearch to underscores'_'*/
  private void clear(){
    for (int row = 0; row < data.length;row++){
      for (int col = 0; col < data[row].length;col++){
        data[row][col]='_';
      }
    }
  }
  /**Each row is a new line, there is a space between each letter
  *@return a String with each character separated by spaces, and rows
  *separated by newlines.
  */
  public String toString(){
    String s = "|";
    for (int row = 0;row<data.length;row++){
      for (int col=0;col<data[row].length;col++){
        s = s + data[row][col] + " ";
      }
      s = s + "|" + "\n";
    }
    for (int i = 0; i < wordsAdded.size(); i++){
      s = s + wordsAdded.get(i) + ",";
    }
    return s;
  }
  /**Attempts to add a given word to the specified position of the WordGrid.
  *The word is added in the direction rowIncrement,colIncrement
  *Words must have a corresponding letter to match any letters that it overlaps.
  *
  *@param word is any text to be added to the word grid.
  *@param row is the vertical locaiton of where you want the word to start.
  *@param col is the horizontal location of where you want the word to start.
  *@param rowIncrement is -1,0, or 1 and represents the displacement of each letter in the row direction
  *@param colIncrement is -1,0, or 1 and represents the displacement of each letter in the col direction
  *@return true when: the word is added successfully.
  *        false when: the word doesn't fit, OR  rowchange and colchange are both 0,
  *        OR there are overlapping letters that do not match
  */
  private boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement){
    /*[rowIncrement,colIncrement] examples:
    *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)
    *[ 1,0] would add downwards because (row+1), with no col change
    *[ 0,-1] would add towards the left because (col - 1), with no row change
    */
    char[][] newBoard = data;
    int index = 0;
    int columnIndex = col;
    int rowIndex = row;
    if (col + word.length() <= data[row].length && row + word.length() <= data.length){ //checks if word is in bounds if starting from row/col 0 subtract 1 from both cases
      for (int i = 0; i < word.length(); i++){
        if (data[rowIndex][columnIndex] == '_' || Character.toString(data[rowIndex][columnIndex]).equals(word.substring(index, index + 1))){
          data[rowIndex][columnIndex] = word.charAt(index);
          index ++;
          columnIndex += colIncrement;
          rowIndex += rowIncrement;
        } else {
          data = newBoard;
          return false;
        }
      }
      return true;
    }
    data = newBoard;
    return false;
  }
  private void addAllWords(){
  }
}
