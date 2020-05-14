import java.util.*; 
import java.io.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;
import java.util.regex.*;
import javax.swing.*;
import java.awt.Image;
import java.net.*;
/**
 * ImagePanel.java 
 * 
 * @version 1.0
 *
 *CLIENT:
 *
 * This is a multiplayer game desgined using networking, GUI and some data structures for storing the data.
 * This class is creating the very second interface calling from UNO.java for the user. This is the main GUI portion of the game 
 * which consist of Player numbers along with there stack of card which is visible to every other client as well.
 * The Gui also contains the stack of cards in the middle for the clients to pick it up along with the Uno button
 * in the bottom upside. this also contains an chat window to the right (public chat) among the clients.
 * The bottom panel of the GUI contains the player cards(Client).
 * 
 *  
 * @author   Siddhesh Periaswami , Shubham Malhotra , Joe Chenn
 * Final Project
 * Class ISTE 121
 */

public class ImagePanel extends JFrame implements ActionListener{ // class extending JFrame and holding the action performed
   //Global variables - adding the accessibility to use it any where till the main scope 
   private BufferedImage image; // bufferedImage for reading the Image
   private Image newimage; // object of Image
   private static final int intofUno = -1; // initialising a final int with -1
   private JButton stackButton; // creating JButton 
   private BufferedImage imagef; // BufferedImage for reading the image
   private Image newimagef; // creating the object of image
   private BufferedImage image1; // BufferedImage for reading the image
   private Image newimage1; // creating the object of image
   private BufferedImage image_onee; // BufferedImage for reading the image
   private Image newimage_onee; // creating the object of image
   private JButton button; // creating JButton
   private JTextArea jtaWindow; // creating JTextArea
   private JButton cbutton; // creating JButton
   private JButton jb1Chat; // creating JButton
   private JButton all_buttons; // creating JButton
   private JPanel jpc; // creating a JPanel
   private JPanel jpchat; // creating a JPanel
   private JPanel jp; // creating a JPanel
   private JPanel jpw; // creating a JPanel
   private JPanel jpe; // creating a JPanel
   private JTextField jtfChat; // creating JTextArea
   private JButton jn1Chat; // creating JButton
   private JButton jbUno; // creating JButton
   private JButton jb1; // creating JButton
   private JPanel jpButtons; // creating JPanel
   private JTextField jtf1; // creating JTextField
   private JTextArea jta; // creating JTextArea
   private Vector<JButton> vb = new Vector<>(); // creating a vector of buttons
   private Vector<Image> vi = new Vector<>(); // creating a vector of buttons
   private ObjectOutputStream oos; // creating ObjectOutputStream
   private ObjectInputStream ois; // creating ObjectInputStream
   private Socket s; // creating socket variable
   private Object lock = new Object(); // creating Object = lock
   private ArrayList<JButton> albutton= new ArrayList<>(); // creating arraylist of buttons 
   private ArrayList<JButton> albutton_north= new ArrayList<>(); // creating arraylist of buttons
   private ArrayList<JButton> albutton_east= new ArrayList<>(); // creating arraylist of buttons
   private ArrayList<JButton> albutton_west= new ArrayList<>(); // creating arraylist of buttons
   private String file="b2.png"; // creating a string with b2.png for default card to start the game with. 
   private static String n; // creating a string
   private String text; // creating the string -> text
   private Object obj123; // creating an Object variable
   private int num; // creating an integer variable
   private String ob; // creating an string
   private String regSecond; // creating a string
   private String regFirst; // creating a string
   private Boolean bool=true; // creating a boolean with value -> true
   private Boolean bools=true; // creating a boolean with value -> true
   private String msg; // creating a string
   private int index_onee; // creating an integer variable
   private Vector<File> files; // creating a vector of files
   private ArrayList<ArrayList<JButton>> ba; // creating an arraylist of buttons
   private JPanel inner_main; // creating a JPanel
   private JPanel j; // creating a JPanel
   private JButton button_one;
   private JPanel jpw1;
   private JPanel jpe1;
   private JPanel jpn1;
   private JPanel jpn;
   private JPanel jp1;
   private int size;
   private Object choice;
   private static String ip;
   private String ipaddress;
   public ImagePanel(String name,String ip) { // constructor with argument as string
      text=name; // setting the string text - > name (recieved)
      ipaddress = ip;
      //Getting all cards in an arraylist
      File f = new File("cards"); // creating a File object
      files = new Vector<File>(Arrays.asList(f.listFiles())); //initialising the vector with files(deck of cards). 
      int size = (files.size()); // getting the size of the vector
      JPanel jpwest = new JPanel(new BorderLayout()); // creating a JPanel
      jp1 = new JPanel(new GridLayout(1,0,5,5)); // creating the JPanel with GridLayout
      jp = new JPanel(); // initialising the panel with default FlowLayout
      try {
         // try block
         /*
         * In this block it is using for loop is running for some 10 times further generating 
         *creating 10 random images, adding it to different arraylists.
         */                
         for(int i=0;i<10;i++){
            int count=0; // intialising the ccount as 0
            int index = (int)(size*Math.random()); // getting the index which is being generating randomly
            //System.out.println("Random number is " + index); // printing the random number generated
            image = ImageIO.read(files.get(index)); // reading the image at a particular index
           // System.out.println("Card: "+files.get(index).getName()); // printing the name of the file
            String fn = ""+files.get(index).getName();
            System.out.println("Card!!: "+f);
            System.out.println("Index "+index); // printing thr index
            newimage = image.getScaledInstance(110, 110, Image.SCALE_DEFAULT); // scaling the image
            button= new JButton(new ImageIcon(newimage)); // initialising the button with the image
            button.setPreferredSize(new Dimension(108, 108)); // setting the dimensions of the button
            button.setActionCommand(""+files.get(index).getName()); // getting the string through Action Command
            button.addActionListener(this); // adding the functionality to the button (ActionListener)
            albutton.add(button); // addling the button to the ArrayList
            image1 = ImageIO.read(new File("back.png")); // reading the image
            newimage1 = image1.getScaledInstance(110, 110, Image.SCALE_DEFAULT); // scaling the image 
            all_buttons= new JButton(new ImageIcon(newimage1)); // initialising the button with the image
            all_buttons.setPreferredSize(new Dimension(108, 108)); // setting the size of the button
            albutton_north.add(all_buttons); // adding the button to the north panel arraylist
            JButton all_buttons2 = new JButton(new ImageIcon(newimage1)); // initialising the button with the image
            all_buttons2.setPreferredSize(new Dimension(108, 108)); // setting the size of the button
            albutton_east.add(all_buttons2); // adding the button to the east panel arraylist
            JButton all_buttons3= new JButton(new ImageIcon(newimage1)); // initialising the button with the image
            all_buttons3.setPreferredSize(new Dimension(108, 108)); // setting the size of the button
            albutton_west.add(all_buttons3); // adding the button to the west panel arraylist
         }
      } 
      catch (IOException ex) {
         // catch block
         System.out.println(ex); // printing the thrown exception
      }
      try{
         // try block
         /*
         * connecting to the Server with the default port number as 16789 and the Ip as localhost (127.0.0.1) -> loopback
         *
         */
         s = new Socket(ipaddress,16789); // initialising the socket
         ois = new ObjectInputStream(s.getInputStream()); // initialising the ObjectinputStream
         oos = new ObjectOutputStream(s.getOutputStream()); // initialising the ObjectOutputStream
         System.out.println("Reading client number from the server"); // printing the statement
         num = Integer.parseInt((String)ois.readObject()); // reading the object from the Server after casting it into an String
         System.out.println("Number of client is "+num); // printiing the Client Number
         if(num==3){
            oos.writeObject(new Double(1));
            oos.flush();
         }
         else{
            oos.writeObject(new Double(2));
            oos.flush();
         }
      }
      catch(Exception ae){
         // catch block
         System.out.println("client not connected"); // printing the statement if client not connected
      }
    
          //2D Array List
      ba = new ArrayList<ArrayList<JButton>>(); // initialising the object of 2D ArrayList
      
      /*
      * checking the conditions and placing the ArrayList in the 2D ArrayList  
      * in the apppropriate order
      *
      */
      //Indirect addressing
      if(num==0){
         ba.add(albutton);
         ba.add(albutton_west);
         ba.add(albutton_north);
         ba.add(albutton_east);
      }// end of if
      else if(num==1){
         ba.add(albutton_west);
         ba.add(albutton);
         ba.add(albutton_north);
         ba.add(albutton_east);
      }// end of else if
      else if(num==2){
         ba.add(albutton_north);       
         ba.add(albutton_west);
         ba.add(albutton);
         ba.add(albutton_east);
        
      }// end of else if
      else if(num==3){
         ba.add(albutton_east);
         ba.add(albutton_west);
         ba.add(albutton_north);       
         ba.add(albutton);
      }// end of else if
      
      
      
      inner_main = new JPanel(new BorderLayout()); // initialising the panel with BorderLayout
      InnerClass ic = new InnerClass(ois);//Creating the thread 
      ic.start(); // starting the thread
       
      //Adding buttons to the panels accordingly for all the players
      
      //SOUTH
      
      for(int i=0;i<(ba.get(num)).size()-4;i++){
         jp.add((ba.get(num)).get(i));
         System.out.println((ba.get(num)).size());
         System.out.println((ba.get(num)));
      }
      jp.add(new JLabel("Player "+num));
      //jp.add(jtf);
      jp1.add(jp);
      inner_main.add(jp1,BorderLayout.SOUTH);
      
       //NORTH(PANEL)
      jpn1 = new JPanel(new GridLayout(1,0));
      jpn = new JPanel();
      for(int i=0;i<(ba.get((num+2)%4)).size()-4;i++){
         jpn.add((ba.get((num+2)%4)).get(i));
       
      }
      jpn.add(new JLabel("Player "+(num+2)%4));
      jpn1.add(jpn);
      inner_main.add(jpn1,BorderLayout.NORTH);
   
   
       //WEST(PANEL)
      jpw1 = new JPanel(new GridLayout(0,1));
       //WEST
      for(int i=0;i<(ba.get((num+1)%4)).size()-4;i++){
         jpw1.add((ba.get((num+1)%4)).get(i));
      }
      jpw1.add(new JLabel("Player "+(num+1)%4));
       //jpw1.add(jpw);
      inner_main.add(jpw1,BorderLayout.WEST);
   
   
      //EAST(PANEL)
           
      jpe1 = new JPanel(new GridLayout(0,1));
      //EAST
          
      for(int i=0;i<(ba.get((num+3)%4)).size()-4;i++){
         jpe1.add((ba.get((num+3)%4)).get(i));
      
      }
      jpe1.add(new JLabel("Player "+(num+3)%4));
      
      
      
      jpchat = new JPanel(new GridLayout(0,2)); // initialising the new JPanel with GridLasyout 0,2
      jtfChat = new JTextField(7); // initialising the textfield with size = 7
      jb1Chat = new JButton("Send"); // initialising the button
      jb1Chat.addActionListener(this); // adding actionListener to the button
      JPanel text = new JPanel(new BorderLayout()); // creating a new JPanel
      jtaWindow = new JTextArea(15,15); // initialising the textArea with size as 15,15
      

      
      jpchat.add(jtfChat); // adding the textfield to the pannel
      jpchat.add(jb1Chat); // adding the button to the panel
      text.add(jpchat,BorderLayout.NORTH); // aligning it to the North
      text.add(jtaWindow,BorderLayout.CENTER); // aligning it to the center
      add(text,BorderLayout.EAST); // aligning iot to the east
   
      inner_main.add(jpe1,BorderLayout.EAST); // adding the panels to the main JPanel
       
       
       //CENTER
      jpc = new JPanel(new GridLayout(0,1)); // initialising the JPanel with GridLayout as 0,1
      j = new JPanel(); // initialising the panel with default FlowLAyout
      JPanel k = new JPanel(); // initialising the panel with default FlowLAyout

      try{        
         // try block
         imagef = ImageIO.read(new File("cards/"+file)); // reading the file from the cards folder
         newimagef = imagef.getScaledInstance(110, 110, Image.SCALE_DEFAULT); // scaling the image
         System.out.println("Have newimagef "+newimagef); // printing the statement
      }
      catch (IOException ex) {
         // catch block
         System.out.println("Caught IOexception 1"); // printing the statement
         System.out.println(ex); // printing the thrown exception
      }
                  
      cbutton= new JButton(new ImageIcon(newimagef)); // initialising the buttojn with image icon
      cbutton.setPreferredSize(new Dimension(108, 108)); // setting the size of the button   
      j.add(cbutton); // adding the button to the Jpanel(j)
      jpc.add(j); // adding the JPanel(j) to the JPanel (jpc)
      
      JLabel jlStack = new JLabel("Stack"); // creating a label with text as stack
      
      stackButton = new JButton(new ImageIcon(newimage1)); // initialising the button with image icon
      
      stackButton.addActionListener(this); // adding the functional;ity to the button -> ActionListener
      k.add(stackButton); // adding the button the panel
      k.add(jlStack); // adding the label to the panel
      jpc.add(k); // adding the JPanel(k) to the centermost panel (jpc)
   
      inner_main.add(jpc, BorderLayout.CENTER); // adding the JPanel(jpc) to the center of the main JPanel_
      add(inner_main,BorderLayout.CENTER); // adding the panel to the center
   
      //DISPLAY
      setTitle("UNO"); // setting the title 
      setVisible(true); // set Visible -> true
      setDefaultCloseOperation(EXIT_ON_CLOSE); // setting the default close op[eration
      pack(); // pack
   }
   /*
   *
   * Action performed will be catched in this method as the class is getting the data from
   * the actionListener and applying the functionalities to the the particular object.
   * Specific assigned tasks will be performed with this method while clicking the buttons.
   *
   */
   public void actionPerformed(ActionEvent ae){
      choice = ae.getSource(); // getting the object (global) data
      System.out.println("choice ="+choice); // printing the object
      String what = ae.getActionCommand(); // gettijng the string by Action command
      System.out.println("Clicked: "+what); // printing the file name 
   
      /*
      * if choice equals chat button then it will print that button is pressed over the client 
      * and get the text from the text field and further writes it to the Server.
      *
      */
      if(choice==jb1Chat){
         System.out.println("chat button pressed"); // prinitng the statement
         System.out.println(jtfChat.getText()); // printing the text from textfield
         String chat = jtfChat.getText();  // getting the text from the textfield
          
         try
         {
            // try block
            oos.writeObject(chat); // writing the Object (String) to the Server
            oos.flush(); // flushing
         }
         catch(Exception mae)
         {  // catch block
            System.out.println("hit exception "+mae); // printing the thrown exception
         }
      }
      
              
      /*
      * if the choice equals to the stack button then this will simply add the cards 
      * to the player 
      *
      */    
      //Generating a random number and making a button out of that image
      else if (choice==stackButton)
      {
         try{
            // try block
            System.out.println("stackbutton pressed"); // prinitng the statement
            index_onee = (int)(Math.random()*40); // using math.random() to get random int
            image_onee = ImageIO.read(files.get(index_onee)); // reading the file
            System.out.println("Index_onee is "+index_onee); // printing the staement while appending the index
            newimage_onee = image_onee.getScaledInstance(110, 110, Image.SCALE_DEFAULT); // scaling the image
            button= new JButton(new ImageIcon(newimage_onee)); // initialising the button with the image
            button.setPreferredSize(new Dimension(108, 108)); // setting the dimensions
            button.setActionCommand(""+files.get(index_onee).getName()); // getting the String by ActionCommandf
            button.addActionListener(this); // adding the functionality to the button
            albutton.add(button); // adfding button to the arrayList of the Player
            for(int i=0;i<albutton.size()-4;i++){ 
               jp.add((albutton).get(i)); // adding the arrayList elements to the JPanel
            }
            
            jp1.add(jp); // adding the JPanel to another JPanel
            inner_main.add(jp1,BorderLayout.SOUTH); // adding the JPanel to the main Jpanel
            inner_main.revalidate(); // revalidate the main JPanel created
            setSize(1002,1002); //re-setting the size inorder for the players to see that the card has been added.
            //Sending over an object of Player cclass with filename as an empty string
            Player p = new Player(num,text,"",albutton.size()-4);
            oos.writeObject(p);
            oos.flush();
            
         }
         catch(IOException ioe5){
            // catch block
            System.out.println(ioe5); // prinitng the thrown Exception
         }
      
      }
      /*
      * Validation.
      * if choice equals the instance of JButton and if it is not equal to uno button or
      * stack button, then it is getting the strings from the class which is returning two
      * strings and further comparing the number as well as the color and gives appropriate choice to
      * the client to play, or else throw error.
      *
      */
      else if(choice instanceof JButton&&choice!=jbUno&&choice!=jb1Chat&&choice!=stackButton){
         button_one = (JButton)choice; // creating and initialising a new JButton
         System.out.println("(JButton)choice: "+(JButton)choice); // prinitng the statement and appending the JButton
         //Validation - check color and number
         try{
            CardCheck cardDetails = splitmethod(file); // creating the Object of the class CardCheck with arguments as file variable
            CardCheck cardDetails1 = splitmethod(what); // creating the Object of the class CardCheck with arguments as what variable
            System.out.println("first"+cardDetails.first); // printing the first of the object
            System.out.println("first"+cardDetails1.first); // printing the first of the object
            
            /*
            * if the cardDetails object have same object attributes of first or second then this would be executed
            * and validate the game further.
            *
            */
            //If the card has same color or number,
            if(cardDetails1.first.equals(cardDetails.first) || cardDetails1.second.equals(cardDetails.second)){
               jp.remove(button_one); // removing the JButton
               System.out.println("size of the sarray list before"+(albutton.size()-4)); // prinitng the statement with ArratList size
               albutton.remove(button_one); // removing the JButton
               System.out.println("size of the sarray list after"+(albutton.size()-4)); // prinitng the statement with ArratList size
               size = albutton.size();
               validate(); // validating
               //When size of the arraylist is not zero:
               if(albutton.size()-4!=0){
                  Player p = new Player(num,text, what,albutton.size()-4); // making an Object of Player class
                  System.out.println("Player object is "+p); // prinitng the Object of Player
                  oos.writeObject(p); // writing the Object ceated to the Server
                  oos.flush(); // flushing  
               }
               /*
               *This part of the code is to send an integer accordingly to the server if the size of the array list is zero.
               */
               else{
                  if(num==0){
                  oos.writeObject(new Integer(num));
                  oos.flush();}
                 else if(num==1){
                  oos.writeObject(new Integer(num));
                  oos.flush();}
                  else if(num==2){
                  oos.writeObject(new Integer(num));
                  oos.flush();}
                 else if(num==3){
                  oos.writeObject(new Integer(num));
                  oos.flush();}
                  
                  
               }   
                  
               
            }
            /*
            * if no choice is hit then the Client will throw an Dialog box showing:
            * Not an Appropriate choice.
            */
            
            else{
               JOptionPane.showMessageDialog(null,"Not an appropriate choice.");  
            }
         
         }
         
         catch(IOException ioe){
            // catch block
            System.out.println("Caught IOexception 2"); // prinitng the statement
            ioe.printStackTrace(); // getting the Exception Stacktrace
         }
      
      }
   
   
   }
   
