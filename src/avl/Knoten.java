package avl;

public class Knoten {

	public int value;
	public int hoehe;
	public Knoten links;
	public Knoten rechts;
	
	public Knoten(int val){
		this.value = val;
		this.hoehe = 0;
		this.links = this.rechts = null;
	}
	
	public String toString(){
		return String.valueOf(value);
	}

}
