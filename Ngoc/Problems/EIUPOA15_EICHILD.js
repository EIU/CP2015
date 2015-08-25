registerProblem('EIUPOA15_CHILD',
    [{
        input: '2 3\n\
                2 4\n\
                6 1 4\n\
                2 7 1',
        output: '3.667'
    }
    ],
    function () {


        var tokens = readline().split(' ');
        var n = +tokens[0], m = +tokens[1];
        tokens = readline().split(' ');
        var a = +tokens[0], b = +tokens[1];

        function createRow() {
            var row = [];
            for (var i = 0; i <= m; i++) {
                row.push(0);
            }
            return row;
        }

        var sum2D = [createRow()];
        for (var i = 1; i <= n; i++) {
            tokens = readline().split(' ');
            var rowi = createRow();
            var rowi1 = sum2D[i - 1];
            var sumi = 0;
            for (var j = 1; j <= m; j++) {
                sumi += +tokens[j - 1];
                rowi[j] = sumi + rowi1[j];
            }
            sum2D.push(rowi);
        }

        var values = [];
        for (var i = 0; i <= 100000; i++) {
            values[i] = 0;
        }
        var totalValue = 0;
        for (var top = 0; top < n; top++) {
            for (var bottom = top + 1; bottom <= n; bottom++) {
                var width = bottom - top;
                for (var left = 0; left < m; left++) {
                    for (var right = left + 1; right <= m; right++) {
                        var height = right - left;
                        var area = width * height;
                        if (a <= area && area <= b) {
                            var average = (sum2D[bottom][right] - sum2D[bottom][left] - sum2D[top][right] + sum2D[top][left]);
                            average = Math.round(average / area * 1000);
                            values[average]++;
                            totalValue++;
                        }
                    }
                }
            }
        }

        var prei = 0;
        var count = 0;
        var mid = (totalValue + 1) / 2;
        for (var i = 0; i <= 10000 * 1000; i++) {
            if (values[i] > 0) {
                if (count + values[i] >= mid) {
                    if (totalValue % 2 == 1 || (count < mid - 1)) {
                        print(i / 1000);
                    }
                    else {
                        print(((prei + i) / 1000 / 2).toFixed(3));
                    }
                    break;
                }
                prei = i;
                count += values[i];
            }
        }
    }
);

/*
                            //values[average] = (values[average] || 0) + 1;
Link: http://www.spoj.com/EIUPOA15/problems/EIUGAME/
*/