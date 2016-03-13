// Generic floating point checker, for UofC Programming Contest Control Centre
// Expects output file to have single lines containing floating point values
// Compares them with absolute or relative error
// Note: If the program output has more lines than the judge output, the extra lines are ignored
//
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class EpsChecker {

	private void check(String[] args) throws IOException {
		Scanner programOut = new Scanner(new BufferedReader(new FileReader(
				args[0])));
		Scanner judgeOut = new Scanner(new BufferedReader(new FileReader(
				args[1])));
		Scanner judgeIn = new Scanner(new BufferedReader(
				new FileReader(args[2])));

		if (!programOut.hasNext()) {
			WA("No answer");
		}
		while (judgeOut.hasNextLine()) {
			if (!programOut.hasNextLine()) WA("Expected line");
			String judgeLine = judgeOut.nextLine().trim();
			String contLine = programOut.nextLine().trim();
			compareDoubles(judgeLine, contLine);
		}

		System.err.println("OK!");
		System.exit(0);
	}

	private void compareDoubles(String a, String b) {
		try {
			double x = Double.valueOf(a);
			double y = Double.valueOf(b);
			if (Math.abs(x - y) > 1e-6) {
				if (x == 0.0 || Math.abs(1 - y / x) > 1e-6)
					WA("Numbers differ: judge=" + x + ", contestant=" + y);
			}
		} catch (RuntimeException e) {
			WA("Expected a number, got " + a + " and " + b);
		}
	}

	private void WA(String msg) {
		System.err.println(msg);
		System.err.close();
		System.exit(-1);
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.err
					.println("Usage: java EpsChecker <file_out> <judge_out> <judge_in>");
			System.exit(-1);
		}
		new EpsChecker().check(args);
	}

}
