public class Driver{
  public static void main(String[] arg){
    WordSearch n = new WordSearch(3,3);
    System.out.println("~ ~ ~ TESTING toString AND CONSTRUCTORS ~ ~ ~");
    System.out.println("\n~ Should print 3x3 grid of \"_\" (Spaces in between) ~");
    System.out.println(n);
    System.out.println("\n~ ~ ~ TESTING addWordHorizontal ~ ~ ~");
    System.out.println("\n~ Should print out \"hi\" in the first two places in first row ~");
    n.addWordHorizontal("hi",0,0);
    System.out.println(n);
    System.out.println("\n~ ~ These Tests See If Nothing Happens When (Mismatched) OverLapping Words Occur Or When Desired Word Is Too Large ~ ~");
    System.out.println("~ Should Return 2 True and 2 False and Grid with Just \"hin\" and \"X\" ~");
    System.out.println(n.addWordHorizontal("X",2,0));
    System.out.println(n.addWordHorizontal("in",0,1));
    System.out.println(n.addWordHorizontal("food",1,0));
    System.out.println(n.addWordHorizontal("REE",2,0));
    System.out.println("\n"+n);
    System.out.println("\n~ ~ ~ TESTING addWordVertical ~ ~ ~");
    WordSearch n2 = new WordSearch(5,5);
    System.out.println("\n~ Should print \"food\" in second column");
    n2.addWordVertical("food",0,1);
    System.out.print(n2);
    System.out.println("\n~ ~ These Tests See If Nothing Happens When (Mismatched) OverLapping Words Occur Or When Desired Word Is Too Large ~ ~");
    System.out.println("~ Should Return False then True and Grid with Just \"foody\" ~");
    System.out.println(n2.addWordVertical("fe",2,1));
    System.out.println(n2.addWordVertical("dy",3,1));
    System.out.println("\n"+n2);
  }
}
