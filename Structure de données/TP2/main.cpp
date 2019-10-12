/**
* \ file main.cpp
* \ author Olivier Cote et Yoan David
* \ brief
*/

#include "vector.h"
#include "deque.h"

int main(){

	cout << "------------------Vector--------------------------" << endl << endl;
	int intTab[10]{ 999,299,399,499,599,699,799,899,999,1099 };
		vector<int> vect(3); //test constructeur


		for (int i = 0; i < 10; i++)
		{
			vect.push_back(intTab[i]);
			cout << "ajout dans le vector de l'element " << i<< endl;
			vect.afficher(); 

			cout << "vect front " << vect.front() << endl; // test la fonction front()
			cout << "vect back " << vect.back() << endl;  //test la fonction back()
			cout << "--------------------------------------------" << endl;
		}
		cout << "test operator[] vec[0] = " << vect[0]<<endl; //test operator []




		cout <<endl<< "------------------Deque--------------------------" << endl << endl;

		int intTab2[10]{ 100,200,300,400,500,600,700,800,900,1000 };

	deque<int> deq(3); //test constructeur avec vlaeur en parametre
	deque<int> deq2(3);

	cout <<endl<< "-------------------PushBack-------------------------" << endl << endl;
	for (int i = 0; i < 10; i++)
	{
		deq.push_back(intTab[i]); // test le pushback du deque
		deq2.push_back(intTab2[i]);
		deq.afficher();
		cout << "deq front " << deq.front() << endl; // test de deque.front()
		cout << "deq back " << deq.back() << endl; //test de deque.back()
		cout << "--------------------------------------------" << endl << endl;
	}
	cout << endl << "-------------------swap-------------------------" << endl << endl;
	deq.swap(deq2); // test le swap du deque
	deq.afficher();
	
	cout << endl << "-------------------Constructeur copie-------------------------" << endl << endl;
	deque<int> deq4(deq2); // test le constructeur de copie
	deq4.afficher();

	cout << endl << "-------------------operator ==-------------------------" << endl << endl;
	deque<int> deq5(10); // test le constructeur de copie
	deq5 = deq2;
	deq5.afficher();


	cout << endl<<"-------------------PushFront-------------------------" <<  endl;
	deque<int> deq3(8);
	
	// Le Push Front compile ,mais ne fonctionne pas parfaitement.
	for (int i = 0; i < 10; i++)
	{
		deq3.push_front(intTab[i]);
		deq3.afficher();
		cout << "deq front " << deq3.front() << endl;
		cout << "deq back " << deq3.back() << endl;
		cout << "--------------------------------------------" << endl << endl;
	}


		system("PAUSE");
}
