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



	public void jvnLockRead() throws JvnException {
		// TODO Auto-generated method stub

	}



	public void jvnLockWrite() throws JvnException {
		// TODO Auto-generated method stub

	}

	public void jvnUnLock() throws JvnException {
		// TODO Auto-generated method stub

	}

	public int jvnGetObjectId() throws JvnException {
		// TODO Auto-generated method stub
		return 0;
	}

	public Serializable jvnGetObject() throws JvnException {
		// TODO Auto-generated method stub
		return null;
	}

	public void jvnInvalidateReader() throws JvnException {
		// TODO Auto-generated method stub

	}

	public Serializable jvnInvalidateWriter() throws JvnException {
		// TODO Auto-generated method stub
		return null;
	}

	public Serializable jvnInvalidateWriterForReader() throws JvnException {
		// TODO Auto-generated method stub
		return null;
	}

}
