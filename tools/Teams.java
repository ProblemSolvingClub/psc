import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Teams {

	class Team implements Comparable<Team> {
		String name, user, password;
		int div;

		public Team(String name, String user, String password, int div) {
			super();
			this.name = name;
			this.user = user;
			this.password = password;
			this.div = div;
		}

		@Override
		public int compareTo(Team o) {
			if (div == o.div)
				return name.compareTo(o.name);
			return div - o.div;
		}

		public String toString() {
			return name + "\t" + user + "\t" + password;
		}
	}

	private String divisionStr = "";

	public Teams(String divisionStr) {
		this.divisionStr = divisionStr;
	}

	private void work() throws IOException {
		Scanner sc = new Scanner(new FileReader("teams" + divisionStr + ".txt"));
		List<Team> teams = new ArrayList<Team>();
		while (sc.hasNextLine()) {
			String line = sc.nextLine().trim();
			if (line.length() == 0)
				continue;
			String[] spl = line.split("[;]", -1);
			String user = spl[0];
			if (user.length() != 7)
				continue;
			String pass = spl[1];
			String name = spl[3];
			// System.err.println(user);
			int teamNumber = Integer.parseInt(user.substring(4, 7));
			/*if (teamNumber < 200) {
				int div = 1;
				if ((teamNumber >= 140 && teamNumber < 160) || teamNumber > 164)
					div = 2;
				teams.add(new Team(name, user, pass, div));
			}*/
			teams.add(new Team(name, user, pass, 1));
		}
		sc.close();

		PrintWriter pw = new PrintWriter(new FileWriter("labels" + divisionStr
				+ ".tsv"));
		Collections.sort(teams);
		for (Team t : teams)
			pw.println(t);
		pw.close();
	}

	public static void main(String[] args) throws IOException {
		new Teams("").work();
	}

}
