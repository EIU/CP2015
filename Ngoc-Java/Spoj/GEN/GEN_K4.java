import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GEN_K4 {

	public static void main(String[] args) throws Exception {
		gen_AboveAverage(1, 10, -1000, 1000);
		gen_AboveAverage(2, 100, -1000000000, 1000000000);
		gen_AboveAverage(3, 1000, -1000000000, 1000000000);
		gen_AboveAverage(4, 100000, -1000000000, 1000000000);
		gen_AboveAverage(5, 100000, -1000000000, -2000000000);
		gen_AboveAverage(6, 100000, 1000000000, 2000000000);
		gen_AboveAverage(7, 100000, 1230000045, 1230000045);
		gen_AboveAverage(8, 100000, -777, 777);
		gen_AboveAverage(9, 1000, -4234234, 4234234);
		gen_AboveAverage(0, 100, 555, 555);
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

	public static void gen_AboveAverage(int id, int N, int max, int min) throws IOException {
		String root = "F:\\GitHub\\IntrProW15\\";
		String basePath = root + "";
		String classPath = root + "bin\\";
		String javaClass = "EIABOVEA";

		String outPath = basePath + javaClass + "\\";
		File theDir = new File(outPath);
		theDir.mkdir();
		String inFilename = outPath + id + ".in";
		String outFilename = outPath + id + ".out";
		FileWriter in = new FileWriter(inFilename);

		StringBuffer inBuffer = new StringBuffer();
		inBuffer.append(N + "\n");
		for (int i = 0; i < N; i++) {
			inBuffer.append(randBetween(min, max) + " ");
		}

		in.write(inBuffer.toString() + "\r\n");
		in.close();

		String command = "java -cp " + classPath + " " + javaClass + " < " + inFilename + " > " + outFilename;
		System.out.println(command);
	}
}
