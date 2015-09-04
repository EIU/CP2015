import java.util.Scanner;

class TestE {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long k = sc.nextLong();
		long t=0;
		if (k<n) {
			long a = n*(n+1)-n-k*(k + 1);
			long b = (long)Math.ceil((double)a / k);
			t = (b+2*k);
			
		}
		if(k >= n){
			t = 2*n -1;
			
		}
		System.out.println(t+" ");

	}

}
