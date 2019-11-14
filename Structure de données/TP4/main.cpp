#include<iostream>
#include"set.h"
using namespace std;

int main(){
    cout << "_____ TEST 1 _____";

    set<int> test1;
    set<int>::iterator it = test1.begin();
    
    test1.insert(0);
    test1.erase(it);
    
    test1.afficher();
    
//    set<int>::iterator it1=test1.begin();
//    while(it1 != test1.end()){
//        cout << *it1 << endl;
//        it1++;
//    }

}
