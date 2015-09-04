import java.util.Scanner;

public class EIUSESOR {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String dataType = sc.next();
		if (dataType == "double" || true) {
			Double[] array = new Double[N];

			insertionSort(array);
		}
		// Your code here
	}

	static <T extends Number> void insertionSort(T[] array) {
		// Your code here
	}
}
