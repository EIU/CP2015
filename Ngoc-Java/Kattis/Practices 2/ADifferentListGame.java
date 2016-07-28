import java.io.*;
import java.util.*;

public class ADifferentListGame {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 5);
		long X = Long.parseLong(reader.readLine());

		long s = System.currentTimeMillis();
		List<Integer> powers = new ArrayList<Integer>();
		X = factorize(X, 2, powers);
		X = factorize(X, 3, powers);
		for (long p = 5; p * p <= X; p += 6) {
			X = factorize(X, p, powers);
			X = factorize(X, p + 2, powers);
		}
		if (X > 1) {
			powers.add(1);
			System.out.println(X + "^1");
		}

		//		powers.sort((s1, s2) -> s2 - s1);
		//		Collections.sort(powers);
		//		powers.sort(new Comparator<Integer>() {
		//			@Override
		//			public int compare(Integer arg0, Integer arg1) {
		//				return arg1 - arg0;
		//			}
		//		});

		System.out.println((System.currentTimeMillis() - s) + " ms");
	}

	static long factorize(long X, long factor, List<Integer> powers) {
		int power = 0;
		while (X % factor == 0) {
			X /= factor;
			power++;
		}
		if (power > 0) {
			powers.add(power);
			System.out.println(factor + "^" + power);
		}
		return X;
	}
}

/*
#include <cstdio>
#include <vector>

using namespace std;

typedef long long ll;
typedef vector<int> vi;

vi reduct(vi v, const vi &p) {
  for (int i = 0; i < p.size(); ++i)
    if (!v[p[i]]--) return vi();
  return v;
}

int gen(vector<int> v, vector<int> lb, int tobeat) {
  int tot = 0;
  for (int i = 0; i < v.size(); ++i) tot += v[i];

  if (lb.empty()) lb.push_back(0);
  else
  for (int i = 0; ++lb[i] >= v.size(); ++i) {
    if (i == lb.size()-1) {
      lb = vi(lb.size()+1, 0);
      break;
    } else {
      lb[i] = lb[i+1]+1;
      for (int j = 0; j < i; ++j)
	lb[j] = lb[i];
    }
  }

  if (tobeat >= tot/lb.size()) return tobeat;
  vi g = reduct(v, lb);
  if (!g.empty()) tobeat = 1+gen(g, lb, max(tobeat-1, 0));
  tobeat = gen(v, lb, tobeat);
  return tobeat;
}

int main(void) {
  ll X;
  vector<int> v;
  scanf("%lld", &X);
  int k = 0;
  for (ll p = 2; p*p <= X; ++p) {
    int e = 0;
    while (X % p == 0) X /= p, ++e;
    if (e) {
      ++k;
      if (e > 1) v.push_back(e-1);
    }
  }
  if (X != 1) ++k;
  vi lb(1, v.size());
  if (!v.empty()) k += gen(v, lb, 0);
  printf("%d\n", k);
  return 0;
}
*/