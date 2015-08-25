registerProblem('EIUPOA15_EIMAX',
    [{
        input: 'SIZEROXSIEVDEMX',
        output: ''
    }
    ],
    function () {
        function convert(words) {
            return words.map(function (word) {
                return word.split('').map(function (char) { return char.charCodeAt(0); }).reverse();
            });
        }

        var nums = [convert(['ZERO', 'UNU', 'DOI', 'TREI', 'PATRU', 'CINCI', 'SASE', 'SAPTE', 'OPT', 'NOUA'])
                    , convert(['SIFIR', 'BIR', 'IKI', 'UC', 'DORT', 'BES', 'ALTI', 'YEDI', 'SEKIZ', 'DOKUZ'])
                    , convert(['ZERO', 'JEDEN', 'DWA', 'TRZY', 'CZTERY', 'PIEC', 'SZESC', 'SIEDEM', 'OSIEM', 'DZIEWIEC'])
                    , convert(['ZERO', 'ONE', 'TWO', 'THREE', 'FOUR', 'FIVE', 'SIX', 'SEVEN', 'EIGHT', 'NINE'])];
        var nZ = 'Z'.charCodeAt(0);

        var input = readline().split('').map(function (char) { return char.charCodeAt(0); }).reverse();
        var map = [[]];
        map[0][nZ] = undefined;
        map[0][input[0]] = -1;
        var inputLen = input.length;
        for (var i = 1; i < inputLen; i++) {
            var mapi1 = map[i - 1];
            var mapi = [];
            for (var j = 0; j <= nZ; j++) {
                mapi[j] = mapi1[j];
            }
            mapi[input[i]] = i - 1;
            map.push(mapi);
        }

        function find(map, num, i) {
            var last = num.length - 1;
            do {
                i = map[i][num[last]];
                last--;
            } while (last >= 0 && k !== undefined);
            return i;
        }

        var maxValues = [{ len: 0, num: 0, pre: -1 }];
        var maxValuesArray = [[{ len: 0, num: 0, pre: -1 }]
                                , [{ len: 0, num: 0, pre: -1 }]
                                , [{ len: 0, num: 0, pre: -1 }]
                                , [{ len: 0, num: 0, pre: -1 }]];

        function setMaxValues(values, d, i, pre) {
            if (!values[i] || !values[pre].len) values[i] = { len: 0, num: d, pre: pre };
            var vi = values[i];
            var vpre = values[pre];
            if (vi.len < vpre.len + 1
                || (vi.len == vpre.len + 1 && vi.num < d)
                || (vi.len == vpre.len + 1 && vi.num == d && vi.pre < pre)) {
                vi.len = vpre.len + 1;
                vi.num = d;
                vi.pre = pre;
            }

        }

        for (var i = 0; i < inputLen; i++) {
            for (var d = 0; d < 10; d++) {
                for (var k = 0; k < 4; k++) {
                    var t = find(map, nums[k][d]);
                    if (t >= 0) {
                        setMaxValues(maxValuesArray[k], d, i, t);
                        setMaxValues(maxValues, d, i, t);
                    }
                }
            }
        }

        function getValue(maxValues) {
            var resultArray = [];
            var i = maxValues.length - 1;
            while (i >= 0) {
                resultArray.push(maxValues[i].num);
                i = maxValues[i].pre;
            }
            return resultArray.reverse().join('');
        }

        var maxValue = '';
        for (var k = 0; k < 4; k++) {
            var value = getValue(maxValuesArray[k]);
            if (value.length > maxValue.length || (value.length == maxValue.length && value > maxValue)) {
                maxValue = value;
            }
        }

        print(maxValue + ' ' + getValue(maxValues));
    }
);

/*
Link: http://www.spoj.com/EIUPOA15/problems/EIMAX/
*/