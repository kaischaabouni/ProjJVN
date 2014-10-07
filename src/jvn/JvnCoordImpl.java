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
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.io.Serializable;


public class JvnCoordImpl 	
              extends UnicastRemoteObject 
							implements JvnRemoteCoord{
	
	private Hashtable<String,JvnObject> listeObjetsJVN;
	private Hashtable<Integer,JvnSerialLock> listeLockJVN;
	private Integer number;
	private String name = "CoordName";

  /**
  * Default constructor
  * @throws JvnException
  **/
	private JvnCoordImpl() throws Exception {
		// to be completed
		listeObjetsJVN = new Hashtable<String,JvnObject>();
		listeLockJVN = new Hashtable<Integer,JvnSerialLock>();
		number = 0;
		
		LocateRegistry.createRegistry(1099);
		
		//associer objet objSayHello au nom name
		Naming.rebind(name, this);
	}
	
	public static void main(String argv[]) {
		try {
			JvnCoordImpl coord = new JvnCoordImpl();
			System.out.println("Coordinateur en marche.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
  /**
  *  Allocate a NEW JVN object id (usually allocated to a 
  *  newly created JVN object)
  * @throws java.rmi.RemoteException,JvnException
  **/
  public int jvnGetObjectId()
  throws java.rmi.RemoteException,jvn.JvnException {
    // to be completed 
	  int id = number;
	  number++;
    return id;
  }
  
  /**
  * Associate a symbolic name with a JVN object
  * @param jon : the JVN object name
  * @param jo  : the JVN object 
  * @param joi : the JVN object identification
  * @param js  : the remote reference of the JVNServer
  * @throws java.rmi.RemoteException,JvnException
  **/
  public void jvnRegisterObject(String jon, JvnObject jo, JvnRemoteServer js)
  throws java.rmi.RemoteException,jvn.JvnException{
    // to be completed 
		listeObjetsJVN.put(jon,jo);
		JvnLock  jlock = new JvnLock(jo,js);
		JvnSerialLock jserialLock = new JvnSerialLock(jo.jvnGetObject(), jlock);
		listeLockJVN.put(((JvnObjectImpl) jo).getId(), jserialLock);

  }
  
  /**
  * Get the reference of a JVN object managed by a given JVN server 
  * @param jon : the JVN object name
  * @param js : the remote reference of the JVNServer
  * @throws java.rmi.RemoteException,JvnException
  **/
  public JvnObject jvnLookupObject(String jon, JvnRemoteServer js)
  throws java.rmi.RemoteException,jvn.JvnException{
    // to be completed 
	  JvnObject objet = listeObjetsJVN.get(jon);
	  if ( objet != null) {
			  JvnLock jlock = listeLockJVN.get(((JvnObjectImpl)objet).getId()).getJvnLock();
		  if (jlock != null)
			  jlock.addServer(js);
	  }
    return objet;
  }
  
  /**
  * Get a Read lock on a JVN object managed by a given JVN server 
  * @param joi : the JVN object identification
  * @param js  : the remote reference of the server
  * @return the current JVN object state
  * @throws java.rmi.RemoteException, JvnException
  **/
   public Serializable jvnLockRead(int joi, JvnRemoteServer js)
   throws java.rmi.RemoteException, JvnException{
    // to be completed
	   JvnLock jlock = listeLockJVN.get(joi).getJvnLock();
	   int lock = jlock.getLock();
	   if ( lock != 0 ) {
		   //ArrayList<JvnRemoteServer> serverAvecLock = jlock.getListServer();
		   for(JvnRemoteServer s: jlock.getListServer()){
			   s.jvnInvalidateReader(joi);
			   jlock.removeServer(s);
		   }
	   }
	   listeLockJVN.get(joi).getJvnLock().setLock(1);
	   jlock.addServer(js);
    return listeLockJVN.get(joi).getObjet();
   }

  /**
  * Get a Write lock on a JVN object managed by a given JVN server 
  * @param joi : the JVN object identification
  * @param js  : the remote reference of the server
  * @return the current JVN object state
  * @throws java.rmi.RemoteException, JvnException
  **/
   public Serializable jvnLockWrite(int joi, JvnRemoteServer js)
   throws java.rmi.RemoteException, JvnException{
	   
	   JvnLock jlock = listeLockJVN.get(joi).getJvnLock();
	   int lock = jlock.getLock();
	   if ( lock != 0 ) {
		   //ArrayList<JvnRemoteServer> serverAvecLock = jlock.getListServer();
		   for(JvnRemoteServer s: jlock.getListServer()){
			   JvnSerialLock serialLock = listeLockJVN.get(joi);
			   serialLock.setObjet(s.jvnInvalidateWriter(joi));
			   jlock.removeServer(s);
		   }
	   }
	   listeLockJVN.get(joi).getJvnLock().setLock(2);
	   jlock.addServer(js);
	  return listeLockJVN.get(joi).getObjet();
   }

	/**
	* A JVN server terminates
	* @param js  : the remote reference of the server
	* @throws java.rmi.RemoteException, JvnException
	**/
    public void jvnTerminate(JvnRemoteServer js)
	 throws java.rmi.RemoteException, JvnException {
	 // to be completed
    	
    }

}

 
