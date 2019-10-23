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

	test.afficher();
	system("PAUSE");


}