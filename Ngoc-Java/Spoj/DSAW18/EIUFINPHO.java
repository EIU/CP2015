import java.util.*;
import java.io.*;

class EIUFINPHO {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Long[] phoneNumbers = new Long[] { 11L, 12L, 13L, 15L, 30L, 39L, 40L, 42L, 45L, 52L, 57L, 58L, 59L, 63L, 66L,
				68L, 68L, 73L, 75L, 76L, 77L, 78L, 81L, 83L, 84L, 86L, 89L, 90L };

		Arrays.sort(phoneNumbers, (n1, n2) -> n2.compareTo(n1));
		int i = Arrays.binarySearch(phoneNumbers, 24L, (n1, n2) -> n2.compareTo(n1));
		System.out.println(i);

		/*
		 * for (int i = 0; i < n; i++) { phoneNumbers[i] = sc.nextLong(); }
		 * 
		 * Arrays.sort(phoneNumbers);
		 * 
		 * long min = 100000000, max = 9999999999l; StringBuilder outBuff = new
		 * StringBuilder(); for (int i = 0; i < n; i++) { if ((i == 0 || phoneNumbers[i]
		 * != phoneNumbers[i - 1]) && min <= phoneNumbers[i] && phoneNumbers[i] <= max)
		 * { outBuff.append(phoneNumbers[i] + "\n"); } }
		 * 
		 * System.out.println(outBuff); //
		 */
	}

}
