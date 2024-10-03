import java.util.Scanner;
import java.util.Random; //need this to make "random" responses

/**
 * Class called Conversation, contains two arrays of mirror words and replacements, has methods to check for these words in user strings
 */
public class Conversation {
//attributes
//can't change internal stuff, that would be bad
  private String[] mirrorWords;
  private String[] replacementWords;
  

  /**
   * constructor for class Conversation, puts all the needed mirror words into the arrays
   */
  public Conversation() {
    this.mirrorWords = new String[6];
    this.replacementWords = new String[6];

    this.mirrorWords[0] = "i"; 
    this.mirrorWords[1] = "me";
    this.mirrorWords[2] = "am";
    this.mirrorWords[3] = "you";
    this.mirrorWords[4] = "my";
    this.mirrorWords[5] = "your";

    this.replacementWords[0] = "you";
    this.replacementWords[1] = "you";
    this.replacementWords[2] = "are";
    this.replacementWords[3] = "i";
    this.replacementWords[4] = "your";
    this.replacementWords[5] = "my";
  }
  

  /**
   * Generates a canned response based on an int 
   * @param index the index number of the array of responses, should be random
   * @return String: it gives back one of the strings in the array
   */
  public String randoThought(int index) {
    String[] cannedResponses = new String[5];
    cannedResponses[0] = "huh, thats neat I guess.";
    cannedResponses[1] = "I believe you buddy.";
    cannedResponses[2] = "Woah, radical dude.";
    cannedResponses[3] = "mmhmm";
    cannedResponses[4] = "Absolutely stupendous thoughts you have.";

    return cannedResponses[index];
  }

  /**
   * splits up a string of multiple words based on the white space between them
   * @param s the string passed into the splitting function to be broken up
   * @return array: each index holds one word from the original string
   */
  public String[] sliceAndDice(String s){
    String[] words = s.split("\\s+"); //I tried splitting along " ", but for some reason it gave me grief so I did //s+
    return words;
  }

  /**
   * Checks through the string to see if any of the mirror words are contained in it
   * @param line the original user input chunked into an array of strings by sliceAndDice
   * @return T/F: are any of the the strings in the array mirror words?
   */
  public boolean checker(String[] line){
    boolean proceed = false;
    String[] littleLine = new String[line.length];
    
    for (int i = 0; i < (line.length); i++){ //transfers the user input to 
      littleLine[i] = (line[i]).toLowerCase();
    }

    for(int i = 0; i < line.length; i++){
      for(int j = 0; j < 6; j++){ //6 because that's how many mirror words there are
        if(littleLine[i].equals(mirrorWords[j])){
          proceed = true;
        }
      }
    }
    return proceed;
  }

  /**
   * Swaps out the mirror words for their replacements
   * @param words an array of individual words from user input
   * @return array of strings having reversed the mirror words
   */
  public String[] swipSwap(String[] words){
    //gets #of elements in the array words
    int howManyWords = words.length;
    String[] response = new String[(words.length)];

    for(int i = 0; i < howManyWords; i++){
      if(words[i].equalsIgnoreCase("I")){
        response[i] = "you";
      } else if (words[i].equalsIgnoreCase("me")){
        response[i] = "you";
      } else if (words[i].equalsIgnoreCase("am")){
        response[i] = "are";
      } else if (words[i].equalsIgnoreCase("you")){
        response[i] = "i";
      } else if (words[i].equalsIgnoreCase("my")){
        response[i] = "your";
      } else if (words[i].equalsIgnoreCase("your")){
        response[i] = "my";
      } else if (words[i].equalsIgnoreCase("are")){
        response[i] = "am";
      } else {
        response[i] = words[i];
      }
    }
    return response;
  }


/**
 * Main function, which is where the conversation is begun and carried out in a for loop, 
 * it creates an array to store the transcript and outputs it after the conversation
 * @param arguments
 */
  public static void main(String[] arguments) {
    //initializes conversation so I can use it's methods
    Conversation myConvo = new Conversation();
    Random rand = new Random(); //makes it so I can have random numbers
    //String transcript = "***TRANSCRIPT***" + "\n";


    //declares a Scanner named input and uses it to get number of lines
    Scanner input = new Scanner(System.in);
    String line;
    int counter = 2;


    //ask number of rounds (need to know for transcript array and for loop)
    System.out.print("How many rounds would you like? ");

    int times = input.nextInt();
    input.nextLine(); //gets rid of the return character that ignored the next input command
    System.out.println();

    String[] transcript = new String[(times*2) + 3];
    transcript[0] = "***TRANSCRIPT***";

    //greeting
    System.out.println("I'm glad to talk with you! What's up?");
    transcript[1] = "I'm glad to talk with you! What's up?";
    //transcript += "I'm glad to talk with you! What's up? \n";

    //for loop for the conversation
    for (int i = 0; i < times; i++) {
      line = input.nextLine();
      transcript[counter] = line;
      counter += 1;

      if(myConvo.checker(myConvo.sliceAndDice(line))){ //checks for mirror word existence
        String collection = ""; //to collect the edited string for the transcript
        String[] words = myConvo.swipSwap(myConvo.sliceAndDice(line)); //swaps mirror words out
        for(int j = 0; j<(words.length); j++){
          //transcript += (words[j] + " "); //concatenates these words into the transcript
          System.out.print(words[j] + " ");
          collection += words[j] + " ";
        }
        //transcript += "\n";
        transcript[counter] = collection;
        counter += 1;
        System.out.println(); //otherwise my next line will squish right onto the last one
      } else {
        String canned = myConvo.randoThought(rand.nextInt(5)); //returns a random response because there's no mirror words
        System.out.println(canned);
        transcript[counter] = canned;
        counter += 1;
        //transcript += canned + "\n";
      }
    }
    System.out.println("Whoops I'm late, bye!"); 
    //transcript += "Whoops I'm late, bye!";
    transcript[(times*2) + 2] = "Whoops I'm late, bye!";

    System.out.println();
    System.out.println();

    for(int i = 0; i < ((times*2) + 3); i++){
      System.out.println(transcript[i]);
    }
    //System.out.println(transcript);
    input.close(); //no memory leaks?
  }
}