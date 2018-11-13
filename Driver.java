import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class Driver {
  public static void main(String[] args) {
    try{
      WordSearch animals = new WordSearch(15,15,"words.txt");
      System.out.println(animals);
    } catch (FileNotFoundException e){
      System.out.println("File not found");
      System.exit(1);
    }
  }
}
