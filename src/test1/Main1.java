package test1;

import irc.Sentence;

import java.io.Serializable;

import jvn.JvnObject;
import jvn.JvnServerImpl;

public class Main1 {

	public static void main(String[] args) {

		try {
			JvnServerImpl js = JvnServerImpl.jvnGetServer() ;

			// création d’un objet partagé de classe C1
			Entier o = new Entier();
			JvnObject jvnO = js.jvnCreateObject((Serializable)o);

			// appel d'une méthode applicative
			//jvnO.jvnLockRead();
			System.out.println(((Entier) jvnO.jvnGetObject()).getX());
			//jvnO.jvnUnLock();

			// after creation, I have a write lock on the object
			js.jvnRegisterObject("Nom1", jvnO);

		} catch (Exception e) {
			System.out.println("IRC problem : " + e.getMessage());
		}

	}

}
