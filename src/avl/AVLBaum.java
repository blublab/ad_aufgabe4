package avl;

public class AVLBaum {
	
	private Knoten root = null;
	
	public AVLBaum(){}
	
	public void insert(int n){
		Knoten k = new Knoten(n);
		if(root == null){
			root = k;
		}
		else
			insertRek(root,k);
	}
	
	private void insertRek(Knoten x, Knoten y){
		if(y.value < x.value){
			if(x.links == null){
				x.links = y;
				y.parent = x;
				
				checkBalance(x);
			}
			else
				insertRek(x.links,y);
		}
		else if(y.value > x.value){
			if(x.rechts == null){
				x.rechts = y;
				y.parent = x;
				
				checkBalance(x);
			}
			else
				insertRek(x.rechts, y);
		}
	}
	
	private void checkBalance(Knoten k){
		int balance = k.balance;
		
		assert(balance <= 2);
		assert(balance >= -2);
		
		if(balance == -2){
			if(hoehe(k.links.links) >= hoehe(k.links.rechts))
				k = rechtsRotation(k);
			else 
				k = doppelteLinksRotation(k);
		}
		else if(balance == 2){
			if(hoehe(k.rechts.rechts) >= hoehe(k.rechts.links))
				k = linksRotation(k);
			else
				k = doppelteRechtsRotation(k);
		}
		
		if(k.parent != null)	//pruefen, ob wir nicht an wurzel sind
			checkBalance(k.parent);
		else
			root = k;
	}
	
	private Knoten rechtsRotation(Knoten k){
		Knoten neu = k.links;
		neu.parent = k.parent;
		k.links = neu.rechts;
		
		//falls neuer linker knoten des zu balancierenden knotens nicht leer ist, parent setzen
		if(k.links != null)
			k.links.parent = k;
		
		neu.rechts = k;
		k.parent = neu;
		
		if(neu.parent != null){
			if(neu.parent.rechts ==k)
				neu.parent.rechts = neu;
			else
				neu.parent.links = neu;
		}
		balanciere(k);
		balanciere(neu);
		return neu;
	}
	
	private Knoten linksRotation(Knoten k){
		Knoten neu = k.rechts;
		neu.parent = k.parent;
		k.rechts = neu.links;
		
		//falls neuer rechter knoten des zu balancierenden knotens nicht leer ist, parent setzen
		if(k.rechts != null)
			k.rechts.parent = k;
		
		neu.links = k;
		k.parent = neu;
		
		if(neu.parent != null){
			if(neu.parent.rechts == k)
				neu.parent.rechts = neu;
			else
				neu.parent.links = neu;
		}
		balanciere(k);
		balanciere(neu);
		return neu;
	}
	
	private Knoten doppelteRechtsRotation(Knoten k){
		k.rechts = rechtsRotation(k.rechts);
		return linksRotation(k);
	}
	
	private Knoten doppelteLinksRotation(Knoten k){
		k.links = linksRotation(k.links);
		return rechtsRotation(k);
	}
	
	private void balanciere(Knoten k){
		k.balance = hoehe(k.rechts) - hoehe(k.links);
	}
	
	private int hoehe(Knoten k){
		if(k.rechts == null && k.links == null)
			return 0;
		else if(k.links == null)
			return 1+hoehe(k.rechts);
		else if(k.rechts == null)
			return 1+hoehe(k.links);
		else
			return 1+ max(hoehe(k.links), hoehe(k.rechts));
	}

	private int max (int a, int b){
		if(a >= b)
			return a;
		else 
			return b;
	}
}
