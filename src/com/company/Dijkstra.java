/*
 * Name  : D. W. K. S. Gunathilake.
 * UoW ID: w1761405
 * IIT ID: 2019822
 *
 * */

package com.company;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

    public static void calculateShortestPathFromSource(Graph graph, Vertex source) {

        source.setDistance(0);
        Set<Vertex> visitedVertices = new HashSet<>();
        Set<Vertex> unVisitedVertices = new HashSet<>();

        unVisitedVertices.add(source);

        while (unVisitedVertices.size() != 0) {
            Vertex fromVertex = getLowestDistanceVertex(unVisitedVertices, graph);

            unVisitedVertices.remove(fromVertex);

            for (Map.Entry<Vertex, Integer> adjacencyPair: fromVertex.getAdjacentVertices().entrySet()) {
                Vertex adjacentVertex = graph.getVertexByPos(adjacencyPair.getKey().getI(), adjacencyPair.getKey().getJ());

                int distance = adjacencyPair.getValue();
                if (!visitedVertices.contains(adjacentVertex)) {
                    calculateMinimumDistance(adjacentVertex, distance, fromVertex);
                    unVisitedVertices.add(adjacentVertex);
                }
            }
            visitedVertices.add(fromVertex);
        }

        graph.setVertices(visitedVertices);

    }

    private static Vertex getLowestDistanceVertex(Set<Vertex> unvisitedVertices, Graph graph) {
        Vertex lowestDistanceVertex = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Vertex vertex : unvisitedVertices) {
            int vertexDistance = vertex.getDistance();
            if (vertexDistance < lowestDistance) {
                lowestDistance = vertexDistance;
                lowestDistanceVertex = graph.getVertexByPos(vertex.getI(), vertex.getJ());
            }
        }
        return lowestDistanceVertex;
    }

    private static void calculateMinimumDistance(Vertex toVertex, int distance, Vertex fromVertex) {
        int currentDistance = fromVertex.getDistance();
        //calculate new suggest distance for toVertex
        int targetDistance = currentDistance + distance;
        if (targetDistance < toVertex.getDistance()) {
            //appoint new suggest distance as shortest distance
            toVertex.setDistance(currentDistance + distance);
            LinkedList<Vertex> shortestPath = new LinkedList<>(fromVertex.getShortestPath());
            shortestPath.add(fromVertex);
            toVertex.setShortestPath(shortestPath);
        }
    }
}
