registerProblem('558C Amr and Chemistry',
    [{
        input: '3\n\
4 8 2',
        output: '2'
    },
{
    input: '3\n\
3 5 6',
    output: '5'
}],
    function () {

        MAX = 100010
        MAX2 = MAX / 2;

        counts = [];
        sums = [];
        n = +readline();
        for (i = 0; i <= MAX; i++) {
            sums[i] = counts[i] = 0;
        }

        array = readline().split(' ');
        for (i = 0; i < n; i++) {
            n2 = n1 = +array[i];
            counts[n1]++;

            mulOps = 0;
            while (n1 < MAX2) {
                n1 <<= 1;
                mulOps++;
                counts[n1]++;
                sums[n1] += mulOps;
            }

            divideOps = 0;
            while (n2 > 1) {
                odd = (n2 & 1) === 1;
                n2 >>= 1;
                divideOps++;
                counts[n2]++;
                sums[n2] += divideOps;
                if (odd) {
                    n3 = n2;
                    ops = divideOps;
                    while (n3 < MAX2) {
                        n3 <<= 1;
                        ops++;
                        counts[n3]++;
                        sums[n3] += ops;
                    }
                }
            }
        }

        minSum = sums[1];
        for (i = 1; i < MAX; i++) {
            if (counts[i] == n && minSum > sums[i]) {
                minSum = sums[i];
            }
        }

        print(minSum);

    }
);
