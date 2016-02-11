import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * A program to solve a 2D maze created by a 2D array of strings.The entry point
 * to the maze is always the Row 0 Col 0. The end point is array index where "E"
 * is located. Places marked with "#" are obstacles within the maze and should not
 * be visited.
 * 
 * { " ", "#","#"," "}
 * { " ", " "," ","#"}
 * { " "," ", "#"," "}
 * { " ", "E","#"," "}
 * 
 * This program uses a breadth first search. It keeps of the places it has visited
 * in a queue of nodes. Each node has an x coordinate, y coordinate, and parent node reference.
 * It dequeues one element at a time from the queue and adds all of the neighbors of the parent
 * to the queue so they can be explored as well.
 * 
 * Each dequed item is added to a list as well. This keeps track of all the elements that have 
 * been visited in the array. Using this list, all the possible paths to the end of the maze
 * are traced and saved. The shortest path is calculated using the saved paths.
 *
 * @author Sahithi Bonala
 * @version 1
 *
 */

public class MyMazeSolver {
    private String[][] maze;
    private int row;
    private int col;
    
   /**
    * Create a maze solver for a grid.
    * @param maze the input maze the program aims to solve.
    */
    
    public MyMazeSolver(String[][] maze) {
	this.maze = maze;
	this.row = maze.length;
	this.col = maze[0].length;
    }

    //Creation of node objects that have references to the neighboring or "parent" node.
    private static class Node {
	int x;
	int y;
	Node parent;
	public Node(int x, int y, Node parent) {
	    this.x = x;
	    this.y = y;
	    this.parent = parent;
	}
	public Node getParent(Node x) {
	    return parent;
	}

	//@Overide
	//public boolean equals(Node o) {
	//    Checks for equality of nodes. Two nodes are equal if they both
	//    contain same x and y coordinates and they also have same the same
	//    parent reference.
	//}
    }

    //Creation of the LinkedList Queue that will be used to keep track of nodes that need to be visited.
    public static Queue<Node> q = new LinkedList<Node>();

    //Creation of a ArrayList that will keep track of dequed elements.
    public static ArrayList<Node> path = new ArrayList<>();

   /**
    * Perform a breadth-first search beginning at the node passed in and adds 
    * all the steps taken to HashSet path. Visited nodes are marked with an * 
    * to avoid backtracking. 
    *
    * @param x the row where the path should start.
    * @param y the column where the path should start.
    * @return the destination node.
    */
    public Node solver(int x, int y) {
	//enqueue the starting point of the maze to the queue.
	q.add(new Node(x,y,null));

	//for each element in the queue, vist the element in the array based on whether it is open or not, and also based
	//on whether is has been visited or not.
	while(!q.isEmpty()) {
	    Node p = q.remove();
	    path.add(p); //add removed items to path
	    if (maze[p.x][p.y] == "E") {
		return p;
	    }
	    if (p.x + 1 >= 0 && p.x + 1 < row && p.y >= 0 && p.y < col && (maze[p.x + 1][p.y].equals(" ") || maze[p.x+1][p.y].equals("E"))) {
		maze[p.x][p.y] = "*";
		Node next = new Node(p.x+1,p.y,p);
		q.add(next);
	    }

	    if (p.x-1 >= 0 && p.x - 1 < row && p.y >= 0 && p.y < col && (maze[p.x - 1][p.y].equals(" ") || maze[p.x - 1][p.y].equals("E"))) {
		maze[p.x][p.y] = "*";
		Node next = new Node(p.x-1,p.y,p);
		q.add(next);
	    }

	    if (p.x >= 0 && p.x < row && p.y - 1 >= 0 && p.y - 1 < col && (maze[p.x][p.y-1].equals(" ") || maze[p.x][p.y - 1].equals("E"))) {
		maze[p.x][p.y] = "*";
		Node next = new Node(p.x, p.y - 1, p);
		q.add(next);
	    }

	    if (p.x >= 0 && p.x < row && p.y + 1 >= 0 && p.y + 1 < col && (maze[p.x][p.y+1].equals(" ") || maze[p.x][p.y + 1].equals("E"))) {
		maze[p.x][p.y] = "*";
		Node next = new Node(p.x,p.y + 1,p);
		q.add(next);
	    }
	    
	}

	return null;	
    }
    
    //l = a list that will hold all possible paths from the starting point.
    
    //public void allPaths() {
    //  if (path.length() == 0) {
    //     System.out.println("You have an empty map.");
    //  } else {
    //	    recurse(path,ind,currPath);
    //	}
    //}
    
    //public void recurse(ArrayList<Node> p,int ind, List l) {
	//if (position in current node == position of END)
	//   Add the node to the current list of nodes
	//   return;
	//if (no node in list has current node as parent)
	//   return;
	//if (one node in the list has the current node as a parent)
	//    add the current node to the on going list.
	//    recurse(with node that has current node as parent)
	//if (two nodes in the list have the current node as a parent)
	//    add the current node to the on-going path list.
	//    recurse with each node.
	//}

    //public list shortestPath()
    //    for(each list in listOfPaths)
    //    Loop through listOfPaths and find the list with least nodes in it.

        
   /**
    * Retrieve the number of columns in the 2D array.
    *
    * @return col columns in the 2D array.
    */
    
    public int getCol() {
	return col;
    }

   /**
    * Retrieve the number of rows in the 2D array.
    *
    * @return row rows in the 2D array.
    */
    
    public int getRow() {
	return row;
    }

   /**
    * Prints the maze by traversing it.
    */
    
    public void printMyMaze() {
	for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
    
}