   /*
   * method of type CradCheck returning two strings and taking an stringwithin the argument.
   * this function basically helps us getting the two strings from one string which is a file name
   * with the help of Regular Expression and help us know the color as well as the card nuumber
   * which is used further within the main program to validate the moves.
   *
   */
   //Method for validation:
   public CardCheck splitmethod(String compare){
      String hello1 = new String(); // initialising the string
      String hello = new String(); // initialising the string
        
      Pattern p = Pattern.compile("[A-Za-z]{1}"); // pattern
      Pattern num = Pattern.compile("[0-9]{1}"); // pattern
      Matcher m = p.matcher(compare); // matching
      Matcher m1 = num.matcher(compare); // matching
      if(m.find()){
         hello = m.group();} // storing in the local string variables if found
      if(m1.find()){
         
         hello1 = m1.group(); // storing in the local string variables if found
      }
      CardCheck cc = new CardCheck(hello,hello1); // creating an Object of Cardcheck with the args as local Strings updated recently
      System.out.println(cc);   // Printing the object created
      return cc;   // returning the Object of Cardcheck type
      
   }
      //Inncer class extending Thread
   class InnerClass extends Thread{
      ObjectInputStream ois; // local variable of ObjectInputStream
      public InnerClass(ObjectInputStream _ois){
         ois=_ois; // storing the value of Object Input stream recieved to the local variable of Object Input Stream
      }
      //run method
      public void run(){
         try{
            // try block
            while(true){
               // infinite loop
               try{
                     // try block
                  obj123 = ois.readObject(); // reading the Object from the Server
               }
               catch(ClassNotFoundException cnfe){
                     // catch block
                  System.out.println(cnfe); // printing the thrown Exception
               }
                  
                  /*
                  * if Object recieved is the instance of String, then it will append the 
                  * the String to the textarea after every new line.
                  * This is basically allowing the clients to have a public chat among all 
                  * them.
                  *
                  */
               //If instance of string, append it to the text area
               if( obj123 instanceof String){
                  System.out.println("Inside if loop, instannce of string"); // printing the statement
                  msg = (String)obj123; // initialising the object after casting it in String
                  jtaWindow.append("\n"+msg); // appending the Object to the textarea
               }
                /*
                 * This part of the code is to display messages accordingly when all the clients connect 
                */  
               else if(obj123 instanceof Double){
                  double t = (double)obj123;
                  if(t==1){
                     JOptionPane.showMessageDialog(null,"Let the game begin! All players have joined the game, it is Player 0's turn.");
                  }
                  else if(t==2){
                     System.out.println("Wait for other players to join");
                     //JOptionPane.showMessageDialog(null,"Wait for other players to join");
                  }
               }
               //This part of the code is to decide the winner
               else if(obj123 instanceof Integer){
               int n = (int)obj123;
                  JOptionPane.showMessageDialog(null,"Game is over! Player "+n+" has won!");
               }
               /*If it is an object of Player class:*/
               else{              
                  System.out.println("Server sent me an object back "+obj123);
                  Player temp = (Player)obj123;
                        
                  System.out.println("Nick: " + temp.getNickname());
                  System.out.println("File: " + temp.getFilename());
                  System.out.println("Numb: " + temp.getPlayerNumber());
                  System.out.println("Size is: " + temp.getSize());
                  //If file name is not an empty string:
                  if(!temp.getNickname().equals("")){
                     //For every turn, show the dialog box
                     if(num-temp.getPlayerNumber()==1||num-temp.getPlayerNumber()==-3){
                        JOptionPane.showMessageDialog(null,"Your turn");
                     }
                     if(!temp.getFilename().equals("")){
                        //Read the file name
                        file=(String)temp.getFilename();
                         
                        System.out.println("Server sent "+file);
                        try{        
                           imagef = ImageIO.read(new File("cards/"+ file));
                           newimagef = imagef.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
                           System.out.println("Have newimagef "+newimagef);
                        }
                        catch (IOException ex) {
                           System.out.println("Caught IOexception 1");
                           System.out.println(ex);
                        }
                        //Make a button out of the file name read
                        cbutton= new JButton(new ImageIcon(newimagef));
                        cbutton.setPreferredSize(new Dimension(108, 108));
                        //Remove the current button from the panel in the center
                        j.remove(0);
                        //Add a new button
                        j.add(cbutton);
                        //Refresh the window
                        validate();

                        //Removing buttons from other players' window
                        //Removing button from the first player's GUI when player 0 plays:
                        if(temp.getPlayerNumber()==0 && num==1){
                           inner_main.remove(jpe1);
                           jpe1 = new JPanel(new GridLayout(0,1));
                           for(int i=0;i<temp.getSize();i++){
                              jpe1.add((ba.get((num+3)%4)).get(i));
                              
                           }
                           jpe1.add(new JLabel("Player "+(num+3)%4));
                           inner_main.add(jpe1,BorderLayout.EAST);
                           validate();
                           
                        }
                        //Removing button from the second player's GUI when player 0 plays:
                        else if(temp.getPlayerNumber()==0 && num==2){
                           jpn1.remove(jpn);
                           jpn = new JPanel();
                           for(int i=0;i<temp.getSize();i++){
                              jpn.add((ba.get((num+2)%4)).get(i));
                              
                           }
                           jpn.add(new JLabel("Player "+(num+2)%4));
                           jpn1.add(jpn);
                           inner_main.add(jpn1,BorderLayout.NORTH);
                           validate();
                        }
                        //Removing button from the third player's GUI when player 0 plays:
                        else if(temp.getPlayerNumber()==0 && num==3){
                           inner_main.remove(jpw1);
                           jpw1 = new JPanel(new GridLayout(0,1));
                           for(int i=0;i<temp.getSize();i++){
                              jpw1.add((ba.get((num+1)%4)).get(i));
                           }
                           jpw1.add(new JLabel("Player "+(num+1)%4));
                           inner_main.add(jpw1,BorderLayout.WEST);
                           validate();
                        }
                        //Removing button from the player 0's GUI when player 1 plays:
                        else if(temp.getPlayerNumber()==1 && num==0){
                           inner_main.remove(jpw1);
                           jpw1 = new JPanel(new GridLayout(0,1));
                           for(int i=0;i<temp.getSize();i++){
                              jpw1.add((ba.get((num+1)%4)).get(i));
                           }
                           jpw1.add(new JLabel("Player "+(num+1)%4));
                           inner_main.add(jpw1,BorderLayout.WEST);
                           validate();
                        }
                        //Removing button from the second player's GUI when player 1 plays:
                        else if(temp.getPlayerNumber()==1 && num==2){
                           inner_main.remove(jpe1);
                           jpe1 = new JPanel(new GridLayout(0,1));
                           for(int i=0;i<temp.getSize();i++){
                              jpe1.add((ba.get((num+3)%4)).get(i));
                              
                           }
                           jpe1.add(new JLabel("Player "+(num+3)%4));
                           inner_main.add(jpe1,BorderLayout.EAST);
                           validate();
                           
                        }
                        //Removing button from the third player's GUI when player 1 plays:
                        else if(temp.getPlayerNumber()==1 && num==3){
                           jpn1.remove(jpn);
                           jpn = new JPanel();
                           for(int i=0;i<temp.getSize();i++){
                              jpn.add((ba.get((num+2)%4)).get(i));
                              
                           }
                           jpn.add(new JLabel("Player "+(num+2)%4));
                           jpn1.add(jpn);
                           inner_main.add(jpn1,BorderLayout.NORTH);
                           validate();
                        }
                        //Removing button from the player 0's GUI when player 2 plays:
                        else if(temp.getPlayerNumber()==2 && num==0){
                           jpn1.remove(jpn);
                           jpn = new JPanel();
                           for(int i=0;i<temp.getSize();i++){
                              jpn.add((ba.get((num+2)%4)).get(i));
                              
                           }
                           jpn.add(new JLabel("Player "+(num+2)%4));
                           jpn1.add(jpn);
                           inner_main.add(jpn1,BorderLayout.NORTH);
                           validate();
                        }
                        //Removing button from the first player's GUI when player 2 plays:
                        else if(temp.getPlayerNumber()==2 && num==1){
                           inner_main.remove(jpw1);
                           jpw1 = new JPanel(new GridLayout(0,1));
                           for(int i=0;i<temp.getSize();i++){
                              jpw1.add((ba.get((num+1)%4)).get(i));
                           }
                           jpw1.add(new JLabel("Player "+(num+1)%4));
                           inner_main.add(jpw1,BorderLayout.WEST);
                           validate();
                        }
                        //Removing button from the third player's GUI when player 2 plays:
                        else if(temp.getPlayerNumber()==2 && num==3){
                           inner_main.remove(jpe1);
                           jpe1 = new JPanel(new GridLayout(0,1));
                           for(int i=0;i<temp.getSize();i++){
                              jpe1.add((ba.get((num+3)%4)).get(i));
                              
                           }
                           jpe1.add(new JLabel("Player "+(num+3)%4));
                           inner_main.add(jpe1,BorderLayout.EAST);
                           validate();
                           
                        }
                        //Removing button from the player 0's GUI when player 3 plays:
                        else if(temp.getPlayerNumber()==3 && num==0){
                           inner_main.remove(jpe1);
                           jpe1 = new JPanel(new GridLayout(0,1));
                           for(int i=0;i<temp.getSize();i++){
                              jpe1.add((ba.get((num+3)%4)).get(i));
                              
                           }
                           jpe1.add(new JLabel("Player "+(num+3)%4));
                           inner_main.add(jpe1,BorderLayout.EAST);
                           validate();
                        }
                        //Removing button from the first player's GUI when player 3 plays:
                        else if(temp.getPlayerNumber()==3 && num==1){
                           jpn1.remove(jpn);
                           jpn = new JPanel();
                           for(int i=0;i<temp.getSize();i++){
                              jpn.add((ba.get((num+2)%4)).get(i));
                              
                           }
                           jpn.add(new JLabel("Player "+(num+2)%4));
                           jpn1.add(jpn);
                           inner_main.add(jpn1,BorderLayout.NORTH);
                           validate();
                        }
                        //Removing button from the second player's GUI when player 3 plays:
                        else if(temp.getPlayerNumber()==3 && num==2){
                           inner_main.remove(jpw1);
                           jpw1 = new JPanel(new GridLayout(0,1));
                           for(int i=0;i<temp.getSize();i++){
                              jpw1.add((ba.get((num+1)%4)).get(i));
                           }
                           jpw1.add(new JLabel("Player "+(num+1)%4));
                           inner_main.add(jpw1,BorderLayout.WEST);
                           validate();
                           
                        }
                     }
                     //Adding a button in other plyers' GUI when one player plays
                     else if(temp.getFilename().equals("")){
                        System.out.println("Inside else if loop");
                        System.out.println("Size is = "+temp.getSize());
                        //Adding button from the first player's GUI when player 0 plays:
                        if(temp.getPlayerNumber()==0 && num==1){
                           inner_main.remove(jpe1);
                           jpe1 = new JPanel(new GridLayout(0,1));
                           for(int i=0;i<temp.getSize();i++){
                              jpe1.add((ba.get((num+3)%4)).get(i));
                              
                           }
                           jpe1.add(new JLabel("Player "+(num+3)%4));
                           inner_main.add(jpe1,BorderLayout.EAST);
                           validate();
                           
                        }
                        //Adding button from the second player's GUI when player 0 plays:
                        else if(temp.getPlayerNumber()==0 && num==2){
                           jpn1.remove(jpn);
                           jpn = new JPanel();
                           for(int i=0;i<temp.getSize();i++){
                              jpn.add((ba.get((num+2)%4)).get(i));
                              
                           }
                           jpn.add(new JLabel("Player "+(num+2)%4));
                           jpn1.add(jpn);
                           inner_main.add(jpn1,BorderLayout.NORTH);
                           validate();
                        }
                        //Adding button from the third player's GUI when player 0 plays:
                        else if(temp.getPlayerNumber()==0 && num==3){
                           inner_main.remove(jpw1);
                           jpw1 = new JPanel(new GridLayout(0,1));
                           for(int i=0;i<temp.getSize();i++){
                              jpw1.add((ba.get((num+1)%4)).get(i));
                           }
                           jpw1.add(new JLabel("Player "+(num+1)%4));
                           inner_main.add(jpw1,BorderLayout.WEST);
                           validate();
                        }
                        //Adding button from the player 0's GUI when player 1 plays:
                        else if(temp.getPlayerNumber()==1 && num==0){
                           inner_main.remove(jpw1);
                           jpw1 = new JPanel(new GridLayout(0,1));
                           for(int i=0;i<temp.getSize();i++){
                              jpw1.add((ba.get((num+1)%4)).get(i));
                           }
                           jpw1.add(new JLabel("Player "+(num+1)%4));
                           inner_main.add(jpw1,BorderLayout.WEST);
                           validate();
                        }
                        //Adding button from the second player's GUI when player 1 plays:
                        else if(temp.getPlayerNumber()==1 && num==2){
                           inner_main.remove(jpe1);
                           jpe1 = new JPanel(new GridLayout(0,1));
                           for(int i=0;i<temp.getSize();i++){
                              jpe1.add((ba.get((num+3)%4)).get(i));
                              
                           }
                           jpe1.add(new JLabel("Player "+(num+3)%4));
                           inner_main.add(jpe1,BorderLayout.EAST);
                           validate();
                           
                        }
                        //Adding button from the third player's GUI when player 1 plays:
                        else if(temp.getPlayerNumber()==1 && num==3){
                           jpn1.remove(jpn);
                           jpn = new JPanel();
                           for(int i=0;i<temp.getSize();i++){
                              jpn.add((ba.get((num+2)%4)).get(i));
                              
                           }
                           jpn.add(new JLabel("Player "+(num+2)%4));
                           jpn1.add(jpn);
                           inner_main.add(jpn1,BorderLayout.NORTH);
                           validate();
                        }
                        //Adding button from the player 0's GUI when player 2 plays:
                        else if(temp.getPlayerNumber()==2 && num==0){
                           jpn1.remove(jpn);
                           jpn = new JPanel();
                           for(int i=0;i<temp.getSize();i++){
                              jpn.add((ba.get((num+2)%4)).get(i));
                              
                           }
                           jpn.add(new JLabel("Player "+(num+2)%4));
                           jpn1.add(jpn);
                           inner_main.add(jpn1,BorderLayout.NORTH);
                           validate();
                        }
                        //Adding button from the first player's GUI when player 2 plays:
                        else if(temp.getPlayerNumber()==2 && num==1){
                           inner_main.remove(jpw1);
                           jpw1 = new JPanel(new GridLayout(0,1));
                           for(int i=0;i<temp.getSize();i++){
                              jpw1.add((ba.get((num+1)%4)).get(i));
                           }
                           jpw1.add(new JLabel("Player "+(num+1)%4));
                           inner_main.add(jpw1,BorderLayout.WEST);
                           validate();
                        }
                        //Adding button from the third player's GUI when player 2 plays:
                        else if(temp.getPlayerNumber()==2 && num==3){
                           inner_main.remove(jpe1);
                           jpe1 = new JPanel(new GridLayout(0,1));
                           for(int i=0;i<temp.getSize();i++){
                              jpe1.add((ba.get((num+3)%4)).get(i));
                              
                           }
                           jpe1.add(new JLabel("Player "+(num+3)%4));
                           inner_main.add(jpe1,BorderLayout.EAST);
                           validate();
                           
                        }
                        //Adding button from the player 0's GUI when player 3 plays:
                        else if(temp.getPlayerNumber()==3 && num==0){
                           inner_main.remove(jpe1);
                           jpe1 = new JPanel(new GridLayout(0,1));
                           for(int i=0;i<temp.getSize();i++){
                              jpe1.add((ba.get((num+3)%4)).get(i));
                              
                           }
                           jpe1.add(new JLabel("Player "+(num+3)%4));
                           inner_main.add(jpe1,BorderLayout.EAST);
                           validate();
                        }
                        //Adding button from the first player's GUI when player 3 plays:
                        else if(temp.getPlayerNumber()==3 && num==1){
                           jpn1.remove(jpn);
                           jpn = new JPanel();
                           for(int i=0;i<temp.getSize();i++){
                              jpn.add((ba.get((num+2)%4)).get(i));
                              
                           }
                           jpn.add(new JLabel("Player "+(num+2)%4));
                           jpn1.add(jpn);
                           inner_main.add(jpn1,BorderLayout.NORTH);
                           validate();
                        }
                        //Adding button from the second player's GUI when player 3 plays:
                        else if(temp.getPlayerNumber()==3 && num==2){
                           inner_main.remove(jpw1);
                           jpw1 = new JPanel(new GridLayout(0,1));
                           for(int i=0;i<temp.getSize();i++){
                              jpw1.add((ba.get((num+1)%4)).get(i));
                           }
                           jpw1.add(new JLabel("Player "+(num+1)%4));
                           inner_main.add(jpw1,BorderLayout.WEST);
                           validate();
                           
                        }
                     
                           
                     }
                    
                        /*
                        *This part of the code is to display the winner.
                        */
                     else if(temp.getSize()==0){
                        JOptionPane.showMessageDialog(null,"Game is over. "+temp.getNickname()+" has won!");
                     }
                  }
                     /*
                        When the object returned back has an empty nickname string, display a message saying it is not their turn.
                     */
                     //If file is an empty string:
                  else{
                     if(temp.getNumber()==num){
                        JOptionPane.showMessageDialog(null,"Wait for your turn");
                        System.out.println("Wait for your turn!");
                        JButton button_two = (JButton)choice;
                        jp.add(button_two);
                        albutton.add(button_two);
                        validate();
                     }
                     
                  }
               }
            }
            
         }
         catch(IOException ioe1){
            // catch block
            ioe1.printStackTrace(); // printing the Exception (printStacktrace)
         }
      }
   }



}
