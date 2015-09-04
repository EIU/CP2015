registerProblem('2014 Winter Programming School, Kharkiv, day 1 (E. Kapun). Junior league. Problem A - A Hard Way',
    [{
        input: '1 1',
        output: '0.5'
    }, {
        input: '10 20',
        output: '0'
    }, {
        input: '1000 100',
        output: '0.0001694'
    }],

    function () {
        array = readline().split(' ');
        N = +array[0];
        M = +array[1];

        status = [];
        newStatus = [];
        MAX = 1002;
        MAX2 = MAX * 2;
        for (i = 0; i < MAX2; i++) {
            status[i] = 0;
            newStatus[i] = 0;
        }
        status[MAX] = 1;
        for (j = 0; j < N; j++) {
            newStatus[0] = status[1] / 2;
            for (i = 1; i < MAX2 - 1; i++) newStatus[i] = status[i - 1] / 2 + status[i + 1] / 2;
            newStatus[MAX2 - 1] = status[MAX2 - 2] / 2;
            var temp = newStatus;
            newStatus = status;
            status = temp;
        }

        print(status[M + MAX])
    }

    /*
        Problem A: http://codeforces.com/gym/100370

        Solution: At step j, status[i] is probability that Alex is at block i (Start from -MAX)
    */
);
