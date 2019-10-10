/**
* \ file main.cpp
* \ author Olivier Cote et Yoan David
* \ brief
*/

#include "vector.h"
#include "deque.h"

int main(){
	int intTab[10]{ 999,299,399,499,599,699,799,899,999,1099 };
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
		}


		system("PAUSE");
}
