import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This class holds the main method.
* The purpose of this program is to mimic the
* functionality of working with a baseball database.
* It includes search and sorting routines. It also
* works with exceptions and limits user input.
* ****************************/
class Assignment10JianY {
  public static ArrayList<BaseBallStatsJianY> list;
  public static void main(String[] args) {
    list = new ArrayList<BaseBallStatsJianY>();
    System.out.println("Welcome to the Baseball players' database.");
    MainMenu obj = new MainMenu();
    obj.mainMenu();
  }
}
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This class is holds the menu system.
* ****************************/
class MainMenu {
  int choiceNo;
  Scanner userInput;
  MainMenu() {
    userInput = new Scanner(System.in);
  }
  public void mainMenu() {
    while (choiceNo!=9) {
      Scanner userInput = new Scanner(System.in);
      System.out.println("Enter the number of your choice of the corresponding commands.");
      System.out.println("1.  Load stats info from a file ...");
      System.out.println("9.  Exit the program");
      try {
        choiceNo = userInput.nextInt();
        switch (choiceNo) {
          case 1:
            loadInformation();
            while (choiceNo!=10) {
              try {
                System.out.println("Enter the number of your choice of the corresponding commands.");
                System.out.println("2.  Display all players");
                System.out.println("3.  Enter Player's Height");
                System.out.println("4.  Sort all players alphabetically by Surname");
                System.out.println("5.  Sort all players by Batting Average (High to Low)");
                System.out.println("6.  Delete a player by selecting player's surname from a list.");
                System.out.println("7.  Add a player to the stats");
                System.out.println("8.  Search for a player by Surname and display if found");
                System.out.println("9.  Save stats to a file");
                System.out.println("10.  Exit the program");
                choiceNo = userInput.nextInt();
                switch (choiceNo) {
                  case 2:
                    displayPlayerInfo();
                    break;
                  case 3:
                    addDataHeight();                 
                    break;
                  case 4:
                    sortDataAlpha();               
                    break;
                  case 5:
                    sortDataBattingAverage();
                    break;
                  case 6:
                    deleteAPlayer();                
                    break;
                  case 7:
                    addAPlayer();
                    break;
                  case 8:
                    searchPlayer();
                    break;
                  case 9:
                    saveInformation();
                    break;
                  case 10:
                    System.out.println("Closed program.");
                    break;
                  default:
                    System.out.println("Invalid choice. Please follow instructions.");
                    break;
                }
              } catch(InputMismatchException e) {
                e.getMessage();
                System.out.println("InputMismatchException detected.");
              }
            }
            choiceNo=9;
            break;
          case 9:
            System.out.println("Closed program.");
            break;
          default:
            System.out.println("Invalid choice. Please follow instructions.");
            break;
        }
    } catch(InputMismatchException e) {
        e.getMessage();
        System.out.println("InputMismatchException detected.");
      }
    userInput.close();
    }
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This method loads players' data from a file.
* @param: None
* @return: None
* ****************************/
  public void loadInformation() {
      String fileName;
      System.out.print("Enter an existing file name(*.txt): ");
      fileName = userInput.nextLine();
      try {
        File file = new File(fileName);
        Scanner fileInput = new Scanner(file);
        fileInput.useDelimiter("\\s");
        try {
          while (fileInput.hasNext()) {
            String lastName = fileInput.next();
            String firstName = fileInput.next();
            String position = fileInput.next();
            int height = Integer.parseInt(fileInput.next());
            int hits = Integer.parseInt(fileInput.next());
            int atBats = Integer.parseInt(fileInput.next());
            int singles = Integer.parseInt(fileInput.next());
            int doubles = Integer.parseInt(fileInput.next());
            int triples = Integer.parseInt(fileInput.next());
            int homeruns = Integer.parseInt(fileInput.next());
            if (fileInput.hasNext()) {
              String nul = fileInput.next();            
            }
            Assignment10JianY.list.add(new BaseBallStatsJianY(lastName, firstName, position, height, hits, atBats,
                                            singles, doubles, triples, homeruns));
          }
            fileInput.close();
        } catch(InputMismatchException e) {
          System.out.println("InputMismatchException detected. Make sure the file is in proper format.");
          System.out.println("The following delimiters are: ||, ||,||\r\n|| \r\n||,\r\n||, \r\n");  
        }
      } catch(IOException e) {
        e.getMessage();
        System.out.print("File does not exist. ");
        loadInformation();
      }
    }
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This method save players' data to a file.
* @param: None
* @return: None
* ****************************/
    public void saveInformation() {
      System.out.print("Enter file name which you wish to save in: ");
      String fiName=userInput.nextLine();
      try {
      PrintWriter inFile = new PrintWriter(fiName);
      for (int x=0; x<Assignment10JianY.list.size(); x++) {
        inFile.print("nigga");
        inFile.printf("%s\t",Assignment10JianY.list.get(x).getSName());
        inFile.printf("%s\t",Assignment10JianY.list.get(x).getGName());
        inFile.printf("%s\t",Assignment10JianY.list.get(x).getPosition());
        inFile.printf("%d\t",Assignment10JianY.list.get(x).getHeight());
        inFile.printf("%d\t",Assignment10JianY.list.get(x).getHits());
        inFile.printf("%d\t",Assignment10JianY.list.get(x).getAtBat());
        inFile.printf("%d\t",Assignment10JianY.list.get(x).getSingles());
        inFile.printf("%d\t",Assignment10JianY.list.get(x).getDoubles());
        inFile.printf("%d\t",Assignment10JianY.list.get(x).getTriples());
        inFile.printf("%d%n",Assignment10JianY.list.get(x).getHomeruns());
        }
      inFile.close();
      } catch(IOException e) {
        System.out.println("File not found. Type in a file name that exists.");
        saveInformation();
      }
    }
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This method searches and displays specified
* player data.
* @param: None
* @return: None
* ****************************/
    public void searchPlayer() {
      System.out.println("Search Player surn2i31ame: ");
      String nameSearched = userInput.next();
      for(int x=0; x<Assignment10JianY.list.size(); x++) {
        if (Assignment10JianY.list.get(x).getSName().equals(nameSearched)) {
          System.out.printf("%s %s\n",Assignment10JianY.list.get(x).getSName(),Assignment10JianY.list.get(x).getGName());
          System.out.printf("Position: %s\n",Assignment10JianY.list.get(x).getPosition());
          System.out.printf("Height(cm): %d\n",Assignment10JianY.list.get(x).getHeight());
          System.out.printf("Hits: %d\n",Assignment10JianY.list.get(x).getHits());
          System.out.printf("AtBat: %d\n",Assignment10JianY.list.get(x).getAtBat());
          System.out.printf("Singles: %d\n",Assignment10JianY.list.get(x).getSingles());
          System.out.printf("Doubles: %d\n",Assignment10JianY.list.get(x).getDoubles());
          System.out.printf("Triples: %d\n",Assignment10JianY.list.get(x).getTriples());
          System.out.printf("Homeruns: %d\n",Assignment10JianY.list.get(x).getHomeruns());
          System.out.printf("Batting Average: %.2f\n",Assignment10JianY.list.get(x).calcBattingAvg());
        }
        else if (x==Assignment10JianY.list.size() && !Assignment10JianY.list.get(x).getSName().equals(nameSearched)) {
          System.out.println("Player cannot be found.");
        }
        else {
          //nothing        
        }
      }
    }
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This method adds a player along with stats.
* @param: None
* @return: None
* ****************************/
    public void addAPlayer() {
      Assignment10JianY.list.add(new BaseBallStatsJianY());
      Assignment10JianY.list.get(Assignment10JianY.list.size()-1).assignValuePlayer();
      Assignment10JianY.list.get(Assignment10JianY.list.size()-1).setAll();
      
      if (Assignment10JianY.list.get(Assignment10JianY.list.size()-1).checkStats()==true) {
        System.out.println("Player added.");      
      }
      else {
        Assignment10JianY.list.get(Assignment10JianY.list.size()-1).correctStats();
      }
    }
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This method deletes a player.
* @param: None
* @return: None
* ****************************/
    public void deleteAPlayer() {
      System.out.println("Nothing will occur if player does not exist.");
      System.out.println("Enter Player's Surname: ");
      String nameSearched = userInput.next();
      for(int x=0; x<Assignment10JianY.list.size(); x++) {
        if (Assignment10JianY.list.get(x).getSName().equals(nameSearched)) {
        System.out.printf("Is %s %s the record you wish to delete? (y/n)",
                          Assignment10JianY.list.get(x).getGName(), Assignment10JianY.list.get(x).getSName());
        int checker=0;
        while (checker==0) {
          String answer;
          answer=userInput.next();
          if (answer.equals("y") || answer.equals("Y")) {
            Assignment10JianY.list.remove(x);
            checker++;
          }
          else if (answer.equals("n") || answer.equals("N")) {
            //x++
            checker++;
          }
          else {
            System.out.println("Invalid input.");
            x=Assignment10JianY.list.size()+1;
          }
        }
      }
      }
    }
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This method sets the height of all players.
* @param: None
* @return: None
* ****************************/
    public void addDataHeight() {
      Scanner userInput = new Scanner(System.in);
      int h=0;
      System.out.println(Assignment10JianY.list.size());
      try {
        while (h<Assignment10JianY.list.size()) {
        System.out.printf("Enter %s %s's height: ",Assignment10JianY.list.get(h).getGName(),
                          Assignment10JianY.list.get(h).getSName());
        int check = userInput.nextInt();
        while (check<125 || check>240) {
          System.out.println("Height must be less than 240cm and greater than 125cm.");
          check = userInput.nextInt();
        }
        Assignment10JianY.list.get(h).setHeight(check);
        h++;
        }
      } catch(InputMismatchException e) {
        System.out.printf("\nInputMismatchException detected. ");
        addDataHeight();      
      }
      userInput.close();
    }
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This method sorts by batting average.
* @param: None
* @return: None
* ****************************/
    //Insertion Sort Routine
    public void sortDataBattingAverage() {
      for (int x=Assignment10JianY.list.size()-1; x>0; x--) {
        if (Assignment10JianY.list.get(x).calcBattingAvg()<Assignment10JianY.list.get(x-1).calcBattingAvg()) {
          //continue checking
        }
        else {
          for (int y=x; y>0; y--) {
            BaseBallStatsJianY itemHolder1 = Assignment10JianY.list.get(y-1); 
            BaseBallStatsJianY itemHolder2 = Assignment10JianY.list.get(y);
            Assignment10JianY.list.set(y-1, itemHolder2);
            Assignment10JianY.list.set(y, itemHolder1);
            if (Assignment10JianY.list.get(y).calcBattingAvg()<Assignment10JianY.list.get(y-1).calcBattingAvg()) {
              x=Assignment10JianY.list.size()-1;
            }
          }
        }
      }
    }
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This method sorts players alphabetically
* @param: None
* @return: None
* ****************************/
    //Bubble Sort Routine
    public void sortDataAlpha() {
      int count=Assignment10JianY.list.size();
      for (int x=1; x<count; count--) {
        for (int y=1; y<Assignment10JianY.list.size(); y++) {
          if (Assignment10JianY.list.get(y-1).getSName().compareTo(Assignment10JianY.list.get(y).getSName()) > 0) {
            BaseBallStatsJianY itemHolder1 = Assignment10JianY.list.get(y-1); 
            BaseBallStatsJianY itemHolder2 = Assignment10JianY.list.get(y);
            Assignment10JianY.list.set(y-1, itemHolder2);
            Assignment10JianY.list.set(y, itemHolder1);
          }
          else {
          }
        }
      }
    }
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This method displays player data.
* @param: None
* @return: None
* ****************************/
    public void displayPlayerInfo() {
      System.out.printf("Surname\t GivenName\t Position\t Height(cm)\t Hits\t AtBats\t Singles\t Doubles\t Triples\t Homeruns\t Batting Average\n");
      for (int x=0; x<Assignment10JianY.list.size(); x++) {
      System.out.printf("%s\t %s\t %s\t %d\t %d\t %d\t %d\t %d\t %d\t %d\t %.2f\n", 
                        Assignment10JianY.list.get(x).getSName(),Assignment10JianY.list.get(x).getGName(),
                        Assignment10JianY.list.get(x).getPosition(),Assignment10JianY.list.get(x).getHeight(),
                        Assignment10JianY.list.get(x).getHits(),Assignment10JianY.list.get(x).getAtBat(),
                        Assignment10JianY.list.get(x).getSingles(),Assignment10JianY.list.get(x).getDoubles(),
                        Assignment10JianY.list.get(x).getTriples(),Assignment10JianY.list.get(x).getHomeruns(),
                        Assignment10JianY.list.get(x).calcBattingAvg());
      }
      System.out.println("");
    }
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This class is used to create player objects,
* including surname, given name, position, and
* height. Includes methods to change this data.
* ****************************/
class PlayerJianY {
  private String sName;
  private String gName;
  private String position;
  private int height;
  public void setSName(String A) {sName=A;}
  public String getSName() {return sName;}
  public void setGName(String A) {gName=A;}
  public String getGName() {return gName;}
  public void setPosition(String A) {position=A;}
  public String getPosition() {return position;}
  public void setHeight(int A) {height=A;}
  public int getHeight() {return height;}
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This method initializes player data.
* @param: None
* @return: None
* ****************************/
  public void initializePlayer() {
    sName="";
    gName="";
    position="";
    height=-1;
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This method assigns values to player data.
* @param: None
* @return: None
* ****************************/
  public void assignValuePlayer() {
    Scanner userInput = new Scanner(System.in);
    try {
    System.out.print("Enter value for surname: ");
    sName=userInput.next();
    System.out.printf("\nEnter value for given name: ");
    gName=userInput.next();
    System.out.printf("\nEnter value for position: ");
    position=userInput.next();
    System.out.printf("\nEnter value for height: ");
    int check = userInput.nextInt();
    while (check<125 || check>240) {
      System.out.println("Height must be less than 240cm and greater than 125cm.");
      check = userInput.nextInt();
    }
    height=check;
  } catch(InputMismatchException e) {
      System.out.println("InputMismatchException detected.");
      System.out.println("Please re-enter the values with proper integer format.");
      assignValuePlayer();
  }
  userInput.close();
  }
}
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This class is holds the stats of player objects.
* Includes methods to change stats.
* ****************************/
class BaseBallStatsJianY extends PlayerJianY {
  private int hits;
  private int atBat;
  private int singles;
  private int doubles;
  private int triples;
  private int homeruns;
  public void setHits(int A) {hits=A;}
  public int getHits() {return hits;}
  public void setAtBat(int A) {atBat=A;}
  public int getAtBat() {return atBat;}
  public void setSingles(int A) {singles=A;}
  public int getSingles() {return singles;}
  public void setDoubles(int A) {doubles=A;}
  public int getDoubles() {return doubles;}
  public void setTriples(int A) {triples=A;}
  public int getTriples() {return triples;}
  public void setHomeruns(int A) {homeruns=A;}
  public int getHomeruns() {return homeruns;}
  
  BaseBallStatsJianY() {}
  BaseBallStatsJianY(String lastName, String firstName, String pos, int heig, int hitNo, int batNo,
                     int sings, int doubs, int trips, int homes) {
    initializePlayer();
    initialize();
    setSName(lastName);
    setGName(firstName);
    setPosition(pos);
    setHeight(heig);
    hits=hitNo;
    atBat=batNo;
    singles=sings;
    doubles=doubs;
    triples=trips;
    homeruns=homes;
  }
  
  public void initialize() {
    hits=-1;
    atBat=-1;
    singles=-1;
    doubles=-1;
    triples=-1;
    homeruns=-1;
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This method sets player stats.
* @param: None
* @return: None
* ****************************/
  public void setAll() {
    Scanner userInput = new Scanner(System.in);
    try {
      System.out.print("Enter value for hits: ");
      hits=userInput.nextInt();
      System.out.printf("\nEnter value for atBat: ");
      atBat=userInput.nextInt();
      System.out.printf("\nEnter value for singles: ");
      singles=userInput.nextInt();
      System.out.printf("\nEnter value for doubles: ");
      doubles=userInput.nextInt();
      System.out.printf("\nEnter value for triples: ");
      triples=userInput.nextInt();
      System.out.printf("\nEnter value for homeruns: ");
      homeruns=userInput.nextInt();
    } catch (InputMismatchException e) {
      System.out.println("InputMismatchException detected.");
      System.out.println("Please re-enter the values with proper integer format.");
      correctStats();
    }
    userInput.close();
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This method calculates player batting average.
* @param: None
* @return: None
* ****************************/
  public double calcBattingAvg() {
    double hitss = Double.valueOf(hits);
    double atBats = Double.valueOf(atBat);
    return hitss/atBats;
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This method checks if stats are correct.
* @param: None
* @return: None
* ****************************/
  public boolean checkStats() {
    if (hits!=(singles+doubles+triples+homeruns)) {
      System.out.println("Stats are incorrect.");
      return false;
    }
    else {
      return true;
    }
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-18
* This method is called for user to correct stats.
* @param: None
* @return: None
* ****************************/
  public void correctStats() {
    Scanner userInput = new Scanner(System.in);
    System.out.println("Error: hits must equal sum of singles, doubles, triples, and homeruns.");
    try {
      System.out.print("Enter correct value for hits: ");
      hits=userInput.nextInt();
      System.out.printf("\nEnter correct value for atBat: ");
      atBat=userInput.nextInt();
      System.out.printf("\nEnter correct value for singles: ");
      singles=userInput.nextInt();
      System.out.printf("\nEnter correct value for doubles: ");
      doubles=userInput.nextInt();
      System.out.printf("\nEnter correct value for triples: ");
      triples=userInput.nextInt();
      System.out.printf("\nEnter correct value for homeruns: ");
      homeruns=userInput.nextInt();
      userInput.close();
      if (checkStats()==false) {
        correctStats();
      }
    } catch (InputMismatchException e) {
      System.out.println("InputMismatchException detected.");
      System.out.println("Please re-enter the values with proper integer format.");
      correctStats();
    }
  }
}