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
	
	public void delete(int i){
		root.delete(i);
	}
	
	public int preOrderSum(){
		return root.preOrderSum();
	}
	
	public int postOrderSum(){
		return root.postOrderSum();
	}
}