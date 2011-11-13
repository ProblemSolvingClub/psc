import java.util.*;

public class C {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		in.nextLine();
		for (int i = 0; i < cases; i++) {
			String name = in.nextLine();
			NameGame game = new NameGame(name);
			System.out.println(NumMoves(game));
		}
	}

	public static int NumMoves(NameGame game) {
		int min = Integer.MAX_VALUE;
		for (int left = 0; left < game.numChars; left++) {
			int moves = 0;
			int queued = 0;
			CharPos curr = game.screen.start;
			int right = game.numChars - left-1;
			for (int move = 0; move < left; move++) {
				queued++;	
				curr = curr.prev;
				if (curr.needsToBeChanged)
				{
					moves += queued;
					queued = 0;
				}
			}
			queued = left;
			curr = game.screen.start;
			for (int move = 0; move < right; move++) {
				queued++;	
				curr = curr.next;
				if (curr.needsToBeChanged)
				{
					moves += queued;
					queued = 0;
				}
			}
			if (moves < min)
				min = moves;
		}
		for (int right = 0; right < game.numChars; right++) {
			int moves = 0;
			int queued = 0;
			CharPos curr = game.screen.start;
			int left = game.numChars - right-1;
			for (int move = 0; move < right; move++) {
				queued++;	
				curr = curr.next;
				if (curr.needsToBeChanged)
				{
					moves += queued;
					queued = 0;
				}
			}
			queued = right;
			curr = game.screen.start;
			for (int move = 0; move < left; move++) {
				queued++;	
				curr = curr.prev;
				if (curr.needsToBeChanged)
				{
					moves += queued;
					queued = 0;
				}
			}
			if (moves < min)
				min = moves;
		}
		return min + game.vertChange;
	}

	private static class NameGame {
		public int numChars;
		public int vertChange;
		public Cycle screen;
		public NameGame(String name) {
			this.numChars = name.length();
			ArrayList<Boolean> needToChange = new ArrayList<Boolean>();
			for (int i = 0; i < name.length(); i++) {
				char letter = name.charAt(i);
				int change = this.Dist(letter);
				if (change == 0)
					needToChange.add(false);
				else
					needToChange.add(true);
				this.vertChange += change;
			}
			this.screen = new Cycle(needToChange);
		}
		public int Dist(char letter) {
			String alphaStart = "ABCDEFGHIJKLM";
			String alphaEnd = "ZYXWVUTSRQPON";
			int idx = alphaStart.indexOf(letter);
			if (idx == -1)
				return alphaEnd.indexOf(letter)+1;
			else
				return idx;
		}
		public void Display() {
			CharPos root = this.screen.start;
			CharPos nxt = root.next;
			System.out.println(root.needsToBeChanged);
			while(nxt != root) {
				System.out.println(nxt.needsToBeChanged);
				nxt = nxt.next;
			}
		}
	}

	private static class Cycle {
		public CharPos start;
		public Cycle(ArrayList<Boolean> needToChange) {
			this.start = new CharPos(needToChange.get(0));
			CharPos pos = this.start;
			for (int i = 1; i < needToChange.size(); i++) {
				CharPos nextPos = new CharPos(needToChange.get(i));
				pos.next = nextPos;
				nextPos.prev = pos;
				pos = nextPos;
			}
			pos.next = this.start;
			this.start.prev = pos;
		}
	}

	private static class CharPos {
		public Boolean needsToBeChanged;
		public CharPos next;
		public CharPos prev;
		public CharPos(Boolean needsToBeChanged) {
			this.needsToBeChanged = needsToBeChanged;	
		}
	}
}
