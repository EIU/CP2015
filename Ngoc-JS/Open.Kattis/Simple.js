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

        let line = null;
        while ((line = readline()) != '0 0') {
            let params = line.split(' ');
            let n = +params[0];
            let m = +params[1];

            let matrix = [];
            for (let i = 0; i < n; i++) {
                matrix.push(readline().split(''));
            }

            matrix = convert(matrix, n, m);
            matrix.sort(function (line1, line2) {
                return line1.join('').toLowerCase().localeCompare(line2.join('').toLowerCase());
            });
            matrix = convert(matrix, m, n);

            for (let i = 0; i < n; i++) {
                print(matrix[i].join(''));
            }
            print('');
        }

        function convert(matrix, n, m) {
            let newMatrix = [];
            for (let i = 0; i < m; i++) {
                var newLine = [];
                for (let j = 0; j < n; j++) {
                    newLine.push(matrix[j][i]);
                }
                newMatrix.push(newLine);
            }
            return newMatrix;
        }
    }
);