package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		
		Cell randCell = maze.cells[randGen.nextInt()][randGen.nextInt()];
		
		
		//5. call selectNextPath method with the randomly selected cell
		
		selectNextPath(randCell);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		Random randDirection = new Random();
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> UnvisitedNeighbors = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(UnvisitedNeighbors.size() <= 1) {
			//C1. select one at random.
			Cell direction = UnvisitedNeighbors.get(randDirection.nextInt(4) + 1);
			//C2. push it to the stack
			uncheckedCells.push(direction);
			//C3. remove the wall between the two cells

			//C4. make the new cell the current cell and mark it as visited
		
			//C5. call the selectNextPath method with the current cell
		}
			
		//D. if all neighbors are visited
		
			//D1. if the stack is not empty
			
				// D1a. pop a cell from the stack
		
				// D1b. make that the current cell
		
				// D1c. call the selectNextPath method with the current cell
				
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		int cellX = c.getX();
		int cellY = c.getY();
		
		ArrayList<Cell> UnvisitedNeighbors = new ArrayList<Cell>();
		
		if(!maze.cells[cellX + 1][cellY].hasBeenVisited()) {
			UnvisitedNeighbors.add(maze.cells[cellX + 1][cellY]);
		}
		if(!maze.cells[cellX - 1][cellY].hasBeenVisited()) {
			UnvisitedNeighbors.add(maze.cells[cellX - 1][cellY]);
		}
		if(!maze.cells[cellX][cellY + 1].hasBeenVisited()) {
			UnvisitedNeighbors.add(maze.cells[cellX][cellY + 1]);
		}
		if(!maze.cells[cellX][cellY - 1].hasBeenVisited()) {
			UnvisitedNeighbors.add(maze.cells[cellX][cellY - 1]);
		}
		
		return UnvisitedNeighbors;
	}
}
