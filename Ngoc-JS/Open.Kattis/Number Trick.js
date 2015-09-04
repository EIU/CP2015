registerProblem('D1I: Red And Yellow',
    [{
        input: '2.6',
        output: '135 270 135135 270270'
    }, {
        input: '1',
        output: ''
    }, {
        input: '2',
        output: ''
    }, {
        input: '1.5',
        output: ''
    }, {
        input: '2.3',
        output: ''
    }, {
        input: '3.1416',
        output: 'No solution'
    }
    ],
    function () {

        var X = +readline();
        var p = Math.round(X * 10000);
        var q = 10000;
        var gcdpq = gcd(p, q);
        p /= gcdpq;
        q /= gcdpq;

        var result = [];
        var pow10 = 1;
        for (k = 0; k <= 7; k++) {
            for (var d = 1; d <= 9; d++) {
                if (10 * q - p !== 0) {
                    var H = d * (pow10 * p - q) / (10 * q - p);
                    if ((d * (pow10 * p - q)) % (10 * q - p) === 0 && H < pow10) {
                        result.push(d * pow10 + H);
                    }
                }
            }
            pow10 *= 10;
        }

        print(result.join('\n') || 'No solution');

        function gcd(a, b) {
            return b === 0 ? a : gcd(b, a % b);
        }
    }
);

/*
Link: https://open.kattis.com/problems/trick
*/