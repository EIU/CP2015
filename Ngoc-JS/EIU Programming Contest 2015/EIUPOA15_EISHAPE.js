registerProblem('EIUPOA15_EISHAPE',
    [{
        input: '1\n\
                4.000\n\
                2\n\
                1.000 1.000\n\
                0',
        output: 'NO\n\
                YES'
    }
    ],
    function () {

        var k = +readline();
        while (k > 0) {
            var sum = 0;
            var isOK = false;
            var nums = readline().split(' ').map(function (numText) { return +numText * 1000; })
                .sort(function (e1, e2) { return e1 - e2; })
                .forEach(function (num) {
                    isOK |= (sum >= num);
                    sum += num;
                });
            print(isOK ? 'YES' : 'NO');
            var k = +readline();
        }
    }
);

/*
Link: http://www.spoj.com/EIUPOA15/problems/EIUGAME/
*/