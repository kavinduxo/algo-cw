/*
 * Name  : D. W. K. S. Gunathilake.
 * UoW ID: w1761405
 * IIT ID: 2019822
 *
 * */

package com.company;

import java.util.*;

public class Vertex {
    private final int i;
    private final int j;
    private final Map<Vertex, Integer> adjacentVertices;
    private List<Vertex> shortestPath;
    private int distance;

    public Vertex(int i, int j) {
        this.i = i;
        this.j = j;
        distance = Integer.MAX_VALUE;
        adjacentVertices = new HashMap<>();
        shortestPath = new LinkedList<>();
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Map<Vertex, Integer> getAdjacentVertices() {
        return adjacentVertices;
    }

    public void addAdjacentVertex(Vertex adjVertex, int distance) {
        this.adjacentVertices.put(adjVertex, distance);
    }

    public List<Vertex> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Vertex> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

}
