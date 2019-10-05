import java.io.*;
import java.util.*;

public class Gen_Giaohangtoiuu {

	public static void main(String[] args) throws Exception {
		int i = 101;
		int n = 10;
		gen(i++, n, 7, 1, 1000, 1, 20);
		gen(i++, n, 7, 1, 1000, 1, 20);
		gen(i++, n, 7, 1, 1000, 1, 20);
		gen(i++, n, 7, 1, 1000, 1, 20);
		gen(i++, n, 7, 1, 1000, 1, 20);
		gen(i++, n, 7, 1, 1000, 1, 20);
		gen(i++, n, 7, 1, 1000, 1, 20);
		gen(i++, n, 7, 1, 1000, 1, 20);
		gen(i++, n, 7, 1, 1000, 1, 20);

		n = 100;
		gen(i++, n, 101, 1, 1000, 1, 200);
		gen(i++, n, 101, 1, 1000, 1, 200);
		gen(i++, n, 101, 1, 1000, 1, 200);

		n = 10000;
		int k = 10007;
		int mostRight = 1000000;
		gen(i++, n, k, 1, mostRight, 1, 2 * k);

		List<Long> locations = genPowArray(1, 1000000, 1, n);
		List<Long> products = genPowArray(1, 10 * k, 1, n);
		gen(i++, n, k, locations, products);

		//
		n = 10000;
		locations = genPowArray(1, 150000, 1.53, n);
		products = genPowArray(1, 150000, 1.53, n);
		gen(i++, n, k, locations, products);

		n = 10000;
		locations = genPowArray(1, 150000, 1.53, n);
		products = genPowArray(1, 150000, 1.53, n);
		gen(i++, n, k, locations, products);

		n = 50000;
		locations = genPowArray(1, 150000, 1.53, n);
		products = genPowArray(1, 150000, 1.53, n);
		gen(i++, n, k, locations, products);

		//
		n = 10000;
		locations = genPowArray(1, 100000, 1.7, (n + 1) / 2);
		locations.addAll(genPowArray(1, 100000, 1.7, (n + 1) / 2));
		products = genPowArray(1, 100000, 1.7, n);
		products.addAll(genPowArray(1, 100000, 1.7, (n + 1) / 2));
		gen(i++, n, k, locations, products);

		n = 10000;
		locations = genPowArray(1, 100000, 1.7, (n + 1) / 2);
		locations.addAll(genPowArray(1, 100000, 1.7, (n + 1) / 2));
		products = genPowArray(1, 100000, 1.7, n);
		products.addAll(genPowArray(1, 100000, 1.7, (n + 1) / 2));
		gen(i++, n, k, locations, products);

		n = 50000;
		locations = genPowArray(1, 100000, 1.7, (n + 1) / 2);
		locations.addAll(genPowArray(1, 100000, 1.7, (n + 1) / 2));
		products = genPowArray(1, 100000, 1.7, n);
		products.addAll(genPowArray(1, 100000, 1.7, (n + 1) / 2));
		gen(i++, n, k, locations, products);

		//
		n = 10000;
		locations = genPowArray(1, 10000, 1.7, n - 1);
		locations.add(1000000000L);
		products = genPowArray(1, 10000, 1.7, n - 1);
		products.add(1000000000L);
		gen(i++, n, k, locations, products);

		n = 50000;
		locations = genPowArray(1, 10000, 1.7, n - 1);
		locations.add(1000000000L);
		products = genPowArray(1, 10000, 1.7, n - 1);
		products.add(1000000000L);
		gen(i++, n, k, locations, products);

		//
		n = 10000;
		locations = genPowArray(1, 10000, 1.7, n - 1);
		locations.add(1000000000L);
		products = genPowArray(1, 10000, 1.7, n - 1);
		products.add(1000000000L);
		complement(products, 1000000001);
		gen(i++, n, k, locations, products);

		n = 50000;
		locations = genPowArray(1, 10000, 1.7, n - 1);
		locations.add(1000000000L);
		complement(locations, 1000000001);
		products = genPowArray(1, 10000, 1.7, n - 1);
		products.add(1000000000L);
		gen(i++, n, k, locations, products);

		//
		n = 10000;
		locations = genPowArray(1, 10000, 1.7, n - 1);
		locations.add(1000000000L);
		complement(locations, 1000000001);
		products = genPowArray(1, 10000, 1.7, n - 1);
		products.add(1000000000L);
		complement(products, 1000000001);
		gen(i++, n, k, locations, products);

		n = 50000;
		locations = genPowArray(1, 10000, 1.7, n - 1);
		locations.add(1000000000L);
		complement(locations, 1000000001);
		products = genPowArray(1, 10000, 1.7, n - 1);
		products.add(1000000000L);
		complement(products, 1000000001);
		gen(i++, n, k, locations, products);

		//
		n = 10000;
		locations = genPowArray(1, 150000, 1.53, n);
		products = genPowArray(1, 150000, 1.53, n);
		complement(products, 1000000001);
		gen(i++, n, k, locations, products);

		n = 50000;
		locations = genPowArray(1, 150000, 1.53, n);
		products = genPowArray(1, 150000, 1.53, n);
		complement(products, 1000000001);
		gen(i++, n, k, locations, products);

		//
		n = 10000;
		locations = genPowArray(1, 150000, 1.53, n);
		complement(locations, 1000000001);
		products = genPowArray(1, 150000, 1.53, n);
		gen(i++, n, k, locations, products);

		n = 50000;
		locations = genPowArray(1, 150000, 1.53, n);
		complement(locations, 1000000001);
		products = genPowArray(1, 150000, 1.53, n);
		gen(i++, n, k, locations, products);

		//
		n = 10000;
		locations = genPowArray(1, 150000, 1.53, n);
		complement(locations, 1000000001);
		products = genPowArray(1, 150000, 1.53, n);
		complement(products, 1000000001);
		gen(i++, n, k, locations, products);

		n = 50000;
		locations = genPowArray(1, 150000, 1.53, n);
		complement(locations, 1000000001);
		products = genPowArray(1, 150000, 1.53, n);
		complement(products, 1000000001);
		gen(i++, n, k, locations, products);

		//
		k = 15959;
		locations = genPowArray(1, 150000, 1.53, n);
		products = genPowArray(1, 150000, 1.53, n);
		gen(i++, n, k, locations, products);

		//
		n = 10000;
		locations = genPowArray(1, 100000, 1.7, (n + 1) / 2);
		locations.addAll(genPowArray(1, 100000, 1.7, (n + 1) / 2));
		products = genPowArray(1, 100000, 1.7, n);
		products.addAll(genPowArray(1, 100000, 1.7, (n + 1) / 2));
		gen(i++, n, k, locations, products);

		//
		n = 100000;
		locations = genPowArray(1, 10000, 1.7, n - 1);
		locations.add(1000000000L);
		products = genPowArray(1, 10000, 1.7, n - 1);
		products.add(1000000000L);
		gen(i++, n, k, locations, products);

		//
		n = 500;
		locations = genPowArray(1, 5000, 1, n - 1);
		locations.add(1000000000L);
		products = genPowArray(1, 5000, 1, n - 1);
		products.add(1000000000L);
		gen(i++, n, k, locations, products);

		//
		n = 500;
		locations = genPowArray(1, 5000, 1, n - 1);
		locations.add(1000000000L);
		complement(locations, 1000000001);
		products = genPowArray(1, 5000, 1, n - 1);
		products.add(1000000000L);
		complement(products, 1000000001);
		gen(i++, n, k, locations, products);

		//
		n = 100000;
		locations = genPowArray(1, 150000, 1.53, n);
		products = genPowArray(1, 150000, 1.53, n);
		complement(products, 1000000001);
		gen(i++, n, k, locations, products);

		//
		n = 50000;
		locations = genPowArray(1, 150000, 1.53, n);
		complement(locations, 1000000001);
		products = genPowArray(1, 150000, 1.53, n);
		gen(i++, n, k, locations, products);

		//
		n = 10000;
		locations = genPowArray(1, 150000, 1.53, n);
		complement(locations, 1000000001);
		products = genPowArray(1, 150000, 1.53, n);
		complement(products, 1000000001);
		gen(i++, n, k, locations, products);
	}

