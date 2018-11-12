import java.util.random;
import java.util.scanner;
import java.util.arraylist;
import java.io.file;
import java.io.filenotfoundexception;
public class WordSearch{
  private char[][]data;
  /**Initialize the grid to the size specified
   *and fill all of the positions with '_'
    *@param row is the starting height of the WordSearch
    *@param col is the starting width of the WordSearch
    */
  //the random seed used to produce this WordSearch
  private int seed;
  //a random object to unify random calls
  private Random randgen;
  //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
  private ArrayList<String>wordsToAdd;
  //all words that were successfully added get moved into wordsAdded.
  private ArrayList<String>wordsAdded;
  public WordSearch(int rows,int cols){
    data = new char[rows][cols];
    for (int row = 0; row < data.length;row++){
      for (int col = 0; col < data[row].length;col++){
        data[row][col]='_';
      }
    }
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
    String s = "";
    for (int row = 0;row<data.length;row++){
      for (int col=0;col<data[row].length;col++){
        s = s + data[row][col] + " ";
      }
      s = s + "\n";
    }
    return s;
  }
  /**Attempts to add a given word to the specified position of the WordGrid.
   *The word is added from left to right, must fit on the WordGrid, and must
   *have a corresponding letter to match any letters that it overlaps.
   *
   *@param word is any text to be added to the word grid.
   *@param row is the vertical locaiton of where you want the word to start.
   *@param col is the horizontal location of where you want the word to start.
   *@return true when the word is added successfully. When the word doesn't fit,
   * or there are overlapping letters that do not match, then false is returned
   * and the board is NOT modified.
   */
  public boolean addWordHorizontal(String word,int row, int col){
    char[][] newBoard = data;
    String temp = word;
    int place = 0;
    if (col+word.length() <= data[row].length){
      for (int i=col;i<temp.length() + col ;i++){
        if ( data[row][i] == '_' || Character.toString( data[row][i] ).equals( temp.substring (place,place+1) ) ) {
          data[row][i]=temp.charAt(place);
          place++;
        }else{
          data=newBoard;
          return false;
        }
      }
      return true;
    }
    data=newBoard;
    return false;
  }
  /**Attempts to add a given word to the specified position of the WordGrid.
   *The word is added from top to bottom, must fit on the WordGrid, and must
   *have a corresponding letter to match any letters that it overlaps.
   *
   *@param word is any text to be added to the word grid.
   *@param row is the vertical locaiton of where you want the word to start.
   *@param col is the horizontal location of where you want the word to start.
   *@return true when the word is added successfully. When the word doesn't fit,
   *or there are overlapping letters that do not match, then false is returned.
   *and the board is NOT modified.
   */
   public boolean addWordVertical(String word,int row, int col){
    char[][] newBoard = data;
    String temp = word;
    int place = 0;
    if (row+word.length() <= data.length){
      for (int i=row;i<temp.length() + row ;i++){
        if ( data[i][col] == '_' || Character.toString( data[i][col] ).equals( temp.substring (place,place+1) ) ) {
          data[i][col]=temp.charAt(place);
          place++;
        }else{
          data=newBoard;
          return false;
        }
      }
      return true;
    }
    data=newBoard;
    return false;
  }
  /**Attempts to add a given word to the specified position of the WordGrid.
  *The word is added from top left to bottom right, must fit on the WordGrid,
  *and must have a corresponding letter to match any letters that it overlaps.
  *
  *@param word is any text to be added to the word grid.
  *@param row is the vertical locaiton of where you want the word to start.
  *@param col is the horizontal location of where you want the word to start.
  *@return true when the word is added successfully. When the word doesn't fit,
  *or there are overlapping letters that do not match, then false is returned.
  */
  public boolean addWordDiagonal(String word,int row, int col){
    char[][] newBoard = data;
    int index = 0;
    int columnIndex = col;
    if (col - 1 + word.length() <= data[row].length && row - 1 + word.length() <= data.length){
      for (int i = row; i < word.length(); i++){
        if (data[i][columnIndex] == '_' || Character.toString(data[i][columnIndex]).equals(word.substring (index, index + 1))){
          data[i][columnIndex] = word.charAt(index);
          index ++;
          columnIndex ++;
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
}
