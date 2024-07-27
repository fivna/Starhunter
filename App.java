import java.util.Scanner;
public class App {
    public static char[][] generateGrid(int numRows, int numCols){
        char[][] grid = new char[numRows][numCols];
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                grid[i][j] = '○';
            }
        }
        return (grid);
    }
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    public static void main(String[] args) {
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.print("Number of Rows: ");
        int numRows = input.nextInt();
        System.out.print("Number of Columns: ");
        int numCols = input.nextInt();
        System.out.print("Number of Walls: ");
        int numWalls = input.nextInt();
        clearScreen();
        int points = 0;
        int numMoves = 0;
        while (numWalls > numRows * numCols){
            System.out.print("Invalid number. Input a new number: ");
            numWalls = input.nextInt();
        }
        char[][] grid = generateGrid(numRows, numCols);
        char command;
        int numWallsPlaced = 0;
        while(numWallsPlaced != numWalls){
            int wallRow = (int)(Math.random()*numRows);
            int wallCol = (int)(Math.random()*numCols);
            if (grid[wallRow][wallCol] == '○'){
                grid[wallRow][wallCol] = '☐';
                numWallsPlaced++;
            }
        }
        int xRow = (int)(numRows/2.0-0.5);
        int xCol = (int)(numCols/2.0-0.5);
        int pointBlocks = 0;
            int pointBlocksNeeded = (int)(numRows * numCols * 0.2);
        while (points != 25 && numMoves < 61){
            while (pointBlocks != pointBlocksNeeded){
                int pointBlockRow = (int)(Math.random()*numRows);
                int pointBlockCol = (int)(Math.random()*numCols);
                if (grid[pointBlockRow][pointBlockCol] == '○'){
                   grid[pointBlockRow][pointBlockCol] = '★';
                  pointBlocks++;
                }
            }
            System.out.println("Points: " + points);
            System.out.println("Moves: " + numMoves);
            grid[xRow][xCol] = '●';
            for (int i = 0; i < grid.length; i++){
                for (int j = 0; j < grid[0].length; j++){
                    System.out.print(grid[i][j]);
                }
                System.out.println();
            }
            command = input.next().charAt(0);
            clearScreen();
            if (command == 'w'){
                numMoves++;
                if (xRow>0 && grid[xRow-1][xCol] != '☐'){
                    grid[xRow][xCol] = '○';
                    if (grid[xRow-1][xCol] == '★'){
                        points++; 
                        pointBlocks--;
                     }
                     xRow--;
                }
            }
            else if (command == 'a'){
                numMoves++;
                if (xCol>0 && grid[xRow][xCol-1] != '☐'){
                    grid[xRow][xCol] = '○';
                    if (grid[xRow][xCol-1] == '★'){
                        points++;
                        pointBlocks--;
                    }
                    xCol--;
                }
            }
            else if (command == 's'){
                numMoves++;
                if (xRow<numRows-1 && grid[xRow+1][xCol] != '☐'){
                    grid[xRow][xCol] = '○';
                    if (grid[xRow+1][xCol] == '★'){
                        points++;
                        pointBlocks--;
                    }
                    xRow++;
                }
            }
            else if (command == 'd'){
                numMoves++;
                if (xCol<numCols-1 && grid[xRow][xCol+1] != '☐'){
                    grid[xRow][xCol] = '○';
                    if (grid[xRow][xCol+1] == '★'){
                        points++;
                        pointBlocks--;
                    }
                    xCol++;
                }
            }
            else{
                System.out.println("Not a valid command.");
            }
        }
        if (points == 25){
            System.out.print("Congratulations! You scored 25 points and won.");
        }
        else{
            System.out.print("Sory, you lost. Your number of moves exceeded the maximum limit of 60.");
        }
    }
}
