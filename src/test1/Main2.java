package test1;

import java.io.Serializable;

import jvn.JvnObject;
import jvn.JvnServerImpl;

public class Main2 {

	public static void main(String[] args) {
		try {
			JvnServerImpl js = JvnServerImpl.jvnGetServer() ;

			// création d’un objet partagé de classe C1
			Entier o = new Entier();
			JvnObject jvnO = js.jvnLookupObject("Nom1");
			// appel d'une méthode applicative
			//jvnO.jvnLockRead();
			int x = ((Entier) jvnO.jvnGetObject()).getX();
			((Entier) jvnO.jvnGetObject()).setX(x+1);
			System.out.println(((Entier) jvnO.jvnGetObject()).getX());
			//jvnO.jvnUnLock();
			// after creation, I have a write lock on the object
			
			js.jvnRegisterObject("Nom1", jvnO);

		} catch (Exception e) {
			System.out.println("IRC problem : " + e.getMessage());
		}

	}

}
