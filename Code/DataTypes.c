#include <stdio.h>

typedef struct human_type {
	int id;
	char name[100];
} Human;

int playWithDataTypes() {
	// Type Casting: Among Pointer Types
	//		It Doesn't Create Side Effect for Pointer Value
	//		But It Creates Side Effect On
	//			Derefencing Pointer Value
	
	// This Is Valid Code
	int a 		= 20;
	int *ptr 	= &a;
	char *cptr 	=  (char *) ptr;
	float *fptr =  (float *) cptr;
	double *dptr = (double *) fptr;
	void *vptr 	 = (void *) dptr;

//	Following These Lines Are Also INValid
	// printf("\nValues Are: %d %d", a, *vptr);
	
//	Following These Lines Are Also Valid
	printf("\nValues Are: %d %d", a, *(int *) vptr);
	printf("\n%ul %ul %ul %ul %ul", ptr, cptr, fptr, dptr, vptr);
	
//	Following These Lines Are Also INValid
	// Because It was Sideeffects for Derefenced Valued
	printf("\n%d %d %d %d %d", *ptr, *cptr, *fptr, *dptr);

//	Following These Lines Are Also Valid
	printf("\n%d %d %d %d %d %d", *ptr, *(int *)cptr, *(int *)fptr, *(int *)dptr, *(int *)vptr);
}

int playWithDataTypesOnceMore() {
	int a 		= 20;
	int *ptr 	= &a;
	char *cptr 	=  (char *) ptr;
	float *fptr =  (float *) cptr;
	double *dptr = (double *) fptr;
	void *vptr 	 = (void *) dptr;

	printf("\n ptr :%ld %ld", ptr, ++ptr);
	printf("\ncptr :%ld %ld", cptr, ++cptr);
	printf("\nfptr :%ld %ld", fptr, ++fptr);
	printf("\ndptr :%ld %ld", dptr, ++dptr);
	printf("\nvptr :%ld %ld", vptr, ++vptr);
}

// Can We Store Heterogenous Types Data in Same C Array?
int playWithArrays() {
	int something[10] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 } ;

	char c = 'A';
	int a = 20;
	float f = 40.9;
	double d = 89.99;
	Human h = { 101, "Gabbar Singh" };

	// Generic Collection
	//		Collection Which Store Any Type Object	
	
	// Because Pointer Type Casting Never Creates Side Effects
	//		For Pointer Values

	void * collection[5] = { &c, &a, &f, &d, &h };
	printf("\n%c", *(char *) 	collection[0]);
	printf("\n%d", *(int *) 	collection[1]);
	printf("\n%f", *(float *) 	collection[2]);
	printf("\n%lf", *(double *)	collection[3]);
	printf("\n%d %s", ((Human *)collection[4])->id, 
					  ((Human *)collection[4])->name);
	//In Java
	// Array of void * Pointers
	// Object [] container = new Object[10];
	// You can Store Only Java Objects
	//		objects are created with Object Type/It's SubTypes
	// Primitive Types In Java Creates Variables
	//		e.g. int a = 10;
	// 		You can't store in container

	// ArrayList<String> stringArray = new ArrayList<String>(10);
	// Type Erasing
	//  	<String> will erased at Compile Times
	//		Container is of Objects Container
	//		Rather Than String Type Container

	// Will Generate Internally Container of Object[] Object List
	// 	*(String *) Object[0];
	// 	*(String *) Object[1];


	Employee * e1 = (Employee *) malloc(sizeof(Employee));
	Employee * e2 = (Employee *) malloc(sizeof(Employee));
	
	Employee * collection[5] = { e1, e2 };
	printf("\n%c", *(Employee *) collection[0]);
	printf("\n%d", *(Employee *) collection[1]);
	
	Manager * m1 = (Manager *) malloc(sizeof(Manager));
	Manager * m2 = (Manager *) malloc(sizeof(Manager));

	collection[0] = { m1 };
	printf("\n%c", *(Employee *) collection[0]);

	//In Java

}

int main() {
	printf("\nFunction: playWithDataTypes");
	playWithDataTypes();

	printf("\nFunction: playWithDataTypesOnceMore");
	playWithDataTypesOnceMore();

	printf("\nFunction: playWithArrays");
	playWithArrays();

	// printf("\nFunction: \n");
	// printf("\nFunction: \n");
}

