/* ***** BEGIN LICENSE BLOCK *****
 * 
 * Copyright (C) 2012 OpsResearch LLC (a Delaware company)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License, version 3,
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * ***** END LICENSE BLOCK ***** */

package com.opsresearch.orobjects.lib.graph.sp;

import java.util.Iterator;
import java.util.Vector;

import com.opsresearch.orobjects.lib.CompareNumberReverse;
import com.opsresearch.orobjects.lib.InvalidPriorityException;
import com.opsresearch.orobjects.lib.PairI;
import com.opsresearch.orobjects.lib.cont.PriorityQueue;
import com.opsresearch.orobjects.lib.cont.PriorityQueueI;
import com.opsresearch.orobjects.lib.graph.EdgeI;
import com.opsresearch.orobjects.lib.graph.EdgeValueI;
import com.opsresearch.orobjects.lib.graph.ElementI;
import com.opsresearch.orobjects.lib.graph.GraphError;
import com.opsresearch.orobjects.lib.graph.GraphI;
import com.opsresearch.orobjects.lib.graph.InvalidGraphError;
import com.opsresearch.orobjects.lib.graph.InvalidPropertyException;
import com.opsresearch.orobjects.lib.graph.PropertiesAdapter;
import com.opsresearch.orobjects.lib.graph.PropertiesI;
import com.opsresearch.orobjects.lib.graph.VertexI;
import com.opsresearch.orobjects.lib.graph.VertexNotFoundException;

public class Connections implements SingleVertexI {

	static private class Vertex {
		private int _label;
		private boolean _isCand;
		private Edge _prevEdge;
		private Vertex _nextCand;
		private VertexI _vertex;

		Vertex(VertexI vertex) {
			_label = 0;
			_isCand = false;
			_vertex = vertex;
		}

		void setPerm(int label) {
			_label = label + 1;
		}

		boolean isPerm(int label) {
			return _label == label + 1;
		}

		public String toString() {
			return _vertex.toString() + (_isCand ? ", cand" : "");
		}

	}

	static public class Edge implements EdgeValueI {
		private int _label;
		private PairI<Double, Edge> _queuePair;
		private double _cost, _time, _dist;
		private int _len;
		private boolean _isCand;
		private Vertex _orig;
		private Vertex _dest;
		private Edge _prevEdge;
		private EdgeI _edge;

		Edge(EdgeI edge, Vertex orig, Vertex dest) {
			_label = 0;
			_orig = orig;
			_dest = dest;
			_edge = edge;
		}

		void setPerm(int label) {
			_label = label + 1;
		}

		void setIsInQueue(int label) {
			_label = label;
		}

		boolean isPerm(int label) {
			return _label == label + 1;
		}

		boolean isInQueue(int label) {
			return _label == label;
		}

		public double getCost(boolean reverse) {
			return _cost;
		}

		public double getTime(boolean reverse) {
			return _time;
		}

		public double getDistance(boolean reverse) {
			return _dist;
		}

		public String toString() {
			return _edge.toString() + (_isCand ? ", cand" : "");
		}
	}

	static private class CandIterator implements Iterator<VertexI> {
		private Vertex _vertex;

		CandIterator(Vertex vertex) {
			_vertex = vertex;
		}

		public boolean hasNext() {
			return _vertex != null;
		}

		public VertexI next() {
			if (_vertex == null)
				return null;
			VertexI vertex = _vertex._vertex;
			_vertex = _vertex._nextCand;
			return vertex;
		}

		@Override
		public void remove() {
		}

	}

	static private class PathIterator implements Iterator<ElementI> {
		private Vertex _vertex;
		private Edge _edge;
		private boolean _didEdge;

		PathIterator(Vertex vertex) {
			_vertex = vertex;
			_edge = null;
		}

		public boolean hasNext() {
			return _vertex != null || _edge != null;
		}

