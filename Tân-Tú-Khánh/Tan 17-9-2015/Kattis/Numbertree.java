import java.util.Scanner;

//https://open.kattis.com/problems/numbertree
public class Main {

	static String s = "";
	
	static long minus;
	public static void main(String [] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		minus = (long) Math.pow(2, n+1);
		long k = minus-1;
		
		if(sc.hasNext()){
			s = sc.next()+" ";
		} else{
			s = " ";
		}
		
		solve (0, k);
		sc.close();
		
	}
	static void solve(int com, long pos){
		long res = pos;
		pos =pos - (minus - pos);
		if(s.charAt(com) == 'L'){
			
			solve(com+1,pos);
		}else{
			if (s.charAt(com) == 'R'){
				solve(com+1,pos-1);
			} else{
				System.out.println(res);;
			}
		}
		
	}
}
