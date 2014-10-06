package jvn;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class JvnLock {
	private int lock;
	private ArrayList<JvnRemoteServer> listServer;
	
	public JvnLock(JvnObjectImpl jo, JvnRemoteServer server) {
		this.lock = jo.getLock();
		this.listServer = new ArrayList<JvnRemoteServer>(); 
		this.listServer.add(server);
	}

	public int getLock() {
		return lock;
	}

	public void setLock(int lock) {
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
