package avl;

public class AVLBaum {

	public Knoten root = null;

	public AVLBaum() {
	}

	public void insert(int n) {
		root = insert(root, n);
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
	
	public void remove(int number){
		Knoten k = new Knoten(number);
		root = (remove(root, k));
		
	}
	
	private Knoten remove(Knoten k, Knoten  deleteKnoten) {
		if (k.equals( deleteKnoten)) {
			Knoten  deleteKnotenLinks = k.links;
			Knoten  deleteKnotenRechts = k.rechts;
			
			if ( deleteKnotenLinks == null &&  deleteKnotenRechts == null){
				return null;
			}
			if ( deleteKnotenLinks != null &&  deleteKnotenRechts == null) {
				return  deleteKnotenLinks;
			}
			if ( deleteKnotenLinks == null &&  deleteKnotenRechts != null) {
				return  deleteKnotenRechts;
			}
			if ( deleteKnotenLinks != null &&  deleteKnotenRechts != null) {
				if( hoehe(deleteKnotenLinks) <  hoehe(deleteKnotenRechts)){
					k.rechts = (removeRekRechtsRum(k.rechts, k));
				}else{
					k.links = (removeRekLinksRum(k.links, k));
				}
				return k;
			}
		}
		if (!k.equals( deleteKnoten)) {
			if (k.value >  deleteKnoten.value) {
				k.links = (remove(k.links,  deleteKnoten));
			} else {
				k.rechts = (remove(k.rechts,  deleteKnoten));
			}
		}

		int leftHeight = k.links != null ? hoehe(k.links) : -1;
		int rechtsHeight = k.rechts != null ? hoehe(k.rechts) : -1;

		if (Math.abs(leftHeight - rechtsHeight) == 2) {
			if (hoehe(k.links) - hoehe(k.rechts) == 2) {
				if (hoehe(k.links.links) >= hoehe(k.links.rechts))
					k = rechtsRotation(k);
				else
					k = doppelteLinksRotation(k);
			} else if (hoehe(k.rechts) - hoehe(k.links) == 2) {
				if (hoehe(k.rechts.rechts) >= hoehe(k.rechts.links))
					k = linksRotation(k);
				else
					k = doppelteRechtsRotation(k);
			}
			return k;
		} else {
			k.resetHeight();
		}
		return k;
	}
	
	private Knoten removeRekRechtsRum(Knoten k, Knoten  deleteKnoten) {
		if (k.links==null) {
			Knoten  deleteKnotenRechts = k.rechts;
			
			if ( deleteKnotenRechts == null){
				 deleteKnoten.value = k.value;
				return null;
			}
			if ( deleteKnotenRechts != null) {
				 deleteKnoten.value = k.value;
				return  deleteKnotenRechts;
			}
		}
		if (k.links!=null) {
				k.links = (removeRekRechtsRum(k.links,  deleteKnoten));
		}

		int leftHeight = k.links != null ? hoehe(k.links) : -1;
		int rechtsHeight = k.rechts != null ? hoehe(k.rechts) : -1;

		if (Math.abs(leftHeight - rechtsHeight) == 2) {
			if (hoehe(k.links) - hoehe(k.rechts) == 2) {
				if (hoehe(k.links.links) >= hoehe(k.links.rechts))
					k = rechtsRotation(k);
				else
					k = doppelteLinksRotation(k);
			} else if (hoehe(k.rechts) - hoehe(k.links) == 2) {
				if (hoehe(k.rechts.rechts) >= hoehe(k.rechts.links))
					k = linksRotation(k);
				else
					k = doppelteRechtsRotation(k);
			}
			return k;
		} else {
			k.resetHeight();
		}
		return k;
	}
	
	private Knoten removeRekLinksRum(Knoten k, Knoten  deleteKnoten) {
		if (k.rechts==null) {
			Knoten  deleteKnotenLinks = k.links;
			
			if ( deleteKnotenLinks == null){
				 deleteKnoten.value = k.value;
				return null;
			}
			if ( deleteKnotenLinks != null) {
				 deleteKnoten.value = k.value;
				return  deleteKnotenLinks;
			}
		}
		if (k.rechts!=null) {
				k.rechts = (removeRekLinksRum(k.rechts,  deleteKnoten));
		}

		int leftHeight = k.links != null ? hoehe(k.links) : -1;
		int rechtsHeight = k.rechts != null ? hoehe(k.rechts) : -1;

		if (Math.abs(leftHeight - rechtsHeight) == 2) {
			if (hoehe(k.links) - hoehe(k.rechts) == 2) {
				if (hoehe(k.links.links) >= hoehe(k.links.rechts))
					k = rechtsRotation(k);
				else
					k = doppelteLinksRotation(k);
			} else if (hoehe(k.rechts) - hoehe(k.links) == 2) {
				if (hoehe(k.rechts.rechts) >= hoehe(k.rechts.links))
					k = linksRotation(k);
				else
					k = doppelteRechtsRotation(k);
			}
			return k;
		} else {
			k.resetHeight();
		}
		return k;
	}

	private Knoten getMaxKnotenInLeftTreeSide(Knoten k) {
		if (k.rechts == null) {
			return k;
		} else {
			return getMaxKnotenInLeftTreeSide(k.rechts);
		}
	}
	
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
