package avl2;

import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedPseudograph;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		/*
		 * Zahlenfolge einlesen
		 */
		int[] folge = Reader.getSequence("./Files/zahlen2.dat");
		
		/*
		 * AVL Baum erstellen und die Folge einfuegen
		 */
		AVLBaum avlbaum = new AVLBaum();
		for (int n : folge) {
			avlbaum.insert(n);
		}
		
		/*
		 * Ausgabe der Summe ueber alle Keys, Preorder, Postorder und direkt aus der Folge
		 */
		System.out.println("====Summe ueber alle Knoten====");
		System.out.println("PreOrder Summe: " + avlbaum.preOrderSum());
		System.out.println("PostOrder Summe: " + avlbaum.postOrderSum());
		int summe = 0;
		for(int i: folge){
			summe += i;
		}
		System.out.println("Direkte Summe: " + summe);
		
		/*
		 * Uberfuehrung des AVL Baums in einen Graphen und visuelle Ausgabe
		 */
		Knoten root = avlbaum.root;
		int toRemove1 = 3;
		int toRemove2 = 14;
		int toRemove3 = 5;
		System.out.println("==== Entferne "+ toRemove1 +", "+ toRemove2 +" und "+ toRemove3 +" ====");
		avlbaum.remove(toRemove1);
		avlbaum.remove(toRemove2);
		avlbaum.remove(toRemove3);
		
		System.out.println("==== Summe nach Entfernen von "+ toRemove1 +", "+ toRemove2 +" und "
		+ toRemove3 +" ="+(toRemove1+toRemove2+toRemove3)+" ===");

		System.out.println(avlbaum.postOrderSum());
		
		System.out.println("erwartete summe: " + (summe-(toRemove1+toRemove2+toRemove3)));
		
		Graph<Knoten, DefaultWeightedEdge> g1 = tree2Graph(avlbaum);
		Visualizer.starte(g1, root);
	}

	/**
	 * Ueberfuehrt den Baum in einen gerichteten Graphen 
	 */
	private static Graph<Knoten, DefaultWeightedEdge> tree2Graph(AVLBaum tree) {
		Graph<Knoten, DefaultWeightedEdge> g = new DirectedPseudograph<Knoten, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);
		g.addVertex(tree.root);
		tree2GraphRek(tree.root, g);
		return g;
	}

	//Hilfsmethode fuer tree3Graph
	private static void tree2GraphRek(Knoten k,
			Graph<Knoten, DefaultWeightedEdge> g) {
		if (k.links != null) {
			g.addVertex(k.links);
			g.addEdge(k, k.links);
			tree2GraphRek(k.links, g);
		}
		if (k.rechts != null) {
			g.addVertex(k.rechts);
			g.addEdge(k, k.rechts);
			tree2GraphRek(k.rechts, g);
		}
	}
}
