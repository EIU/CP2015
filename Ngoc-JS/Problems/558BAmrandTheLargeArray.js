registerProblem('558B Amr and The Large Array - Counting',
    [{
        input: '5\n\
                1 1 2 2 1',
        output: '1 5'
    },
    {
        input: '5\n\
                1 2 2 3 1',
        output: '2 3'
    },
    {
        input: '6\n\
                1 2 2 1 1 2',
        output: '1 5'
    }
    ],
    function () {

        MAX = 1000001;
        counts = [];
        firsts = [];
        lasts = [];
        for (i = 0; i < MAX; i++) {
            counts[i] = 0;
            firsts[i] = -1;
            lasts[i] = MAX;
        }
        n = +readline();
        array = readline().split(' ');
        for (i = 0; i < n; i++) {
            ele = array[i] = +array[i];
            counts[ele]++;
            if (firsts[ele] < 0) firsts[ele] = i + 1;
            lasts[ele] = i + 1;
        }

        max = -1;
        left = -1;
        right = MAX;
        for (i = 0; i < MAX; i++) {
            if (max < counts[i]) {
                max = counts[i];
                left = firsts[i];
                right = lasts[i];
            } else if (max == counts[i] && right - left > lasts[i] - firsts[i]) {
                left = firsts[i];
                right = lasts[i];
            }
        }

        print(left + ' ' + right);


    }
);
