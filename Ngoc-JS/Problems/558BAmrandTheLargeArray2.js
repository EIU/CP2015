registerProblem('558B Amr and The Large Array - Sorting',
    [{
        input: '5\n\
                1 1 2 2 1',
        output: '1 5'
    },
    {
        input: '5\n\
                1 2 2 3 1',
        output: '2 3'
    },
    {
        input: '6\n\
                1 2 2 1 1 2',
        output: '1 5'
    }
    ],
    function () {

        Node = function (position, value) {
            this.position = position;
            this.value = value;
            this.toString = function () {
                return '(' + this.position + ', ' + this.value + ')';
            };
        };

        n = +readline();
        numbers = readline().split(' ');
        nodes = [];
        for (i = 0; i < n; i++) {
            nodes[i] = new Node(i, +numbers[i]);
        }
        nodes.sort(function (n1, n2) {
            if (n1.value != n2.value) return n1.value - n2.value;
            return n1.position - n2.position;
        });

        max = 1;
        mright = mleft = nodes[0].position;
        count = 1;
        left = right = nodes[0].position;
        for (i = 1; i < n; i++) {
            if (nodes[i].value != nodes[i - 1].value) {
                left = right = nodes[i].position;
                count = 1;
            } else {
                right = nodes[i].position;
                count++;
            }
            if (max < count) {
                max = count;
                mleft = left;
                mright = right;
            } else if (max == count && mright - mleft > right - left) {
                mleft = left;
                mright = right;
            }

        }

        print((mleft + 1) + ' ' + (mright + 1));


    }
);
