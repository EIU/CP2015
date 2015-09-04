registerProblem('Review JavaScript',
    [{
        input: '',
        output: ''
    }, {
        input: '',
        output: ''
    }],
    function () {

        // Thinking like Java/C#/C++     Plus     Google     Plus     Keyword: JavaScript/JScript

        // IDE + Debug: Visual Studio + Chrome/IE/FF - Developer Tools - F12 - Console

        // Docs: https://developer.mozilla.org/en-US/docs/Web/JavaScript

        // Tutorial: https://www.codecademy.com/tracks/javascript

        //Variable: Replace [int, String, Student...] by [var, '']

        // Number
        var i = 3;
        m = 5;
        n = i + m;
        f = 9.0;
        f = i / 2;
        T = 0x0040;

        // String
        s1 = "String Value";
        var s2 = 'String " \' Value';

        // Object and JSON
        var o1 = new Object();
        o2 = Object();
        o3 = {};
        o4 = { name: 'Ngoc', role: 'Lecture' };
        o5 = { 'na   me': 'Thinh', 'role': 'Student' };

        o1.AAAA = "THINK";
        o1['AAAA'] = "PANADOL";

        // Array
        array = new Array();
        var a2 = Array();
        a3 = [];
        for (i = 0; i < 1000; i++) {
            array[i] = i;
        }
        for (i = 0; i < 1000; i++) {
            a3.push(i);
        }

        // Date
        var date = new Date();

        // Funtion
        function f1(p1, p2) {
            return p1 + p2;
        }

        var f2 = function () {
            console.log(f1(2,3));
        }

        f3 = f2;


        // Operations
        n = i + m;
        s2 = s1 + n;
        s3 = n + m + s1;
        s3 += o1;

        // Control Statement: if for while do switch case

        // Regular Expression

        // Object Oriented Programming

        // HTML DOM  & CSS

        // JQuery

        // Bootstrap

        // Tools: http://jsbeautifier.org/, http://ideone.com/, http://codeforces.com/problemset/customtest
    }
);


/*
        countries = {};
        $('tr.user').each(function (index, ele) {
            var team = $(this).find('td.team img').attr('title');
            var score = +$(this).find('td.score.global').text().trim();
            countries[team] = (countries[team] || 0) + score;
        })
        var arr = [];
        for (key in countries) {
            arr.push({
                country: key, score: countries[key]
            });
        }
        arr.sort(function (c1, c2) {
            return c2.score - c1.score;
        })

        i = 1; s = '';
        arr.forEach(function (ele) {
            s += (i++) + '.\t' + ele.country + '\t' + Math.round(ele.score) + '\n';
        });
        s;

1.	Korea	1951
2.	China	1917
3.	Russian Federation	1901
4.	United States of America	1870
5.	Japan	1731
6.	Iran	1694
7.	Poland	1592
8.	Kazakhstan	1557
9.	Vietnam	1553
10.	Australia	1548
11.	Taiwan	1492
12.	Thailand	1467
13.	Romania	1457
14.	Bulgaria	1439
15.	Serbia	1410
16.	Croatia	1395
17.	Turkey	1391
18.	Singapore	1366
19.	Canada	1347
20.	Hong Kong	1336
21.	Belarus	1299
22.	Ukraine	1274
23.	Finland	1231
24.	Slovakia	1170
25.	India	1137
26.	Latvia	1088
27.	Indonesia	1064
28.	Italy	1018
29.	Germany	1011
30.	Georgia	1005
31.	Hungary	991
32.	South Africa	910
33.	Sweden	893
34.	Israel	884
35.	Brazil	839
36.	Lithuania	833
37.	Armenia	833
38.	Norway	799
39.	United Kingdom	798
40.	Czech Republic	766
41.	Austria	766
42.	New Zealand	758
43.	Greece	720
44.	Switzerland	714
45.	Estonia	690
46.	France	689
47.	Moldova	658
48.	Tajikistan	643
49.	Belgium	640
50.	Turkmenistan	634
51.	Spain	597
52.	Mexico	570
53.	Syria	570
54.	Malaysia	535
55.	Bangladesh	528
56.	Luxembourg	513
57.	Macedonia	505
58.	Netherlands	476
59.	Egypt	470
60.	Philippines	431
61.	Kyrgyzstan	428
62.	Denmark	394
63.	Tunisia	389
64.	Macau	378
65.	Portugal	365
66.	Mongolia	346
67.	Chile	343
68.	Argentina	322
69.	Cyprus	317
70.	Bosnia and Herzegovina	301
71.	Colombia	299
72.	Montenegro	299
73.	Sri Lanka	282
74.	Slovenia	224
75.	Ireland	207
76.	Bolivia	204
77.	Jordan	203
78.	Azerbaijan	184
79.	Dominican Republic	158
80.	Cuba	156
81.	Uzbekistan	138
82.	Venezuela	90
83.	Nigeria	0
*/
