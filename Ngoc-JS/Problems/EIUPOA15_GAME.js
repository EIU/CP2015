registerProblem('EIUPOA15_GAME',
    [{
        input: '3 3\n\
1 2 3\n\
-1 5 6\n\
0 2 9',
        output: '23'
    }
    ],
    function () {

        var tokens = readline().split(' ');
        var n = +tokens[0];
        var m = +tokens[1];

        var MIN = -1000 * 1000 * 1000 * 1000 * 1000 * 1000;
        var maxValues = [];
        maxValues[0] = 0;
        for (var j = 1; j <= m; j++) {
            maxValues[j] = MIN;
        }

        for (var i = 0; i < n; i++) {
            var rowValues = readline().split(' ');
            for (var j = 1; j <= m; j++) {
                maxValues[j] = Math.max(maxValues[j], maxValues[j - 1]) + +rowValues[j-1];
            }
            maxValues[0] = maxValues[1];
        }

        print(maxValues[m]);
    }
);

/*
Link: http://www.spoj.com/EIUPOA15/problems/EIUGAME/
*/