/*
 * (C) Copyright 2018-2018, by Dimitrios Michail and Contributors.
 *
 * JGraphT : a free Java graph-theory library
 *
 * This program and the accompanying materials are dual-licensed under
 * either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation, or (at your option) any
 * later version.
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */
package org.jgrapht.graph.guava;

import java.io.Serializable;
import java.util.function.ToDoubleFunction;

import org.jgrapht.Graph;

import com.google.common.graph.EndpointPair;
import com.google.common.graph.ImmutableValueGraph;
import com.google.common.graph.MutableValueGraph;

/**
 * A graph adapter class using Guava's {@link MutableValueGraph} specialized with double values.
 *
 * <p>
 * Each edge in {@link ImmutableValueGraph} is associated with a double value which is mapped to the
 * edge weight in the resulting {@link Graph}. Thus, the graph is weighted and calling methods
 * {@link #getEdgeWeight(Object)} and {@link #setEdgeWeight(EndpointPair, double)} will get and set
 * the value of an edge.
 *
 * @author Dimitrios Michail
 *
 * @param <V> the graph vertex type
 */
public class MutableDoubleValueGraphAdapter<V>
    extends MutableValueGraphAdapter<V, Double>
{
    private static final long serialVersionUID = -6335845255406679994L;

    /**
     * Create a new adapter.
     * 
     * @param valueGraph the value graph
     */
    public MutableDoubleValueGraphAdapter(MutableValueGraph<V, Double> valueGraph)
    {
        super(
            valueGraph, Graph.DEFAULT_EDGE_WEIGHT,
            (ToDoubleFunction<Double> & Serializable) x -> x);
    }

    @Override
    public void setEdgeWeight(EndpointPair<V> e, double weight)
    {
        if (e == null) {
            throw new NullPointerException();
        }
        if (!containsEdge(e)) {
            throw new IllegalArgumentException("no such edge in graph: " + e.toString());
        }
        super.valueGraph.putEdgeValue(e.nodeU(), e.nodeV(), weight);
    }

}