		public ElementI next() {
			if (_vertex != null) {
				VertexI vertex = _vertex._vertex;
				_edge = _vertex._prevEdge;
				_vertex = null;
				_didEdge = false;
				return vertex;
			}

			if (!_didEdge) {
				_didEdge = true;
				return _edge._edge;
			}
			_didEdge = false;
			VertexI vertex = _edge._orig._vertex;
			_edge = _edge._prevEdge;
			return vertex;
		}

		@Override
		public void remove() {
		}
	}

	private GraphI _graph;
	private PriorityQueueI<Double, Edge> _queue;
	private SingleVertexListenerI _listener;
	private PropertiesI _properties = new PropertiesAdapter();

	private int _label;
	private int _numVert;
	private int _numEdge;
	private int _pathCnt;
	private int _graphChangeCount;
	private Edge[] _edges;
	private Edge[] _reversedEdges;
	private Vertex _candHead;
	private Vertex _candTail;
	private Vertex[] _vertices;

	private int _maxLen;
	private int _maxPaths;
	private double _maxCost;
	private double _maxTime;
	private double _maxDist;
	private boolean _reverseEdges;
	private boolean _allEdgesAreDirected;

	public Connections(GraphI graph) {
		_init();
		_setGraph(graph);
		_queue = new PriorityQueue<Double, Edge>(new CompareNumberReverse());
	}

	public Connections(GraphI graph, PriorityQueueI<Double, Edge> queue) {
		_init();
		_setGraph(graph);
		_queue = queue;
		_queue.setCompare(new CompareNumberReverse());
	}

	private void _init() {
		_label = 2;
		_candHead = _candTail = null;
		_pathCnt = 0;
		_listener = null;
		_properties = null;

		_maxLen = Integer.MAX_VALUE;
		_maxCost = Double.POSITIVE_INFINITY;
		_maxTime = Double.POSITIVE_INFINITY;
		_maxDist = Double.POSITIVE_INFINITY;
		_maxPaths = Integer.MAX_VALUE;
	}

	private void _setGraph(GraphI graph) {
		_graph = graph;
		_graphChangeCount = graph.getChangeCount();

		_numVert = graph.sizeOfVertices();
		_vertices = new Vertex[_numVert];
		for (Iterator<VertexI> e = graph.vertices(); e.hasNext(); ) {
			VertexI vertex = e.next();
			_vertices[vertex.getIndex()] = new Vertex(vertex);
		}
		setCandidate(false);

		_numEdge = graph.sizeOfEdges();
		_edges = new Edge[_numEdge];
		for (Iterator<EdgeI> e = graph.edges(); e.hasNext(); ) {
			EdgeI edge = e.next();
			Vertex dest = _vertices[edge.getToVertex().getIndex()];
			Vertex orig = _vertices[edge.getFromVertex().getIndex()];
			_edges[edge.getIndex()] = new Edge(edge, orig, dest);
		}

		_allEdgesAreDirected = (graph.sizeOfDirectedEdges() == graph.sizeOfEdges());

		if (!_allEdgesAreDirected) {
			_reversedEdges = new Edge[_numEdge];
			for (Iterator<EdgeI> e = graph.edges(); e.hasNext(); ) {
				EdgeI edge = e.next();
				Vertex orig = _vertices[edge.getToVertex().getIndex()];
				Vertex dest = _vertices[edge.getFromVertex().getIndex()];
				_reversedEdges[edge.getIndex()] = new Edge(edge, orig, dest);
			}
		}

		if (_listener != null)
			_listener.initialize(graph, this);
	}


	public boolean usesConnectionProperties() {
		return true;
	}

	public void setListener(SingleVertexListenerI listener) {
		_listener = listener;
		if (_listener != null && _graph != null)
			_listener.initialize(_graph, this);
	}

	public void setProperties(PropertiesI properties) {
		_properties = properties;
	}

	public void setMaxPaths(int maxPaths) {
		_maxPaths = maxPaths;
	}

	public void setMaxLength(int maxLength) {
		_maxLen = maxLength;
	}

