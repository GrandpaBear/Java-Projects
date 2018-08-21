import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

class TicTacToe {
  public static void main(String[] args) {
    Window game = new Window();
    game.start();
  }
}

class Window {
  private int win=0;
  private int count;
  private JFrame frame;
  private JPanel menuPane;
  private JLayeredPane gamePane;
  private ButtonInput[][] buttons;
  private int turn;

  //get/set methods
  public ButtonInput getButtons(int A, int B) {return buttons[A][B];}
  public void setTurn(int A) {turn=A;}
  public int getTurn() {return turn;}
  
  public void frameInitializer() {
    frame = new JFrame();
    frame.setSize(500,500);
    frame.setResizable(false);
    frame.setBackground(Color.WHITE);
    frame.setTitle("Tic-Tac-Toe");
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ImageIcon img = new ImageIcon("icon.jpg");
    frame.setIconImage(img.getImage());
  }
  
  public void mainMenu() {
    menuPane = new JPanel(new CardLayout());
    menuPane.setLayout(null);
    menuPane.setPreferredSize(new Dimension(500,500));
    menuPane.setBackground(Color.ORANGE);
    
    JLabel mainTitle = new JLabel("Tic-Tac-Toe");
    mainTitle.setFont(new Font("Comic Sans", Font.BOLD, 50));
    mainTitle.setBounds(108,40,300,60);
    menuPane.add(mainTitle);
    
    /*
    JButton onePlayerBTN = new JButton("One Player");
    onePlayerBTN.setFont(new Font("Tahoma", Font.BOLD, 20));
    onePlayerBTN.setBackground(Color.WHITE);
    onePlayerBTN.setBounds(163,150,175,50);
    onePlayerBTN.setFocusPainted(false);
   // onePlayerBTN.addActionListener();
    menuPane.add(onePlayerBTN);
    */
    
    JButton twoPlayerBTN = new JButton("Two Players");
    twoPlayerBTN.setFont(new Font("Tahoma", Font.BOLD, 20));
    twoPlayerBTN.setBackground(Color.WHITE);
    twoPlayerBTN.setBounds(163,225,175,50);
    twoPlayerBTN.setFocusPainted(false);
    twoPlayerBTN.addActionListener(new twoPlayerAction());
    menuPane.add(twoPlayerBTN);
    
    /*
    JButton aboutBTN = new JButton("About");
    aboutBTN.setFont(new Font("Tahoma", Font.BOLD, 20));
    aboutBTN.setBackground(Color.WHITE);
    aboutBTN.setBounds(163,300,175,50);
    aboutBTN.setFocusPainted(false);
    menuPane.add(aboutBTN);
    */
    /*
    JButton exitBTN = new JButton("Exit Game");
    exitBTN.setFont(new Font("Tahoma", Font.BOLD, 20));
    exitBTN.setBackground(Color.WHITE);
    exitBTN.setBounds(163,375,175,50);
    exitBTN.setFocusPainted(false);
    menuPane.add(exitBTN);
    */
    frame.add(menuPane);
  }

  public void gridInitializer() {
    gamePane = new JLayeredPane();
    gamePane.setPreferredSize(new Dimension(500,500));
    gamePane.setOpaque(true);
    gamePane.setBackground(Color.WHITE);
    
    
    buttons = new ButtonInput[3][3];
    int x=0, y=0;
    for(int i=0; i<3; i++) {
      y=y+150;
      x=0;
      for(int j=0; j<3; j++) {
        x=x+150;
        buttons[i][j] = new ButtonInput();
        buttons[i][j].addActionListener(buttons[i][j]);
        buttons[i][j].setFocusPainted(false); 
        buttons[i][j].setBorderPainted(false);
        buttons[i][j].setBackground(Color.WHITE);
        buttons[i][j].setA(i);
        buttons[i][j].setB(j);
        buttons[i][j].setBounds(x-115,y-120,125,125);
        gamePane.add(buttons[i][j], new Integer(0));
      }
    }
    frame.add(gamePane);
    
  }

  public void start() {
    count=0;
    turn=0;
    frameInitializer();
    mainMenu();
    frame.setVisible(true);
    
    try {
      AudioInputStream soundStream = AudioSystem.getAudioInputStream(new File("TicTacToeTheme.wav"));
      Clip clip = AudioSystem.getClip();
      clip.open(soundStream);
      clip.start();
      clip.loop(Clip.LOOP_CONTINUOUSLY);
    } catch(Exception e) {
      System.out.println("Audio File is cannot be located: TicTacToeTheme.wav");      
    }
  }
  
