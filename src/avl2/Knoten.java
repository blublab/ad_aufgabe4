package avl2;

public class Knoten {

	public int value;
	public int balance;
	public Knoten links;
	public Knoten rechts;
	
	public Knoten(int val){
		this.value = val;
		this.balance = 0;
		this.links = this.rechts = null;
	}
	
	public String toString(){
		return String.valueOf(value);
	}
	
	public void insert(Knoten k){
		if (k.value < this.value){
			if (links != null)
				links.insert(k);
			else {
				links = k;
				System.out.println(k.value + " nach " + this + " links eingefuegt");
			}
		}
		if (k.value > this.value){
			if (rechts != null)
				rechts.insert(k);
			else {
				rechts = k;
				System.out.println(k.value + " nach " + this + " rechts eingefuegt");
			}
		}
		balanceOut();
	}
	
	private int balance(){
		if ((links == null) && (rechts == null))
			return 0;
		if (links == null){
			return rechts.hoehe();
		}
		if (links == null){
			return rechts.hoehe();
		}
		return rechts.hoehe() - links.hoehe();
	}
	
	private void balanceOut(){
		int balance = balance();
		
		assert (balance <= 2);
		assert (balance >= -2);
		
		if (balance == -2) {
			int ll = (links.links == null) ? 0 : links.links.hoehe();
			int lr = (links.rechts == null) ? 0 : links.rechts.hoehe();
			if (ll >= lr) // >= ??
				rechtsRotation();
			else
				doppelteLinksRotation();
		} else if (balance == 2) {
			int rr = (rechts.rechts == null) ? 0 : rechts.rechts.hoehe();
			int rl = (rechts.links == null) ? 0 : rechts.links.hoehe();
			if (rr >= rl) // >= ??
				linksRotation();
			else
				doppelteRechtsRotation();
		}
	}
	
	private void rechtsRotation(){
		System.out.println("Rechtsrotation an Knoten: " + this);
		
		Knoten temp = new Knoten(this.value);
		
		this.value 	= links.value;
		this.links	= links.links;
		temp.links 	= links.rechts;
		temp.rechts = rechts;
		this.rechts	= temp;
	}
	
	private void linksRotation(){
		System.out.println("Linksrotation an Knoten: " + this);
		
		Knoten temp = new Knoten(this.value);
		
		this.value 	= rechts.value;
		this.rechts = rechts.rechts;
		temp.links 	= links;
		temp.rechts = rechts.links;
		this.links 	= temp;
	}
	
	private void doppelteRechtsRotation(){
		rechts.rechtsRotation();
		linksRotation();
	}
	
	private void doppelteLinksRotation(){
		links.linksRotation();
		rechtsRotation();
	}
	
	private int hoehe(){
		if (rechts == null && links == null)
			return 0;
		else if (links == null)
			return 1 + rechts.hoehe();
		else if (rechts == null)
			return 1 + links.hoehe();
		else
			return 1 + Math.max(links.hoehe(), rechts.hoehe());
	}

}
