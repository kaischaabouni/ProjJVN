package test1;

public class Entier implements java.io.Serializable {
	
	private int x;

	public Entier() {
		super();
		this.x = 0;
	}

	public Entier(int x) {
		super();
		this.x = x;
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
}
