/* ***************************
* Author: Jianying Chiang    Date: 2018-05-04
* This class holds the main method.
* The purpose of this program is to simulate a 
* doubly Linked List that holds String type objects.
* For each objects, they have a memory address,
* as well as the memory addresses of the previous
* and next objects. The user may add, remove, display, etc.
* This program will display this info.
* ****************************/
class Assignment9JianY {
 public static int itemTotal = 0; // used to hold the number of items in the list
 public static ItemJianY items;    // used to simulate a  C++ pointer to the data always points to the first data item
  public static void main(String [] arg) { 

    add("Agostini");  //  1st index
    add("Spano");    //  2nd index
    add("Menesis"); //   3rd index
    add("Hello");  // 4th index
    add("Blake"); // 5th index
    addAt(1,"Beginning"); //add to beginning
    addAt(itemTotal+1,"End"); //add to end
    addAt(4,"Middle"); // add to middle
    remove(itemTotal); //removes ending
    remove(1); //removes beginning
    remove(3); // removes middle
    
    display();
    
  }
  /*  This static method will add items to the beginning of the list.
   * add(String): void static   */
  public static void add(String name) {
    if (itemTotal==0) {
      itemTotal++;
      items = new ItemJianY(name);
    }
    else {
      itemTotal++;
      ItemJianY obj = new ItemJianY(name);
      items.previous=obj;
      items=obj;
    }
  }
 /* this method will display items in our list
  * display(): void static    */
  public static void display() {
    System.out.println("====================================================================================");
    System.out.printf("List Size(# of Elements): %s\n\n", size());
    if (itemTotal>0) {
      System.out.printf("info=%s,       \t Previous=%s, \t Next=%s \t Current Address= %s\n", items.info,  
                         String.valueOf(items.previous),String.valueOf(items.next), items.toString());
      for (int x=1; x<itemTotal; x++) {
      items=items.next;
      System.out.printf("info=%s,        \t Previous=%s, \t Next=%s \t Current Address= %s\n", items.info,  
                         String.valueOf(items.previous),String.valueOf(items.next), items.toString());
      }
    }
    System.out.println("====================================================================================");
  }
  /* This method returns the number of items in 
   *   size(): int static     */
  public static int size() {
    return itemTotal;  
  }
  /* This method removes an item from the list given
   * an integer index-remember item 1 has index 0.  
   * remove(int): void static                        */
  public static void remove(int index) {
    if (itemTotal>0) {
      ItemJianY holder = items;
      if (index==1) {
        if (itemTotal>1) {
          items=items.next;
        }
        else {
          items.next=null;        
        }
        items.previous=null;
        itemTotal--;
      }
      else if (index==itemTotal) {
        for (int x=1; x<index-1; x++) {
          items=items.next;    
        }
        items.next=null;
        items=holder;
        itemTotal--;
      }
      else if (index>itemTotal || index<1) {
        System.out.printf("Removal for index %d is out of bounds.\n",index);
        System.out.println("Note that index begins at index 1 and not index 0.");
      }
      else {
        ItemJianY realHolder = items;
        for (int x=1; x<index-1; x++) {
          items=items.next;
          holder=holder.next;
        }
        holder=holder.next;
        holder=holder.next;
        items.next=holder;
        holder = items;
        items=items.next;
        items.previous=holder;
        items=realHolder;
        itemTotal--;
      }
    }
    else {
      System.out.printf("# of Items is 0. Cannot remove index %d.\n",index);
    }
  }
    /* This method adds an item to the list given
   * an integer index-remember item 1 has index 0.
   *     addAt(int, String): void static                       */
  public static void addAt(int index, String name) {
    itemTotal++;
    if (index>0 && index<=itemTotal) {
    ItemJianY obj = new ItemJianY(index,name);
    if (index==1) {
      items.previous=obj;
      items=obj;
    }
    else if (itemTotal==index) {
      ItemJianY holder = items;
      for (int x=1; x<itemTotal-1; x++) {
        items=items.next;      
      }
      items.next=obj;
      items=holder;
    }
    else {
      ItemJianY holder = items;
      for (int x=1; x<index-1; x++) {
        items=items.next;     
      }
      items.next=obj;
      items=items.next;
      items=items.next;
      items.previous=obj;
      items=holder;
    }
  }
  else {
    itemTotal--;
    System.out.printf("Addition for index %d is out of bounds.\n",index);
    System.out.println("Note that index begins at index 1 and not index 0.");
  }
}
  /* ********FOR FUN*************************************
   * You can add a clear() method, a find method      */
  public static void clear() {
    itemTotal=0;
    items=null;  
  } 
}
//----FOLLOW UML for ItemYourName class in presentation.
/* ***************************
* Author: Jianying Chiang    Date: 2018-05-04
* This class will manipulate memory addresses
* and store the required addresses within the
* String type objects.
* ****************************/
class ItemJianY {
  ItemJianY() {}
  ItemJianY(String name) {
    objID=Assignment9JianY.itemTotal;
    info=name;
    previous=null;
    if (Assignment9JianY.itemTotal==1) {
      next=null;
    }
    else {
      next=Assignment9JianY.items;
    }
  }
  ItemJianY(int index, String name) {
    objID=index;
    info=name;
    if (index==0) {}
    else if (index==1) {
      previous=null;
      if (Assignment9JianY.itemTotal==1) {
        next=null;
      }
      else {
        next=Assignment9JianY.items;
      }
               
    }
    else if (index==Assignment9JianY.itemTotal) {
      ItemJianY holder = Assignment9JianY.items;
      for (int x=1; x<Assignment9JianY.itemTotal-1; x++) {
        holder=holder.next;        
      }
      previous=holder;
      next=null;
    }
    else {
      ItemJianY holder = Assignment9JianY.items;
      for (int x=1; x<index-1; x++) {
        holder=holder.next; 
      }
      previous=holder;
      holder=holder.next;
      next=holder;
    }
  }
  int objID;
  String info;
  ItemJianY previous;
  ItemJianY next;
}