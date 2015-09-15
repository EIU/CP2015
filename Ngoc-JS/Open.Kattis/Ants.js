registerProblem('Kattis: Ant',
    [{
        input: '2\n\
                10 3\n\
                2 6 7\n\
                214 7\n\
                11 12 7 13\n\
                176 23 191\n',
        output: '4 8\n\
38 207'
    }
    ],
    function () {

        var tokens = [];
        var current = 0;
        function nextInt() {
            if (current == tokens.length) {
                tokens = readline().split(' ').filter(function (ele) { return ele; });
                current = 0;
            }
            return +tokens[current++];
        }

        var T = nextInt();
        for (var t = 0; t < T; t++) {
            var l = nextInt();
            var n = nextInt();
            var min = -1;
            var max = -1;

            for (var i = 0; i < n; i++) {
                var position = nextInt();
                min = Math.max(min, Math.min(position, l - position));
                max = Math.max(max, Math.max(position, l - position));
            }
            print(min + ' ' + max);
        }
    }
);

/*
Link: https://open.kattis.com/problems/trick
*/