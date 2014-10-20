/*
 * Impl�mentation de JvnObject (intercepteur de l'objet applicatif)
 */
package jvn;

import java.io.Serializable;

public class JvnObjectImpl implements JvnObject {
	
	//l'objet applicatif
	private Serializable objet;
	
	//id unique de l'objet
	private int id;
	
	//type du verrou acquis {NL,R,W,RC,WC,RWC}
	private JvnState lock;
	
	//Serveur local, qui a cr�� l'objet
	private transient JvnServerImpl remoteServ;

	public JvnObjectImpl(Serializable objet, int id,JvnServerImpl server) {
		super();
		this.objet = objet;
		this.id = id;
		this.lock = JvnState.W;
		this.remoteServ = server;
	}

	public JvnState getLock() {
		return lock;
	}

	public int getId() {
		return id;
	}
	
	public void setRemoteServ(JvnServerImpl remoteServ) {
		this.remoteServ = remoteServ;
	}

	//acqu�rir le verrou R pour l'objet
	public void jvnLockRead() throws JvnException {
		if ( lock == JvnState.NL ) {
			objet = remoteServ.jvnLockRead(id);
			lock = JvnState.R;
		}else if (lock == JvnState.W || lock == JvnState.WC || lock == JvnState.RWC) {
			lock = JvnState.RWC;
		}else if (lock == JvnState.RC || lock == JvnState.R) {
			lock = JvnState.R;
		}
	}
	
	//acqu�rir le verrou W pour l'objet
	public void jvnLockWrite() throws JvnException {
		if ( lock == JvnState.NL || lock == JvnState.RC || lock == JvnState.R) {
			objet = remoteServ.jvnLockWrite(id);
			lock = JvnState.W;
		}else if (lock == JvnState.W || lock == JvnState.WC || lock == JvnState.RWC) {
			lock = JvnState.W;
		}
	}

	//Liberer, relacher le verrou
	public synchronized void jvnUnLock() throws JvnException {
		if (lock == JvnState.W  || lock == JvnState.RWC) 
			lock = JvnState.WC;
		else if (lock == JvnState.R ) 
			lock = JvnState.RC;


		notifyAll();
	}

	public int jvnGetObjectId() throws JvnException {
		return id;
	}

	public Serializable jvnGetObject() throws JvnException {
		return objet;
	}

	public synchronized void jvnInvalidateReader() throws JvnException {
		 if (lock == JvnState.R ) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lock = JvnState.NL;
		}else {
			lock = JvnState.NL;
		}
		
	}

	public synchronized Serializable jvnInvalidateWriter() throws JvnException {
		if (lock == JvnState.W ) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock = JvnState.NL;
		}else if (lock == JvnState.WC ) {
			lock = JvnState.NL;
		}else {
			lock = JvnState.NL; 
		}
		return objet;
	}

	public synchronized Serializable jvnInvalidateWriterForReader() throws JvnException {		
		if (lock == JvnState.W ) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock = JvnState.RC;
		}else if (lock == JvnState.WC || lock == JvnState.RWC ) {
			lock = JvnState.RC;
		}else {
			lock = JvnState.NL;
		}
		return objet;
	}


	public void setLock(JvnState lock) {
		this.lock = lock;
	}

	
}
