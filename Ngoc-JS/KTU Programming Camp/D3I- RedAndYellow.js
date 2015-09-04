registerProblem('D1I: Red And Yellow',
    [{
        input: '2 2 2',
        output: '1'
    },
    {
        input: '1 2 2',
        output: '2'
    }
    ],
    function () {
        var tokens = readline().split(' ');
        var A = +tokens[0];
        var B = +tokens[1];
        var R = +tokens[2];

        var lower = Math.ceil(Math.PI / Math.asin(R / (A + R)));
        lower = Math.max(lower, 2);
        var upper = Math.floor(Math.PI / Math.asin(R / (B + R)) + 0.0000000000001);

        print(upper - lower + 1);
    }
);

/*
Link: http://codeforces.com/gym/100739/problem/I
*/