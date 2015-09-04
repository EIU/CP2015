registerProblem('2014 Winter Programming School, Kharkiv, day 1 (E. Kapun). Junior league. Problem C - A Full Set',
    [{
        input: '5 1',
        output: '11.4166667'
    }, {
        input: '5 2',
        output: '1.0'
    }, {
        input: '100 1',
        output: '1.0'
    }, {
        input: '1000 1000',
        output: '1.0'
    }],

    function () {
        var tokens = readline().split(' ');
        var N = +tokens[0];
        var K = +tokens[1];

        var log_xCy = [];
        for (var i = 0; i <= N; i++) {
            var xCy = [0];
            log_xCy.push(xCy);
            var val = 0;
            for (var j = 1; j <= i; j++) {
                val += Math.log(i - j + 1) - Math.log(j);
                xCy.push(val);
            }
        }

        var probs = [];
        var days = [];

        for (var i = 0; i < K; i++) {
            probs[i] = 0;
            days[i] = 0;
        }
        var Y = Math.exp(log_xCy[K][K] - log_xCy[N][K]);
        var Z = 1;
        if (K < N) {
            Z = 1 / (1 - Y);
        }
        probs[K] = 1 * Z;
        days[K] = 1 * Z * Z;

        for (var i = K + 1; i <= N; i++) {
            var prob = 0;
            var day = 0;
            for (var j = i - K; j < i; j++) {
                var X = Math.exp(log_xCy[j][K - i + j] + log_xCy[N - j][i - j] - log_xCy[N][K]);
                var Y = Math.exp(log_xCy[i][K] - log_xCy[N][K]);
                var Z = 1;
                if (i < N) {
                    Z = 1 / (1 - Y);
                }

                prob += X * Z * probs[j];
                day += X * Z * days[j] + X * Z * Z * probs[j];
            }
            probs[i] = prob;
            days[i] = day;
        }

        print(days[N].toFixed(9));
        //print(probs);
        //print(days);
    }

    /*
        Problem C: http://codeforces.com/gym/100370

        Initial Idea:
        Let P(i, j) is probability of day i-th which has exactly j dice has been taken
        We have P(i+1, j) = Sum(P(i,v) * C(v, K-j+v) * C(N-v, j-v) / C(N, K)): 0 <= v <= K   
        Result: Sum(i * P(i, N))
        Complexity: O(K * N * N)

        Improve:
        Kiểm tra P(i, j) ảnh hưởng thế nào đến P(*, j+u)
            P(i+1, j+u) += P(i, j) * X
            P(i+2, j+u) += P(i, j) * X * Y
            P(i+3, j+u) += P(i, j) * X * Y^2
        X: C(i, K-u) * C(N-i, u) / C(N, K)
        Y: C(j+u, K) / C(N, K)
        => P(j+u) += P(j) * X * 1 / (1-Y) 
           && D(j+u) += D(j) * X/(1-Y) + P(j) * X/(1-Y)^2
        

        // It seems too complicated? Is there any other idea?

        // There may be a relation between prob and day?
    */
);


