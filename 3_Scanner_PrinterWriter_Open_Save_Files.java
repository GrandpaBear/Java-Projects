import java.util.Random;
import java.util.Scanner;
import java.io.*;
/* ***************************
* Author: Jianying Chiang    Date: 2018-03-29
* The purpose of this program is to print certain 
* strings of names with the use of object arrays, 
* and their corresponding values of marks and the 
* average. Additionally it gives the class average.
* It has also been modified to display this in an
* txt file. Additionally, data from a txt file is
* displayed into the Interactions tab.
* ****************************/
class ScannerPrinterWriterOpenSaveFiles {
  public static void main(String[] args) throws IOException {
    Classroom obj = new Classroom();
    try {
    obj.openFileMethod();
    obj.saveFileMethod();
    } catch (IOException e) {System.out.print(e.getMessage());}
  } //--------End of Main Method--------
} //--------End of class MainPrgJYC--------
/* ***************************
* Author: Jianying Chiang    Date: 2018-03-21
* The Student class holds the marks, surname, and
* givenname variables. It has constructors that set
* all the indexes of the array of marks to -1. It
* also has the capability to calculate student average,
* generate random marks between 50 and 100, inclusively,
* and the ability to display these results.
* ****************************/
class Student {
  private String surName = "";
  private String givenName = "";
  private int[] marks = new int[10];
  public void setSurName(String A) {A=surName;}
  public String getSurName() {return surName;}
  public void setGivenName(String B) {B=givenName;}
  public String getGivenName() {return givenName;}
  public void setMarks(int C, int D) {D=marks[C];}
  public int getMarks(int C) {return marks[C];}
  Student() {
    int x;
    for(x=0; x<10; x++) {
      marks[x] = -1;          
    }
    surName=null;
    givenName=null;
  }
  Student(String lastName, String firstName) {
    int x;
    for(x=0; x<10; x++) {
          marks[x] = -1;          
    }
    this.surName = lastName;
    this.givenName = firstName;
    
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-03-21
* This method randomly fills integer values between
* 50 and 100, inclusively, into int[] marks for 
* each student.
* @param: None
* @return: None
* ****************************/
  public void fillRandom() {
    int x;
    Random rn = new Random();
    for (x=0; x<10; x++) {
      marks[x] = rn.nextInt(50)+50; 
    }  
  } //--------End of fillRandom Method--------
/* ***************************
* Author: Jianying Chiang    Date: 2018-03-21
* This method calculates student's average.
* @param: None
* @return: double
* ****************************/
  public double calcAverage() {
    double average = 0.0;
    int x;
    for (x=0; x<10; x++) {
      average = average + marks[x];   
    }
    average = average/10;
    return average;
  } //--------End of calcAverage Method--------
/* ***************************
* Author: Jianying Chiang    Date: 2018-03-21
* This method displays the student's average.
* @param: None
* @return: None
* ****************************/
  public void display() {
    int x;
    System.out.println("=====================================");
    System.out.println(surName+ " " + givenName);
    System.out.println("========================");
    System.out.print("Marks: [");
    for (x=0; x<9; x++) {
      System.out.print(Integer.toString(marks[x]) + ", ");    
    }
    System.out.print(Integer.toString(marks[9]));
    System.out.println("]");
  
  } //--------End of display Method--------
} //----------End of Student class---------
/* ***************************
* Author: Jianying Chiang    Date: 2018-03-21
* The Classroom class holds the array of Student objects. 
* It also has the capability to calculate class average,
* and the ability to display these results.
* ****************************/
class Classroom {
  private Student[] objStud = new Student[5];
  public void setObjStud(int student) {objStud[student]=objStud[student];}
  public Student getObjStud(int student) {return objStud[student];}
  Classroom() {
    objStud[0] = new Student("Aldo", "Agostini");
    objStud[1] = new Student("Joe", "Spano");
    objStud[2] = new Student("Jordan", "Ribkoff");
    objStud[3] = new Student("Maria", "Perrone");
    objStud[4] = new Student("Alain","Menesis");
    int x;
    for(x=0; x<5; x++) {
      objStud[x].fillRandom();
    }
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-03-21
* This method calculates class average.
* @param: None
* @return: double
* ****************************/
  public double calcAverage() {
    int x;
    double average = 0.0;
    for (x=0; x<5; x++) {
      average = average + objStud[x].calcAverage(); 
    }
    average = average/5;
    return average;
  } //--------End of calcAverage Method--------
/* ***************************
* Author: Jianying Chiang    Date: 2018-03-21
* This method displays the class average.
* @param: None
* @return: None
* ****************************/
  public void display() {
    int x;
    for (x=0; x<5; x++) {
      objStud[x].display();
    }
    System.out.println("=====================================");
    System.out.printf("Class Average: %.1f", calcAverage());
  }//--------End of display Method--------
/* ***************************
* Author: Jianying Chiang    Date: 2018-03-29
* This method opens a file called 3_info.txt and
* retrieves the names and corresponding marks 
* in order to display them. Additionally, the
* average and class average are calculated and
* displayed
* @param: None
* @return: None
* ****************************/
  public void openFileMethod() throws IOException {
    String num = "";
    int x, y;
    double avg=0, clsavg = 0;
    File fill = new File("3_info.txt");
    Scanner in = new Scanner(fill);
    for (y=0; y<5; y++) {
        System.out.println("=====================================");
        System.out.println(in.next() + " " + in.next());
        System.out.println("========================");
        System.out.print("Marks: [");  
        for (x=0; x<9; x++) {
          num = in.next();
          System.out.print(num + ", ");
          avg = avg + Integer.parseInt(num);
        }
        System.out.println(in.next()+"]");
        avg = avg + Integer.parseInt(num);
        avg= (avg/10)+0.1;
        System.out.printf("Average: %.1f\n", avg);
        clsavg = clsavg + avg;
        avg = 0;
      }
    System.out.println("=====================================");
    clsavg = clsavg/5;
    System.out.printf("Class Average: %.1f", clsavg);
    in.close();
  } //-----End of openFileMethod Method--------
/* ***************************
* Author: Jianying Chiang    Date: 2018-03-29
* This method opens a file called 3_info.txt and
* retrieves the names and corresponding marks 
* in order to display them. Additionally, the
* average and class average are calculated and
* displayed.
* @param: None
* @return: None
* ****************************/
  public void saveFileMethod() throws IOException {
    int x, y;
    PrintWriter inFile = new PrintWriter("infoNew.txt");
    for (x=0; x<5; x++) {
      inFile.print(objStud[x].getSurName() + " " + objStud[x].getGivenName() + " ");
      for (y=0; y<9; y++) {
        inFile.print(Integer.toString(objStud[x].getMarks(y)) + " ");    
      }
      inFile.println("");
    }
    inFile.close();
  } //-----End of saveFileMethod Method--------
}//--------End of Classroom class--------