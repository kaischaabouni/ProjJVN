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


public class Irc {
	public TextArea		text;
	public TextField	data;
	Frame 			frame;
	JvnObject       sentence;


  /**
  * main method
  * create a JVN object nammed IRC for representing the Chat application
  **/
	public static void main(String argv[]) {
	   try {
		   
		// initialize JVN
		JvnServerImpl js = JvnServerImpl.jvnGetServer();
		
		// look up the IRC object in the JVN server
		// if not found, create it, and register it in the JVN server
		JvnObject jo = js.jvnLookupObject("IRC");
		   
		if (jo == null) {
			jo = js.jvnCreateObject((Serializable) new Sentence());
			// after creation, I have a write lock on the object
			jo.jvnUnLock();
			js.jvnRegisterObject("IRC", jo);
		}
		// create the graphical part of the Chat application
		 new Irc(jo);
	   
	   } catch (Exception e) {
		   System.out.println("IRC problem : " + e.getMessage());
	   }
	}

  /**
   * IRC Constructor
   @param jo the JVN object representing the Chat
   **/
	public Irc(JvnObject jo) {
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
		read_button.addActionListener(new readListener(this));
		frame.add(read_button);
		Button write_button = new Button("write");
		write_button.addActionListener(new writeListener(this));
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
 class readListener implements ActionListener {
	Irc irc;
  
	public readListener (Irc i) {
		irc = i;
	}
   
 /**
  * Management of user events
  **/
	public void actionPerformed (ActionEvent e) {
	 try {
		// lock the object in read mode
		irc.sentence.jvnLockRead();
		
		// invoke the method
		String s = ((Sentence)(irc.sentence.jvnGetObject())).read();
		
		// unlock the object
		irc.sentence.jvnUnLock();
		
		// display the read value
		irc.data.setText(s);
		irc.text.append(s+"\n");
	   } catch (JvnException je) {
		   System.out.println("IRC problem : " + je.getMessage());
	   }
	}
}
 /**
  * Internal class to manage user events (read) on the CHAT application
  **/
 class exitListener implements WindowListener {
	Irc irc;
  
	public exitListener (Irc i) {
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
 class writeListener implements ActionListener {
	Irc irc;
  
	public writeListener (Irc i) {
        	irc = i;
	}
  
  /**
    * Management of user events
    **/
	public void actionPerformed (ActionEvent e) {
		try {	
			// get the value to be written from the buffer
			String s = irc.data.getText();

			// lock the object in write mode
			irc.sentence.jvnLockWrite();

			// invoke the method
			((Sentence)(irc.sentence.jvnGetObject())).write(s);
			// unlock the object
			irc.sentence.jvnUnLock();
		} catch (JvnException je) {
			System.out.println("IRC problem  : " + je.getMessage());
		}
	}
 }



