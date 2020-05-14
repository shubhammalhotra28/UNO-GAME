/* 
 * UNO.java 
 * 
 * Version: 
 *     Id$ 1.0
 *
 */
 /*
 * This class is creating the very first interface for the user to login which will
 * further help the user to login by entering his/her port number along with the 
 * nickname, which will further displays a message and opens a new window for the user
 * if it meets the login creditials. PORT NUMBER USED - 16789.
 * 
 *  
 * @authors :   Siddhesh Periaswami , Shubham Malhotra , Joe Chenn
 * Final Project
 * Class ISTE 121
 */


//packages 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.Image;
import javax.swing.ImageIcon;

public class UNO extends JFrame { // class extending JFrame

//Global variables - adding the accessibility to use it any where till the main scope 
private JTextField jtf; // jtf -> textfield for the PortNumber
private JTextField jtf1;// jtf1 -> textfield for adding the nickname
private JLabel jl1; // label for the PortNumber     
private JLabel jl2; // label for the nickname
private JButton Login; // JButton to login   
private JButton clear;  // JButton to clear
private JLabel jl; // 
private ImageIcon image1;

public static void main(String[] args){
   new UNO(); //constructor calling
   
   }
   
public UNO(){
      
    String msg  = "If you're looking for a fun card game to play with friends \n  give Uno a try! Each player begins with a hand of 6 Uno cards. To play \n match one of your cards with the card that's been dealt with.\n The first player to get rid of all of their cards wins the round \n Then all players tally up their scores\n The game continues until one person have no card that holds.\n Once you've gotten the hang of Uno, try variations to switch things up";
    // msg is holding the details (READ ME), instructions for the Game  
    JMenuBar jmbBar = new JMenuBar(); // Creating Jmenubar
    JMenu jm = new JMenu("About"); // creating JMenu 
    JMenuItem jmi = new JMenuItem("Read me"); // Adding the item under menu as Read me
    jm.add(jmi); // adding menuitem to the about menu
    jmbBar.add(jm); // adding JMenu to the JMenuBar
    this.add(jmbBar, BorderLayout.NORTH); // adding everything to North
    
    jmi.addActionListener(new ActionListener(){ //function to add functionality over the Menu created 
    public void actionPerformed(ActionEvent ae){
      JOptionPane.showMessageDialog(null,msg);
      ;}});
                  
                                          

   //Importing image        
   JPanel j = new JPanel(new GridLayout(2,1)); // creating the Panel as GridLayout with 2,1
   image1 = new ImageIcon(getClass().getResource("back.png")); // getting the image
   jl = new JLabel(image1); // setting the image as the label
   add(jl); // adding it to the panel
   add(jl,BorderLayout.WEST); // adding label(image) to the West of the main panel
   
   
   JPanel j1 = new JPanel(); // creating a new panel
   jl1 = new JLabel("Host name:"); // creating the label for PortNumber
   jtf = new JTextField(10); // creating the textfield with size = 10.
   j1.add(jl1); // adding to the panels
   j1.add(jtf); // adding the textfield
   j.add(j1); // adding to the GridLayout panel 
   add(j,BorderLayout.CENTER); // making the GridLayout panel in the center
   
    JPanel j2 = new JPanel(); // creating a new panel with default flow Layout
   jl2 = new JLabel("Nickname  :"); // creating the label for nickname
   jtf1 = new JTextField(10); // creating the text field with size = 10.
   j2.add(jl2); // adding label to the panel 
   j2.add(jtf1); // adding textfield to the panel
   j.add(j2); // adding the panel to the GridLayout panel
   add(j,BorderLayout.CENTER); // centering the main panel
   

   
   JPanel jp = new JPanel(new GridLayout(1,0)); // creating the Panel as GridLayout with 2,1   
   JPanel jpb = new JPanel();
   Login = new JButton("Login"); // initialising the button
   Login.addActionListener(new Inner()); // adding the action performed
   JPanel jpb1 = new JPanel(); // creatiing a new panel with default FlowLayout
   clear = new JButton("Clear"); // initialising the button
   clear.addActionListener( 
   // adding the action performed
      new ActionListener(){
        /*
         * performing the task of setting the text fields as empty
        */
         public void actionPerformed(ActionEvent ae){
         jtf.setText("");
         jtf1.setText("");

         }
         });
         this.addWindowListener(
             new WindowAdapter(){
             /*
             * Window listener further perfoirming the task as displaying a 
             * thankyou message and exiting the program with 0.
             */
             
            public void windowClosing(WindowEvent e){
            System.out.print("Thanks you for using it");
            System.exit(0);
            }
            });
            
   jpb.add(Login); // adding the login button
   jpb1.add(clear); // addinfg the clear button
   jp.add(jpb); // adding the panel to JPanel jp 
   jp.add(jpb1); // adding the panel to JPanel jp
   add(jp,BorderLayout.SOUTH); // setting the positioning of the JPanel to the south 
   
   setTitle("UNO sign in"); // setting the title
   setVisible(true); // set visible - > true/
   pack(); // pack to get the proper screen -> interface 
   setDefaultCloseOperation(EXIT_ON_CLOSE); // setting the default operation as close
   }
   
   class Inner implements ActionListener{  // inner class inheriting from Action Listener
 public void actionPerformed(ActionEvent ae){ // action performed
            // local variables
            String portnumber = jtf.getText(); // getting the data from textfield and initialising the portNumber
            String nickname = jtf1.getText(); // getting the data from textfield and initialising the Nickname
            
            
            /*
            * If portNumber equals 16789 then only user could login without getting tghe
            * unsucessful login message.
            */
           // if( portnumber.trim().equals("16789")) 
//            {
               JOptionPane.showMessageDialog(null, "Login Sucessfull " + "Welcome " +nickname); // showing dialog box with welcome message
                ImagePanel ip = new ImagePanel(nickname,portnumber); // creating the object of class ImagePanel(Server)
            //}
            /*
            * if the else condition is hit, it will display an error message stating login fail within the dialog box
            */
           //  else
//             {
//                 JOptionPane.showMessageDialog(  null, "Login fail");                 
//             }
                              
        }
    
   }
}
   
   
   