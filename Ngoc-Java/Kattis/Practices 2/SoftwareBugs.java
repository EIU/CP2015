import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SoftwareBugs {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 26);
		StringBuilder outBf = new StringBuilder();
		String firstLine;
		while ((firstLine = reader.readLine()) != null && firstLine.length() > 0) {
			String[] tokens = firstLine.split(" ");
			int nLine = Integer.parseInt(tokens[0]);
			String preprocessor = tokens[1];
			for (int i = 0; i < nLine; i++) {
				String line = reader.readLine();
				int preIndex = 0;
				int index = 0;
				while ((index = line.indexOf(preprocessor, preIndex)) >= 0) {
					outBf.append(line.substring(preIndex, index));
					preIndex = index + preprocessor.length();
				}
				outBf.append(line.substring(preIndex, line.length()) + "\n");
			}
		}
		System.out.println(outBf);
	}
}
