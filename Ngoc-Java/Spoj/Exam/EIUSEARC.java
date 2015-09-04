import java.util.Arrays;
import java.util.Scanner;

class EIUSEARC {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		Person[] persons = new Person[N];
		sc.nextLine();
		for (int i = 0; i < N; i++) {
			persons[i] = new Person(sc.nextLine());
		}
		Arrays.sort(persons);

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < N; i++) {
			result.append(persons[i].toString() + "\n");
		}

		for (int i = 0; i < M; i++) {
			int index = Arrays.binarySearch(persons, new Person(sc.nextLine()));
			if (index >= 0) {
				index++;
			}
			else {
				index = -1;
			}
			// result.append(index + "\r\n");
		}
		System.out.println(result.toString());
	}

	static class Person implements Comparable<Person> {
		public String name = "";
		public String middlename = "";
		public String lastname = "";

		public Person(String fullname) {
			String[] names = fullname.split(" ");
			name = names[names.length - 1];
			if (names.length > 1) {
				lastname = names[0];
			}
			for (int i = 1; i < names.length - 1; i++) {
				if (middlename != "") {
					middlename += " ";
				}
				middlename += names[i];
			}
		}

		@Override
		public int compareTo(Person to) {
			int cmp = name.compareTo(to.name);
			if (cmp != 0) {
				return cmp;
			}
			cmp = lastname.compareTo(to.lastname);
			if (cmp != 0) {
				return cmp;
			}
			return middlename.compareTo(to.middlename);
		}

		@Override
		public String toString() {
			String result = name;
			if (middlename != "") {
				result = middlename + " " + name;
			}
			if (lastname != "") {
				result = lastname + " " + result;
			}
			return result;
		}
	}
}
