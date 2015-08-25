registerProblem('EIUPOA15_EISCORING',
    [{
        input: '2 1 10\n\
1 3 8',
        output: ''
    }
    ],
    function () {

        var tokens = readline().split(' ');
        var n = +tokens[0];
        var m = +tokens[1];
        var p = +tokens[2];

        var scoreArray = [];
        for (var i = 0; i < n; i++) {
            var candidate = { id: i + 1, scores: [], total: 0 };
            for (var j = 0; j < p; j++) {
                candidate.scores[j] = 0;
            }
            scoreArray.push(candidate);
        }

        for (var i = 0; i < m; i++) {
            var tokens = readline().split(' ');
            var candidate = scoreArray[+tokens[0] - 1];
            var scores = candidate.scores;
            var problem = +tokens[1] - 1;
            var score = +tokens[2];
            if (scores[problem] < score) {
                candidate.total += score - scores[problem];
                scores[problem] = score;
            }
        }

        scoreArray.sort(function (c1, c2) {
            if (c2.total == c1.total) {
                return c1.id - c2.id;
            }
            return c2.total - c1.total;
        })

        for (var i = 0; i < n; i++) {
            var candidate = scoreArray[i];
            var result = [candidate.id, candidate.total];
            for (var j = 0; j < p; j++) {
                result.push(candidate.scores[j]);
            }
            print(result.join(' '));
        }
    }
);

/*
Link: http://www.spoj.com/EIUPOA15/problems/EIUGAME/
*/