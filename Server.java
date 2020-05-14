/* 
 * Server.java 
 * 
 * Version: 
 *     Id$ 1.0
 *
 */
 /* This is Server which is getting connected to the multiple clients called ImagePanels.
 *  It is further reading and writing the objects back and forth to the client.
 *  
 *  
 * @authors :   Siddhesh Periaswami , Shubham Malhotra , Joe Chenn
 * Final Project
 * Class ISTE 121
 */


// importing packages
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.regex.*;
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
import javax.swing.*;
import java.awt.Image;
import java.net.*;
import java.io.*;
import java.util.*;

public class Server extends JFrame{ // class extending JFrame
   // Global variables
   
   private int counter = 0; // creating the integer with 0
   private ServerSocket ss; // creating the ServerSocket
   private Vector<ObjectOutputStream> voos = new Vector<>(); // creating the vector of ObjectOutputStream
   private Object lock = new Object(); // creating the Object = lock
   private Object obj; // creating the Object
   private String message; // creating the string
   private int num; // creating the integer number
   private int unoInteger; // creating the integer variable
   private JTextArea jta; // creating the JTextArea
   private Boolean bool=false; // creating the boolean variable with defined value as false
   private Stack<Integer> stack = new Stack<>(); // creatting the Stack as DataType of Integer
   
   public static void main(String args[]){
      new Server(); // constructor calling
   
   }
   public Server(){
      JPanel jp = new JPanel(); // creating the JPanel with default FlowLayout
      jta = new JTextArea("",30,60); // initialising the JTextArea with size 30,60
      jta.setLineWrap(true); // setting the line wrap as true
      jta.setWrapStyleWord(true); // settting wrap style word as true
      jp.add(jta); // adding the JtextArea to the JPanel
      add(jp,BorderLayout.CENTER); // making the JPanel to the center
      setTitle("Server"); // setting the title as Server
      setVisible(true); // setting the visible to ve true
      pack(); // packing the content of GUI
      setDefaultCloseOperation(EXIT_ON_CLOSE); // setting the default close operation 
       
      ss = null; // setting the saerver socket as null
      try{ 
         // try block
        // ServerChat chat = new ServerChat();
         ss = new ServerSocket(16789); // initialising the server socket with port number as 16789
         Socket cs; // initialising the socket 
         jta.append("\n"+"Server is running");
         while(true){
            // infinite loop
            //jta.append("waiting to connect");
            cs = ss.accept(); // accepting the data in the Client Socket
            jta.append("\n"+"Client Connected - "+cs); // appending the text to the JTextArea showing the top connected client
            ThreadedServer th = new ThreadedServer(cs); // creating an Object of ThreadedServer while passing the socket as an argument
            th.start(); // start5ing the thread
         }
      
      }
      catch(Exception uhe){
         // catch block
         System.out.println(uhe); // prinitng the Exception thrown
      }
   
      
   }
   
   // Inner class inheriting the Thread
   class ThreadedServer extends Thread{ 
      Socket cs; // local variable Socket
      public ThreadedServer(Socket cs){
         // setting the local variable Socket to the value recieved as an argument
         this.cs = cs;
      }
      public void run(){
      	/* 
      	* This will further called while the thread would be started and the Vector of Object Output Stream
         * would be copntinuously update while being up as the global vector which is shared among all the 
         * threads.
      	*/
         try{
            // try block
            ObjectOutputStream oos = new ObjectOutputStream(cs.getOutputStream()); // creating and initialising ObjectOutputStream
            ObjectInputStream ois = new ObjectInputStream(cs.getInputStream()); // creating and initialising ObjectOutputStream
            
            synchronized(voos){
               // synchronized block -> as all the threads are sharing the common resource
               voos.add(oos); // adding the ObjectoutputStream to the global Vector
               num = voos.indexOf(oos); // storing the index of the ObjectOutputStream from the vector( voos) 
               // send this num to the client, somehow telling the client it is player 'num'
            } 
            
            /*
            * checks the size of the vector(voos) and further append to the JTextArea 
            * the number of the clients joining the game.
            *
            */
            
            if(voos.size()==1){
               jta.append("\n"+"Player "+num+" has joined the game!");
            } 
            else if(voos.size()==2){
               jta.append("\n"+"Player "+num+" has joined the game!");
            } 
            else if(voos.size()==3){
               jta.append("\n"+"Player "+num+" has joined the game!");
            } 
            else if(voos.size()==4){
               jta.append("\n"+"Player "+num+" has joined the game!");
               jta.append("\n"+"All the players have joined the game!");
            }             
            oos.writeObject(new String(""+num));//Using objectoutputstream to send message to other clients
            oos.flush(); // flushing
           
               
            while(true){
               // infinite loop
               obj = ois.readObject(); // reading the Object 
            
               /*
               * this condition would be checked if the Object recieved is the instance of 
               * the Strings and will update the string of message and the same is set to the obj
               *
               */
            
               if(obj instanceof String){
                  message=(String)obj;
                  
                  message="Player "+voos.indexOf(oos)+":"+message;
                  jta.append("\n"+"Player "+voos.indexOf(oos)+": "+message);
                  obj = message;
               }
               else if(obj instanceof Integer){
                  jta.append("\n"+"Game is over! Player "+voos.indexOf(oos)+" has won");
               }
               
               /*
               * this condition would be checked if the object would be the instance of Player and
               * then the object is further used while setting the player Number and then the object is updated 
               * as well.
               */
                   
               else if(obj instanceof Player){
                  Player temp = (Player)obj;
                  temp.setPlayerNumber(voos.indexOf(oos));
                  if(!temp.getFilename().equals("") ){
                     if(stack.size()==0||temp.getPlayerNumber()-stack.peek()==1||temp.getPlayerNumber()-stack.peek()==-3){
                        stack.push(temp.getPlayerNumber());
                     
                        jta.append("\n"+temp.getNickname()+" has played");
                        obj = temp;
                     }
                     else{
                        temp.setNickname("");
                        obj=temp;
                     }
                  }
                  /*If the player picks a card from the middle, consider it as a move*/
                  else if(temp.getFilename().equals("")){
                     stack.push(temp.getPlayerNumber());
                     
                     jta.append("\n"+temp.getNickname()+" has played");
                     obj = temp;
                  }
                  else if(temp.getSize()==0){
                     System.out.println("Size is$: "+temp.getSize());
                     jta.append("\n"+temp.getNickname()+ " has won!!!");//Add which player has won to the text area
                  }
               
                  
               }
               else if(obj instanceof Double){
                  System.out.println("Double is: "+obj);//Printing the double sent by the client
               }
               
               synchronized(lock){
                  // synchronized block
                  for(ObjectOutputStream oos1: voos){ // foor loop
                        
                     System.out.println("Inside the for loop"); // printing the statement
                     oos1.writeObject(obj);//Using objectoutputstream to send message to other clients
                        //System.out.println(obj);
                     oos1.flush(); // flushing
                  }
               }
                        
            }
         
         }
         catch(Exception aeg){
            // exception block
            System.out.println(aeg); // printing the Exception 
         }
      }
   
   }

}