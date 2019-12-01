#include <iostream>
#include "map.h"

using namespace std;

int main() {

    map<int,int> test;
    test.insert(10);
    test.insert(20);
    test.insert(3);
    test.insert(0);
    test.insert(40);
    test.insert(4);

    map<int,int>::iterator it = test.find(45);

//    map<int,int>::iterator itor = test.begin();
//    cout << itor->first;

    cout << it->first;

//    test.find(20);
    test.afficher();

}