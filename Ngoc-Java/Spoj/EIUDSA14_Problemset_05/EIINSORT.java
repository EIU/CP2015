import java.util.Arrays;
import java.util.Scanner;

class EIINSORT {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Employee[] employees = new Employee[N];
		sc.nextLine();
		for (int i = 0; i < N; i++) {
			employees[i] = new Employee(sc.nextInt(), sc.nextInt());
		}

		insertionSort(employees);

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < N; i++) {
			result.append(employees[i].toString() + "\r\n");
		}
		System.out.println(result.toString());
	}

	static void insertionSort(Employee[] employees) {
		int len = employees.length;
		for (int i = 1; i < len; i++) {
			Employee current = employees[i];
			int j = i - 1;
			while (j >= 0 && employees[j].compareTo(current) > 0) {
				employees[j + 1] = employees[j];
				j--;
			}
			employees[j + 1] = current;
		}
	}

	static class Employee implements Comparable<Employee> {
		public int id;
		public int sale;

		public Employee(int id, int sale) {
			this.id = id;
			this.sale = sale;
		}

		@Override
		public int compareTo(Employee to) {
			return sale - to.sale;
		}

		@Override
		public String toString() {
			return id + " " + sale;
		}
	}
}
