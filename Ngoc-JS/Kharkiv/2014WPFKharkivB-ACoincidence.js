registerProblem('2014 Winter Programming School, Kharkiv, day 1 (E. Kapun). Junior league. Problem B - A Coincidence',
       [{
           input: '3\n\
                   2 1 3',
           output: '2.0'
       }, {
           input: '3\n\
                   3 1 2',
           output: '3.0'
       }],

    function () {
        n = +readline();
        array = readline().split(' ');
        diff = 0;
        for (i = 0; i < n; i++) {
            if (+array[i] !== (i + 1)) diff++;
        }
        print(diff);
    }


    /*
        Problem B: http://codeforces.com/gym/100370
 
        Lemma 1: Let A1 = (a1, a2, a3,...an) is a permutation of (1, 2, 3,...n). There is average 1 position i that ai = i
 
        Prove: Let A2 = (an, a1, a2, a3,...a(n-1)), A3(a(n-1), an, a1, a2, a3,...a(n-1)), ... An(...)
        Each value ai will be in ai position exactly one time in (A1, A2, A3,...An)
 
        Solution:
        + Let Designed Permutation is D = (d1, d2, d3,...dn)
        + Step i: we select Si = {aj | aj != dj}, stir them
        + From Lemma 1: we imply in average |Si| = |S(i-1)| +1 
        => Result will be |S0|
    */
);