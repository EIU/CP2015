class TestRecurent {

	public static void main(String[] args) {
		test(1);
	}

	static void test(int n) {
		try {
			test(n + 1);
		} catch (StackOverflowError ex) {
			System.out.println(n + "\n" + ex.getMessage());
		}
	}
}
