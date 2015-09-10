#include <cstdio>
#include <cstdlib>
#include <iostream>
#include <string.h>
#include <map>
#include <vector>
#include <algorithm>
#include <queue>
#include <deque>

using namespace std;

FILE *input;
FILE *output;

int main()
{       
    #ifdef ONLINE_JUDGE
        input = fdopen(0, "r");
        output = fdopen(6, "w");
    #else
        input = stdin;
        output = stdout;
    #endif
       
    fprintf(output, "1\n1\nData\n0\n");
    
    char buffer[4096];
    while(fgets(buffer, 4096, input) != NULL){
        int len=strlen(buffer)-1;
        fprintf(output, "%s", buffer);
    }
    
    fclose(output);        
    return 0;
}