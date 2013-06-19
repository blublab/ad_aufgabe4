package avl2;

public class AVLBaum {

	public Knoten root = null;

	public AVLBaum() {
	}
	
	public void insert(int i){
		if (root != null)
			root.insert(new Knoten(i));
		else
			root = new Knoten(i);
	}
	
}
