/***
 * JAVANAISE Implementation
 * JvnServerImpl class
 * Contact: 
 *
 * Authors: 
 */

package jvn;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.io.*;

import com.sun.xml.internal.bind.v2.runtime.Coordinator;



public class JvnServerImpl 	
              extends UnicastRemoteObject 
							implements JvnLocalServer, JvnRemoteServer{
	
  // A JVN server is managed as a singleton 
	private static JvnServerImpl js = null;
	private Hashtable<Integer, JvnObject> tableauObjet;
	private JvnObject[] tableauLimiteObjet;
	private JvnRemoteCoord remoteCoord;
	private String name = "CoordName";	

  /**
  * Default constructor
  * @throws JvnException
  **/
	private JvnServerImpl() throws Exception {
		super();
		remoteCoord = (JvnRemoteCoord) Naming.lookup(name);
		tableauObjet = new Hashtable<Integer, JvnObject>();
		tableauLimiteObjet = new JvnObject[5];
		// to be completed
	}
	
  /**
    * Static method allowing an application to get a reference to 
    * a JVN server instance
    * @throws JvnException
    **/
	public static JvnServerImpl jvnGetServer() {
		if (js == null){
			try {
				js = new JvnServerImpl();
			} catch (Exception e) {
				return null;
			}
		}
		return js;
	}
	
	/**
	* The JVN service is not used anymore
	* @throws JvnException
	**/
	public  void jvnTerminate()
	throws jvn.JvnException {
    // to be completed 
		
	} 
	
	/**
	* creation of a JVN object
	* @param o : the JVN object state
	* @throws JvnException
	**/
	public  JvnObject jvnCreateObject(Serializable o)
	throws jvn.JvnException { 
		// to be completed 
		int id;
		try {
			id = remoteCoord.jvnGetObjectId();
			JvnObject newJVN = new JvnObjectImpl(o,id,this);	
			tableauObjet.put(id, newJVN);
			return newJVN ; 
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	*  Associate a symbolic name with a JVN object
	* @param jon : the JVN object name
	* @param jo : the JVN object 
	* @throws JvnException
	**/
	public  void jvnRegisterObject(String jon, JvnObject jo)
	throws jvn.JvnException {
		// to be completed 
		try {
			remoteCoord.jvnRegisterObject(jon,jo,this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	* Provide the reference of a JVN object beeing given its symbolic name
	* @param jon : the JVN object name
	* @return the JVN object 
	* @throws JvnException
	**/
	public  JvnObject jvnLookupObject(String jon)
	throws jvn.JvnException {
    // to be completed 
		try {
			JvnObject result = remoteCoord.jvnLookupObject(jon,this);
			if (result != null){
				((JvnObjectImpl)result).setRemoteServ(this);
				tableauObjet.put(((JvnObjectImpl)result).getId(), result);
				return result;
			}else {
				return null;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}	
	
	/**
	* Get a Read lock on a JVN object 
	* @param joi : the JVN object identification
	* @return the current JVN object 
	* @throws  JvnException
	**/
   public Serializable jvnLockRead(int joi)
	 throws JvnException {
		// to be completed 
		try {
			return remoteCoord.jvnLockRead(joi, this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}	
	/**
	* Get a Write lock on a JVN object 
	* @param joi : the JVN object identification
	* @return the current JVN object 
	* @throws  JvnException
	**/
   public Serializable jvnLockWrite(int joi)
	 throws JvnException {
		// to be completed 
		try {
			return remoteCoord.jvnLockWrite(joi, this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}	

	
  /**
	* Invalidate the Read lock of the JVN object identified by id 
	* called by the JvnCoord
	* @param joi : the JVN object id
	* @return void
	* @throws java.rmi.RemoteException,JvnException
	**/
  public void jvnInvalidateReader(int joi)
	throws java.rmi.RemoteException,jvn.JvnException {
		// to be completed 
	  tableauObjet.get(joi).jvnInvalidateReader();
	};
	    
	/**
	* Invalidate the Write lock of the JVN object identified by id 
	* @param joi : the JVN object id
	* @return the current JVN object state
	* @throws java.rmi.RemoteException,JvnException
	**/
  public Serializable jvnInvalidateWriter(int joi)
	throws java.rmi.RemoteException,jvn.JvnException { 
		// to be completed 
		return tableauObjet.get(joi).jvnInvalidateWriter();
	};
	
	/**
	* Reduce the Write lock of the JVN object identified by id 
	* @param joi : the JVN object id
	* @return the current JVN object state
	* @throws java.rmi.RemoteException,JvnException
	**/
   public Serializable jvnInvalidateWriterForReader(int joi)
	 throws java.rmi.RemoteException,jvn.JvnException { 
		// to be completed 
		return tableauObjet.get(joi).jvnInvalidateWriterForReader();
	 };

}

 
