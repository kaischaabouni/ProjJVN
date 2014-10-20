/*
 * Objet JvnLock contenant (lock, liste des serveurs ayant le lock)
 */

package jvn;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class JvnLock {
	//lock
	private JvnState lock;
	
	//liste des serveurs contenants le lock
	private ArrayList<JvnRemoteServer> listServer;
	
	public JvnLock(JvnObject jo, JvnRemoteServer server) {
		this(jo);
		this.listServer.add(server);
	}

	public JvnLock(JvnObject jo) {
		this.lock = ((JvnObjectImpl) jo).getLock();
		if ( lock == JvnState.NL) {
			lock = JvnState.NL;
		}else if (lock == JvnState.W || lock == JvnState.WC || lock == JvnState.RWC ) {
			lock = JvnState.W;
		}else if (lock == JvnState.RC || lock == JvnState.R ) {
			lock = JvnState.R;
		}
		this.listServer = new ArrayList<JvnRemoteServer>(); 
	}


	public JvnState getLock() {
		return lock;
	}
	
	public void resetServer(JvnRemoteServer server) {
		this.listServer = new ArrayList<JvnRemoteServer>(); 
		this.listServer.add(server);
	}

	public void setLock(JvnState lock) {
		this.lock = lock;
	}

	public ArrayList<JvnRemoteServer> getListServer() {
		return listServer;
	}

	public void addServer(JvnRemoteServer server) {
		this.listServer.add(server);
	}
	
	public void removeServer(JvnRemoteServer server) {
		this.listServer.remove(server);
	}
	
	
	
}
