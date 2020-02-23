import java.util.*;
public class ConnectFour {
	public static void main(String args[]) {
		char[][] inputArray = {
			{' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' '},
		};
		DrawBoard(inputArray);
		char[][] newArray = inputArray;
		boolean done = false;
		int turn = 1;
		while (!done) {
			if (turn > (inputArray.length * inputArray[0].length)) {
				System.out.print("Draw! Neither player won.");
				break;
			}
			try {
				newArray = DropDisk(turn, inputArray);
				DrawBoard(newArray);
				done = isConnectFour(turn, newArray);
				if (done) {
					if (turn % 2 == 0)
						System.out.print("The yellow player won!");
					else 
						System.out.print("The red player won!");
					break;
				}
				inputArray = newArray;
				turn += 1;
			} catch (IndexOutOfBoundsException ex) {
				System.out.println("Invalid input, entered integer is not from 0 - 6. Try again.");
			} catch (InputMismatchException ex) {
				System.out.println("Invalid input, enter an integer from 0 - 6.");
			}
		}
	}

	public static void DrawBoard(char[][] inputArray) {
		for (int i = 0; i < inputArray.length; i++) {
			for (int j = 0; j < inputArray[0].length; j++) {
				System.out.print("|" + (char)(inputArray[i][j]));
				if (j == inputArray[0].length - 1) {
					System.out.print('|');
				}
			}
			System.out.println();	
		}
		System.out.println("...............");
	}
	public static char[][] DropDisk(int turn, char[][] inputArray){
		
		if (turn % 2 == 0) {
			System.out.println("Drop a yellow disk at column (0 - 6): ");
		} else {
			System.out.println("Drop a red disk at column (0 - 6): ");
		}
		Scanner scan = new Scanner(System.in);
		int column = scan.nextInt();
		if (inputArray[0][column] != ' ') {
			System.out.println("Column already full, try again");
			DropDisk(turn, inputArray);
		}
		char[][] newArray = inputArray;
		for (int i = newArray.length - 1; i >= 0; i--) {
			if (newArray[i][column]== ' ') {
				if (turn % 2 == 0)
					newArray[i][column] = 'Y';
				else 
					newArray[i][column] = 'R';
				break;
			}
		}
		return newArray;
	}
	public static boolean isConnectFour(int turn, char[][] inputArray) {
		boolean connectFour = false;
		char color;
		if (turn % 2 == 0)
			color = 'Y';
		else 
			color = 'R';
		for (int i = 0; i < inputArray.length; i++) {
			for (int j = 0; j < inputArray[0].length - 3; j++) {
				if (inputArray[i][j] == color && inputArray[i][j + 1] == color && inputArray[i][j + 2] == color && inputArray[i][j + 3] == color)
					connectFour = true;
			}
		}
		for (int j = 0; j < inputArray[0].length; j++) {
			for (int i = 0; i < inputArray.length - 3; i++) {
				if (inputArray[i][j] == color && inputArray[i + 1][j] == color 
					&& inputArray[i + 2][j] == color && inputArray[i + 3][j] == color)
					connectFour = true;
			}
		}
		for (int i = 0; i < inputArray.length - 3; i++) {
			for (int j = 0; j < inputArray[0].length - 3; j++) {
				if (inputArray[i][j] == color && inputArray[i + 1][j + 1] == color 
					&& inputArray[i + 2][j + 2] == color && inputArray[i + 3][j + 3] == color)
					connectFour = true;
			}
		}	
		for (int i = 0; i < inputArray.length - 3; i++) {
			for (int j = inputArray[0].length - 1; j > 3; j--) {
				if (inputArray[i][j] == color && inputArray[i + 1][j - 1] == color 
					&& inputArray[i + 2][j - 2] == color && inputArray[i + 3][j - 3] == color)
					connectFour = true;
			}
		}	
		return connectFour;
	}
	
}


