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
		
		Cell randCell = maze.cells[randGen.nextInt(w)][randGen.nextInt(h)];
		
		
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
		if(UnvisitedNeighbors.size() >= 1) {
			//C1. select one at random.
			Cell direction = UnvisitedNeighbors.get(randDirection.nextInt(UnvisitedNeighbors.size()));
			//C2. push it to the stack
			uncheckedCells.push(direction);
			//C3. remove the wall between the two cells
			removeWalls(direction, currentCell);
			//C4. make the new cell the current cell and mark it as visited
			currentCell = direction;
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
			
		//D. if all neighbors are visited
		if(UnvisitedNeighbors.size() == 0) {
			//D1. if the stack is not empty
			if(uncheckedCells.size() >= 1) {
				// D1a. pop a cell from the stack
				Cell newCell = uncheckedCells.pop();
				// D1b. make that the current cell
				currentCell = newCell;
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		// c1 c2
		if (c2.getX() == c1.getX() + 1) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		}
		
		// c2 c1
		if (c1.getX() == c2.getX() + 1) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		}
		
		// c2 
		// c1
		if (c1.getY() == c2.getY() + 1) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}
		
		// c1 
		// c2
		if (c2.getY() == c1.getY() + 1) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		int cellX = c.getX();
		int cellY = c.getY();
		
		ArrayList<Cell> UnvisitedNeighbors = new ArrayList<Cell>();
		
		if(cellX < 4 && !maze.cells[cellX + 1][cellY].hasBeenVisited()) {
			UnvisitedNeighbors.add(maze.cells[cellX + 1][cellY]);
		}
		if(cellX > 0 && !maze.cells[cellX - 1][cellY].hasBeenVisited()) {
			UnvisitedNeighbors.add(maze.cells[cellX - 1][cellY]);
		}
		if(cellY < 4 && !maze.cells[cellX][cellY + 1].hasBeenVisited()) {
			UnvisitedNeighbors.add(maze.cells[cellX][cellY + 1]);
		}
		if(cellY > 0 && !maze.cells[cellX][cellY - 1].hasBeenVisited()) {
			UnvisitedNeighbors.add(maze.cells[cellX][cellY - 1]);
		}
		
		return UnvisitedNeighbors;
	}
}
