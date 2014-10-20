/*
 * Objet JvnSeriallLock: contient (l'objet applicatif de type serialisable, JvnLock)
 * Et le JvnLock contient le lock et la liste des serveurs ayant ce lock
 */
package jvn;

import java.io.Serializable;

public class JvnSerialLock {
	private Serializable objet;
	private JvnLock jvnLock;
	
	

	public JvnSerialLock(Serializable objet, JvnLock jvnLock) {
		super();
		this.objet = objet;
		this.jvnLock = jvnLock;
	}



	public Serializable getObjet() {
		return objet;
	}



	public void setObjet(Serializable objet) {
		this.objet = objet;
	}



	public JvnLock getJvnLock() {
		return jvnLock;
	}



	public void setJvnLock(JvnLock jvnLock) {
		this.jvnLock = jvnLock;
	}

	
	
}
