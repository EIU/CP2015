registerProblem('2014 Winter Programming School, Kharkiv, day 1 (E. Kapun). Junior league. Problem D - A Fish Lunch',
    [{
        input: '10 8 7 8 60',
        output: ''
    }, {
        input: '10 5 5 10 50',
        output: ''
    }, {
        input: '10 8 7 8 60',
        output: ''
    }, {
        input: '1000 500 500 600 50',
        output: ''
    }, {
        input: '1000 500 500 600 80',
        output: ''
    }, {
        input: '1000 500 500 600 90',
        output: ''
    }, {
        input: '1000 400 500 600 90',
        output: ''
    }, {
        input: '1000 300 500 600 90',
        output: ''
    }],

    function () {
        var arr = readline().split(' ');
        var n = +arr[0];
        var n2 = +arr[1];
        var n1 = +arr[2];
        var i = +arr[3];
        var p = +arr[4] / 100;

        var status = [1];
        var newStatus = [];
        for (var u = 1; u <= n1; u++) {
            status[u] = 0;
        }
        for (var j = 1; j < i; j++) {
            newStatus[0] = status[0] * (1 - p);
            for (var u = 1; u < n1; u++) {
                newStatus[u] = status[u - 1] * p + status[u] * (1 - p);
            }
            newStatus[n1] = status[n1 - 1] * p + status[n1];
            var temp = newStatus;
            newStatus = status;
            status = temp;
        }
        var res = Math.abs(1 - status[n1]);
        print(res.toFixed(9));
    }

    /*
        Problem D: http://codeforces.com/gym/100370

        Solution: At step j, status[u] is probability of there are u passengers choose fish
    */
);
