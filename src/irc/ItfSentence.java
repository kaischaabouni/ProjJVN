/*
 * Interface de Sentence
 */

package irc;


public interface ItfSentence extends java.io.Serializable{
	
	@Write
	public void write(String text);
	
	@Read
	public String read();
	
}
