        let n = +readline();
        let s = readline();
        let board = new Array();
        for (let i = 0; i < n * n; i += n) {
            board.push(s.substr(i, n));
        }
        let pattern = 'ICPCASIASG';
        let nextMatrix = [{ row: 1, col: 2 }, { row: 1, col: -2 },
                            { row: -1, col: 2 }, { row: -1, col: -2 },
                            { row: 2, col: 1 }, { row: 2, col: -1 },
                            { row: -2, col: 1 }, { row: -2, col: -1 }];

        function test(index, row, colum) {
            if (index >= pattern.length) return true;
            if (0 > row || row >= n
                || 0 > colum || colum >= n
                || pattern[index] != board[row][colum]) return false;

            for (let i = 0; i < nextMatrix.length; i++) {
                var next = nextMatrix[i];
                if (test(index + 1, row + next.row, colum + next.col)) return true;
            }
            return false;
        }

        var result = false;
        for (let i = 0; i < n; i++) {
            for (let j = 0; j < n; j++) {
                result |= test(0, i, j);
                if (result) break;
            }
            if (result) break;
        }

        print(result ? 'YES' : 'NO');