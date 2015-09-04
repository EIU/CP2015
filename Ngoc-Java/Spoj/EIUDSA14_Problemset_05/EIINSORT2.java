import java.util.Arrays;
import java.util.Scanner;

class EIINSORT2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Employee[] employees = new Employee[N];
		sc.nextLine();
		for (int i = 0; i < N; i++) {
			employees[i] = new Employee(i, sc.nextInt(), sc.nextInt());
		}

		Arrays.sort(employees);

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < N; i++) {
			result.append(employees[i].toString() + "\r\n");
		}
		System.out.println(result.toString());
	}

	static class Employee implements Comparable<Employee> {
		public int order;
		public int id;
		public int sale;

		public Employee(int order, int id, int sale) {
			this.order = order;
			this.id = id;
			this.sale = sale;
		}

		@Override
		public int compareTo(Employee to) {
			if (sale != to.sale) {
				return sale - to.sale;
			}
			return order - to.order;
		}

		@Override
		public String toString() {
			return id + " " + sale;
		}
	}
}
