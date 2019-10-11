/**
* \ file main.cpp
* \ author Olivier Cote et Yoan David
* \ brief
*/

#include "vector.h"
#include "deque.h"

int main(){
	/*int intTab[10]{ 999,299,399,499,599,699,799,899,999,1099 };
		vector<int> vect(3);


		vect.afficher();
		cout << "vect front " << vect.front() << endl;
		cout << "vect back " << vect.back() << endl;
		cout << "--------------------------------------------" << endl << endl;

		for (int i = 0; i < 10; i++)
		{
			vect.push_back(intTab[i]);
			vect.afficher();
			cout << "vect front " << vect.front() << endl;
			cout << "vect back " << vect.back() << endl;
			cout << "--------------------------------------------" << endl << endl;
		}*/

	int intTab[10]{ 199,299,399,499,599,699,799,899,999,1099 };
	deque<int> deq(3);


	//deq.afficher();
//	cout << "deq front " << deq.front() << endl;
	//cout << "deq back " << deq.back() << endl;
	cout << "--------------------------------------------" << endl << endl;

	/*for (int i = 0; i < 10; i++)
	{
		deq.push_back(intTab[i]);
		deq.afficher();
		cout << "deq front " << deq.front() << endl;
		cout << "deq back " << deq.back() << endl;
		cout << "--------------------------------------------" << endl << endl;
	}*/
	deque<int> deq2(8);
	deq2.afficher();
	cout << "--------------------------------------------" << endl << endl;
	for (int i = 0; i < 10; i++)
	{
		deq2.push_front(intTab[i]);
		deq2.afficher();
		cout << "deq front " << deq2.front() << endl;
		cout << "deq back " << deq2.back() << endl;
		cout << "--------------------------------------------" << endl << endl;
	}


		system("PAUSE");
}
