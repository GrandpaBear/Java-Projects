import java.util.Scanner;
import java.util.ArrayList;
/* ***************************
* Author: Jianying Chiang    Date: 2018-08-21
* The purpose of this program is to find the largest palindrome(s).
* It asks the user for a string input. It verifies that the word is valid 
* (valid length and valid characters). It scans the palindrome to find 
* the largest palindrome(s) and display them.
* ****************************/
class PalindromeFinder {
  public static ArrayList<String> arr;
  public static boolean valid=false;
  
  public static void main(String[] args) {
    String input = "";
    System.out.println("Welcome to the PalindromeFinder. This program will display the largest palindrome(s) found within your input.");
    System.out.println("Your input will automatically be lower cased");
    
    //user input
    while(valid==false) {
      input = userInput();
      for (int l = 0; l < input.length(); l++) {
        int asciiVal = input.charAt(l);
        if (asciiVal<65 || (asciiVal>90 && asciiVal<97) || asciiVal>122) {
          System.out.println("Only letter values are allowed in your palindrome. Try again!");
          l=input.length();
          valid=false;
        }
      }
    }
    input.toLowerCase();
    
    //finds largest palindrome
    arr = new ArrayList();
    int i = 0;
    int j = input.length();
    int pos = input.length();
    
    while(i!=j && i<j) {
      palindromeScanner(input.substring(i, j));
      if (j==input.length() && arr.isEmpty()==true) {
        pos--;
        j=pos;
        i=0;
      }
      else if (j==input.length() && arr.isEmpty()==false) {
        i=j;              
      }
      else {
        i++;
        j++;
      }
    }
    
    //displays results
    display();   
  } // --------------------------end of main method--------------------------
/* ***************************
* Author: Jianying Chiang    Date: 2018-08-21
* userInput method takes user input as a string.
* @param: none
* @return: String
* ****************************/
  public static String userInput() {
    String input = "";
    Scanner in = new Scanner(System.in);
    while (input.length()<2) {
    System.out.print("Enter a palindrome(must have a length greater than 1): ");
    input = in.nextLine();
    }
    in.close();
    valid=true;
    return input;  
  } // -----------------------end of userInput method-----------------------
/* ***************************
* Author: Jianying Chiang    Date: 2018-08-21
* palindromeScanner method takes a substring of the user input
* and adds it to the ArrayList<String> arr if it is a palindrome.
* @param: String
* @return: none
* ****************************/
  public static void palindromeScanner(String word) {
    int i = 0;
    int j = word.length()-1;
    while (i!=j && i<j) {
      if (word.charAt(i)==word.charAt(j)) {
        if (i==j-1) {
          arr.add(word);
        }
        i++;
        j--;
        if (i==j) {
          arr.add(word);
        }
      }
      else {
        i=j;
      }
    }
  } // -----------------end of palindromeScanner method-----------------
/* ***************************
* Author: Jianying Chiang    Date: 2018-08-21
* display method displays the the largest palindrome(s) by
* printing ArrayList<String >arr, if any. 
* @param: none
* @return: String
* ****************************/
  public static void display() {
    if (arr.isEmpty()==false) {
      System.out.print("Here are the largest palindrome(s) in your input: ");
      System.out.println(arr);
    }
    else {
      System.out.println("There are no palindromes in your input.");    
    }
  } // -----------------------end of display method-------------------------
} // -----------------------end of PalindromeFinder class-----------------
