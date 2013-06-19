package avl2;

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
	
	public void insert(Knoten k){
		if (k.value < this.value){
			if (links != null)
				links.insert(k);
			else {
				links = k;
				System.out.println(k.value + " nach " + this + " links eingefuegt");
			}
		}
		if (k.value >= this.value){
			if (rechts != null)
				rechts.insert(k);
			else {
				rechts = k;
				System.out.println(k.value + " nach " + this + " rechts eingefuegt");
			}
		}
		balanceOut();
	}
	
	public void delete(int i){
		if (value == i){
			System.out.println("Root Knoten loeswhen noch zu iimperkemkrnjeieren");
			System.exit(1);
		}

		if (i > value){
			if (rechts.value == i){
				if ((rechts.rechts == null) && rechts.links == null)
					rechts = null;
			} else 
				rechts.delete(i);
		} else {
			if (links.value == i){
				if ((links.rechts == null) && links.links == null)
					links = null;
			} else {
				links.delete(i);
			}
		}

		balanceOut();
	}
	
	public int preOrderSum(){
		if ((links == null) && (rechts == null))
			return value;
		if (links == null)
			return rechts.preOrderSum();
		if (rechts == null)
			return rechts.preOrderSum();
		return value + links.preOrderSum() + rechts.preOrderSum();
	}
	
	public int postOrderSum(){
		if ((links == null) && (rechts == null))
			return value;
		if (links == null)
			return rechts.preOrderSum();
		if (rechts == null)
			return rechts.preOrderSum();
		return links.preOrderSum() + rechts.preOrderSum() + value;
	}

	
	private int balance(){
		if ((links == null) && (rechts == null))
			return 0;
		if (links == null){
			return rechts.hoehe();
		}
		if (rechts == null){
			return links.hoehe();
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
		if (links != null)
			temp.links 	= links.rechts;
		temp.rechts = rechts;		
		this.value 	= links.value;
		this.links	= links.links;
		this.rechts	= temp;
	}
	
	private void linksRotation(){
		System.out.println("Linksrotation an Knoten: " + this);
		
		Knoten temp = new Knoten(this.value);

		if (rechts != null)
			temp.rechts = rechts.links;
		temp.links 	= links;
		this.value 	= rechts.value;
		this.rechts = rechts.rechts;
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
			return 1;
		else if (links == null)
			return 1 + rechts.hoehe();
		else if (rechts == null)
			return 1 + links.hoehe();
		else
			return 1 + Math.max(links.hoehe(), rechts.hoehe());
	}

}
