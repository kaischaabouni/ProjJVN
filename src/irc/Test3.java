package irc;

import java.io.Serializable;

import jvn.JvnObject;
import jvn.JvnServerImpl;

public class Test3 {

	public static void main(String[] args) {
		
		try {
			//initialise
			JvnServerImpl js = JvnServerImpl.jvnGetServer() ;

			// création d’un objet partagé de classe C1
			Sentence sent1 = new Sentence();
			sent1.write("salut");
			System.out.println(sent1.read());
			JvnObject jo1 = js.jvnCreateObject((Serializable) sent1);
			
			jo1.jvnLockRead();
			//appel de la méthode applicative
			System.out.println(((Sentence)jo1.jvnGetObject()).read());
			
			jo1.jvnUnLock();
			js.jvnRegisterObject("TEST3", jo1);
			
			
			
			jo1.jvnLockWrite();
			((Sentence) jo1.jvnGetObject()).write("hello");
			jo1.jvnUnLock();
			
			
		} catch (Exception e) {
			System.out.println("IRC problem : " + e.getMessage());
		}
		
	}

}