  class twoPlayerAction extends JButton implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      menuPane.setVisible(false);
      gridInitializer();
    }  
  }

  class ButtonInput extends JButton implements ActionListener {
    private JButton returnBTN;
    private JButton resetBTN;
    private JPanel returnFrame;
    private JLabel returnText;
    private String type = "";
    private boolean used;
    private int a,b;

    //set methods
    public void setA(int A) {a=A;}
    public void setB(int A) {b=A;}
    //constructor
    ButtonInput() {
      used=false;
    }

    public void actionPerformed(ActionEvent e) {
        if (used==false && getTurn()==0) {
          getButtons(a,b).setIcon(new ImageIcon("x.jpg"));
          type="x";
          used=true;
          setTurn(1);
          count++;
        }
        else if (used==false && getTurn()==1) {
          getButtons(a,b).setIcon(new ImageIcon("o.jpg")); 
          used=true;
          type="o";
          setTurn(0);
          count++;
        }
        else {
          //Nothing
        }
        if (count>4 && count<11) {
          checkWin();
        }
      }


    public void checkWin() {
      int i=0;
      int j=0;
      int stop=0;
      while (stop<3 && win==0) {
        if (getButtons(i,j).type.equals("x")) {
          if (getButtons(i,j+1).type.equals("x")) {
            if (getButtons(i,j+2).type.equals("x")) {
              gameTermination("X");
              stop=3;
              win=1;
            }
            else {
              stop++;
              i++;
            }
          }
          else {
            stop++;
            i++;
          }
        }
        else {
          stop++;
          i++;
        }
      }
      i=0;
      j=0;
      stop=0;
      while (stop<3 && win==0) {
        if (getButtons(i,j).type.equals("x")) {
          if (getButtons(i+1,j).type.equals("x")) {
            if (getButtons(i+2,j).type.equals("x")) {
              gameTermination("X");
            win=1;
          }
          else {
            stop++;
            j++;
          }
        }
          else {
            stop++;
            j++;
          }
        }
        else {
          stop++;
          j++;
        }
      }
      i=0;
      j=0;
      stop=0;
      if (getButtons(i,j).type.equals("x") && win==0) {
        if (getButtons(i+1,j+1).type.equals("x")) {
          if (getButtons(i+2,j+2).type.equals("x")) {
            gameTermination("X");
            win=1;
          }
        }
      }
      else if (getButtons(i+2,j).type.equals("x") && win==0) {
        if (getButtons(i+1,j+1).type.equals("x")) {
          if (getButtons(i,j+2).type.equals("x")) {
            gameTermination("X");
            win=1;
          }
        }
      }
      while (stop<3 && win==0) {
        if (getButtons(i,j).type.equals("o")) {
          if (getButtons(i,j+1).type.equals("o")) {
            if (getButtons(i,j+2).type.equals("o")) {
              gameTermination("O");
              stop=3;
              win=1;
            }
            else {
              stop++;
              i++;
            }
          }
          else {
            stop++;
            i++;
          }
        }
        else {
          stop++;
          i++;
        }
      }
      i=0;
      j=0;
      stop=0;
      while (stop<3 && win==0) {
        if (getButtons(i,j).type.equals("o")) {
          if (getButtons(i+1,j).type.equals("o")) {
            if (getButtons(i+2,j).type.equals("o")) {
              gameTermination("O");
              win=1;
          }
          else {
            stop++;
            j++;
          }
        }
          else {
            stop++;
            j++;
          }
        }
        else {
          stop++;
          j++;
        }
      }
      i=0;
      j=0;
      stop=0;
      if (getButtons(i,j).type.equals("o") && win==0) {
        if (getButtons(i+1,j+1).type.equals("o")) {
          if (getButtons(i+2,j+2).type.equals("o")) {
            gameTermination("O");
            win=1;
          }
        }
      }
      else if (getButtons(i+2,j).type.equals("o") && win==0) {
        if (getButtons(i+1,j+1).type.equals("o")) {
          if (getButtons(i,j+2).type.equals("o")) {
            gameTermination("O");
            win=1;
          }
        }
      }
      if (win==1) {
        for(int d=0; d<3; d++) {
          for(int f=0; f<3; f++) {
            buttons[d][f].removeActionListener(buttons[d][f]);
          }
        }
      }
    }
    
    
    
    
    
    public void gameTermination(String type) {
      
      returnFrame = new JPanel();
      returnFrame.setBackground(Color.ORANGE);
      returnFrame.setBorder(BorderFactory.createLineBorder(Color.black));
      returnFrame.setBounds(148, 175, 200, 120);
      gamePane.add(returnFrame, new Integer(1));
      
      returnText = new JLabel(type+" wins!");
      returnText.setFont(new Font(("Comic Sans"),Font.BOLD, 30)); 
      returnFrame.add(returnText);
      
      /*
      resetBTN = new JButton("Reset game");
      resetBTN.setFont(new Font(("Tahoma"),Font.BOLD, 15));
      returnFrame.add(resetBTN);
      resetBTN.addActionListener(new resetBTNListener());
      
      returnBTN = new JButton("Return to main menu");
      returnBTN.setFont(new Font(("Tahoma"),Font.BOLD, 15));    
      returnFrame.add(returnBTN);
      returnBTN.addActionListener(new returnBTNListener());
      */
      
    }
    
    class resetBTNListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        gamePane.remove(returnFrame);
        
      }
    }
    
    class returnBTNListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        frame.remove(gamePane);
        buttons=null;
        gridInitializer();
      }
    }
  }
}