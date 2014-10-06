package jvn;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class JvNLock {
	private int lock;
	private ArrayList<JvnServerImpl> listServer;
	
	public JvNLock(JvnServerImpl server) {
		this.lock = 2;
		this.listServer = new ArrayList<JvnServerImpl>(); 
		this.listServer.add(server);
	}

	public int getLock() {
		return lock;
	}

	public void setLock(int lock) {
		this.lock = lock;
	}

	public ArrayList<JvnServerImpl> getListServer() {
		return listServer;
	}

	public void addServer(JvnServerImpl server) {
		this.listServer.add(server);
	}
	
	public void removeServer(JvnServerImpl server) {
		this.listServer.remove(server);
	}
	
	
	
}
