import java.io.*;
import java.util.*;

public class mafija {

	public static void main(String[] args) {
		int N = reader.nextInt();
		Person[] persons = new Person[N + 1];
		for (int i = 1; i <= N; i++) {
			persons[i] = new Person();
		}

		for (int i = 1; i <= N; i++) {
			int accuseTo = reader.nextInt();
			persons[i].accuseTo = persons[accuseTo];
			persons[accuseTo].nBeAccused++;
		}

		// Reduce to rings or single nodes
		Queue<Person> queue = new ArrayDeque<Person>();
		for (int i = 1; i <= N; i++) {
			if (persons[i].nBeAccused == 0) {
				persons[i].selectedMaxValue = 1;
				queue.add(persons[i]);
			}
		}
		while (!queue.isEmpty()) {
			Person person = queue.poll();
			if (person != null) {
				Person beAccusedPerson = person.accuseTo;
				beAccusedPerson.selectedMaxValue += person.unselectedMaxValue;
				beAccusedPerson.unselectedMaxValue += Math.max(person.selectedMaxValue, person.unselectedMaxValue);
				beAccusedPerson.nBeAccused--;
				if (beAccusedPerson.nBeAccused == 0) {
					queue.add(beAccusedPerson);
					beAccusedPerson.selectedMaxValue++;
				}
				person.selectedMaxValue = 0;
				person.unselectedMaxValue = 0;
			}
		}
		// End reduce

		int total = 0;

		// Process rings
		for (int i = 1; i <= N; i++) {
			Person person = persons[i];
			if (person.nBeAccused == 1) {
				int ss = Math.max(person.selectedMaxValue + 1, person.unselectedMaxValue), su = 0;
				int us = 0, uu = person.unselectedMaxValue;
				person.nBeAccused = 0;

				Person next = person.accuseTo;
				while (next.accuseTo != person) {
					int temp = su;
					su = Math.max(su, ss) + next.unselectedMaxValue;
					ss = temp + next.selectedMaxValue + 1;

					temp = uu;
					uu = Math.max(uu, us) + next.unselectedMaxValue;
					us = temp + next.selectedMaxValue + 1;
					next.nBeAccused = 0;
					next = next.accuseTo;
				}
				next.nBeAccused = 0;

				total += Math.max(Math.max(ss + next.unselectedMaxValue, su + next.unselectedMaxValue),
						Math.max(us + next.unselectedMaxValue, uu + next.selectedMaxValue + 1));
			}
		}
		System.out.println(total);
	}

	static class Person {
		public Person accuseTo;
		public int nBeAccused;
		public int selectedMaxValue = 0;
		public int unselectedMaxValue = 0;
	}

	static FastInputReader reader = new FastInputReader(System.in);

	static class FastInputReader {
		byte[] inbuf = new byte[1 << 25];
		int lenbuf = 0, ptrbuf = 0;
		InputStream is;

		public FastInputReader(InputStream stream) {
			is = stream;
		}

		int readByte() {
			if (lenbuf == -1)
				throw new InputMismatchException();
			if (ptrbuf >= lenbuf) {
				ptrbuf = 0;
				try {
					lenbuf = is.read(inbuf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (lenbuf <= 0)
					return -1;
			}
			return inbuf[ptrbuf++];
		}

		public boolean hasNext() {
			return ptrbuf + 3 < lenbuf;
		}

		boolean isSpaceChar(int c) {
			return !(c >= 33 && c <= 126);
		}

		int skip() {
			int b;
			while ((b = readByte()) != -1 && isSpaceChar(b))
				;
			return b;
		}

		public String next() {
			int b = skip();
			StringBuilder sb = new StringBuilder();
			while (!(isSpaceChar(b))) {
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}

		public char[] next(int n) {
			char[] buf = new char[n];
			int b = skip(), p = 0;
			while (p < n && !(isSpaceChar(b))) {
				buf[p++] = (char) b;
				b = readByte();
			}
			return n == p ? buf : Arrays.copyOf(buf, p);
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		char nextChar() {
			return (char) skip();
		}

		public int nextInt() {
			int num = 0, b;
			boolean minus = false;
			while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
				;
			if (b == '-') {
				minus = true;
				b = readByte();
			}

			while (true) {
				if (b >= '0' && b <= '9') {
					num = num * 10 + (b - '0');
				} else {
					return minus ? -num : num;
				}
				b = readByte();
			}
		}

		long nextLong() {
			long num = 0;
			int b;
			boolean minus = false;
			while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
				;
			if (b == '-') {
				minus = true;
				b = readByte();
			}

			while (true) {
				if (b >= '0' && b <= '9') {
					num = num * 10 + (b - '0');
				} else {
					return minus ? -num : num;
				}
				b = readByte();
			}
		}
	}
}
