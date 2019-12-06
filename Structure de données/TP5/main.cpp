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
	test.insert(5);
	test.insert(1);
	test.insert(320);
	test.insert(202);
	test.insert(103);

	map<int, int> test2;
	test2.insert(10);
	test2.insert(20);
	test2.insert(40);
	test2.insert(50);
	test2.insert(3);
	test.afficher();

    map<int,int>::iterator it = test.find(202);
	test.insert(it, 201);

	test.afficher();

	it = test.find(321);
	test.insert(it, 321);
	test.afficher();

	it = test.find(324);
	test.insert(it, 324);


	map<int, int>::iterator it2 = test.end();



//    map<int,int>::iterator itor = test.begin();
//    cout << itor->first;

    //cout << it->first;

//    test.find(20);
    test.afficher();

}