	public void setMaxCost(double maxCost) {
		_maxCost = maxCost;
	}

	public void setMaxTime(double maxTime) {
		_maxTime = maxTime;
	}

	public void setMaxDistance(double maxDistance) {
		_maxDist = maxDistance;
	}

	public void setCandidate(boolean isCandidate) {
		if (_graph == null)
			throw new GraphError("The graph has not been set.");
		if (_graph.getChangeCount() != _graphChangeCount)
			throw new InvalidGraphError("The graph has changed since construction, create a new algorithm.");
		for (int i = 0; i < _numVert; i++)
			_vertices[i]._isCand = isCandidate;
	}

	public void setCandidate(Object key, boolean isCandidate) throws VertexNotFoundException {

		if (_graph == null)
			throw new GraphError("The graph has not been set.");
		if (_graph.getChangeCount() != _graphChangeCount)
			throw new InvalidGraphError("The graph has changed since construction, create a new algorithm.");
		VertexI vertex = _graph.getVertex(key);
		if (vertex == null)
			throw new VertexNotFoundException();
		_vertices[vertex.getIndex()]._isCand = isCandidate;
	}

	public int generatePathsTo(Object rootKey) throws VertexNotFoundException, InvalidPropertyException {
		_reverseEdges = true;
		return _generatePaths(rootKey);
	}

	public int generatePathsFrom(Object rootKey) throws VertexNotFoundException, InvalidPropertyException {
		_reverseEdges = false;
		return _generatePaths(rootKey);
	}

