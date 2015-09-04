registerProblem('2014 Winter Programming School, Kharkiv, day 1 (E. Kapun). Junior league. Problem F - Dura Lex',
    [{
        input: '3\n\
                1 10\n\
                10 10\n\
                5 90',
        output: '3 1 2'
    }, {
        input: '6\n\
                0 0\n\
                1 10\n\
                0 0\n\
                1 10\n\
                10 10\n\
                5 90',
        output: '3 1 2'
    }, {
        input: '3\n\
                2 20\n\
                3 30\n\
                4 40',
        output: '1 2 3'
    }, {
        input: '13\n\
                0 10\n\
                0 20\n\
                0 15\n\
                1 0\n\
                3 0\n\
                2 0\n\
                1 1\n\
                1 3\n\
                1 2\n\
                99 3\n\
                99 2\n\
                99 1\n\
                5 90',
        output: '3 1 2'
    }],

    function () {
        var N = +readline();
        var array = [];
        var zeroArray = [];
        for (var i = 1; i <= N; i++) {
            var tokens = readline().split(' ');
            var d = +tokens[0];
            var p = +tokens[1] / 100;
            if (d == 0 && p == 0) {
                zeroArray.push(i);
            }
            else {
                array.push({
                    index: i,
                    ratio: (p ? (d / p) : 1000000000)
                });
            }
        }

        array.sort(function (o1, o2) {
            var v = o1.ratio - o2.ratio;
            if (v < -0.000000001) {
                return -1;
            }
            else if (v > 0.000000001) {
                return 1;
            }
            return o1.index - o2.index;
        })

        var result = [];
        var i = 0;
        var j = 0;
        while (i < array.length || j < zeroArray.length) {
            if (i < array.length && j < zeroArray.length) {
                if (array[i].index < zeroArray[j]) {
                    result.push(array[i].index);
                    i++;
                }
                else {
                    result.push(zeroArray[j]);
                    j++;
                }
            }
            else if (i < array.length) {
                result.push(array[i].index);
                i++;
            }
            else {
                result.push(zeroArray[j]);
                j++;
            }
        }
        print(result.join(' '));
    }

    /*
        Problem G: http://codeforces.com/gym/100370

        Lemma: (Relate to Problem C) If probability of rejection is p then we need average 1/(1-p) times to resubmit
                Reject  Accept
        Time 1:     p      1-p
            2:      p^2    p(1-p)
            3:      p^3    p^2(1-p)
            4:      p^4    p^3(1-p)
        => Average days: Sum(i * p^(i-1) * (1-p)) = (1-p) * X
           X = 1 + p + p^2 + p^3 + ....
               + p(1 + p + p^2 + ...
                  + p^2(1 + p + p^2 + ...
           X = 1/(1-p)^2 => Davg = 1/(1-p)

        Assume the best order will be i1, i2, i3,..... i(n-1) = j, i(n) = i
        => Fn = 1/(1-Pi) * (Di + F(n-1))
              = Di*(1-Pj)/(1-Pi)/(1-Pj) + Dj/(1-Pi)/(1-Pj) + ...
        If Fn is the best => Di(1-Pj) + Dj < Dj(1-Pi) + Di => Di/Pi > Dj/Pj

        Spectial cases:
        + Di = 0: Should be at the begin of list
        + Pi = 0: Should be at the end of list
        + Di = Pi = 0: Should be merge carefully with remain items - they should be maintained in separate list
    */
);
