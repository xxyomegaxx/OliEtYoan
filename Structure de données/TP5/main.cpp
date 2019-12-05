#include <iostream>
#include "map.h"

using namespace std;

int main() {

    map<int,int> test;
    test.insert(50);
    test.insert(40);
    test.insert(30);
    test.insert(20);
    test.insert(10);

	map<int, int> test2;
	test2.insert(10);
	test2.insert(20);
	test2.insert(40);
	test2.insert(50);
	test2.insert(3);

    map<int,int>::iterator it = test2.lower_bound(55);


//    map<int,int>::iterator itor = test.begin();
//    cout << itor->first;

    cout << it->first;

//    test.find(20);
    test.afficher();
	test2.afficher();

}