	private int _generatePaths(Object key) throws VertexNotFoundException, InvalidPropertyException {
		if (_graph == null)
			throw new GraphError("The graph has not been set.");
		if (_graph.getChangeCount() != _graphChangeCount)
			throw new InvalidGraphError("The graph has changed since construction, create a new algorithm.");
		VertexI origI = _graph.getVertex(key);
		if (origI == null)
			throw new VertexNotFoundException("Can't find the origin vertex.");
		if (_listener != null)
			_listener.beginPathTree(origI, _reverseEdges);
		Vertex orig = _vertices[origI.getIndex()];
		_label += 2;
		_candHead = null;
		_queue.removeAllElements();

		orig._prevEdge = null;
		orig.setPerm(_label);

		if (!_reverseEdges || !_allEdgesAreDirected) {
			for (Iterator<EdgeI> e = orig._vertex.outEdges(); e.hasNext(); ) {
				EdgeI edgeI = e.next();
				if (_reverseEdges && edgeI.isDirected())
					continue;
				Edge edge = _edges[edgeI.getIndex()];
				edge._len = 1;
				edge._prevEdge = null;
				edge.setIsInQueue(_label);
				edge._cost = _properties.getEdgeCost(edgeI, _reverseEdges);
				edge._time = _properties.getEdgeTime(edgeI, _reverseEdges);
				edge._dist = _properties.getEdgeDistance(edgeI, _reverseEdges);
				edge._queuePair = _queue.insert(new Double(edge._cost), edge);
			}
		}

		if (_reverseEdges || !_allEdgesAreDirected) {
			for (Iterator<EdgeI> e = orig._vertex.inEdges(); e.hasNext(); ) {
				EdgeI edgeI = e.next();
				if (!_reverseEdges && edgeI.isDirected())
					continue;
				Edge edge = _reversedEdges[edgeI.getIndex()];
				edge._len = 1;
				edge._prevEdge = null;
				edge.setIsInQueue(_label);
				edge._cost = _properties.getEdgeCost(edgeI, !_reverseEdges);
				edge._time = _properties.getEdgeTime(edgeI, !_reverseEdges);
				edge._dist = _properties.getEdgeDistance(edgeI, !_reverseEdges);
				edge._queuePair = _queue.insert(new Double(edge._cost), edge);
			}
		}

		_pathCnt = 0;
		while (_queue.size() > 0 && _pathCnt < _maxPaths) {
			Edge perm = (Edge) _queue.popHead().getSecond();
			perm.setPerm(_label);

			if (!perm._dest.isPerm(_label)) {
				perm._dest.setPerm(_label);
				perm._dest._prevEdge = perm;
				if (perm._dest._isCand && perm._dest != orig) {
					perm._dest._nextCand = null;
					if (_candHead == null) {
						_candHead = perm._dest;
						_candTail = perm._dest;
					} else {
						_candTail._nextCand = perm._dest;
						_candTail = perm._dest;
					}
					_pathCnt++;
				}
			}

			if (_properties.isVertexRestricted(perm._dest._vertex))
				continue;

			if (!_reverseEdges || !_allEdgesAreDirected) {
				for (Iterator<EdgeI> e = perm._dest._vertex.outEdges(); e.hasNext(); ) {
					EdgeI edgeI = e.next();
					if (_reverseEdges && edgeI.isDirected())
						continue;
					Edge edge = _edges[edgeI.getIndex()];
					if (edge.isPerm(_label))
						continue;
					if (_properties.isEdgeRestricted(edge._edge, _reverseEdges))
						continue;
					if (!_reverseEdges && _properties.isConnectionRestricted(perm._edge, perm._dest._vertex, edgeI))
						continue;
					if (_reverseEdges && _properties.isConnectionRestricted(edgeI, perm._dest._vertex, perm._edge))
						continue;
					_updateEdge(perm, edge, _reverseEdges);
				}
			}

			if (_reverseEdges || !_allEdgesAreDirected) {
				for (Iterator<EdgeI> e = perm._dest._vertex.inEdges(); e.hasNext(); ) {
					EdgeI edgeI = e.next();
					if (!_reverseEdges && edgeI.isDirected())
						continue;
					Edge edge = _reversedEdges[edgeI.getIndex()];
					if (edge.isPerm(_label))
						continue;
					if (_properties.isEdgeRestricted(edge._edge, !_reverseEdges))
						continue;
					if (!_reverseEdges && _properties.isConnectionRestricted(perm._edge, perm._dest._vertex, edgeI))
						continue;
					if (_reverseEdges && _properties.isConnectionRestricted(edgeI, perm._dest._vertex, perm._edge))
						continue;
					_updateEdge(perm, edge, !_reverseEdges);
				}
			}
		}

		if (_listener != null)
			_listener.endPathTree(_pathCnt);
		return _pathCnt;
	}

	private final void _updateEdge(Edge perm, Edge edge, boolean reverse) {
		int len = perm._len + 1;
		if (len > _maxLen)
			return;

		double cost = perm._cost;
		cost += _properties.getEdgeCost(edge._edge, reverse);
		cost += _properties.getVertexCost(perm._dest._vertex);
		cost += _properties.getConnectionCost(perm._edge, perm._dest._vertex, edge._edge);
		double time = perm._time;
		time += _properties.getEdgeTime(edge._edge, reverse);
		time += _properties.getVertexTime(perm._dest._vertex);
		time += _properties.getConnectionTime(perm._edge, perm._dest._vertex, edge._edge);
		double dist = perm._dist;
		dist += _properties.getEdgeDistance(edge._edge, reverse);

		if (cost > _maxCost || time > _maxTime || dist > _maxDist)
			return;

		if (!edge.isInQueue(_label)) {
			edge._len = len;
			edge._cost = cost;
			edge._time = time;
			edge._dist = dist;
			edge._prevEdge = perm;
			edge.setIsInQueue(_label);
			edge._queuePair = _queue.insert(new Double(cost), edge);
			if (_listener != null)
				_listener.updateVertexCost(perm._dest._vertex, edge._edge, edge._dest._vertex, edge);
		} else if (edge._cost > cost) {
			edge._len = len;
			edge._cost = cost;
			edge._time = time;
			edge._dist = dist;
			edge._prevEdge = perm;
			try {
				_queue.changePriority(edge._queuePair, new Double(cost));
			} catch (InvalidPriorityException e) {
				throw new GraphError("The priority queue does not support change priority.");
			}
			if (_listener != null)
				_listener.updateVertexCost(perm._dest._vertex, edge._edge, edge._dest._vertex, edge);
		}
	}

