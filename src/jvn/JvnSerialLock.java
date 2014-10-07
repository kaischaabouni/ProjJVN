package jvn;

import java.io.Serializable;
import java.util.ArrayList;

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
