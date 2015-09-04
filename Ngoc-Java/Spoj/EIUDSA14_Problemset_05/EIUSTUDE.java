import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class EIUSTUDE {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Person[] persons = new Person[N];
		sc.nextLine();
		for (int i = 0; i < N; i++) {
			persons[i] = new Person(sc.nextLine());
		}
		Arrays.sort(persons, personComparator);
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < N; i++) {
			result.append(persons[i].toString() + "\r\n");
		}
		System.out.println(result.toString());
	}

	static final Comparator<Person> personComparator = new Comparator<Person>() {
		@Override
		public int compare(final Person s, final Person t) {
			int cmp = s.name.compareTo(t.name);
			if (cmp != 0) {
				return cmp;
			}
			cmp = s.lastname.compareTo(t.lastname);
			if (cmp != 0) {
				return cmp;
			}
			return s.middlename.compareTo(t.middlename);

		}
	};

	static class Person {
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
