import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int id = 1;
		while (sc.hasNext()) {
			int item = sc.nextInt();
			int proposal = sc.nextInt();
			sc.nextLine();

			if (item == 0) {
				break;
			}

			for (int i = 0; i < item; i++) {
				sc.nextLine();
			}

			String bestProposal = "";
			int maxCompiliance = Integer.MIN_VALUE;
			double minPrice = Double.MAX_VALUE;

			for (int i = 0; i < proposal; i++) {
				String name = sc.nextLine();
				double price = sc.nextDouble();
				int proItem = sc.nextInt();
				sc.nextLine();

				for (int j = 0; j < proItem; j++) {
					sc.nextLine();
				}

				if (proItem > maxCompiliance || (proItem == maxCompiliance && price < minPrice)) {
					bestProposal = name;
					maxCompiliance = proItem;
					minPrice = price;
				}
			}

			if(id > 1){
				System.out.println();
			}
			System.out.println("RFP #" + id);
			System.out.println(bestProposal);

			id++;
		}
	}
}
