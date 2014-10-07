package irc;

import java.io.Serializable;

import jvn.JvnObject;
import jvn.JvnServerImpl;

public class Test4 {

	public static void main(String[] args) {
		
		try {
			//initialise
			JvnServerImpl js = JvnServerImpl.jvnGetServer() ;

			JvnObject jo2 = js.jvnLookupObject("TEST3");
			
			jo2.jvnLockRead();
			//appel de la méthode applicative
			System.out.println(((Sentence)jo2.jvnGetObject()).read());
			
			jo2.jvnUnLock();
			
			
		} catch (Exception e) {
			System.out.println("IRC problem : " + e.getMessage());
		}
		
	}

}
