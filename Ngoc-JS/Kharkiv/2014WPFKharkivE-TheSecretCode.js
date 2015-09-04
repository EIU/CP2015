registerProblem('2014 Winter Programming School, Kharkiv, day 1 (E. Kapun). Junior league. Problem E - The Secret Code',
    [{
        input: '2 2 1\n\
                50 50\n\
                10 90',
        output: '0.45'
    }, {
        input: '3 5 4\n\
                10 15 20 25 30\n\
                1 2 3 4 90\n\
                100 0 0 0 0',
        output: '0.81'
    }],

    function () {
        var tokens = readline().split(' ');
        var N = +tokens[0];
        var M = +tokens[1];
        var K = +tokens[2];

        var status = [1];

        for (var i = 0; i < N; i++) {
            var piArr = readline().split(' ').map(function (ele) { return +ele / 100; });
            var newStatus = [];

            // Complexity: N*K*M Log(K*M) - Can be improve to O(N*max(K,M).log(K))
            for (var u = 0; u < status.length; u++) {
                for (var v = 0; v < M; v++) {
                    newStatus.push(status[u] * piArr[v]);
                }
            }
            newStatus.sort(function (i1, i2) { return i2 - i1; });
            status = newStatus.slice(0, K);
        }

        var res = 0;
        for (var i = 0; i < status.length; i++) {
            res += status[i];
        }
        print(res.toFixed(9));
    }

    /*
        Problem E: http://codeforces.com/gym/100370
    */
);
