import java.io.File;
import java.io.FileWriter;

public class GEN_P153PROE   {

	public static void main(String[] args) throws Exception {
		// gen_P153PROE(0, 10, new int[] { 10 });
		// gen_P153PROE(1, 11, new int[] { 9 });
		// gen_P153PROE(2, 12, new int[] { 1 });
		// gen_P153PROE(3, 13, new int[] { 0 });
		// gen_P153PROE(4, 14, new int[] { 2 });
		// gen_P153PROE(5, 11, new int[] { 11, 5, 2 });
		// gen_P153PROE(6, 12, new int[] { 11, 5 });
		// gen_P153PROE(7, 12, new int[] { 11, 10, 6 });
		// gen_P153PROE(8, 13, new int[] { 11, 5 });
		// gen_P153PROE(9, 10, new int[] { -1 });
		// gen_P153PROE(10, 11, new int[] { -1 });
		//
		// gen_P153PROE(11, 100000, new int[] { 100000 });
		// gen_P153PROE(12, 100000, new int[] { 100000 - 1 });
		// gen_P153PROE(13, 100000, new int[] { 100000 - 3 });
		// gen_P153PROE(14, 100000, new int[] { 1 });
		// gen_P153PROE(15, 100000 - 1, new int[] { 0 });
		// gen_P153PROE(16, 100000, new int[] { 2 });
		// gen_P153PROE(17, 100000 - 1, new int[] { -1 });
		// gen_P153PROE(18, 100000, new int[] { -1 });
		// gen_P153PROE(19, 100000, new int[] { 100000, 100000 - 1, 100000 - 2 });
		// gen_P153PROE(20, 100000, new int[] { 100000 - 1, 100000 - 2 });
		// gen_P153PROE(21, 100000, new int[] { randBetween(0, 100000) });
		// gen_P153PROE(22, 100000 - 1, new int[] { randBetween(0, 100000 - 1) });
		// gen_P153PROE(23, 100000 - 2, new int[] { randBetween(0, 100000 - 2) });
		// gen_P153PROE(24, 100000, new int[] { randBetween(0, 100000), randBetween(0, 100000) });
		// gen_P153PROE(25, 100000, new int[] { randBetween(0, 100000), randBetween(0, 100000), randBetween(0, 100000) });

		gen_P153PROE(26, 100000, new int[] { -1 });
		gen_P153PROE(27, 100000 - 1, new int[] { -1 });
		gen_P153PROE(28, 100000 - 2, new int[] { -1 });

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

	public static void gen_P153PROE(int id, int n, int[] errors) throws Exception {
		String root = "D:\\GitHub\\Programming2015\\Private\\Ngoc\\Spoj\\";
		String basePath = root + "PTIT2015\\";
		String classPath = root + "bin\\";
		String javaClass = "P153PROE";

		String outPath = basePath + javaClass + "\\";
		File theDir = new File(outPath);
		theDir.mkdir();
		String inFilename = outPath + id + ".in";
		String outFilename = outPath + id + ".out";
		FileWriter in = new FileWriter(inFilename);

		StringBuffer inBuffer = new StringBuffer();
		inBuffer.append(n + "\r\n");
		int[] values = new int[n + 1];
		long acc = 0;
		for (int i = n; i >= 0; i--) {
			long seed = randBetween(acc + i + 1, acc + i + 1 + randBetween(1, 30) * randBetween(1, 30) * 1000 * 1000);
			if (!contains(errors, i)) {
				if (i != 0) {
					seed = seed / i * i;
				}
			}
			values[i] = (int) (seed - acc);
			if (values[i] <= 0 || values[i] > 1000 * 1000 * 1000) {
				throw new Exception();
			}
			if (i != 0) {
				acc += seed / i;
			}
		}

		for (int i = 0; i <= n; i++) {
			inBuffer.append(values[i] + " ");
		}

		in.write(inBuffer.toString() + "\r\n");
		in.close();

		String command = "java -cp " + classPath + " " + javaClass + " < " + inFilename + " > " + outFilename;
		System.out.println(command);
	}

}
