#include<iostream>
#include"set.h"
using namespace std;

int main(){
    cout << "_____ TEST 1 _____";

    set<int> test1;
    set<int>::iterator it = test1.begin();
    
    test1.insert(0);
    test1.insert(10);
    test1.insert(8);
    test1.insert(20);

    test1.afficher();
}
