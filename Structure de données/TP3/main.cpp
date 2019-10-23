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
	list<int>::iterator it(test.begin());
	it++;
	test.insert(it, 1);
	it = test.begin();
	test.erase(it);
	test.afficher();
	system("PAUSE");


}