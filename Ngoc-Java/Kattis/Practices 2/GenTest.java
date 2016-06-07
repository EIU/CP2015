import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenTest {

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 10; i++) {
			FileWriter in = new FileWriter(i + ".in");
			in.write("100000\n");
			Random rand = new Random();
			for (int j = 0; j < 100000; j++) {
				in.write((50000 + rand.nextInt(100000 - 50000)) + " " + (5000 + rand.nextInt(100000 - 5000)) + "\n");
			}
			in.flush();
		}
	}
}
