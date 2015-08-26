registerProblem('EIUPOA15_EIZOOM',
    [{
        input: '3 4\n\
abc\n\
defg\n\
uva',
        output: ''
    }
    ],
    function () {

        var tokens = readline().split(' ');
        var n = +tokens[0];
        var k = +tokens[1];

        for (var i = 0; i < n; i++) {
            var line = readline();
            var zoomLine = [];
            for (var j = 0; j < line.length * k; j++) {
                zoomLine[j] = line[Math.floor(j / k)];
            }
            for (var repeat = 0; repeat < k; repeat++) {
                print(zoomLine.join(''));
            }
        }
    }
);

/*
Link: http://www.spoj.com/EIUPOA15/problems/EIUGAME/
*/