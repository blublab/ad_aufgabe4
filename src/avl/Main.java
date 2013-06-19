package avl;

import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedPseudograph;

public class Main {
	public static void main(String[] args) throws IOException {
		int[] folge = Reader.getSequence("./Files/zahlen1.dat");

		AVLBaum avlbaum = new AVLBaum();
		for (int n : folge) {
			avlbaum.insert(n);
		}

		Graph<Knoten, DefaultWeightedEdge> g = tree2Graph(avlbaum);
		Knoten root = avlbaum.root;

		System.out.println("Summe Baum Preorder: " + avlbaum.preOrderSum(root));
		System.out.println("Summe Baum Postorder: " + avlbaum.postOrderSum(root));
		int sum = 0;
		for (int n: folge){
			sum += n;
		}
		System.out.println("Summe Folge: " + sum);
		Visualizer.starte(g, root);
		
		avlbaum.remove(2);
		Visualizer.starte(g, root);

	}

	private static Graph<Knoten, DefaultWeightedEdge> tree2Graph(AVLBaum tree) {
		Graph<Knoten, DefaultWeightedEdge> g = new DirectedPseudograph<Knoten, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);
		g.addVertex(tree.root);
		tree2GraphRek(tree.root, g);
		return g;
	}

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
