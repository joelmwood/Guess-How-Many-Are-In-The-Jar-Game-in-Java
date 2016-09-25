import java.io.Console;
import java.util.Scanner;

public class Promptor{
  private Jar mJar;
  Console console = System.console(); 
  
  public Promptor(Jar jar){
    mJar = jar;
  }
      
  public void play(){
    console.printf("The possible fill amount is between 1 and %d\n", mJar.getAnswer());
    while(!mJar.isSolved()){
      //The following line can be uncommented to show number of attempts during play.
      //displayProgress();
      promptForGuess();
    }
    if(mJar.isSolved()){
      //System.out.printf("Congratulations! You won with using %d tries.\n", 
      //                  mJar.getTries());
      String attempt;
      if (mJar.getTries() > 1){
        attempt = "attempts";
      }else{
        attempt = "attempt";
      }
      System.out.printf("%d %s\n", mJar.getTries(), attempt);
    }
  }
  
  public void displayProgress(){
    System.out.printf("This is attempt #%d \n", 
                      mJar.getTries());
  }

  public boolean promptForGuess(){
    Scanner scanner = new Scanner(System.in);
    //String livesAsString = console.readLine("How many lives?");
    //int livesAsInt = Integer.parseInt(livesAsString);
    boolean isHit = false;
    boolean isValidGuess = false;
    //console.printf("The possible fill amount is between 1 and %d\n", mJar.getAnswer());
    while(!isValidGuess){
      try{
        String guessAsString = console.readLine("How many %s? ", Game.getTypeOfItem());
        int guessAsInt = Integer.parseInt(guessAsString);
        try{
          isHit = mJar.applyGuess(guessAsInt);
          isValidGuess = true;
        }catch(IllegalArgumentException iae){
          console.printf("%s Please try again.\n", iae.getMessage());
        }
      }catch(NumberFormatException iae){
        console.printf("A number must be entered.\n");
      }
    }
    return isHit;
   }
}
