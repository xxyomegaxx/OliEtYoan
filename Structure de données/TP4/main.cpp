#include<iostream>
#include"set.h"
using namespace std;

int main(){
    cout << "_____ TEST 1 ______test";

    set<int> test1;
    set<int>::iterator it = test1.begin();
  
    test1.insert(10);
	test1.insert(19);
	test1.insert(11);
	test1.insert(9);
	test1.insert(3);



    test1.afficher();
	
	system("PAUSE");

	set<int>::iterator ito = test1.find(11);

	test1.erase(ito);
	test1.afficher();
	system("PAUSE");

	test1.clear();

	test1.afficher();

	system("PAUSE");

	test1.insert(10);
	test1.insert(40);
	test1.insert(50);
	test1.insert(30);
	test1.insert(20);

	set<int>::iterator iti = test1.find(11);
	set<int>::iterator ita= test1.find(40);

	test1.insert(ita, 39);
	test1.insert(ita, 55);

	test1.afficher();
	system("PAUSE");
	set<int>::iterator Fethi = test1.lower_bound(3);
	Fethi = test1.upper_bound(30);
	Fethi = test1.upper_bound(10);
	Fethi = test1.upper_bound(40);
	Fethi = test1.upper_bound(39);
	test1.afficher();










}