	public static String randString(int minLen, int maxLen) {
		int len = randBetween(minLen, maxLen);
		char[] chars = new char[len];
		for (int i = 0; i < len; i++) {
			chars[i] = (char) randBetween('a', 'z' + 1);
		}
		return new String(chars);
	}

	public static int randBetween(int start, int end) {
		if (start == end) {
			end++;
		}
		return start + (int) Math.floor(Math.random() * (end - start));
	}

	static public void shuffle(List<Long> list) {
		for (int i = 1; i < list.size(); i++) {
			int j = randBetween(0, i);
			long temp = list.get(j);
			list.set(j, list.get(i));
			list.set(i, temp);
		}
	}

	static public List<Long> genPowArray(int start, int end, double power, int n) {
		List<Long> seeds = new ArrayList<Long>();
		while (seeds.size() < n) {
			for (int i = start; i < end || i < start + n; i++) {
				seeds.add((long) Math.pow(i, power));
			}
		}
		shuffle(seeds);
		return seeds.subList(0, n);
	}

	static public List<Long> reverse(List<Long> list) {
		Collections.reverse(list);
		return list;
	}

	static public List<Long> complement(List<Long> list, long complement) {
		for (int i = 0; i < list.size(); i++) {
			list.set(i, complement - list.get(i));
		}
		return list;
	}

	static public List<Long> add(List<Long> list, long value) {
		list.add(value);
		return list;
	}

	public static void gen(int id, int n, int k, List<Long> locations, List<Long> products) throws IOException {
		String inFilename = id + ".in";
		FileWriter in = new FileWriter(inFilename);
		StringBuffer inBuffer = new StringBuffer();

		inBuffer.append(n + " " + k + "\n");
		for (int i = 0; i < n; i++) {
			inBuffer.append(locations.get(i) + " " + products.get(i) + "\n");
		}

		in.write(inBuffer.toString());
		in.close();

	}

	public static void gen(int id, int n, int k, int leftMost, int rightMost, int lowestProduct, int highestProduct)
			throws IOException {
		String inFilename = id + ".in";
		FileWriter in = new FileWriter(inFilename);
		StringBuffer inBuffer = new StringBuffer();

		inBuffer.append(n + " " + k + "\n");
		for (int i = 0; i < n; i++) {
			inBuffer.append(randBetween(leftMost, rightMost) + " " + randBetween(lowestProduct, highestProduct) + "\n");
		}

		in.write(inBuffer.toString());
		in.close();
	}
}
