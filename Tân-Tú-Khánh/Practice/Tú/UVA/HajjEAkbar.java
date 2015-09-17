package uva;

import java.util.Scanner;

public class HajjEAkbar {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String a;
		int num = 1;
		while(true){
			a = scan.next();
			if(a.equals("*")){
				break;
			}
			else if(a.equalsIgnoreCase("Hajj")){
				System.out.println("Case " + num + ": Hajj-e-Akbar");
			}
			else{
				System.out.println("Case " + num + ": Hajj-e-Asghar");
			}
			num++;
		}
		scan.close();
	}

}
