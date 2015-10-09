import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenTest {

	public static void main(String[] args) throws IOException {
		FileWriter in = new FileWriter("0.in");
		in.write("10000 10000 2000\n");
		Random rand = new Random();
		for (int i = 0; i < 2000; i++) {
			in.write(rand.nextInt(10000) + "\n");
		}
		in.flush();
	}
}
