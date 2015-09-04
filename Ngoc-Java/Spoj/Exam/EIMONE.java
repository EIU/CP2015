import java.util.Scanner;

class EIMONE {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int b = sc.nextInt();
		if (b > 20) {
			System.out.println("20 " + b / 20);
			b = b % 20;
		}
		if (b > 10) {
			System.out.println("10 " + b / 10);
			b = b % 10;
		}
		if (b > 5) {
			System.out.println("5 " + b / 5);
			b = b % 5;
		}
		if (b > 1) {
			System.out.println("1 " + b);
		}
	}

}
