/*
 * Name  : D. W. K. S. Gunathilake.
 * UoW ID: w1761405
 * IIT ID: 2019822
 *
 * */

package com.company;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {

    private Set<Vertex> vertices = new HashSet<>();

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(Set<Vertex> vertices) {
        this.vertices = vertices;
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public Vertex getVertexByPos(int i, int j) {
        for (Vertex vt : vertices) {
            if (vt.getI() == i && vt.getJ() == j)
                return vt;
        }
        return null;
    }

    //creating the Output for shortest path
    public void printShortestPath(Vertex destinationPos) {
        List<Vertex> path = destinationPos.getShortestPath();
        int iterator;
        int x;
        int y;
        path.add(destinationPos);


        for(int i = 0; i < path.size(); i++) {
            Vertex currentVertex = path.get(i);
            if(i == 0) {
                System.out.println("1. Start at (" + (currentVertex.getI() + 1) + ", " + (currentVertex.getJ() + 1) + ")");
                continue;
            }

            iterator = i +1;
            Vertex previousVertex = path.get(i - 1);
            String direction = "";

            if(currentVertex.getJ() - previousVertex.getJ() > 0)
                direction = "down";
            else if(currentVertex.getJ() - previousVertex.getJ() < 0)
                direction = "up";
            else if(currentVertex.getI() - previousVertex.getI() > 0)
                direction = "right";
            else if(currentVertex.getI() - previousVertex.getI() < 0)
                direction = "left";

            //Since array is 0th index need to add 1 in order to get the correct position in Map
            y = currentVertex.getJ() + 1;
            x = currentVertex.getI() + 1;

            System.out.println(iterator + ". Move " + direction + " to (" + x + ", " + y + ")");
        }
        System.out.println((path.size() + 1) + ". Done!");
    }

}
