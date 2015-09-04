#include <bits/stdc++.h>
using namespace std;
 
int T,N,M,countMAX;
long MAX,temp;
 
int main(){
    cin>>T;
    for(int it = 1; it <= T; ++it){
       cin>>N>>M;
       MAX = -(long)1e10;
       for(int i = 0; i < N; ++i){
          for(int j = 0; j < M; ++j){
                  cin>>temp;
                  if(temp > MAX){
	    	              MAX = temp;
		                  countMAX = 1;
                  } else if(temp == MAX) {
		                 ++countMAX;
                  }
          }
       }
       cout<<MAX<<" "<<countMAX<<endl;
    }
    return 0;
} 