	public VertexI getNearestCandidate() {
		if (_graph == null)
			throw new GraphError("The graph has not been set.");
		if (_graph.getChangeCount() != _graphChangeCount)
			throw new InvalidGraphError("The graph has changed since construction, create a new algorithm.");
		return _candHead == null ? null : _candHead._vertex;
	}

	public Iterator<VertexI> candidates() {
		if (_graph == null)
			throw new GraphError("The graph has not been set.");
		if (_graph.getChangeCount() != _graphChangeCount)
			throw new InvalidGraphError("The graph has changed since construction, create a new algorithm.");
		return new CandIterator(_candHead);
	}

	public Iterator<ElementI> pathElements(VertexI candidate) throws VertexNotFoundException {
		if (_graph == null)
			throw new GraphError("The graph has not been set.");
		if (_graph.getChangeCount() != _graphChangeCount)
			throw new InvalidGraphError("The graph has changed since construction, create a new algorithm.");
		if (candidate.getGraph() != _graph)
			throw new GraphError("The vertex is not owned by the graph");
		Vertex vertex = _vertices[candidate.getIndex()];
		if (vertex._label < _label)
			throw new VertexNotFoundException("The vertex is not in the solution.");
		return new PathIterator(vertex);
	}

	public Vector<ElementI> getPath(VertexI candidate) throws VertexNotFoundException {
		if (_graph == null)
			throw new GraphError("The graph has not been set.");
		if (_graph.getChangeCount() != _graphChangeCount)
			throw new InvalidGraphError("The graph has changed since construction, create a new algorithm.");
		if (candidate.getGraph() != _graph)
			throw new GraphError("The vertex is not owned by the graph");
		Vertex vertex = _vertices[candidate.getIndex()];
		if (vertex._label < _label)
			throw new VertexNotFoundException("The vertex is not in the solution.");
		Vector<ElementI> path = new Vector<ElementI>(vertex._prevEdge._len * 2 + 1);
		path.addElement(vertex._vertex);
		Edge edge = vertex._prevEdge;
		while (edge != null) {
			path.addElement(edge._edge);
			path.addElement(edge._orig._vertex);
			edge = edge._prevEdge;
		}
		if (!_reverseEdges) {
			int h = 0;
			int t = path.size() - 1;
			while (t > h) {
				ElementI tmp = path.elementAt(h);
				path.setElementAt(path.elementAt(t), h);
				path.setElementAt(tmp, t);
			}
			h++;
			t--;
		}
		return path;
	}

	public EdgeValueI getEdgeValue(VertexI candidate) throws VertexNotFoundException {
		if (_graph == null)
			throw new GraphError("The graph has not been set.");
		if (_graph.getChangeCount() != _graphChangeCount)
			throw new InvalidGraphError("The graph has changed since construction, create a new algorithm.");
		if (candidate.getGraph() != _graph)
			throw new GraphError("The vertex is not owned by the graph");
		Vertex vertex = _vertices[candidate.getIndex()];
		if (vertex._label < _label)
			return null;
		return vertex._prevEdge;
	}

	public String toString() {
		if (_graph == null)
			return "Dijkstra: The graph is not set\n";
		String ret = "";
		ret += "------------------------------------\n";
		ret += "Connections: " + _graph.sizeOfVertices() + " vertices, " + _graph.sizeOfEdges() + " edges\n";
		ret += "------------------------------------\n";
		for (Iterator<VertexI> e = _graph.vertices(); e.hasNext(); )
			ret += _vertices[((VertexI) e.next()).getIndex()] + "\n";
		ret += "\n";
		return ret;
	}
}
