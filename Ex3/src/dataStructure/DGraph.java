package dataStructure;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.Iterator;

import javax.management.RuntimeErrorException;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.Point3D;

public class DGraph implements graph, Serializable {

	private int edgescount = 0;
	private int MC = 0;
	public static final double EPS1 = 1.0E-4;
	public static final double EPS2 = EPS1 * EPS1;
	public static final double EPS = EPS2;
	private HashMap<Integer, node_data> nodeMap;
	private HashMap<Integer, HashMap<Integer, edge_data>> edgeMap;

	// *******************initial graph data*******************
	private void init() {
		this.nodeMap = new HashMap<Integer, node_data>();
		this.edgeMap = new HashMap<Integer, HashMap<Integer, edge_data>>();
	}

	// *******************initial graph from JSON*******************
	public void init(final String jsonSTR) {
		try {
			node.resetCount();
			this.init();
			this.edgescount = 0;
			final JSONObject graph = new JSONObject(jsonSTR);
			final JSONArray nodes = graph.getJSONArray("Nodes");
			final JSONArray edges = graph.getJSONArray("Edges");
			for (int i = 0; i < nodes.length(); ++i) {
				final int id = nodes.getJSONObject(i).getInt("id");
				final String pos = nodes.getJSONObject(i).getString("pos");
				final Point3D p = new Point3D(pos);
				this.addNode(new node(id, p, 0));
			}
			for (int i = 0; i < edges.length(); ++i) {
				final int s = edges.getJSONObject(i).getInt("src");
				final int d = edges.getJSONObject(i).getInt("dest");
				final double w = edges.getJSONObject(i).getDouble("w");
				this.connect(s, d, w);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// *******************default constructor*******************
	public DGraph() {
		this.nodeMap = new HashMap<Integer, node_data>();
		this.edgeMap = new HashMap<Integer, HashMap<Integer, edge_data>>();
		this.edgescount = 0;
		this.MC = 0;
	}

	// *******************constructor*******************
	public DGraph(DGraph other) {
		this.nodeMap = other.nodeMap;
		this.edgeMap = other.edgeMap;
		this.edgescount = other.edgescount;
		this.MC = other.MC;
	}

	// *******************constructor from JSON*******************
	public DGraph(final String file_name) {
		try {
			this.init();
			node.resetCount();
			final Scanner scanner = new Scanner(new File(file_name));
			final String jsonString = scanner.useDelimiter("\\A").next();
			scanner.close();
			final JSONObject graph = new JSONObject(jsonString);
			final JSONArray nodes = graph.getJSONArray("Nodes");
			final JSONArray edges = graph.getJSONArray("Edges");
			for (int i = 0; i < nodes.length(); ++i) {
				final int id = nodes.getJSONObject(i).getInt("id");
				final String pos = nodes.getJSONObject(i).getString("pos");
				final Point3D p = new Point3D(pos);
				this.addNode(new node(id, p, 0));
			}
			for (int i = 0; i < edges.length(); ++i) {
				final int s = edges.getJSONObject(i).getInt("src");
				final int d = edges.getJSONObject(i).getInt("dest");
				final double w = edges.getJSONObject(i).getDouble("w");
				this.connect(s, d, w);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// *******************graph to JSON object*******************
	public String toJSON() {
		JSONObject jobj = new JSONObject();
		JSONArray Ver = new JSONArray();
		JSONArray Edge = new JSONArray();
		Collection<node_data> V = this.getV();
		Iterator<node_data> it = V.iterator();
		Collection<edge_data> E = null;
		Iterator itr = null;

		try {
			while (it.hasNext()) {
				node_data n = (node_data) it.next();
				int d = n.getKey();
				String p = n.getLocation().toString();
				JSONObject node = new JSONObject();
				node.put("id", d);
				node.put("pos", p);
				Ver.put(node);
				if (this.getE(d) != null)
					itr = this.getE(d).iterator();
				if (this.getE(d) == null)
					continue;

				while (itr.hasNext() && itr != null) {
					edge_data e = (edge_data) itr.next();
					JSONObject edge = new JSONObject();
					edge.put("src", e.getSrc());
					edge.put("dest", e.getDest());
					edge.put("w", e.getWeight());
					Edge.put(edge);
				}
			}

			jobj.put("Nodes", Ver);
			jobj.put("Edges", Edge);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jobj.toString();
	}

	// *******************getNode method*******************
	@Override
	public node_data getNode(int key) {
		return this.nodeMap.get(key);
	}

	// *******************getEdge method*******************
	@Override
	public edge_data getEdge(int src, int dest) {
		if (this.edgeMap.isEmpty() || !this.edgeMap.containsKey(src) || this.edgeMap.get(src).get(dest) == null)
			return null;
		return this.edgeMap.get(src).get(dest);
	}

	// *******************addNode method*******************
	@Override
	public void addNode(node_data n) {
		if (nodeMap.keySet().contains(n.getKey())) {
			System.err.println("Err: key already exists, add fail");
			return;
		}
		if (n.getWeight() < 0) {
			System.err.println("The weight must be positive! . The node hadn't been added successfully..");
			return;
		}
		int key = n.getKey();
		this.nodeMap.put(key, n);// n used to be casted into (node)
		MC++;
	}

	// *******************connect method*******************
	@Override
	public void connect(int src, int dest, double w) {
		if (w <= 0) {
			System.err.println("The weight must be positive! . connect failed");
			return;
		}
		edge e = new edge(src, dest, w);
		if (nodeMap.get(src) == null || nodeMap.get(dest) == null) {
			System.err.println("can't connect");
			// throw new RuntimeErrorException(null);
		}
		if (edgeMap.get(src) != null) {
			edgeMap.get(src).put(dest, e);
			edgescount++;
			MC++;
		} else {
			this.edgeMap.put(src, new HashMap<Integer, edge_data>());
			this.edgeMap.get(src).put(dest, e);
			edgescount++;
			MC++;
		}

	}

	// *******************get all vertex method*******************
	@Override
	public Collection<node_data> getV() {
		Collection<node_data> col = this.nodeMap.values();
		return col;
	}

	// *******************get all edges of specific node method*******************
	@Override
	public Collection<edge_data> getE(int node_id) {
		if (this.edgeMap.get(node_id) == null)
			return null;
		Collection<edge_data> col = this.edgeMap.get(node_id).values();
		return col;
	}

	// *******************removNode method*******************
	@Override
	public node_data removeNode(int key) {
		if (this.nodeMap.get(key) == null) {
			return null;
		}
		node_data n = nodeMap.get(key);
		Set<Integer> edgeKeys = edgeMap.keySet();
		for (Integer node : edgeKeys) {
			if (edgeMap.get(node).get(key) != null) {
				edgeMap.get(node).remove(key);
				edgescount--;
			}
		}

		// remove all edges coming out of key-node.
		if (this.edgeMap.get(key) != null)
			edgescount -= this.edgeMap.get(key).values().size();
		this.edgeMap.remove(key);
		// remove the key-node.
		this.nodeMap.remove(key);
		MC++;

		return n;
	}

	// *******************removeEdge method*******************
	@Override
	public edge_data removeEdge(int src, int dest) {
		if (getEdge(src, dest) == null) {
			return null;
		}
		edge e = (edge) this.edgeMap.get(src).get(dest);
		this.edgeMap.get(src).remove(dest);
		edgescount--;
		MC++;
		return e;
	}

	// *******************nodeSize method*******************
	@Override
	public int nodeSize() {
		return this.nodeMap.size();
	}

	// *******************edgeSize method*******************
	@Override
	public int edgeSize() {
		return edgescount;
	}

	// *******************ModeCount method*******************
	@Override
	public int getMC() {
		return MC;
	}

	// *******************liitle test JSON*******************
	public static void main(String[] args) {
		graph g = new DGraph();
		graph gr = new DGraph();
		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(0, 1, 0);
		Point3D p3 = new Point3D(0, 0, 1);
		node n1 = new node(0, p1, 1);
		node n2 = new node(1, p2, 10);
		node n3 = new node(2, p3, 100);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(0, 2, 15);
		g.connect(2, 0, 15);
		g.connect(0, 1, 15);
		g.connect(1, 0, 15);
		System.out.println(g.toString());
		System.out.println(((DGraph) g).toJSON());
		((DGraph) gr).init(
				"{\"Edges\":[{\"src\":0,\"w\":15,\"dest\":1},{\"src\":0,\"w\":15,\"dest\":2},{\"src\":1,\"w\":15,\"dest\":0},{\"src\":2,\"w\":15,\"dest\":0}],\"Nodes\":[{\"pos\":\"1.0,0.0,0.0\",\"id\":0},{\"pos\":\"0.0,1.0,0.0\",\"id\":1},{\"pos\":\"0.0,0.0,1.0\",\"id\":2}]}\n"
						+ "");
		System.out.println(((DGraph) gr).toJSON());
	}

}