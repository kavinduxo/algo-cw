/*
 * Name  : D. W. K. S. Gunathilake.
 * UoW ID: w1761405
 * IIT ID: 2019822
 *
 * */

package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Graph graph = new Graph();
    static char[][] map;
    static boolean[][] unvisited;
    static long startTime;
    static long endTime;

    public static void main(String[] args) {

        String filePath = "examples_2/puzzle_20.txt";
        map = readTheFile(filePath);
        assert map != null;
        unvisited = new boolean[map.length][map[0].length];
        for(int i = 0; i < map.length; i++) {
            Arrays.fill(unvisited[i], true);
        }

        //defining source and destination
        int[] sourcePos = searchPosition(map, 'S');
        int[] destinationPos = searchPosition(map, 'F');


        visitMap(sourcePos[0], sourcePos[1], map, unvisited, graph);

        Vertex startingVertex = graph.getVertexByPos(sourcePos[0], sourcePos[1]);
        Vertex finishingVertex = graph.getVertexByPos(destinationPos[0], destinationPos[1]);

        startTime = currentTimeMillis();
        Dijkstra.calculateShortestPathFromSource(graph, startingVertex);
        endTime = currentTimeMillis();

        graph.printShortestPath(finishingVertex);

        double timeDiff = (endTime - startTime) / 1000.0;

//        System.out.println(endTime - startTime);
        System.out.println("Time considered: " + timeDiff + " (s)");

    }

    public static char[][] readTheFile(String filePath)  {

        File file = new File(filePath);
        Scanner reader;
        try {
            reader = new Scanner(file);
            int lineCount = (int) Files.lines(Paths.get(filePath)).count();

            char[][] map = new char[lineCount][];

            for (int i = 0; i < lineCount; i++) {
                char[] row = reader.nextLine().toCharArray();
                map[i] = row;
            }

            reader.close();
            return map;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int[] searchPosition(char[][] map, char position) {
        for(int j = 0; j < map.length; j++) {
            for(int i = 0; i < map[0].length; i++) {
                if(map[j][i] == position)
                    return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

    public static void visitMap(int i, int j, char[][] map, boolean[][] unvisited, Graph graph) {
        if(!unvisited[j][i])
            return;

        Vertex vertex = new Vertex(i, j);
        graph.addVertex(vertex);

        if(map[j][i] != 'F')
                unvisited[j][i] = false;
        else
            //if it's the finishing point map stay unvisited since we need to find the best path
            return ;

        // Move Right
        for(int r = i + 1; r < map.length; r++) {
            if(map[j][r] == 'F') {
                moveUtil(r, map, unvisited, graph, vertex, j, i - r);
                break;
            } else if(map[j][r] == '0') {
                if(r - 1 != i) {
                    moveUtil(r - 1, map, unvisited, graph, vertex, j, i - r + 1);
                }
                break;
            } else if(r == map.length - 1) {
                moveUtil(r, map, unvisited, graph, vertex, j, i - r);
                break;
            }
        }

        // check left
        for(int l = i - 1; l >= 0; l--) {
            if(map[j][l] == 'F') {
                moveUtil(l, map, unvisited, graph, vertex, j, i - l);
                break;
            } else if(map[j][l] == '0') {
                if(l + 1 != i) {
                    vertex.addAdjacentVertex(new Vertex(l + 1, j), i - l - 1);
                    visitMap(l + 1, j, map, unvisited, graph);
                }
                break;
            } else if(l == 0) {
                moveUtil(l, map, unvisited, graph, vertex, j, i - l);
                break;
            }
        }

        // Move Up
        for(int u = j - 1; u >= 0; u--) {
            if(map[u][i] == 'F') {
                moveUtil(i, map, unvisited, graph, vertex, u, j - u);
                break;
            } else if(map[u][i] == '0') {
                if(u + 1 != j) {
                    vertex.addAdjacentVertex(new Vertex(i, u + 1), j - u - 1);
                    visitMap(i, u + 1, map, unvisited, graph);
                }
                break;
            } else if(u == 0) {
                moveUtil(i, map, unvisited, graph, vertex, u, j - u);
                break;
            }
        }

        // Move Down
        for(int d = j + 1; d < map.length; d++) {
            if(map[d][i] == 'F') {
                moveUtil(i, map, unvisited, graph, vertex, d, j - d);
                break;
            } else if(map[d][i] == '0') {
                if(d - 1 != j) {
                    moveUtil(i, map, unvisited, graph, vertex, d - 1, j - d + 1);
                }
                break;
            } else if(d == map.length - 1) {
                moveUtil(i, map, unvisited, graph, vertex, d, j - d);
                break;
            }
        }
    }

    //Utility method for add nodes to adjacency list and make this recursive
    private static void moveUtil(int i, char[][] map, boolean[][] unvisited, Graph graph, Vertex vertex, int u, int i2) {
        vertex.addAdjacentVertex(new Vertex(i, u), i2);
        visitMap(i, u, map, unvisited, graph);
    }

    public static long nanoTime() {
        return System.nanoTime();
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

}
