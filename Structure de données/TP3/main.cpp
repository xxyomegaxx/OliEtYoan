#include<iostream>
#include"list.h"
using namespace std;

int main()
{

	list<int> test;
	test.push_back(3);
	test.push_back(4);
	test.push_back(5);
	test.push_front(2);
	test.push_front(0);

	list<int>::iterator it(test.end());
	test.insert(it, 1);

	it = test.end();
	// test.erase(it);
	test.afficher();


	list<int> test2;
	test2.push_back(33);
	test2.push_back(44);
	test2.push_back(55);
	test2.push_back(66);
	test2.push_back(77);
	test2.push_back(88);
	test2.push_back(99);
	test = test2;
	test.afficher();



	test.resize(3,4);
	test.afficher();
	test.resize(5,4);
	test.afficher();

	list<int>::iterator it2(test.begin());
	it2++;
	it2++;
	test.splice(it2, test2);
	test.afficher();
	test2.afficher();
	test.reverse();
	test.afficher();


	system("PAUSE");


}