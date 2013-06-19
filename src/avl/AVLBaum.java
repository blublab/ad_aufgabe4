package avl;

public class AVLBaum {

	public Knoten root = null;

	public AVLBaum() {
	}

	public void insert(int n) {
		root = insert(root, n);
	}
	
	public void remove(int n){
		
	}
	
	public int preOrderSum(Knoten k){
		if ((k.links == null) && (k.rechts == null))
			return k.value;
		if (k.links == null)
			return k.value + preOrderSum(k.rechts);
		if (k.rechts == null)
			return k.value + preOrderSum(k.links);
		return k.value + preOrderSum(k.links) + preOrderSum(k.rechts);
	}
	
	public int postOrderSum(Knoten k){
		if ((k.links == null) && (k.rechts == null))
			return k.value;
		if (k.links == null)
			return k.value + postOrderSum(k.rechts);
		if (k.rechts == null)
			return k.value + postOrderSum(k.links);
		return postOrderSum(k.links) + postOrderSum(k.rechts) + k.value;
	}

	private Knoten insert(Knoten k, int n) {
		if (k == null) {
			return new Knoten(n);
		}
		if (n < k.value) {
			k.links = insert(k.links, n);
			System.out.println(n + " nach " + k + " links eingefuegt");
			if (hoehe(k.links) - hoehe(k.rechts) == 2) {
				if (hoehe(k.links.links) >= hoehe(k.links.rechts))
					k = rechtsRotation(k);
				else
					k = doppelteLinksRotation(k);
			}
		}
		else if (n >= k.value) {
			k.rechts = insert(k.rechts, n);
			System.out.println(n + " nach " + k + " rechts eingefuegt");
			if (hoehe(k.rechts) - hoehe(k.links) == 2) {
				if (hoehe(k.rechts.rechts) >= hoehe(k.rechts.links))
					k = linksRotation(k);
				else
					k = doppelteRechtsRotation(k);
			}
		}
		k.hoehe = max(hoehe(k.links), hoehe(k.rechts)) + 1;
		return k;
	}
	
//	private Knoten remove(Knoten k, int n){
//		
//	}

	private static Knoten rechtsRotation(Knoten k) {
		System.out.println("Rechtsrotation an Knoten: " + k);
		Knoten neu = k.links;
		k.links = k.links.rechts;
		neu.rechts = k;
		k.hoehe = max(hoehe(k.rechts), hoehe(k.links)) + 1;
		neu.hoehe = max(hoehe(neu.rechts), hoehe(neu.links)) + 1;

		return neu;
	}

	private static Knoten linksRotation(Knoten k) {
		System.out.println("Linksrotation an Knoten: " + k);
		Knoten neu = k.rechts;
		k.rechts = k.rechts.links;
		neu.links = k;
		k.hoehe = max(hoehe(k.rechts), hoehe(k.links)) + 1;
		neu.hoehe = max(hoehe(neu.rechts), hoehe(neu.links)) + 1;

		return neu;
	}

	private static Knoten doppelteRechtsRotation(Knoten k) {
		System.out.println("Doppelterechtsrotation an Knoten: " + k);
		k.rechts = rechtsRotation(k.rechts);
		return linksRotation(k);
	}

	private static Knoten doppelteLinksRotation(Knoten k) {
		System.out.println("Doppeltelinksrotation an Knoten: " + k);
		k.links = linksRotation(k.links);
		return rechtsRotation(k);
	}

	private static int hoehe(Knoten k) {
		if (k == null)
			return -1;
		else
			return k.hoehe;
	}

	private static int max(int a, int b) {
		if (a >= b)
			return a;
		else
			return b;
	}
	
}
