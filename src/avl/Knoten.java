package avl;

public class Knoten {

	public int value;
	public int balance;
	public Knoten parent;
	public Knoten links;
	public Knoten rechts;
	
	public Knoten(int val){
		this.value = val;
		this.balance = 0;
		this.parent = this.links = this.rechts = null;
	}
	
	public String toString(){
		return String.valueOf(value);
	}

}
