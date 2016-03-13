import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class GenerateLogins {
	class Team {
		String name, login, pass;
		Set<String> teamMembers, emails;
		boolean official;

		Team(String name, boolean official) {
			this.name = name;
			this.official = official;
			teamMembers = new TreeSet<String>();
			emails = new TreeSet<String>();
		}
	}

	private String divisionStr = "";

	public GenerateLogins(String divisionStr) {
		this.divisionStr = divisionStr;
	}

	private void work() throws IOException {
		String[] fileNames = { "ccpc2016.csv" };
		Map<String, Team> teams = readTeams(fileNames);
		generateLogins(teams);

		writeLoginEmails(teams);
		writeTeamsFile(teams);
	}

	private void writeTeamsFile(Map<String, Team> teams) throws IOException {
		Map<String, Team> sortedTeams = new TreeMap<String, Team>();
		sortedTeams.putAll(teams);
		PrintWriter pw = new PrintWriter(new FileWriter("teams" + divisionStr
				+ ".txt"));
		for (Team t : sortedTeams.values()) {
			String teamMembers = flatten(t.teamMembers);
			pw.println(String.format("%s;%s;%d;%s;%s", t.login, t.pass,
					t.official ? 1 : -1, t.name, teamMembers));
		}
		pw.close();
	}

	private String flatten(Set<String> teamMembers) {
		String ret = "";
		for (String m : teamMembers) {
			if (ret.length() > 0)
				ret += ", ";
			ret += m;
		}
		return ret;
	}

	private void writeLoginEmails(Map<String, Team> teams) throws IOException {
		Map<String, Team> sortedTeams = new TreeMap<String, Team>();
		sortedTeams.putAll(teams);
		PrintWriter pw = new PrintWriter(new FileWriter("logins_emails"
				+ divisionStr + ".csv"));
		for (Team t : sortedTeams.values()) {
			for (String email : t.emails)
				pw.println(String.format("%s, %s, %s, %s", t.name, email,
						t.login, t.pass));
		}
		pw.close();
	}

	private void generateLogins(Map<String, Team> teams) {
		int n1 = 400, n2 = 500;
		for (Team t : teams.values()) {
			if (t.official)
				t.login = "team" + n1++;
			else
				t.login = "team" + n2++;
			t.pass = getPassword(7);
		}
		for (int i = 0; i < 15; i++) {
			String login = "team" + n1++;
			Team t = new Team(login, true);
			t.login = login;
			t.pass = getPassword(7);
			teams.put(login, t);

			login = "team" + n2++;
			t = new Team(login, false);
			t.login = login;
			t.pass = getPassword(7);
			teams.put(login, t);
		}
	}

	private String blah = "qwetyupasdfghjkzxcvb23456789";
	private Random rnd;

	private String getPassword(int length) {
		if (rnd == null)
			rnd = new Random();
		StringBuilder sb = new StringBuilder();
		while (length-- > 0)
			sb.append(blah.charAt(rnd.nextInt(blah.length())));
		return sb.toString();
	}

	private Map<String, Team> readTeams(String[] fileNames)
			throws FileNotFoundException, IOException {
		Map<String, Team> teams = new HashMap<String, Team>();
		for (String fileName : fileNames) {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.length() == 0)
					break;
				String[] spl = line.split("[,]");
				String firstName = spl[2];
				String lastName = spl[3];
				String email = spl[5];
				String teamName = spl[7];
				boolean official = spl[9].equals("University of Calgary");

				if (!teams.containsKey(teamName)) {
					teams.put(teamName, new Team(teamName, official));
				}
				teams.get(teamName).teamMembers.add(firstName + " " + lastName);
				teams.get(teamName).emails.add(email);
			}
			br.close();
		}
		return teams;
	}

	public static void main(String[] args) throws IOException {
		new GenerateLogins("").work();
	}

}
