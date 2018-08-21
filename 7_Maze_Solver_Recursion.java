import java.util.Scanner;
import java.io.File;
import java.io.IOException;
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-20
* The purpose of this program is to start at 
* the top left corner of the park and finish at 
* the bottom right corner. It takes a map with a
* grid that has certain required specifications. 
* The rules and grid is displayed. Finally,
* the pathway of the program will be displayed. 
* ****************************/
class WalkInTheParkJY {
  public static void main(String[] args) {
    new Introduction();
    try {
      Recursion obj = new Recursion();
      if (obj.recursive(0,0,1)==true) {
        System.out.println("Walked through the park!");          
      }
      else {
        System.out.println("Pathway Blocked");    
      }
      obj.displayResults();
    } catch(IOException e) {e.getMessage();}
  } //--------End of Main Method----------
} //--------End of WalkInTheParkJY--------
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-20
* The Introduction class welcomes you and
* displays all rules that must be abided.
* ****************************/
class Introduction {
  Introduction() {
    System.out.println("Welcome to WalkInTheParkJY where you will walk through a park.");
    System.out.println("Here are a couple of rules in order for the program to function:"); 
    System.out.println("1. The map.txt file must have the same amount of columns in every existing row. An example map.txt file is located within the repository.");
    System.out.println("2. Every index must be a number(0 for barrier and 1 for path). 7s will be the program's pathway."); 
    System.out.println("3. Every number can be separated by a comma, a comma with a space, or a space. A new line represents a column");
    System.out.println("4. The first number located at the top left corner must be a 1 and is the start location.");
    System.out.println("5. Careful! The program likes to run in circles a lot :D.");
    System.out.println("6. The program gets tired and can only take 1000 steps before it goes home and assumes the pathway is blocked.");
    System.out.println("7. Enjoy the scenery!");
    System.out.printf("\nMap: \n");
  }
} //---------End of Introduction---------
class Map {
  private int rows;
  private int cols;
  private int[][] map;
  public int getRows() {return rows;}
  public int getCols() {return cols;}
  public void setMap(int A, int B, int C) {map[A][B]= C;}
  public int[][] getMap() {return map;}
  Map() {}
  Map(int y, int x) throws IOException{
    rows=y;
    cols=x;
    mapSize();
    mapCreation();
    rows--;
    cols--;
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-20
* This method takes map.txt data and creates
* a proper sized empty 2d array based on the 
* amount of data.
* @param: None
* @return: None
* ****************************/
  public void mapSize() throws IOException {
    int passCols=0, used=0;
    String used2;
    File coords = new File("map.txt");
    Scanner in = new Scanner(coords);
    in.useDelimiter(", |,| ");
    for (int x=0; x<300; x++) {
      if (in.hasNextInt() && passCols==0) {
        used = in.nextInt();
        cols++;
      }
      else if (in.hasNextInt()){
        used = in.nextInt();
      }
      else if (in.hasNext()){
        if (passCols==0) {
          cols++;
        }
        passCols=1;
        used2=in.next();
        rows++;
      }
      else {
        map = new int[rows][cols];      
        x=400;
      }
    }
    in.close();
  } //--------End of mapSize Method--------
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-20
* This method takes map.txt data and fills the 
* 2d array with that data. It then displays the
* 2d array.
* amount of data.
* @param: None
* @return: None
* ****************************/
  public void mapCreation() throws IOException {
      File coords= new File("map.txt");
      Scanner in = new Scanner(coords);
      in.useDelimiter("[^0-9]+");
      for (int y=0; y<rows; y++) {
        for (int x=0; x<cols; x++) {
          map[y][x] = Integer.parseInt(in.next().trim());
        }
     }
      for (int y=0; y<getRows(); y++) {
        for (int x=0; x<getCols(); x++) {
          System.out.print(getMap()[y][x]+" ");
        }
        System.out.println("");
      }
      in.close();
  }
} //--------End of mapCreation Method--------
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-20
* The Recursion class takes the data from the
* 2d array and finds a pathway to the end of the park.
* It will than display the results.
* ****************************/
class Recursion extends Map{
  int maxSteps=0;
  Recursion() throws IOException{
    super(1,0);
    int direction = 1;
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-20
* This method displays the map that includes
* the program's pathway.
* @param: None
* @return: None
* ****************************/
  public void displayResults() {
    for (int y=0; y<getRows()+1; y++) {
      for (int x=0; x<getCols()+1; x++) {
        System.out.print(getMap()[y][x]+" ");
      }
      System.out.println("");
    }
  } //---------End of displayResults Method---------
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-20
* This method takes row number, column number,
* and direction (represented by numbers). It then
* returns true or false based on whether a valid 
* pathway exists, using recursion. 
* @param: int, int, int
* @return: boolean
* ****************************/
  public boolean recursive(int y, int x, int direction) {    
    maxSteps++;
    if (maxSteps==1000) {
/*      for (int y=0; y<getRows()+1; y++) {
        for (int x=0; x<getCols()+1; x++) {
          if (y==0&&x==0) {
            if (getMap()[y][x+1]==1) {
              return recursive(0,0,2);            
            }
            else {
              return false;
            }
          }
          else if (y==0&&x==getCols()) {
            if (getMap()[y][x-1]==0) {
            
            }          
          }
          else if (y==getRows()&&x==0) {
          
          }
          else if ()
        }
      }
      */
      
      
      System.out.println("The program can't find a way after 1000 steps! It went home.");
      return false;    
    }
    //--------------------------------------------------CORNERS-------------------------------------------------------  
    if (x==getCols() && y==getRows()) {
      setMap(y,x,7);
      return true;    
    } //End Reached
    else if (x==0 && y==0) {
      setMap(y,x,7);
      if (direction==1) {
        if (y<getRows() && getMap()[y+1][x]==1) {
          return recursive(y+1,x,direction);      
        }
        else if (y==getRows()) {
          if (x==getCols()) {
            System.out.println("Small park but....");
            return true;
          }
          else if (getMap()[y][x+1]==0) {
            return false;        
          }
          else if (getMap()[y][x+1]==1) {
            direction=2;
            return recursive(y,x+1,direction);
          }
          else {
            System.out.println("This should not print.");
            return false;
          }
        }
        else if (getMap()[y+1][x]==0) {
          if (getMap()[y][x+1]==0) {
            return false;        
          }
          else {
            direction=2;
            return recursive(y,x+1,direction);
          }
        }
        else if (x<getCols() && getMap()[y][x+1]==1) {
          direction=2;
          return recursive(y,x+1,direction);
        } 
        else {
          System.out.println("This shouldn't print.");
          return false;
        }
      }
      else if (direction==3) {
        if (x<getCols()) {
          if (getMap()[y][x+1]==1) {
            direction=2;
            return recursive(y,x+1,direction);                      
          }
          else if (getMap()[y][x+1]==0) {
            return false;          
          }
          else if (getMap()[y][x+1]==7) {
            direction=2;
            return recursive(y,x+1,direction);          
          }
          else {
            System.out.println("This shouldn't print.");
            return false;
          } 
        }
        else if (x==getCols()) {
          return false;        
        }
        else {
          System.out.println("This shouldn't print.");
          return false;           
        }
      }
      else if (direction==4) {
        if (getMap()[y+1][x]==7) {
          direction=1;
          return recursive(y+1,x,direction);        
        }
        else {
          direction=2;
          return recursive(y,x+1,direction);        
        }
      }
      else {
        System.out.println("This shouldn't print.");
        return false;
      }
    }
    else if (x==0 && y==getRows()) {
      setMap(y,x,7);
      if (direction==1) {
        if (x==getCols()) {
          return true;        
        }
        else if (x<getCols() && getMap()[y][x+1]==0) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else if (x<getCols() && getMap()[y][x+1]==1 || getMap()[y][x+1]==7) {
          direction=2;
          return recursive(y,x+1,direction);        
        }
        else {
          System.out.println("This shouldn't print.");
          return false;      
        }
      }
      else if (direction==4) {
        if (getMap()[y-1][x]==0) {
          direction=2;
          return recursive(y,x+1,direction);
        }
        else if(getMap()[y-1][x]==1) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y-1][x]==7) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else {
          System.out.println("This shouldn't print.");
          return false;                    
        }
      }
      else {
        System.out.println("This shouldn't print.");
        return false;      
      }
    }
    else if (y==0 && x==getCols()) {
      setMap(y,x,7);
      if (direction==2) {
        if (y<getRows()) {
          if (getMap()[y+1][x]==0) {
            if (x-1==0) {
              return false;   
            }
            else {
              direction=4;
              return recursive(y,x-1,direction);  
            }
          }
          else if (getMap()[y+1][x]==1 || getMap()[y+1][x]==7) {
            direction=1;
            return recursive(y+1,x,direction);          
          }
          else {
            System.out.println("This shouldn't print.");
            return false;                     
          }
        }
        else {
          System.out.println("This shouldn't print.");
          return false;  
        }
      }
      else if (direction==3) {
        if (getMap()[y][x-1]==0) {
          direction=1;
          return recursive(x,y+1,direction);
        }
        else if (getMap()[y][x-1]==1 || getMap()[y][x-1]==7) {
          direction=4;
          return recursive(x-1,y,direction);
        }
        else {
          System.out.println("This shouldn't print.");
          return false;
        }
      }
      else {
        System.out.println("This shouldn't print.");
        return false;
      }
    }
    //--------------------------------------------------CORNERS-------------------------------------------------------
    //-------------------------------------------------PERIMITER------------------------------------------------------
    else if (x==0 && y>0 && y<getRows()) {
      setMap(y,x,7);
      if (direction==1) {
        if (getMap()[y+1][x]==1) {
          return recursive(y+1,x,direction);
        }
        else if (getMap()[y][x+1]==1) {
          direction=2;
          return recursive(y,x+1,direction);
        }
        else if (getMap()[y+1][x]==7) {
          return recursive(y+1,x,direction);        
        }
        else if (getMap()[y][x+1]==7) {
          direction=2;
          return recursive(y,x+1,direction);        
        }
        else if (getMap()[y-1][x]!=0) {
          direction=3;
          return recursive(y-1,x,direction);             
        }
        else {
          System.out.println("This shouldn't print.");
          return false;       
        }
      }
      else if (direction==3) {
        if (getMap()[y-1][x]==1) {
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y][x+1]==1) {
          direction=2;
          return recursive(y,x+1,direction);
        }
        else if (getMap()[y-1][x]==7) {
          return recursive(y-1,x,direction);        
        }
        else if (getMap()[y][x+1]==7) {
          direction=2;
          return recursive(y,x+1,direction);        
        }
        else if (getMap()[y+1][x]!=0) {
          direction=1;
          return recursive(y+1,x,direction);             
        }
        else {
          System.out.println("This shouldn't print.");
          return false;       
        }
      }
      else if (direction==4) {
        if (getMap()[y+1][x]==1) {
          direction=1;
          return recursive(y+1,x,direction);
        }
        else if (getMap()[y-1][x]==1) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y+1][x]==7) {
          direction=1;
          return recursive(y+1,x,direction);
        }
        else if (getMap()[y-1][x]==7) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y][x+1]!=0) {
          direction=2;
          return recursive(y,x+1,direction);             
        }
        else {
          System.out.println("This shouldn't print.");
          return false;       
        }
      }
      else {
        System.out.println("This shouldn't print.");
        return false;      
      }
    }    
    else if (x==getCols() && y<getRows() && y>0) {
      setMap(y,x,7);
      if (direction==1) {
        if (y+1==getRows() && getMap()[y+1][x]==0) {
          return false;        
        }
        else if (getMap()[y+1][x]==1) {
          return recursive(y+1,x,direction);
        }
        else if (getMap()[y][x-1]==1) {
          direction=4;
          return recursive(y,x-1,direction);
        }
        else if (getMap()[y+1][x]==7) {
          return recursive(y+1,x,direction);        
        }
        else if (getMap()[y][x-1]==7) {
          direction=4;
          return recursive(y,x-1,direction);        
        }
        else if (getMap()[y-1][x]!=0) {
          direction=3;
          return recursive(y-1,x,direction);             
        }
        else {
          System.out.println("This shouldn't print.");
          return false;       
        }
      }
      else if (direction==3) {
        if (getMap()[y-1][x]==1) {
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y][x-1]==1) {
          direction=4;
          return recursive(y,x-1,direction);
        }
        else if (getMap()[y-1][x]==7) {
          return recursive(y-1,x,direction);        
        }
        else if (getMap()[y][x-1]==7) {
          direction=4;
          return recursive(y,x-1,direction);        
        }
        else if (getMap()[y+1][x]!=0) {
          direction=1;
          return recursive(y+1,x,direction);             
        }
        else {
          System.out.println("This shouldn't print.");
          return false;       
        }
      }
      else if (direction==2) {
        if (getMap()[y+1][x]==1) {
          direction=1;
          return recursive(y+1,x,direction);
        }
        else if (getMap()[y-1][x]==1) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y+1][x]==7) {
          direction=1;
          return recursive(y+1,x,direction);
        }
        else if (getMap()[y-1][x]==7) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y][x-1]!=0) {
          direction=4;
          return recursive(y,x-1,direction);             
        }
        else {
          System.out.println("This shouldn't print.");
          return false;       
        }
      }
      else {
        System.out.println("This shouldn't print.");
        return false;
      }
    }
    else if (y==0 && x>0 && x<getCols()) {
      setMap(y,x,7);
      if (direction==2) {
        if (getMap()[y][x+1]==1) {
          return recursive(y,x+1,direction);        
        }
        else if (getMap()[y+1][x]==1) {
          direction=1;
          return recursive(y+1,x,direction);
        }
        else if (getMap()[y][x+1]==7) {
          return recursive(y,x+1,direction);        
        }
        else if (getMap()[y+1][x]==7) {
          direction=1;
          return recursive(y+1,x,direction);
        }
        else if (getMap()[y][x-1]!=0) {
          direction=4;
          return recursive(y,x-1,direction);
        }
        else {
          System.out.println("This shouldn't print.");
          return false;        
        }
      }
      else if (direction==3) {
        if (getMap()[y][x+1]==1) {
          direction=2;
          return recursive(y,x+1,direction);
        }
        else if (getMap()[y][x-1]==1) {
          direction=4;
          return recursive(y,x-1,direction);        
        }
        else if (getMap()[y][x+1]==7) {
          direction=2;
          return recursive(y,x+1,direction);
        }
        else if (getMap()[y][x-1]==7) {
          direction=4;
          return recursive(y,x-1,direction);        
        }
        else if (getMap()[y-1][x]!=0) {
          direction=1;
          return recursive(y+1,x,direction);
        }
        else {
          System.out.println("This shouldn't print.");
          return false;
        }
      }
      else if (direction==4) {
        if (getMap()[y][x-1]==1) {
          return recursive(y,x-1,direction);
        }
        else if (getMap()[y+1][x]==1) {
          direction=1;
          return recursive(y+1,x,direction);
        }
        else if (getMap()[y][x-1]==7) {
          return recursive(y,x-1,direction);
        }
        else if (getMap()[y+1][x]==7) {
          direction=1;
          return recursive(y+1,x,direction);
        }
        else if (getMap()[y][x+1]!=0) {
          direction=2;
          return recursive(y,x+1,direction);
        }
        else {
          System.out.println("This shouldn't print.");
          return false;        
        }
      }
      else {
        System.out.println("This shouldn't print.");
        return false;      
      }
    }
    else if (y==getRows() && x>0 && x<getCols()) {
      setMap(y,x,7);
      if (direction==1) {
        if (getMap()[y][x+1]==1) {
          direction=2;
          return recursive(y,x+1,direction);
        }
        else if (getMap()[y][x-1]==1) {
          direction=4;
          return recursive(y,x-1,direction);        
        }
        else if (getMap()[y][x+1]==7) {
          direction=2;
          return recursive(y,x+1,direction);
        }
        else if (getMap()[y][x-1]==7) {
          direction=4;
          return recursive(y,x-1,direction);        
        }
        else if (getMap()[y-1][x]!=0) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else {
          System.out.println("This shouldn't print.");
          return false;
        }
      }
      else if (direction==2) {
        if (x+1==getCols() && getMap()[y][x+1]==0) {
          return false;        
        }
        else if (getMap()[y][x+1]==1) {
          return recursive(y,x+1,direction);        
        }
        else if (getMap()[y-1][x]==1) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y][x+1]==7) {
          return recursive(y,x+1,direction);        
        }
        else if (getMap()[y-1][x]==7) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y][x-1]!=0) {
          direction=4;
          return recursive(y,x-1,direction);
        }
        else {
          System.out.println("This shouldn't print.");
          return false;        
        }
      }
      else if (direction==4) {
        if (getMap()[y][x-1]==1) {
          return recursive(y,x-1,direction);        
        }
        else if (getMap()[y-1][x]==1) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y][x-1]==7) {
          return recursive(y,x-1,direction);        
        }
        else if (getMap()[y-1][x]==7) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y][x+1]!=0) {
          direction=2;
          return recursive(y,x+1,direction);
        }
        else {
          System.out.println("This shouldn't print.");
          return false;        
        }
      }
      else {
        System.out.println("This shouldn't print.");
        return false;      
      }
    }
    //-------------------------------------------------PERIMITER------------------------------------------------------
    //------------------------------------------------INNER TILES-----------------------------------------------------
    else if (y!=getRows() && x!=getCols() && y!=0 && x!=0) {
      setMap(y,x,7);
      if (direction==1) {
        if (getMap()[y+1][x]==1) {
          return recursive(y+1,x,direction);
        }
        else if (getMap()[y][x+1]==1) {
          direction=2;
          return recursive(y,x+1,direction);        
        }
        else if (getMap()[y][x-1]==1) {
          direction=4;
          return recursive(y,x-1,direction);
        }
        else if (getMap()[y+1][x]==7) {
          return recursive(y+1,x,direction);
        }
        else if (getMap()[y][x+1]==7) {
          direction=2;
          return recursive(y,x+1,direction);        
        }
        else if (getMap()[y][x-1]==7) {
          direction=4;
          return recursive(y,x-1,direction);
        }
        else if (getMap()[y-1][x]!=0) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else {
          System.out.println("This shouldn't print.");
          return false;        
        }
      }
      else if (direction==2) {
        if (getMap()[y][x+1]==1) {
          return recursive(y,x+1,direction);        
        }
        else if (getMap()[y+1][x]==1) {
          direction=1;
          return recursive(y+1,x,direction);        
        }
        else if (getMap()[y-1][x]==1) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y][x+1]==7) {
          return recursive(y,x+1,direction);        
        }
        else if (getMap()[y+1][x]==7) {
          direction=1;
          return recursive(y+1,x,direction);        
        }
        else if (getMap()[y-1][x]==7) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y][x-1]!=0) {
          direction=4;
          return recursive(y,x-1,direction);
        }
        else {
          System.out.println("This shouldn't print.");
          return false;        
        }
      }
      else if (direction==3) {
        if (getMap()[y-1][x]==1) {
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y][x+1]==1) {
          direction=2;
          return recursive(y,x+1,direction);        
        }
        else if (getMap()[y][x-1]==1) {
          direction=4;
          return recursive(y,x-1,direction);
        }
        else if (getMap()[y-1][x]==7) {
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y][x+1]==7) {
          direction=2;
          return recursive(y,x+1,direction);        
        }
        else if (getMap()[y][x-1]==7) {
          direction=4;
          return recursive(y,x-1,direction);
        }
        else if (getMap()[y+1][x]!=0) {
          direction=3;
          return recursive(y+1,x,direction);
        }
        else {
          System.out.println("This shouldn't print.");
          return false;        
        }
      }
      else if (direction==4) {
        if (getMap()[y][x-1]==1) {
          return recursive(y,x-1,direction);        
        }
        else if (getMap()[y+1][x]==1) {
          direction=1;
          return recursive(y+1,x,direction);        
        }
        else if (getMap()[y-1][x]==1) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y][x-1]==7) {
          return recursive(y,x-1,direction);        
        }
        else if (getMap()[y+1][x]==7) {
          direction=1;
          return recursive(y+1,x,direction);        
        }
        else if (getMap()[y-1][x]==7) {
          direction=3;
          return recursive(y-1,x,direction);
        }
        else if (getMap()[y][x+1]!=0) {
          direction=2;
          return recursive(y,x+1,direction);
        }
        else {
          System.out.println("This shouldn't print.");
          return false;        
        }
      }
      else {
        System.out.println("This shouldn't print.");
        return false;
      }
    }
    //------------------------------------------------INNER TILES-----------------------------------------------------
    else {
      System.out.println("This shouldn't print.");
      return false;
    }
  } //---------End of Recursive Method---------
} //---------End of Recursion Class---------