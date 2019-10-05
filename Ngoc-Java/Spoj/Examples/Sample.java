import java.util.*;

public class Sample {

	public static void main(String[] args) {
		long s = System.currentTimeMillis();
		
		Scanner sc = new Scanner(System.in);
		int n = 10000;

		long total = 0;
		for (int i = 0; i < n - 5; i++) {
			total += sc.nextLong();
			System.out.println(total);
		}
		
		//System.out.println(System.currentTimeMillis() - s + "ms");
	}

}
