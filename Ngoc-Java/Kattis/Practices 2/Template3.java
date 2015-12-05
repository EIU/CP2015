public class Template3 {

	static byte[] inbuf = new byte[1 << 24];
	static int lenbuf = 0, ptrbuf = 0;

	public static void main(String[] args) throws Exception {
		lenbuf = System.in.read(inbuf);
	}

	static void solve() {

	}
}
