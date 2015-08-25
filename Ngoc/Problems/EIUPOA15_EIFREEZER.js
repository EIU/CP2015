registerProblem('EIUPOA15_EIFREEZER',
    [{
        input: '12',
        output: '3 2 2'
    }
    ],
    function () {

        var n = readline();
        var UpperA = Math.floor(Math.pow(n, 1 / 3));

        var minArea = 2 * n + 1, valueA = 1, valueB = 1, valueC = n;
        for (var a = 1; a <= UpperA; a++) {
            if (n % a == 0) {
                var UpperB = Math.floor(Math.sqrt(n / a));
                for (var b = a; b <= UpperB; b++) {
                    if (n % (a * b) == 0) {
                        var haftArea = n / a + a / b + a * b;
                        if (haftArea <= minArea) {
                            valueA = a;
                            valueB = b;
                            valueC = n / a / b;
                        }
                    }
                }
            }
        }
        print(valueC + ' ' + valueB + ' ' + valueA);
    }
);

/*
Link: http://www.spoj.com/EIUPOA15/problems/EIUGAME/
*/