registerProblem('Runtime - Simple problems', [],
    function () {
        let tokens = [];
        let current = 0;

        function nextInt() {
            while (current == tokens.length) {
                tokens = readline().split(' ').filter(function (ele) { return ele; });
                current = 0;
            }
            return +tokens[current++];
        }

        let Y = +readline();
        print(process(Y, 1));

        function process(Y, min) {
            let max = 1;
            for (let i = min + 1; i * i < Y; i++) {
                if (Y % i == 0) {
                    max = Math.max(max, 1 + process(Y / i, i));
                }
            }
            return max;
        }
    }
);