import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
class Test1 {
 
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int count = 0;
		SystemError err = new SystemError(100000);
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < n; ++i) {
			int type = in.nextInt();
			if (type == 1) {
				int x = in.nextInt();
				int y = in.nextInt();
				err.addError(x, y);
			} else if (type == 2) {
				int k = in.nextInt();
				str.append(err.getCountErrors(k));
				str.append('\n');
			} else if (type == 3) {
				int x = in.nextInt();
				int z = in.nextInt();
				str.append(err.getLastErrors(x, z));
				str.append('\n');
 
			} else {
				int x = in.nextInt();
				int z = in.nextInt();
				str.append(err.getnLastErrors(x, z));
			}
		}
		System.out.println(str);
 
	}
}
 
class SystemError {
	List<Integer>[] errors;
	List<Integer> e;
 
	public SystemError(int limit) {
		errors = new List[limit + 1];
		e = new ArrayList<>();
		for (int i = 0; i <= limit; ++i) {
			errors[i] = new ArrayList<>();
		}
	}
 
	public void addError(int x, int y) {
		errors[x].add(y);
		e.add(x);
	}
 
	public int getCountErrors(int k) {
		return errors[k].size();
	}
 
	public int getLastErrors(int x, int n) {
		int min = e.size() - n;
		if (min < 0) {
			min = 0;
		}
		int count = 0;
 
		for (int i = e.size() - 1; i >= min; --i) {
			if (e.get(i) ==x) {
				count++;
			}
		}
		return count;
	}
 
	public String getnLastErrors(int x, int n) {
		StringBuilder str = new StringBuilder();
		int size = errors[x].size();
		int min = size - n;
		if (min < 0) {
			min = 0;
		}
 
		for (int i = size - 1; i >= min; --i) {
			str.append(errors[x].get(i) + "\n");
		}
		return str.toString();
	}
}