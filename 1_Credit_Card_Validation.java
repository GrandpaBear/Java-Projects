import java.util.Scanner;
/* ***************************
* Author: Jianying Chiang    Date: 2018-02-16
* The purpose of this program is to validate
* 16-digit credit cards using a certain algorithm
* and to display the results to the user.
* ****************************/
class CreditCardValidation {
  public static void main(String[] args) {
    CreditCardJianyingChiang methodCaller = new CreditCardJianyingChiang();
    methodCaller.inputNo();
    methodCaller.validateNo();
    methodCaller.displayResult();
  } // -------end of main method--------------------------
}   // -----end of MainPrgAssign1JianyingChiang class-----

/* ***************************
* Author: Jianying Chiang    Date: 2018-02-14
* Secondary class is used to hold the methods that can
* execute the code required to meet the program
* specifications.
* ****************************/
class CreditCardJianyingChiang {
  private int verifier = 0;
  private int k;
  private int q;
  private int f = 0;
  private int check = 0;
  private String wordInput = "";
  private String cardNo = "";
  private String userInput = "";
  private String companyCard = "";
  private boolean x = false;
  CreditCardJianyingChiang() {verifier=0; k=0; q=0; f=0; check=0; wordInput=""; cardNo=""; userInput=""; companyCard="";}
  public int setVerifier() {return verifier;}
  public void getVerifier(int A){verifier = A;}
  public int setK() {return k;}
  public void getK(int B){k = B;}
  public int setQ() {return q;}
  public void getQ(int C){q = C;}  
  public int setF() {return f;}
  public void getF(int D){f = D;}
  public int setCheck() {return check;}
  public void getCheck(int E){check = E;}  
  public String setWordInput() {return wordInput;}
  public void getWordInput(String F){wordInput = F;}
  public String getCardNo(){return cardNo;}
  public void setCardNo(String H){cardNo = H;}
  public String getUserInput(){return userInput;}
  public void setUserInput(String I){userInput = I;}
  public String getCompanyCard(){return companyCard;}
  public void setCompanyCard(String J){companyCard = J;}
  public boolean setX(){return x;}
  public void getX(boolean K){x = K;}
/* ***************************
* Author: Jianying Chiang    Date: 2018-02-14
* inputNo method asks for appropriate user input.
* It further investigates whether the input is
* appropriate. If not, it stops the program if the
* program asks for a 16-digit input, but is unappropriate.
* If the program has a (Y/N) option, it will reask 
* for appropriate input.
* @param: none
* @return: none
* ****************************/
  public void inputNo() {
    Scanner in = new Scanner(System.in);
    int h;
    int f=0;
    if (verifier == 0) {
      System.out.println("Please Enter A 16-digit Credit Card Number:");
    }
    else {
      if (check != 1) {
      System.out.println("Do you wish to enter another 16-digit Credit Card Number (Y/N)?");
      }
      wordInput = in.nextLine();
      while (!wordInput.equals("Y") && !wordInput.equals("y") && !wordInput.equals("n")&& !wordInput.equals("N")) {
        System.out.println("INVALID INPUT. Enter again (Y/N)");
        wordInput = in.nextLine();
      }
    }
    if (wordInput.equals("Y") || wordInput.equals("y") || verifier == 0) {
      userInput = in.nextLine();
      if (userInput.length() == 16) {
        for (h=0; h < userInput.length(); h++) {
          char g = userInput.charAt(h);
          if (g >= 48 && g <= 57) {
            k=1;
            if (h==0) {
              f=0;
              x=false;
            }
          }
          else {
            f = 17;
            verifier = 1;
            check = 1;
            k=2;
            f=17;
            wordInput = "stop";
            System.out.println("INVALID INPUT Format.");
          }
        }
        if (k==1 && f!=17) {
          cardNo = userInput;
        }
      }
      else {
        verifier = 1;
        check = 1;
        f=17;
        wordInput = "stop";
        System.out.println("INVALID INPUT Format.");
      }
    }
    else if (wordInput.equals("N") || wordInput.equals("n")) {
      System.out.println("Good-bye!");
      f=17;
      q=1;
    }
    in.close();
  } // --------end of inputNo method ----------
/* ***************************
* Author: Jianying Chiang    Date: 2018-02-14
* validateNo method transfers the string of number
* into integers so it can be used for the verification
* process. It first identifies whether the type of card
* is Visa, Master Card, or No Name. It then checks
* the validity of the by doubling 
* all odd-positioned digits when counting from left
* to right. These numbers are added with the even-positioned
* digits. If the sum is a multiple of 10, then the card is 
* valid, as long as its a Visa or Master Card.
* @param: none
* @return: none
* ****************************/
  public boolean validateNo() {
    int a;
    int good;
    String odd = "";
    int s;
    int sum = 0;
    int realArray[] = new int[16];
    if (f!=17 && k ==1) {
      char charArray[] = cardNo.toCharArray();
      for (a=0; a<userInput.length(); a++) {
      realArray[a] = Character.getNumericValue(charArray[a]);
      }
      if (realArray[0] != 4 && realArray[0] != 5) {
        System.out.print("No Name Card of");
        f=0;
        check = 1;
        x = false;
      }
      else {
        if (realArray[0] == 4) {
          companyCard = "Visa card";  
        }
        else if (realArray[0] == 5) {
          companyCard = "Master Card";
        }
        for (s=1; s<16; s++) {
          sum = sum + realArray[s];
          s++;
        }
        for (s=0; s<16; s++) {
          good = 2*realArray[s];
          odd = odd + Integer.toString(good);
          s++;
        }
        char evenArray[]= odd.toCharArray();
        for (s=0; s<odd.length(); s++) {
          sum = sum + Character.getNumericValue(evenArray[s]); 
        }
        if (sum%10 == 0) {
          x = true;      
        }
        else {
          x = false;      
        }
      }
    }
    return x;
  } // --------end of validateNo method ----------
/* ***************************
* Author: Jianying Chiang    Date: 2018-02-14
* displayResult method has the capability to take the
* verified information and display the type of card,
* the card number, and whether its valid or not
* It then calls the other methods in order to repeat
* the whole program if requested.
* @param: none
* @return: none
* ****************************/
  public void displayResult() {
    if (f!=17 && !wordInput.equals("stop")) {
    if (x==false) {
      System.out.println(companyCard + " " + cardNo + " is Invalid");
    }
    else {
      System.out.println(companyCard + " " + cardNo + " is Valid");     
    }
    verifier = 1;
    while (wordInput.equals("Y") || wordInput.equals("y") || wordInput.equals("")) {
    wordInput = "";
    cardNo = "";
    userInput = "";
    companyCard = "";
    k = 0;
    f = 0;
    check = 0;
    inputNo();
    validateNo();
    if (q==0) {
    displayResult();
    }
    }
  } 
  } // --------end of displayResult method ----------
} // -------end of CreditCardJianyingChiang class ---