package avl2;

public class Knoten {

	public int value;
	public int hoehe;
	public Knoten links;
	public Knoten rechts;
	
	public Knoten(int val){
		this.value = val;
		this.hoehe = 1;
		this.links = this.rechts = null;
	}
	
	public String toString(){
		return String.valueOf(value);
	}
	
	public int insert(Knoten k){
		if (k.value < this.value){
			if (links != null){
				hoehe = Math.max(hoehe(rechts), links.insert(k)) + 1;
				}
			else {
				links = k;
				hoehe = Math.max(hoehe(links), hoehe(rechts)) + 1;
				System.out.println(k.value + " nach " + this + " links eingefuegt");
			}
		}
		if (k.value >= this.value){
			if (rechts != null){
				hoehe = Math.max(hoehe(links), rechts.insert(k)) + 1;
				}
			else {
				rechts = k;
				hoehe = Math.max(hoehe(links), hoehe(rechts)) + 1;
				System.out.println(k.value + " nach " + this + " rechts eingefuegt");
			}
		}
		balanceOut();
		return hoehe;
	}
	
	public int remove(int i) {
		if (value == i) {
			System.out
					.println("Root Knoten loeswhen noch zu iimperkemkrnjeieren");
			System.exit(1);
		}

		if (i > value) {
			if (rechts.value == i) {
				deleteRechts(i);
			} else
				hoehe = Math.max(hoehe(links), rechts.remove(i)) + 1;
		} else {
			if (links.value == i) {
				deleteLinks(i);
			} else {
				hoehe = Math.max(hoehe(rechts), links.remove(i)) + 1;
			}
		}

		balanceOut();
		return hoehe;
	}
	
	private void deleteRechts(int i){
		if ((rechts.rechts == null) && rechts.links == null) {
			rechts = null;
		}
		else if (rechts.rechts == null){
			int lowerMax = rechts.links.getLowerMax().value;
			hoehe = remove(lowerMax);
			rechts.value = lowerMax;
		}
		else if (rechts.links == null){
			int upperMin = rechts.rechts.getUpperMin().value;
			hoehe = remove(upperMin);
			rechts.value = upperMin;
		}
		else {
			int upperMin = rechts.rechts.getUpperMin().value;
			hoehe = remove(upperMin);
			rechts.value = upperMin;
		}
		hoehe = Math.max(hoehe(links), hoehe(rechts)) + 1;
		balanceOut();
	}
	
	private void deleteLinks(int i){
		if ((links.rechts == null) && links.links == null) {
			links = null;
		}
		else if (links.rechts == null){
			int lowerMax = links.links.getLowerMax().value;
			hoehe = remove(lowerMax);
			links.value = lowerMax;
		}
		else if (links.links == null){
			int upperMin = links.rechts.getUpperMin().value;
			hoehe = remove(upperMin);
			links.value = upperMin;
		}
		else {
			int upperMin = links.links.getUpperMin().value;
			hoehe = remove(upperMin);
			links.value = upperMin;
		}
		hoehe = Math.max(hoehe(links), hoehe(rechts)) + 1;
		balanceOut();
	}
	
	private Knoten getLowerMax(){
		if (rechts == null)
			return this;
		else
			return rechts.getLowerMax();
	}
	
	private Knoten getUpperMin(){
		if (links == null)
			return this;
		else
			return links.getUpperMin();
	}

	public int preOrderSum(){
		if ((links == null) && (rechts == null))
			return value;
		if (links == null)
			return value + rechts.preOrderSum();
		if (rechts == null)
			return value + links.preOrderSum();
		return value + links.preOrderSum() + rechts.preOrderSum();
	}
	
	public int postOrderSum(){
		if ((links == null) && (rechts == null))
			return value;
		if (links == null)
			return rechts.postOrderSum() + value;
		if (rechts == null)
			return links.postOrderSum() + value;
		return links.preOrderSum() + rechts.preOrderSum() + value;
	}
	
	private void balanceOut(){

		int balance = hoehe(rechts) - hoehe(links);
		
		assert (balance <= 2);
		assert (balance >= -2);
		
		if (balance == -2) {
			int ll = (links.links == null) ? 0 : links.links.hoehe;
			int lr = (links.rechts == null) ? 0 : links.rechts.hoehe;
			if (ll >= lr) // >= ??
				rechtsRotation();
			else
				doppelteLinksRotation();
		} else if (balance == 2) {
			int rr = (rechts.rechts == null) ? 0 : rechts.rechts.hoehe;
			int rl = (rechts.links == null) ? 0 : rechts.links.hoehe;
			if (rr > rl) // >= ??
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
		temp.hoehe = Math.max(hoehe(temp.links), hoehe(temp.rechts)) + 1;
		hoehe = Math.max(hoehe(links), hoehe(rechts)) +1;
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
		temp.hoehe = Math.max(hoehe(temp.links), hoehe(temp.rechts)) + 1;
		hoehe = Math.max(hoehe(rechts), hoehe(links)) +1;
	}
	
	private void doppelteRechtsRotation(){
		rechts.rechtsRotation();
		linksRotation();
	}
	
	private void doppelteLinksRotation(){
		links.linksRotation();
		rechtsRotation();
	}
	
	private static int hoehe(Knoten k){
		if (k == null){
			return 0;}
		else {
			return k.hoehe;}
	}
}