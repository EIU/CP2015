
function genTest2014WPFKharkivH_3() {
    var N = 30;
    var M = 10;
    var s = N + ' ' + M + '\n';
    for (i = 0; i < N; i++) {
        var Ni = randBetween(5, 30);
        var linei = [Ni];
        for (var j = 0; j < Ni; j++) {
            linei.push(randBetween(0, 1000));
        }
        s += linei.join(' ') + '\n';
    }
    return s;
}

registerProblem('2014 Winter Programming School, Kharkiv, day 1 (E. Kapun). Junior league. Problem H - A Granite Of Science',
    [{
        input: '3 3\n\
                1 3\n\
                2 1 1\n\
                3 0 0 0',
        output: '14.3333333'
    }, {
        input: '2 1\n\
                1 1\n\
                1 2',
        output: '9.0'
    }
    , {
        input: genTest2014WPFKharkivH_3(),
        output: ''
    }
    ],

    function () {

        var tokens = readline().split(' ');
        var N = +tokens[0];
        var M = +tokens[1];

        var sumSquares = [];
        var sums = [];
        var MAX = 1001;
        for (var i = 0; i < MAX; i++) {
            sumSquares[i] = 0;
            sums[i] = 0;
        }

        for (var i = 0; i < N; i++) {
            var tokens = readline().split(' ');
            var Ni = +tokens[0];

            var square = 0;
            var sum = 0;
            for (var j = 0; j < Ni + M - 1; j++) {
                if (j < Ni) {
                    var v = +tokens[j + 1];
                    sum += v;
                    square += v * v;
                }
                if (j >= M) {
                    var v = +tokens[j - M + 1];
                    sum -= v;
                    square -= v * v;
                }

                sumSquares[j] += square / M + sums[j] * sum * 2 / M;
                sums[j] += sum / M;
            }
        }

        var result = 0;
        for (var i = 0; i < MAX; i++) {
            result += sumSquares[i];
        }
        print(result);


    }

    /*
        Problem H: http://codeforces.com/gym/100370

        With K subject: We have M^K cases!
        Examine day i: 
            Let: Ak is average value Qku = (x1+x2+...+xk)^2, and Bk is average value of Dku = (x1+x2+...+xk)
            We try to compute A(k+1), and B(k+1) - for short A and B
            A = Sum( (Qku + yv)^2 )/M^(K+1) for every u=1..M^K, and v = 1..M
            yv: Value of lesson of Subject K+1 which can appear on day i (l1, l2, ...li)
            A =(Sum(Qk) * M + Sum(yv^2)*M^K + 2*Sum(yv)*Sum(Dku))/M^(K+1)
              = Ak + 2*Bk*Sum(yv)/M + Sum(yv^2)/M
            Similar: B = Bk + Sum(yv)/M

    */
);
