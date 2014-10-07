package jvn;

import java.io.Serializable;

public class JvnObjectImpl implements JvnObject {
	
	private Serializable objet;
	private int id;
	private int lock; // 0: No lock , 1: lock READ , 2 lock WRITTE
	private transient JvnServerImpl remoteServ;


	public JvnObjectImpl(Serializable objet, int id,JvnServerImpl server) {
		super();
		this.objet = objet;
		this.id = id;
		this.lock = 2;
		this.remoteServ = server;
	}


	public int getLock() {
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

		//if ( lock == 0) {
			objet = remoteServ.jvnLockRead(id);
			lock = 1;
		//}
	}

	public void jvnLockWrite() throws JvnException {
		// TODO Auto-generated method stub
		objet = remoteServ.jvnLockWrite(id);
		lock = 2;
	}

	public void jvnUnLock() throws JvnException {
		// TODO Auto-generated method stub
		// NOTIFY
		this.lock = 0;
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
		lock = 0;
	}

	public Serializable jvnInvalidateWriter() throws JvnException {
		// TODO Auto-generated method stub
		// WAIT
		lock = 0;
		return objet;
	}

	public Serializable jvnInvalidateWriterForReader() throws JvnException {
		// TODO Auto-generated method stub
		// WAIT
		lock = 0;
		return objet;
	}

	
}
