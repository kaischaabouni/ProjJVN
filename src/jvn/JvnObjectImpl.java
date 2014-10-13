package jvn;

import java.io.Serializable;

public class JvnObjectImpl implements JvnObject {
	
	private Serializable objet;
	private int id;
	private JvnState lock; // 0: No lock , 1: lock READ , 2 lock WRITTE
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


	public void jvnLockRead() throws JvnException {
		// TODO Auto-generated method stub

		if ( lock == JvnState.NL ) {
			objet = remoteServ.jvnLockRead(id);
			lock = JvnState.R;
		}else if (lock == JvnState.W || lock == JvnState.WC || lock == JvnState.RWC) {
			lock = JvnState.RWC;
		}else if (lock == JvnState.RC || lock == JvnState.R) {
			lock = JvnState.R;
		}
	}

	public void jvnLockWrite() throws JvnException {
		// TODO Auto-generated method stub
		if ( lock == JvnState.NL ) {
			objet = remoteServ.jvnLockWrite(id);
			lock = JvnState.W;
		}else if (lock == JvnState.W ) {
			lock = JvnState.W;
		}else if (lock == JvnState.WC ) {
			lock = JvnState.W;
		}else if (lock == JvnState.RC ) {
			objet = remoteServ.jvnLockWrite(id);
			lock = JvnState.W;
		}else if (lock == JvnState.RWC ) {
			lock = JvnState.W;
		}else if (lock == JvnState.R ) {
			objet = remoteServ.jvnLockWrite(id);
			lock = JvnState.W;
		}
		
	}

	public synchronized void jvnUnLock() throws JvnException {
		// TODO Auto-generated method stub
		if ( lock == JvnState.NL ) {

		}else if (lock == JvnState.W ) {
			lock = JvnState.WC;
		}else if (lock == JvnState.WC ) {
			lock = JvnState.WC;
		}else if (lock == JvnState.RC ) {
			lock = JvnState.RC;
		}else if (lock == JvnState.RWC ) {
			lock = JvnState.RWC;
		}else if (lock == JvnState.R ) {
			lock = JvnState.RC;
		}
		//lock = JvnState.NL;
		notify();
	}

	public int jvnGetObjectId() throws JvnException {
		// TODO Auto-generated method stub
		return id;
	}

	public Serializable jvnGetObject() throws JvnException {
		// TODO Auto-generated method stub
		return objet;
	}

	public void jvnInvalidateReader() throws JvnException {
		// TODO Auto-generated method stub
		 if (lock == JvnState.R ) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock = JvnState.NL;
		}else {
			lock = JvnState.NL;
		}
		
	}

	public Serializable jvnInvalidateWriter() throws JvnException {
		// TODO Auto-generated method stub
		// WAIT
		if ( lock == JvnState.NL ) {
			
		}else if (lock == JvnState.W ) {
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
			lock = JvnState.NL;// ERREUR
		}
		return objet;
	}

	public synchronized Serializable jvnInvalidateWriterForReader() throws JvnException {
		// TODO Auto-generated method stub
		// WAIT
		
		if ( lock == JvnState.NL ) {
			
		}else if (lock == JvnState.W ) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock = JvnState.RC;
		}else if (lock == JvnState.WC ) {
			lock = JvnState.RC;
		}else if (lock == JvnState.RWC ) {
			lock = JvnState.RC;
		}else {
			lock = JvnState.NL;// ERREUR
		}
		return objet;
	}


	public void setLock(JvnState lock) {
		this.lock = lock;
	}

	
}
