import java.io.File;
import java.io.FileWriter;

public class GEN_EIUQA {

	public static void main(String[] args) throws Exception {
		gen_EIUQA_Hard(10, 50000, 50000, 100);
		gen_EIUQA_Hard(11, 50000, 50000, 150);
		gen_EIUQA_Hard(12, 50000, 50000, 200);
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

	public static long randBetween(long start, long end) {
		if (start == end) {
			end++;
		}
		return start + (long) Math.floor(Math.random() * (end - start - 0.000001));
	}

	static boolean contains(int[] array, int value) {
		for (int i : array) {
			if (i == value)
				return true;
		}
		return false;
	}

	public static void gen_EIUQA_Hard(int id, int nAdd, int nQuery, int disCore) throws Exception {
		String root = "D:\\GitHub\\Programming2015\\Private\\Ngoc\\Spoj\\";
		String basePath = root + "Exam\\";
		String classPath = root + "bin\\";
		String javaClass = "EIUQA";

		String outPath = basePath + javaClass + "\\";
		File theDir = new File(outPath);
		theDir.mkdir();
		String inFilename = outPath + id + ".in";
		String outFilename = outPath + id + ".out";
		FileWriter in = new FileWriter(inFilename);

		StringBuffer inBuffer = new StringBuffer();
		inBuffer.append(nAdd + nQuery + "\r\n");
		int startType = 1000;
		int startId = 1000000;
		for (int i = 0; i < nAdd; i++) {
			if (i < 500) {
				inBuffer.append(1 + " " + (startType + randBetween(0, 100)) + " " + (startId += randBetween(1, 2)) + "\n");
			}
			else {
				inBuffer.append(1 + " " + (startType + randBetween(0, disCore) * randBetween(0, disCore)) + " " + (startId += randBetween(1, 2)) + "\n");
			}
		}

		for (int i = 0; i < nQuery; i++) {
			inBuffer.append(4 + " " + (startType + randBetween(0, 100)) + " " + 100 + "\n");
		}

		in.write(inBuffer.toString() + "\r\n");
		in.close();

		String command = "java -cp " + classPath + " " + javaClass + " < " + inFilename + " > " + outFilename;
		System.out.println(command);
	}
}
