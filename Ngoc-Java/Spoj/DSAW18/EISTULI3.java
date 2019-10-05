import java.util.*;
import java.io.*;

class EISTULI3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();

		ArrayList<Student> students = new ArrayList<Student>();
		for (int i = 0; i < n; i++) {
			Student student = new Student(sc.next(), sc.next());
			students.add(student);
			int nSubject = sc.nextInt();
			for (int j = 0; j < nSubject; j++) {
				int grade = sc.nextInt();
				student.addGrade(grade);
			}
		}

		Collections.sort(students);
		while (k > 0 && k < n && students.get(k).compareTo(students.get(k - 1)) == 0) {
			k--;
		}

		StringBuilder outBf = new StringBuilder();
		for (int i = 0; i < k; i++) {
			outBf.append(students.get(i) + "\n");
		}
		System.out.println(outBf);
	}

	static class Student implements Comparable<Student> {
		public String code;
		public String name;
		public int nSubject;
		public int totalGrade;

		public Student(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public void addGrade(int grade) {
			if (grade >= 50) {
				totalGrade += grade;
				nSubject++;
			}
		}

		public double getAverage() {
			return nSubject == 0 ? 0 : (double) totalGrade / nSubject;
		}

		@Override
		public int compareTo(Student other) {
			int cmd = Double.compare(other.getAverage(), getAverage());
			return cmd == 0 ? other.nSubject - nSubject : cmd;
		}

		@Override
		public String toString() {
			return code + " " + name + " " + Math.round(getAverage()) + " " + (nSubject * 4);
		}
	}

}
