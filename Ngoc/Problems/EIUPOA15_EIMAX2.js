registerProblem('EIUPOA15_EIMAX',
    [{
        input: 'SIZEROXSIEVDEMX',
        output: '66'
    }
    ],
    function () {

        var nA = 'A'.charCodeAt(0);
        var nZ = 'Z'.charCodeAt(0) - nA;
        var nums = ['ZERO', 'ONE', 'TWO', 'THREE', 'FOUR', 'FIVE', 'SIX', 'SEVEN', 'EIGHT', 'NINE'].map(function (word) {
            return word.split('').map(function (char) { return char.charCodeAt(0) - nA; });
        });

        var input = readline().split('').map(function (char) { return char.charCodeAt(0) - nA; });
        var inputLen = input.length;

        var map = [];
        var mapi = map[inputLen] = [];
        for (var j = 0; j <= nZ; j++) {
            mapi[j] = inputLen;
        }

        for (var i = inputLen - 1; i >= 0; i--) {
            var mapi1 = map[i + 1];
            var mapi = map[i] = [];
            for (var j = 0; j <= nZ; j++) {
                mapi[j] = mapi1[j];
            }
            mapi[input[i]] = i;
        }

        function find(num, i) {
            var nDigit = num.length;
            for (var j = 0; j < nDigit && i < inputLen; j++) {
                i = map[i][num[j]] + 1;
            }

            return (j == nDigit && i <= inputLen) ? i : false;
        }

        var maxValues = [];
        for (var i = 0; i <= inputLen; i++) {
            maxValues[i] = { len: 0, num: -1, next: inputLen };
        }

        function setMaxValues(d, i, next) {
            var vi = maxValues[i];
            var vNext = maxValues[next];
            if (vi.len < vNext.len + 1
                || (vi.len == vNext.len + 1 && vi.num < d)
                /*|| vi.len == vNext.len + 1 && vi.num = d && vi.next > next => for multiple languages*/
                ) {
                vi.len = vNext.len + 1;
                vi.num = d;
                vi.next = next;
            }
        }

        for (var i = inputLen - 1; i >= 0; i--) {
            for (var d = 9; d >= 0; d--) {
                if (i == 0 && d == 0) break;
                var next = find(nums[d], i);
                if (next) {
                    setMaxValues(d, i, next);
                }
            }
        }

        function getValue() {
            var resultArray = [];
            var i = 0;
            while (i < inputLen && maxValues[i].len > 0) {
                resultArray.push(maxValues[i].num);
                i = maxValues[i].next;
            }
            return resultArray.join('');
        }

        print(getValue());
    }
);

/*
ZERONEZEROZEROZEROZEROZEROZEROZEROONE
ONEZEROZEROZEROZEROZEROZEROZEROONE
Link: http://www.spoj.com/EIUPOA15/problems/EIMAX/
*/