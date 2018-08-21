import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* The purpose of this program is to take data from
* a file and print out the existing receipts of
* the items sold from the dessert-based store.
* ****************************/
class PolymorphismCashRegister {
  public static void main(String[] args) {
    try {
      CashRegister obj = new CashRegister();
    } catch(IOException e) {e.getMessage();}
  } //--------End of Main Method--------
} //--End of class MainPrgJianyingChiang--

/**@Author:  MR. A @Date: today
 * Abstract superclass for Dessert Item hierarchy
 * YOU CANNOT CHANGE THIS AT ALL
 */
abstract class DessertItem {
  private String name;
//Default Constructor
  DessertItem() {
    this("");
  }
//CONSTRUCTOR
  DessertItem(String name) {
         this.name = name;
  }
  /**@Author:  MR. A @Date: today
   * Returns name of DessertItem
   * @param: none
   * @return name of DessertItem
   */ 
  public String getName() {
    return name;
  }
  /**@Author:  MR. A @Date: today
   * Returns cost of DessertItem
   * @param: none
   * @return cost of DessertItem
   */ 
  public abstract int cost();
  
}
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* The Candy class extends the DessertItem class.
* It is able to receive variables and calculate the
* cost of candy items and display it accordingly.
* ****************************/
class Candy extends DessertItem {
  Candy() {}
  Candy(String name) {
    super(name);
  }
  private int weight;
  private int price;
  public void setWeight(int A) {weight=A;}
  public void setPrice(int B) {price=B;}
  public String getWeight() {return Integer.toString(weight);}
  public String getPrice() {return Integer.toString(price);}
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* This method calculates item cost.
* @param: None
* @return: int
* ****************************/
  public int cost() {
    return weight*price;  
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* This method displays item info and costs.
* @param: None
* @return: String
* ****************************/
  public String toString(){
    System.out.printf("%s*********\t%s grams@%s cents/gram\tItem Cost=$ %.2f\n",
                      getName(), getWeight(), getPrice(),Double.valueOf(cost())/100);
    return null; 
  }
}
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* The Cookie class extends the DessertItem class.
* It is able to receive variables and calculate the
* cost of cookie items and display it accordingly.
* ****************************/
class Cookie extends DessertItem {
  Cookie() {}
  Cookie(String name) {
    super(name);  
  }
  private int number;
  private int price;
  public void setNumber(int A) {number=A;}
  public String getNumber() {return Integer.toString(number);}
  public void setPrice(int B) {price=B;}
  public String getPrice() {return Integer.toString(price);}
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* This method calculates item cost.
* @param: None
* @return: int
* ****************************/
  public int cost() {
    return number*price; 
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* This method displays item info and costs.
* @param: None
* @return: String
* ****************************/
  public String toString(){
    System.out.printf("%s*********\t%s cookies@%s cents/gram\tItem Cost=$ %.2f\n",
                      getName(), getNumber(), getPrice(),Double.valueOf(cost())/100);
    return null; 
  }
}
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* The Icecream class extends the DessertItem class.
* It is able to receive variables and calculate the
* cost of icecream items and display it accordingly.
* ****************************/
class Icecream extends DessertItem {
  Icecream() {}
  Icecream(String name) {
    super(name);
  }
  private int scoops;
  private int price;
  public void setScoops(int A) {scoops=A;}
  public String getScoops() {return Integer.toString(scoops);}
  public void setPrice(int B) {price=B;}
  public double getPrice() {return (Double.valueOf(price))/100;}
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* This method calculates item cost.
* @param: None
* @return: int
* ****************************/
  public int cost() {
    return price+scoops*35;  
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* This method displays item info and costs.
* @param: None
* @return: String
* ****************************/
  public String toString(){
    System.out.printf("%s\t$ %.2f with %s scoops@35 cents/each\tItem Cost=$ %.2f\n",
                      getName(), getPrice(), getScoops(), Double.valueOf(cost())/100);
    
    return null;
  }
}
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* The Sundae class extends the Icecream class.
* It is able to receive variables and calculate the
* cost of sundae items and display it accordingly.
* ****************************/
class Sundae extends Icecream {
  Sundae() {}
  Sundae(String name1, int cost1) {
    tops.add(new Toppings(name1, cost1));
    toString();
    count++;
  }
  Sundae(String name1, int cost1, String name2, int cost2) {
    tops.add(new Toppings(name1, cost1));
    toString();
    count++;
    tops.add(new Toppings(name2, cost2));
    toString();
    count++;
  }
  Sundae(String name1, int cost1, String name2, int cost2, String name3, int cost3) {
    tops.add(new Toppings(name1, cost1));
    toString();
    count++;
    tops.add(new Toppings(name2, cost2));
    toString();
    count++;
    tops.add(new Toppings(name3, cost3));  
    toString();
  }
  private ArrayList<Toppings> tops = new ArrayList<Toppings>(3);  
  int count=0;
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* This method returns zero and is not used.
* @param: None
* @return: int
* ****************************/
  public int cost() {
    return 0;
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* This method displays item info and costs,
* along with topping information.
* @param: None
* @return: String
* ****************************/
  public String toString() {
      System.out.printf("%s @$ %.2f\n", tops.get(count).getToppingName(),
                        Double.valueOf(tops.get(count).getToppingPrice())/100);
    return null;  
  }
}
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* The Toppings class is used by the Sundae class
* in which Toppings objects that are required,
* are created and used.
* ****************************/
class Toppings {
  Toppings() {}
  Toppings(String name, int cost) {
    toppingName=name;
    toppingPrice=cost;
  }
  private String toppingName;
  private int toppingPrice;
  public String getToppingName() {return toppingName;}
  public int getToppingPrice() {return toppingPrice;}
}
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* The CashRegister implements the DQStore interface,
* using many useful methods for calculating costs.
* It's constructor ultimately views the file containing
* information and is able to use it to display out the 
* required info
* ****************************/
class CashRegister implements DQStore {
  int costUse = 0;
  int realCost;
  CashRegister() throws IOException {
     File inFile = new File("items.txt");
     Scanner in = new Scanner(inFile);
     in.useDelimiter(",\r\n|,|\n");
     int entryCounter = 0;
     String entries;
     while (in.hasNext()) {
      System.out.println("--------------------------------------------------------------------");
      System.out.printf("********> %s <********\n", STORE_NAME);
      System.out.println(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss").format(LocalDateTime.now()));
      System.out.println("--------------------------------------------------------------------");
      System.out.println("\t\tItem");
      System.out.println("====================================================================");
      double cost = 0.00;
      if (in.hasNext()) {
        entries = in.next();
        entryCounter = Integer.parseInt(entries.trim());
        for (int x=0; x<entryCounter; x++) {
          String item = (in.next()).trim();
          if (item.equals("Sundae")) {
            String stars = "";
            int topNum;
            String sunType;
            topNum = Integer.parseInt(in.next());
            if (topNum > MAX_TOPPINGS) {
              System.out.println("Too many toppings. items.txt is incorrect. Closing Program.");
              in.close();
              x=99;
            }
            sunType = (in.next()).trim();
            for (int y=0; y<(ITEM_NAME_STRING_LENGTH-sunType.length()); y++) {
              stars = stars + "*";
            }
            String sunCost = in.next().trim();
            realCost = realCost+Integer.parseInt(sunCost);
            cost = cost+(Double.valueOf(sunCost))/100;
            System.out.printf("%s%s    $ %.2f\n", sunType, stars, (Double.valueOf(sunCost))/100);
            if (topNum == 0) {
              Sundae sunObj0 = new Sundae();
            }
            else if (topNum == 1) {
              String name1 = (in.next()).trim();
              int cost1 = Integer.parseInt(in.next().trim());
              Sundae sunObj1 = new Sundae(name1, cost1); 
              realCost = realCost+cost1;
              cost = cost+(Double.valueOf(cost1))/100;
            }
            else if (topNum == 2) {
              String name1 = (in.next()).trim();
              int cost1 = Integer.parseInt(in.next().trim());
              String name2 = (in.next()).trim();
              int cost2 = Integer.parseInt(in.next().trim());
              Sundae sunObj2 = new Sundae(name1, cost1, name2, cost2);
              realCost = realCost+cost1+cost2;
              cost = cost+(Double.valueOf(cost1+cost2))/100;
            }
            else if (topNum == 3) {
              String name1 = (in.next()).trim();
              int cost1 = Integer.parseInt(in.next().trim());
              String name2 = (in.next()).trim();
              int cost2 = Integer.parseInt(in.next().trim());
              String name3 = (in.next()).trim();
              int cost3 = Integer.parseInt(in.next().trim());
              Sundae sunObj3 = new Sundae(name1, cost1, name2, cost2, name3, cost3);
              realCost = realCost+cost1+cost2+cost3;
              cost = cost+(Double.valueOf(cost1+cost2+cost3))/100;
            }
            else if (topNum<0||topNum>MAX_TOPPINGS) {
              System.out.println("Inproper order. Only 0-3 toppings are allowed");            
            }
          }
          if (item.equals("Icecream")) {
            String stars = "";
            String creamType;
            creamType = (in.next()).trim();
            for (int y=0; y<(ITEM_NAME_STRING_LENGTH-creamType.length()); y++) {
              stars = stars + "*";                                          
            }
            creamType = creamType + stars;
            Icecream foodObj2 = new Icecream(creamType);
            int price = Integer.parseInt((in.next()).trim());
            int scoops = Integer.parseInt((in.next()).trim());
            foodObj2.setPrice(price);
            foodObj2.setScoops(scoops);
            foodObj2.toString();
            realCost = realCost+price+scoops*SCOOP_COST;
            cost = cost + (Double.valueOf(price+scoops*SCOOP_COST))/100;
          }
          else if (item.equals("Candy")) {
            String stars = "";
            String candyType;
            candyType = (in.next()).trim();
            for (int y=0; y<(ITEM_NAME_STRING_LENGTH-candyType.length()); y++) {
              stars = stars + "*";                                          
            }
            candyType = candyType + stars;
            Candy foodObj3 = new Candy(candyType);
            int weight = Integer.parseInt((in.next()).trim());
            int price = Integer.parseInt((in.next()).trim());
            foodObj3.setWeight(weight);
            foodObj3.setPrice(price);
            foodObj3.toString();
            realCost = realCost+price*weight;
            cost = cost + (Double.valueOf(price*weight))/100;
          }
          else if (item.equals("Cookie")) {
            String stars = "";
            String cookieType;
            cookieType = (in.next()).trim();
            for (int y=0; y<(ITEM_NAME_STRING_LENGTH-cookieType.length()); y++) {
              stars = stars + "*";                                          
            }
            cookieType = cookieType + stars;
            Cookie foodObj4 = new Cookie(cookieType);
            int number = Integer.parseInt((in.next()).trim());
            int price = Integer.parseInt((in.next()).trim());
            foodObj4.setNumber(number);
            foodObj4.setPrice(price);
            foodObj4.toString();
            realCost = realCost+number*price;
            cost = cost + (Double.valueOf(number*price))/100;
          }
        }
        costUse = (int) (cost*100);
        System.out.println("====================================================================");
        System.out.printf("Sub Total Cost=$ %.2f\n", calcSubTotal(costUse));
        System.out.printf("Tax=$ %.2f\n", calcTax(costUse));
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("Total Cost=$ %.2f\n", Double.parseDouble(centsConverter(costUse)));
        clear();
      }
  }
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* This method is used to round your total
* cost to the nearest nickel
* @param: double
* @return: String
* ****************************/
  public String centsConverter(double cents) {
    cents = cents*1.13/100;
    cents = Math.round(cents*100)/100.0;
    if (cents%0.05<0.03 && cents%0.05!=0) {
      return Double.toString(cents-(cents%0.05));       
    }
    else if (cents%0.05>0.03 && cents%0.05!=0){
      return Double.toString(cents+0.05-cents%0.05);  
    } 
    else {
      return Double.toString(cents);    
    }
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* This method clears the database by resetting entryCounter.
* @param: None
* @return: None
* ****************************/
  public void clear() {
    int entryCounter = 0;  
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* This method is not used. Alternate ways were
* used to enter file items.
* @param: DessertItem
* @return: None
* ****************************/
  public void enterItem(DessertItem item) {
              
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* This method calculates sub total cost.
* @param: int
* @return: double
* ****************************/
  public double calcSubTotal(int totalCost) {
    return (Double.valueOf(totalCost))/100.00;  
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* This method calculates total cost.
* @param: int
* @return: int
* ****************************/
  public int calcTotalCost(int totalCost) {
    return totalCost*113; 
  }
/* ***************************
* Author: Jianying Chiang    Date: 2018-04-13
* This method calculates item taxes.
* @param: int
* @return: double
* ****************************/
  public double calcTax(int totalCost) {
    return ((Double.valueOf(totalCost))*TAX_RATE)/100.00;   
  }
  
  
}
/**@Author:  MR. A @Date: today
 * YOU CANNOT CHANGE DQStore interface
 * Interface is used to ensure program limitations through constants
 * furthermore to enforce CashResterYourName class methods */
interface DQStore {
  public final  double TAX_RATE = 0.13;  // provincial HST tax   
  public final  String STORE_NAME = "Dairy Queen Dessert Shoppe";  // name of store.
  public final  int MAX_TOPPINGS = 3;  // max number of toppings permitted
  public static final int SCOOP_COST = 35;  // cost per scoop.
  public static final int ITEM_NAME_STRING_LENGTH=40;
    public String centsConverter(double cents);
    public void clear();
    public void enterItem(DessertItem item);
    public double calcSubTotal(int totalCost);
    public int calcTotalCost(int totalCost);
    public double calcTax(int totalCost);
} 