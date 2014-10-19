/***
 * Irc class : simple implementation of a chat using JAVANAISE
 * Contact: 
 *
 * Authors: 
 */

package irc;

import java.awt.*;
import java.awt.event.*;

import jvn.*;

import java.io.*;
import java.lang.reflect.Proxy;


public class IrcAvecProxy {
	public TextArea		text;
	public TextField	data;
	Frame 			frame;
	ItfSentence       sentence;


  /**
  * main method
  * create a JVN object nammed IRC for representing the Chat application
  **/
	public static void main(String argv[]) {
		try {
			JvnServerImpl js = JvnServerImpl.jvnGetServer();
			ItfSentence jo = (ItfSentence) JvnProxy.newInstance((Serializable)new Sentence(),js); 

			new IrcAvecProxy(jo);

		} catch (Exception e) {
			System.out.println("IRC problem : " + e.getMessage());
		}
	}

  /**
   * IRC Constructor
   @param jo the JVN object representing the Chat
   **/
	public IrcAvecProxy(ItfSentence jo) {
		sentence = jo;
		frame=new Frame();
		frame.setLayout(new GridLayout(1,1));
		text=new TextArea(10,60);
		text.setEditable(false);
		text.setForeground(Color.red);
		frame.add(text);
		data=new TextField(40);
		frame.add(data);
		Button read_button = new Button("read");
		read_button.addActionListener(new readListener2(this));
		frame.add(read_button);
		Button write_button = new Button("write");
		write_button.addActionListener(new writeListener2(this));
		frame.add(write_button);
		frame.setSize(545,201);
		text.setBackground(Color.black); 
		frame.setVisible(true);
	}
}


 /**
  * Internal class to manage user events (read) on the CHAT application
  **/
 class readListener2 implements ActionListener {
	IrcAvecProxy irc;
  
	public readListener2 (IrcAvecProxy i) {
		irc = i;
	}
   
 /**
  * Management of user events
  **/
	public void actionPerformed (ActionEvent e) {

		// invoke the method
		String s;

			s = irc.sentence.read();
			irc.data.setText(s);
			irc.text.append(s+"\n");
		
		
		   
	//	String s = irc.sentence.read();
		
		// display the read value
		
	}
}

 /**
  * Internal class to manage user events (write) on the CHAT application
  **/
 class writeListener2 implements ActionListener {
	IrcAvecProxy irc;
  
	public writeListener2 (IrcAvecProxy i) {
        	irc = i;
	}
  
  /**
    * Management of user events
   **/
	public void actionPerformed (ActionEvent e) {	
		// get the value to be written from the buffer
		String s = irc.data.getText();

			 irc.sentence.write(s);
			irc.data.setText(s);
			irc.text.append(s+"\n");
	

	}
}



