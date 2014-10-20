/***
 * Sentence class : used for representing the text exchanged between users
 * during a chat application
 * Contact: 
 *
 * Authors: 
 */

package irc;

public class Sentence implements ItfSentence {
	
	String 		data;
  
	public Sentence() {
		data = new String("1");
	}
	
	public void write(String text) {
		data = text;
	}
	public String read() {
		return data;	
	}
	
}