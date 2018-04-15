import java.util.Arrays;

public class RightHand {
	private static boolean maze[][];
	private static final int width=10, height=10;
	private static int x, y, direction;

	// for direction: 0 <-> down, 1<->left, 2<->up, 3<->right

	public static void main(String[] args) {
		x=1;
		y=0;
		direction=0;
		maze=Maze.generateMaze(width, height);

		while(!(x==width-1&&y==height-2)) {
			System.out.println("x: " + x + ", y: " + y + ", direction: " + direction + "\n");
			Maze.draw(x, y, maze, null);
			try { Thread.sleep(200); } catch(Exception e) { }
			walk();
			if(x==1&&y==0)
				System.exit(1);
		}
		Maze.draw(x, y, maze, null);
		System.out.println("x: " + x + ", y: " + y + ", direction: " + direction + "\n");
		System.out.println("fin\n");
	}

	private static void walk() {
		switch(direction) {
		case 0:
			if(maze[x-1][y]==false) { //free space
				direction=1;
				x--;
			} else if(maze[x][y+1]==true) { //blocked space
				direction=3;
			} else {
				y++;
			}
			break;
		case 1:
			if(maze[x][y-1]==false) {
				direction=2;
				y--;
			} else if(maze[x-1][y]==true) {
				direction=0;
			} else {
				x--;
			}
			break;
		case 2:
			if(maze[x+1][y]==false) {
				direction=3;
				x++;
			} else if(maze[x][y-1]==true) {
				direction=1;
			} else {
				y--;
			}
			break;
		case 3:
			if(maze[x][y+1]==false) {
				direction=0;
				y++;
			} else if(maze[x+1][y]==true) {
				direction=2;
			} else {
				x++;
			}
			break;
		}
	}
}
