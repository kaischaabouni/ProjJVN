package jvn;

import java.io.Serializable;

public class JvnObjectImpl implements JvnObject {
	
	private Serializable objet;
	private int id;
	private int lock; // 0: No lock , 1: lock READ , 2 lock WRITTE
	


	public JvnObjectImpl(Serializable objet, int id) {
		super();
		this.objet = objet;
		this.id = id;
		this.lock = 2;
	}


	public int getLock() {
		return lock;
	}


	public int getId() {
		return id;
	}
	
	public void jvnLockRead() throws JvnException {
		// TODO Auto-generated method stub
		lock = 1;
	}

	public void jvnLockWrite() throws JvnException {
		// TODO Auto-generated method stub
		lock = 2;
	}

	public void jvnUnLock() throws JvnException {
		// TODO Auto-generated method stub
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
		lock = 0;
		return objet;
	}

	public Serializable jvnInvalidateWriterForReader() throws JvnException {
		// TODO Auto-generated method stub
		lock = 0;
		return objet;
	}

}
