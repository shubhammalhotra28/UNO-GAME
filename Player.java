/* 
 * Player.java 
 * 
 * Version: 
 *     Id$ 1.0
 *
 */
 /*
 * This class is used to pass the Objects to and fro from the client as well as 
 * the server side. 
 * It is made up as Serializable so that we can update or change the Object of this
 * class easily.
 *  
 * @authors :   Siddhesh Periaswami , Shubham Malhotra , Joe Chenn
 * Final Project
 * Class ISTE 121
 */


// importing packages
import java.util.*;
import java.io.*;
import java.net.*;

class Player implements Serializable{ // class further implementing Serializable to update the Objects

   // local variables
   private int number; // creating the integer
   private String nickname; // creating a String
   private String filename; // creating a String
   private int playerNumber; // creating the integer
   private int size;

   // setters and getters -> used to access the private variables of the class
   
   public int getPlayerNumber() {
      return playerNumber; // returning the value stored in playerNumber
   }
   public int getSize() {
      return size; // returning the value stored in playerNumber
   }

   public void setPlayerNumber(int playerNumber) {
      this.playerNumber = playerNumber; // setting the value of playerNumber
   }

   public String getFilename() {
      return filename; // returning the value stored up as String in filename
   }

   public void setFilename(String filename) {
      this.filename = filename; // setting the filename
   }

   public String getNickname() {
      return nickname; // returning the nickname stored up as String
   }

   public void setNickname(String nickname) {
      this.nickname = nickname; // setting the nickname
   }

   public int getNumber() {
      return number; // returning the integer number
   }

   public void setNumber(int number) {
      this.number = number; // setting the number
   }


   
   public Player(int number, String nickname, String filename, int size){ // constructor
      this.nickname = nickname; // setting the nickname from the very second arg of the constructor
      this.filename = filename; // setting the filename from the very third arg of the constructor
      this.number = number; // setting the number from the very first arg of the constructor
      this.size = size;
   } 
   public String toString() // methdod to return the Object as A string and displaying all the attributes values
	{
		return "("+ number + ", " + nickname + ", " + filename + ", " + size +")";
	}



   // mutator and accessors for PRIVATE attributes

  
}