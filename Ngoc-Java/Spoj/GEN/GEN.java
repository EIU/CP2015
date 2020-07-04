import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GEN {

	public static void main(String[] args) throws Exception {

		// gen_EIUPREIU(0, 2);
		// gen_EIUPREIU(1, 4);
		// gen_EIUPREIU(2, 6);

		// for (int i = 3; i < 10; i++) {
		// gen_EIUPREIU(i, 2 * randBetween(1, 500));
		// }

		// gen_EIUPREIU(10, 1000);
		// gen_EIUPREIU(11, 1000);

		// gen_MTAMSAO(0, 1000000, 99999999);
		// gen_MTAMSAO(1, 1000000, 1000000);
		// gen_MTAMSAO(10, 10, 10);
		// gen_MTAMSAO(11, 10, 10);
		// gen_MTAMSAO(12, 10, 10);
		// gen_MTAMSAO(13, 10, 10);
		// gen_MTAMSAO(14, 20, 20);
		// gen_MTAMSAO(15, 20, 20);
		// gen_MTAMSAO(16, 20, 20);
		// gen_MTAMSAO(17, 50, 50);
		// gen_MTAMSAO(18, 50, 50);
		// gen_MTAMSAO(19, 100, 100);
		// gen_MTAMSAO(20, 100, 100);

		// gen_EIUAPPEA(9, 100000, 1000000, 1000000 + 100000);
		// gen_EIUAPPEA(10, 100000, 1000000, 1000000 + 100000);
		// gen_EIUAPPEA(11, 6, 1, 4);

		// String[] names1 = { "Hung", "Tien", "Toan", "Tuan" };
		// String[] names2 = { "Ngoc", "Ha", "Minh", "Hanh", "Tu", "Nguyen",
		// "Duy", "Phuc", "Quan",
		// "Son", "Toan", "Phi", "Khanh", "Hung", "Hau", "Tien", "Toan",
		// "Thinh",
		// "Tuan" };
		// String[] names3 = new String[1000];
		// for (int i = 0; i < names3.length; i++) {
		// names3[i] = randString(1, 20);
		// }
		// gen_EIUFREQU(0, names1, 10);
		// gen_EIUFREQU(1, names1, 3);
		//
		// gen_EIUFREQU(2, names2, 5);
		// gen_EIUFREQU(3, names2, 50);
		// gen_EIUFREQU(4, names2, 500);
		// gen_EIUFREQU(11, names2, 100000);
		// gen_EIUFREQU(12, names2, 100000);
		//
		// gen_EIUFREQU(5, names3, 10);
		// gen_EIUFREQU(6, names3, 100);
		// gen_EIUFREQU(7, names3, 1000);
		// gen_EIUFREQU(8, names3, 10000);
		// gen_EIUFREQU(9, names3, 100000);
		// gen_EIUFREQU(10, names3, 100000);

		// gen_KharkivH(1, 500, 500);
//		gen_EIPAIR(4, 10, 100000, 3, 200000);
//		gen_EIPAIR(5, 10, 100000, 2, 200000);
//		gen_EIPAIR(6, 10, 100000, 2000000000, 0);
//		gen_EIPAIR(7, 10, 100000, 2, 100000);
//		gen_EIPAIR(8, 10, 100000, 2000000000, 0);
//		gen_EIPAIR(9, 10, 100000, 2, 1000000);
		gen_EIUONCE(1, 10, 50, 40, 0);
		gen_EIUONCE(2, 10, 5000, 1000, 0);
		gen_EIUONCE(3, 10, 5000, 2000, 0);
		gen_EIUONCE(4, 10, 5000, 2500, 0);
		gen_EIUONCE(5, 10, 50000, 30000, 0);
		gen_EIUONCE(6, 10, 50000, 25000, 1000000000);
		gen_EIUONCE(7, 5, 50000, 25000, 1000000000);
		gen_EIUONCE(8, 10, 100000, 50000, 0);
		gen_EIUONCE(9, 5, 100000, 50000, 0);
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
		return start + (int) Math.floor(Math.random() * (end - start - 0.000001));
	}

	public static void gen_KharkivH(int id, int N, int M) throws IOException {
		String root = "F:\\GitHub\\Programming2015\\Private\\Ngoc\\Codeforces\\";
		String basePath = root + "";
		String classPath = root + "bin\\";
		String javaClass = "PA_AGrantiveOfScience";

		String outPath = basePath + javaClass + "\\";
		File theDir = new File(outPath);
		theDir.mkdir();
		String inFilename = outPath + id + ".in";
		String outFilename = outPath + id + ".out";
		FileWriter in = new FileWriter(inFilename);

		StringBuffer inBuffer = new StringBuffer();
		inBuffer.append(N + " " + M + "\r\n");
		for (int i = 0; i < N; i++) {
			int Ni = 500;
			inBuffer.append(Ni + " ");
			for (int j = 0; j < Ni; j++) {
				inBuffer.append(randBetween(900, 999) + " ");
			}
			inBuffer.append("\r\n");
		}

		in.write(inBuffer.toString() + "\r\n");
		in.close();

		String command = "java -cp " + classPath + " " + javaClass + " < " + inFilename + " > " + outFilename;
		System.out.println(command);
	}

	public static void gen_EIUFREQU(int id, String[] names, int n) throws Exception {
		String root = "D:\\GitHub\\Programming2015\\Private\\Ngoc\\Spoj\\";
		String basePath = root + "EIUDSA14_Problemset_05\\";
		String classPath = root + "bin\\";
		String javaClass = "EIUFREQU";

		String outPath = basePath + javaClass + "\\";
		File theDir = new File(outPath);
		theDir.mkdir();
		String inFilename = outPath + id + ".in";
		String outFilename = outPath + id + ".out";
		FileWriter in = new FileWriter(inFilename);

		StringBuffer inBuffer = new StringBuffer();
		inBuffer.append(n + "\r\n");
		for (int i = 0; i < n; i++) {
			int index = randBetween(0, names.length);
			inBuffer.append(names[index] + " ");
		}

		in.write(inBuffer.toString() + "\r\n");
		in.close();

		String command = "java -cp " + classPath + " " + javaClass + " < " + inFilename + " > " + outFilename;
		System.out.println(command);
	}

	public static void gen_EIUAPPEA(int id, int n, int minRange, int maxRange) throws Exception {
		String basePath = "F:\\GitHub\\Programming2015\\Private\\Ngoc\\Spoj\\MidTerm\\EIUAPPEA\\";
		String classPath = "F:\\GitHub\\Programming2015\\Private\\Ngoc\\Spoj\\bin";
		String javaClass = "EIUAPPEA";

		String inFilename = basePath + id + ".in";
		String outFilename = basePath + id + ".out";
		FileWriter in = new FileWriter(inFilename);

		StringBuffer inBuffer = new StringBuffer();
		inBuffer.append(n + "\r\n");

		for (int i = 0; i < n; i++) {
			int value = randBetween(minRange, maxRange);
			inBuffer.append(value + " ");
		}

		in.write(inBuffer.toString() + "\r\n");
		in.close();

		String command = "java -cp " + classPath + " " + javaClass + " < " + inFilename + " > " + outFilename;
		System.out.println(command);
	}

	public static void gen_MTAMSAO(int id, int n, int range) throws IOException {
		String basePath = "F:\\GitHub\\Programming2015\\Private\\Ngoc\\Spoj\\bin\\";
		String classPath = "F:\\GitHub\\Programming2015\\Private\\Ngoc\\Spoj\\bin";
		String javaClass = "MTAMSAO1M";

		String inFilename = basePath + id + ".in";
		String outFilename = basePath + id + ".out";
		FileWriter in = new FileWriter(inFilename);

		StringBuffer inBuffer = new StringBuffer();
		inBuffer.append(n + "\r\n");
		for (int i = 0; i < n; i++) {
			int value = randBetween(-range, range);
			if (value == 0)
				value = randBetween(-range, range);
			if (value == 0)
				value = randBetween(-range, range);
			if (value == 0)
				value = 1;
			inBuffer.append(value + " ");
		}

		in.write(inBuffer.toString() + "\r\n");
		in.close();

		String command = "java -cp " + classPath + " " + javaClass + " < " + inFilename + " > " + outFilename;
		System.out.println(command);
	}

	public static void gen_EIUPREIU(int id, int n) throws IOException {
		String basePath = "F:\\GitHub\\Programming2015\\Private\\Ngoc\\Spoj\\bin\\";
		String classPath = "F:\\GitHub\\Programming2015\\Private\\Ngoc\\Spoj\\bin";
		String javaClass = "EIUPREIU";

		String inFilename = basePath + id + ".in";
		String outFilename = basePath + id + ".out";
		FileWriter in = new FileWriter(inFilename);

		StringBuffer inBuffer = new StringBuffer();
		inBuffer.append(n + "\r\n");

		in.write(inBuffer.toString() + "\r\n");
		in.close();

		String command = "java -cp " + classPath + " " + javaClass + " < " + inFilename + " > " + outFilename;
		System.out.println(command);
	}

	public static void gen_EIPAIR(int id, int testCase, int maxN, int maxAi, int baseAi) throws IOException {
		String basePath = "E:\\GitHub\\DSAW2017\\bin\\";
		String classPath = "E:\\GitHub\\DSAW2017\\bin\\";
		String javaClass = "EIPAIR";

		String inFilename = basePath + id + ".in";
		String outFilename = basePath + id + ".out";
		FileWriter in = new FileWriter(inFilename);

		StringBuffer inBuffer = new StringBuffer();
		inBuffer.append(testCase + "\r\n");
		for (int test = 0; test < testCase; test++) {
			int n = maxN; // randBetween(maxN / 2, maxN);
			int[] numbers = new int[Math.min(n, 1000)];
			for (int i = 0; i < numbers.length; i++) {
				numbers[i] = baseAi + randBetween(1, randBetween(1, 100) < 10 ? maxAi : Math.min(maxAi, 100));
				// numbers[i] = baseAi + randBetween(1, maxAi);
			}
			inBuffer.append(n + " ");
			for (int i = 0; i < n; i++) {
				inBuffer.append(numbers[randBetween(0, numbers.length)] + " ");
			}
			inBuffer.append("\r\n");
		}

		in.write(inBuffer.toString() + "\r\n");
		in.close();

		String command = "java -cp " + classPath + " " + javaClass + " < " + inFilename + " > " + outFilename;
		System.out.println(command);
	}

	public static void gen_EIUONCE(int id, int testCase, int maxN, int maxAi, int baseAi) throws IOException {
		String basePath = "E:\\GitHub\\DSAW2017\\bin\\";
		String classPath = "E:\\GitHub\\DSAW2017\\bin\\";
		String javaClass = "EIUONCE";

		String inFilename = basePath + id + ".in";
		String outFilename = basePath + id + ".out";
		FileWriter in = new FileWriter(inFilename);

		StringBuffer inBuffer = new StringBuffer();
		inBuffer.append(testCase + "\r\n");
		for (int test = 0; test < testCase; test++) {
			int n = maxN;
			inBuffer.append(n + " ");
			for (int i = 0; i < n; i++) {
				inBuffer.append((baseAi + randBetween(0, maxAi)) + " ");
			}
			inBuffer.append("\r\n");
		}

		in.write(inBuffer.toString() + "\r\n");
		in.close();

		String command = "java -cp " + classPath + " " + javaClass + " < " + inFilename + " > " + outFilename;
		System.out.println(command);
	}
}
