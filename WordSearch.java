import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
public class WordSearch{
  private char[][] data;
  //the random seed used to produce this WordSearch
  private int seed;
  //a random object to unify random calls
  private Random randgen;
  //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
  private ArrayList<String> wordsToAdd;
  //all words that were successfully added get moved into wordsAdded.
  private ArrayList<String> wordsAdded;
  /**Initialize the grid to the size specified
  *and fill all of the positions with '_'
  *@param row is the starting height of the WordSearch
  *@param col is the starting width of the WordSearch
  *@param fileName is the file from which we are reading the words from
  */
  public WordSearch(int rows, int cols, String fileName){
    try {
      File f = new File(fileName);
      Scanner in = new Scanner(f);
      String word = "";
      randgen = new Random();
      seed = Math.abs(randgen.nextInt() % 10000);
      wordsToAdd = new ArrayList<String>();
      wordsAdded = new ArrayList<String>();
      while(in.hasNext()){
        word = in.next();
        wordsToAdd.add(word);
      }
      data = new char[rows][cols];
      for (int row = 0; row < data.length;row++){
        for (int col = 0; col < data[row].length;col++){
          data[row][col]='_';
        }
      }
      //addAllWords();
    } catch (FileNotFoundException e){
      System.out.println("file not found");
    }
  }
  public WordSearch(int rows, int cols, String fileName, int randSeed){
    try {
      File f = new File(fileName);
      Scanner in = new Scanner(f);
      String word = "";
      seed = randSeed % 10000;
      wordsToAdd = new ArrayList<String>();
      wordsAdded = new ArrayList<String>();
      while(in.hasNext()){
        word = in.next();
        wordsToAdd.add(word);
      }
      data = new char[rows][cols];
      for (int row = 0; row < data.length;row++){
        for (int col = 0; col < data[row].length;col++){
          data[row][col]='_';
        }
      }
      addAllWords();
    } catch (FileNotFoundException e){
      System.out.println("file not found");
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
      s += "|";
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
  public boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement){
    int columnIndex = col;
    int rowIndex = row;
    boolean addOrNo = false;
    if ((rowIncrement == 0 && colIncrement == 0) || row < 0 || col < 0){
      return false;
    }
    if (row + (rowIncrement * word.length()) + 1 < 0 || row + (rowIncrement * word.length()) - 1 > data.length){
      return false;
    }
    if (col + (colIncrement * word.length()) + 1 < 0 || col + (colIncrement * word.length()) - 1 > data[row].length){
      return false;
    }
    for (int i = 0; i < word.length(); i++){
      if (data[rowIndex][columnIndex] == '_' || Character.toString(data[rowIndex][columnIndex]).equals(word.substring(i,i + 1))){
        addOrNo = true;
        rowIndex += rowIncrement;
        columnIndex += colIncrement;
      } else {
        return false;
      }
    }
    columnIndex = col;
    rowIndex = row;
    if (addOrNo){
      for (int i = 0; i < word.length(); i++){
        data[rowIndex][columnIndex] = word.charAt(i);
        columnIndex += colIncrement;
        rowIndex += rowIncrement;
      }
    } else {
      return false;
    }
    return true;
  }
  public void addAllWords(){
    randgen = new Random(seed);
    //System.out.println(rowIncrement);
    //System.out.println(colIncrement);
    int addAttempts = 50;
    //System.out.println("row: " + row);
    //System.out.println("col: " + col);
    while (addAttempts > 0 && wordsToAdd.size() > 0){ //remember that addwords retruns a boolean
      //System.out.println("wordsToAdd.size(): " + wordsToAdd.size());
      int randIndex = Math.abs(randgen.nextInt() % wordsToAdd.size());
      String value = wordsToAdd.get(randIndex);
      int row = Math.abs(randgen.nextInt() % data.length);
      int col = Math.abs(randgen.nextInt() % data[0].length);
      int rowIncrement = randgen.nextInt() % 2;
      int colIncrement = randgen.nextInt() % 2;
      if (addWord(value, row, col, rowIncrement, colIncrement)){
        addAttempts = 50;
        wordsAdded.add(wordsToAdd.remove(randIndex));
      } else {
        addAttempts --;
        System.out.println("row2: " + row);
        System.out.println("col2: " + col);
        while (addAttempts > 0){
          System.out.println(row + randgen.nextInt()%2);
          System.out.println(col + randgen.nextInt()%2);
          if (addWord(value, row + randgen.nextInt() % 2, col + randgen.nextInt() % 2, rowIncrement, colIncrement) == false){
            addAttempts --;
          }
        }
      }
    }
  }
}
