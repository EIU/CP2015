import java.io.FileWriter;

public class EIUAPPEAGEN {

	public static void main(String[] args) throws Exception {
		genTest(9, 100000, 1000000, 1000000 + 100000);
		genTest(10, 100000, 1000000, 1000000 + 100000);
		genTest(11, 6, 1, 4);
	}

	static String basePath = "D:\\GitHub\\Programming2015\\Private\\Ngoc\\Spoj\\MidTerm\\EIUAPPEA\\";
	static String classPath = "D:\\GitHub\\Programming2015\\Private\\Ngoc\\Spoj\\bin";
	static String javaClass = "EIUAPPEA";

	public static int randBetween(int start, int end) {
		if (start == end) {
			end++;
		}
		return start + (int) Math.floor(Math.random() * (end - start - 0.000001));
	}

	public static void genTest(int id, int n, int minRange, int maxRange) throws Exception {
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

		// Process proc = Runtime.getRuntime().exec(command);
		// proc.waitFor();
		System.out.println(command);
	}

}
