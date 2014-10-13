package irc;

public interface ItfSentence extends java.io.Serializable{
	public void write(String text);

	public String read();
	
}
