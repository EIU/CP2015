import java.io.FileWriter;
import java.io.IOException;

public class EIUAPPEA {

	public static void main(String[] args) throws IOException {
		genTest(8, 100000, 1000000, 1000000 + 100000);
	}

	static String basePath = "D:\\GitHub\\Programming2015\\Private\\Ngoc\\Spoj\\MidTerm\\EIUAPPEA\\";

	public static int randBetween(int start, int end) {
		if (start == end) {
			end++;
		}
		return start + (int) Math.floor(Math.random() * (end - start - 0.000001));
	}

	public static void genTest(int id, int n, int minRange, int maxRange) throws IOException {
		FileWriter in = new FileWriter(basePath + id + ".in");
		FileWriter out = new FileWriter(basePath + id + ".out");

		StringBuffer inBuffer = new StringBuffer();
		StringBuffer outBuffer = new StringBuffer();
		inBuffer.append(n + "\r\n");

		in.write(inBuffer.toString() + "\r\n");
		out.write(outBuffer.toString() + "\r\n");
		in.close();
		out.close();
	}

}
