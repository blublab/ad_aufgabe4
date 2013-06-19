package avl2;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

import org.jgraph.*;
import org.jgraph.graph.*;

import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;


/**
 * @author Daniel Kirchner, Yavuz Arslan
 * Teamnr. 5
 * Quelle: 
 * 		jgrapht demo by
 * 		author Barak Naveh
 *		since Aug 3, 2003
 */
public class Visualizer
    extends JApplet
{
    //~ Static fields/initializers ---------------------------------------------

    private static final long serialVersionUID = 3256444702936019250L;
    private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private static final Dimension DEFAULT_SIZE = new Dimension(730, 520);

    //~ Instance fields --------------------------------------------------------

    //
    private JGraphModelAdapter<Knoten, DefaultWeightedEdge> jgAdapter;

    //~ Methods ----------------------------------------------------------------

    public static void starte(Graph<Knoten, DefaultWeightedEdge> g, Knoten root)
    {
        Visualizer applet = new Visualizer();
        applet.init(g, root);

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("JGraphT Adapter to JGraph Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    
    public void init(Graph<Knoten, DefaultWeightedEdge> g, Knoten root)
    {
        // create a JGraphT graph

        // create a visualization using JGraph, via an adapter
        jgAdapter = new JGraphModelAdapter<Knoten, DefaultWeightedEdge>(g);

        JGraph jgraph = new JGraph(jgAdapter);

        adjustDisplaySettings(jgraph);
        getContentPane().add(jgraph);
        resize(DEFAULT_SIZE);

        // position vertices nicely within JGraph component
        ArrayList<Knoten> knoten = new ArrayList<Knoten>();
        knoten.add(root);
        int offsetVertikal = 0;
        int offsetHorizontal = 0;
        
		while(!knoten.isEmpty()) {
			ArrayList<Knoten> temp = new ArrayList<Knoten>();
			for (Knoten v : knoten) {
				positionVertexAt(v, offsetHorizontal, offsetVertikal);
				offsetHorizontal += 150;
				if(v.links != null)
					temp.add(v.links);
				if(v.rechts !=null)
					temp.add(v.rechts);
//				for (DefaultWeightedEdge e: g.edgesOf(v)){
//					if(g.getEdgeSource(e) == v)
//						temp.add(g.getEdgeTarget(e));
//				}
			}
			knoten = temp;
			offsetVertikal += 70;
			offsetHorizontal = 0;
		}

    }

    private void adjustDisplaySettings(JGraph jg)
    {
        jg.setPreferredSize(DEFAULT_SIZE);

        Color c = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter("bgcolor");
        } catch (Exception e) {
        }

        if (colorStr != null) {
            c = Color.decode(colorStr);
        }

        jg.setBackground(c);
    }

    @SuppressWarnings("unchecked") // FIXME hb 28-nov-05: See FIXME below
    private void positionVertexAt(Object vertex, int x, int y)
    {
        DefaultGraphCell cell = jgAdapter.getVertexCell(vertex);
        AttributeMap attr = cell.getAttributes();
        Rectangle2D bounds = GraphConstants.getBounds(attr);

        Rectangle2D newBounds =
            new Rectangle2D.Double(
                x,
                y,
                bounds.getWidth(),
                bounds.getHeight());

        GraphConstants.setBounds(attr, newBounds);

        // TODO: Clean up generics once JGraph goes generic
        AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        jgAdapter.edit(cellAttr, null, null, null);
    }
}
//    //~ Inner Classes ----------------------------------------------------------
//
//    /**
//     * a listenable directed multigraph that allows loops and parallel edges.
//     */
//    private static class ListenableDirectedMultigraph<V, E>
//        extends DefaultListenableGraph<V, E>
//        implements DirectedGraph<V, E>
//    {
//        private static final long serialVersionUID = 1L;
//
//        ListenableDirectedMultigraph(Class<E> edgeClass)
//        {
//            super(new DirectedMultigraph<V, E>(edgeClass));
//        }
//    }
//}