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
			ItfSentence jo = (ItfSentence) JvnProxy.newInstance((Serializable)new Sentence(),js,"IRC"); 

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
		frame.addWindowListener(new exitListener(this));
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

		String s;
		s = irc.sentence.read();
		irc.data.setText(s);
		irc.text.append(s+"\n");

		
	}
}
 
 /**
  * Internal class to manage user events (read) on the CHAT application
  **/
 class exitListener implements WindowListener {
	IrcAvecProxy irc;
  
	public exitListener (IrcAvecProxy i) {
		irc = i;
		
	}
   

public void windowActivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

public void windowClosed(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

public void windowClosing(WindowEvent e) {
	// TODO Auto-generated method stub
	JvnServerImpl js = JvnServerImpl.jvnGetServer();
	try {
		js.jvnTerminate();
	} catch (JvnException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	System.exit(0);
}

public void windowDeactivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

public void windowDeiconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

public void windowIconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
}

public void windowOpened(WindowEvent e) {
	// TODO Auto-generated method stub
	
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
		
		String s = irc.data.getText();

		irc.sentence.write(s);
		irc.data.setText(s);
		irc.text.append(s+"\n");


	}
 }



