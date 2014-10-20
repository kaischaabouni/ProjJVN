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
import java.util.Random;


public class OtherAvecProxyStress {
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
			ItfSentence jo = (ItfSentence) JvnProxy.newInstance((Serializable)new Sentence(),js,"MMS"); 

			new OtherAvecProxyStress(jo);

		} catch (Exception e) {
			System.out.println("IRC problem : " + e.getMessage());
		}
	}

  /**
   * IRC Constructor
   @param jo the JVN object representing the Chat
   **/
	public OtherAvecProxyStress(ItfSentence jo) {
		sentence = jo;
		frame=new Frame();
		frame.setLayout(new GridLayout(1,1));
		text=new TextArea(10,60);
		text.setEditable(false);
		text.setForeground(Color.red);
		frame.add(text);
		data=new TextField(40);
		frame.add(data);
		Button test_button = new Button("TEST");
		test_button.addActionListener(new testListener(this));
		frame.add(test_button);

		frame.setSize(545,201);
		text.setBackground(Color.black); 
		frame.setVisible(true);
	}
}


 /**
  * Internal class to manage user events (read) on the CHAT application
  **/
 class testListener implements ActionListener {
	OtherAvecProxyStress irc;
  
	public testListener (OtherAvecProxyStress i) {
		irc = i;
	}
   
 /**
  * Management of user events
  **/
	public void actionPerformed (ActionEvent e) {

		String s;
		
		Random random = new Random();
		s = irc.sentence.read();
		irc.data.setText(s);
		irc.text.append(s+"\n");
		int[] readWrite = new int[3];
		readWrite[0] = 0;
		readWrite[1] = 1;
		readWrite[2] = 1;
		int val = random.nextInt(2); 
		int j = 0;
		for(int i =0;i<6000;i++) {// +4000
			j++;
			j=j%3;
			val = random.nextInt(2); 
			if ( readWrite[j] == 0){
				s = irc.sentence.read();
				irc.data.setText(s);
				irc.text.append(s+"\n");
			}else {
				//s = irc.data.getText();
				s = irc.sentence.read();
				s= String.valueOf((Integer.parseInt(s) + 1));
				irc.sentence.write(s);
				irc.data.setText(s);
				irc.text.append(s+"\n");
			}
			
		}
		
		
		


	}
}




