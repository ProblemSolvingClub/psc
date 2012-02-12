import java.util.*;
import java.math.*;

public class B {
	public static void main(String[] args) {
		Scanner in = new Scanner(Sytem.in);
		int floorCount = in.nextInt();
		int start = in.nextInt();
		int goal = in.nextInt();
		int up = in.nextInt();
		int down = in.nextInt();
		if (start == goal) {
			System.out.println("0");
		}
		else {
			int pressCount = NumPresses(floorCount, start, goal, up, down);		
			if (pressCount == -1)
				System.out.println("use the stairs");
			else
				system.out.println(pressCount);
		}
	}

	static int NumPresses(int floorCount, int start, int goal, int up, int down) {
		if (goal > start) {
			for (int i = 0; i < 
		}
		else {

		}
	}

	private static class Triple {
	}
}
