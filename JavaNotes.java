
- Polymorphism : API Becomes Far More Flexible
	- You can always assign an object to a variable whose type is an implemented interface, or pass it to a method expecting such an interface.

Type of Type
	Definition: Type, Subtype, Supertype
___________________________________________________________________
	A type S is a supertype of the type T (the subtype) when any value of the subtype can be assigned to a variable of the supertype without a conversion.

In Java
___________________________________________________________________
	- However, Java is an object-oriented language where (just about) everything is an object. 
	- There are no function types in Java. 
		- Instead, functions are expressed as objects, instances of classes that implement a particular interface (Functional)
		- Lambda expressions give you a convenient syntax for creating such instances

	Java is Not Functional Language
	________________________________________________________________
	Because Lambda is Not Fundamental Buidling Block In Java

	Lamdba Syntax In Java
	________________________________________________________________

	(String first, String second) -> first.length() - second.length()
 	
 	Runnable task = () -> { for (int i = 0; i < 1000; i++) doWork(); }

 	Comparator<String> comp = (first, second) -> first.length() - second.length();

Functional Interfaces
________________________________________________________________

- There are many interfaces in Java that express actions,
such as Runnable or Comparator. Lambda expressions are compatible with these.

- You can supply a lambda expression whenever an object of an interface with a single abstract method is expected. Such an interface is called a functional interface
	
	SAM Interfaces
		Single Abstract Method Intefaces
		Expressed Elegantly Using Lambda Expression

 Arrays.sort(words, 
 	(first, second) -> first.length() - second.length() );

- Behind the scenes, the second parameter variable of the
Arrays.sort method receives an object of some class that implements Comparable Interface
Invoking the compare method on that object executes the body of the lambda expression. 

- The management of these objects and classes is completely
implementation-dependent and highly optimized.

- In most programming languages that support function literals, you can declare function types such as
(String, String) -> int, declare variables of those types, put functions into those variables, and invoke them. 

- In Java, there is only one thing you can do with a lambda expression: put it in a variable whose type is a functional interface, so that it is converted to an instance of that interface.

________________________________________________________________

- The Java API provides a large number of functional interfaces

	- One of them is
 
		public interface Predicate<T> {
		       boolean test(T t);
			- // Additional default and static methods
		}

- The ArrayList class has a removeIf method whose parameter is a Predicate. It is specifically designed for receiving a lambda expression.

- For example, the following statement removes all null values from an array list:
	
		 removeIf( Lambda ) 
		// Lambda is Object of Class Which Implements Predicate Interface
	
	list.removeIf(e -> e == null);

________________________________________________________________
. Operator [ Member Access Operator]
VS 
The :: operator [Reference Access Operator]
________________________________________________________________
The :: operator separates the method name from the name of a class or object. There are three variations:

1. Class::instanceMethod 
2. Class::staticMethod 
3. object::instanceMethod

- In the first case, the first parameter becomes the receiver of the method, and any other parameters are passed to the method.

- For example, String::compareToIgnoreCase is the same as 
		(x, y) -> x.compareToIgnoreCase(y).

- In the second case, all parameters are passed to the static method.

- The method expression Objects::isNull is equivalent to 
		x -> Objects.isNull(x).

- In the third case, the method is invoked on the given object, and the parameters are passed to the instance method.

	Therefore, System.out::println is equivalent to 
		x -> System.out.println(x).

Constructor Reference
________________________________________________________________
	Employee::new

Fundamentally [System Perceptive]
________________________________________________________________
	Lambda Can Be Used As Buidling Blocks
	To Simulate Whole System

Lambda Use Cases [Programmer Perceptive]
________________________________________________________________
- The point of using lambdas is deferred execution.

- There are many reasons for executing code later, such as: 
	• Running the code in a separate thread 
	• Running the code multiple times 
	• Running the code at the right point in an algorithm (for example, the comparison operation in sorting) 
	• Running the code when something happens (a button was clicked, data has arrived, and so on) 
	• Running the code only when necessary

	
	// Repeat Usage
	// () -> System.out.println("Hello, World!")
	// Lambda: Which doesn't take any argument and with body

	repeat(10, () -> System.out.println("Hello, World!") );

	// Repeat Implementation
	public static void repeat(int n, Runnable action) {
       for (int i = 0; i < n; i++) action.run();
    }

    // One More Example_____________________________
	
	public interface IntConsumer {
       void accept(int value);
   	}

 	public static void repeat(int n, IntConsumer action) {
       for (int i = 0; i < n; i++) action.accept(i);
    }

	//Write This Way : Elegance In Code
	repeat(10, i -> System.out.println("Countdown: " + (9 - i)));
	// Above Line Will Internally Generate Following Code

	// Or Do This Ways
	class Something implements IntConsumer {
		void accept(int value) {
			System.out.println(value);
		}
	}

	Something some = new Something();
	repeat(10, some) ;	

________________________________________________________________
GENERICS IN JAVA
________________________________________________________________
#Java Generics
________________________________________________________________

- You often need to implement classes and methods that work with multiple types.

- an ArrayList<T> stores elements of an arbitrary class T. We say that the ArrayList class is generic, and T is a type parameter.

- In any programming language with generic types, the details get tricky when you restrict or vary type parameters. For example, suppose you want to sort elements. Then you must specify that T provides an ordering.

- Furthermore, if the type parameter varies, what does that mean for the generic type?

- For example, what should be the relationship between ArrayList<String> to a method that expects an ArrayList<Object>?

- In Java, generic programming is more complex than it perhaps should be, because generics were added when Java had been around for a while, and they were designed to be backward-compatible.

- As a consequence, there are a number of unfortunate restrictions, some of which affect every Java program- mer. Others are only of interest to implementors of generic classes.

- The Key Points

 	1. A generic class is a class with one or more type parameters. 
	2. A generic method is a method with type parameters. 
	3. You can require a type parameter to be a subtype of one or more types. 
	
	4. Generic types are Invariant: 
			When S is a subtype of T. 
			There is no relationship between G<S> and G<T>. 

	5. By using wildcards G<? extends T> or G<? super T> 
		method can accept an instantiation of a generic type superclass argument.
			Function APIs

	6. Type parameters are erased when generic classes compiled. of T, there is no can specify that a with a subclass or and methods are 
	
	7. Erasure puts many restrictions on generic types. 

		In particular, you can’t instantiate generic classes or arrays, cast to a generic type, or throw an object of a generic type. 

	8. The Class<T> class is generic, which is useful because methods such as cast are declared to produce a value of type T. 
	
	9. Even though generic classes and methods are erased in the virtual machine, you can find out at runtime how they were declared.


# Generic Classes
________________________________________________________________

- A generic class is a class with one or more type parameters.

- As a simple example, consider this class for storing key/value pairs:

	public class Entry<K, V> {
	       private K key;
	       private V value;

	       public Entry(K key, V value) {
	           this.key = key;
	           this.value = value;
	       }

	       public K getKey() { return key; }
	       public V getValue() { return value; }
	}

- Type parameters K and V are specified inside angle brackets after the name of the class. 
	- In the definitions of class members, they are used as types for instance variables, method parameters, and return values.

- You instantiate the generic class by substituting types for the type variables.

- For example, 
		Entry<String, Integer> is an ordinary class with 
		methods String getKey() and Integer getValue().

- CAUTION: 
	Type parameters cannot be instantiated with primitive types. 
	For example, Entry<String, int> is not valid in Java.

- When you construct an object of a generic class, you can omit the type parameters from the constructor

 	Entry<String, Integer> entry = new Entry<>("Fred", 42);
 	Entry<String, Integer> entry = new Entry<String, Integer>("Fred", 42);

- still provide an empty pair of angle brackets before the construction arguments. Some people call this empty bracket pair a diamond. When you use the diamond syntax, the type parameters for the constructor are inferred.


# Generic Methods
________________________________________________________________

- Just like a generic class is a class with type parameters, a generic method is a method with type parameters. 
		-A generic method can be a method of a regular class or a generic class.

- an example of a generic method in a class that is not generic:
	public class Arrays {
				//Placeholder
	       public static <T> void swap(T[] array, int i, int j) {
	           T temp = array[i];
	           array[i] = array[j];
	           array[j] = temp;
			} 
	}

- This swap method can be used to swap elements in an arbitrary array
	- as long as the array element type is not a primitive type.
 	
 	String[] friends = ...;
   	Arrays.swap(friends, 0, 1);

- When you declare a generic method, the type parameter is placed after the modifiers (such as public and static) and before the return type:

- When calling a generic method, you do not need to specify the type parameter. It is inferred from the method parameter and return types.

- For example, in the call Arrays.swap(friends, 0, 1), the type of friends is String[], and the compiler can infer that T should be String.

- You can, if you like, supply the type explicitly, before the method name, like this

	Arrays.<String>swap(friends, 0, 1);

	- One reason why you might want to do this is to get better error messages when something goes wrong

- With the Entry class, the key and value types can be arbitrary. With the swap method, the array type can be arbitrary. That is plainly expressed with type variables.

# Type Bounds
________________________________________________________________

- Sometimes, the type parameters of a generic class or method need to fulfill certain requirements. You can specify a type bound to require that the type extends certain classes or implements certain interfaces.

	// One Of The Implementation
	public static <T> void closeAll(ArrayList<T> elems) throws Exception {
		for (T elem : elems) elem.close(); 
	}

	// One Approach For Error Handling: Runtime 
	try {
		closeAll(someData);
	} catch(Exception ex) {

	}

	// Second Approach For Error Handling: Compile Time Check
	if ( someData instanceOf AutoCloseable ) {
		closeAll(someData);
	}

	// Third Approach
	public static void closeAll(AutoCloseable[] elems) throws Exception

- Suppose, for example, you have an ArrayList of objects of a class that implements the AutoCloseable interface, and you want to close them all:

	// Upper Bound
	public static <T extends AutoCloseable> void closeAll(ArrayList<T> elems)
	           throws Exception {
			for (T elem : elems) elem.close(); 
	}

- The type bound extends AutoCloseable ensures that the element type is a subtype of AutoCloseable. Therefore, the call elem.close() is valid.

- Note that the extends keyword in a type bound actually means “subtype”
	—the Java designers just used the existing extends keyword instead of coming up with another keyword or symbol.

- NOTE: In this example, we need a type bound because the parameter is of type ArrayList. If the method accepted an array, you wouldn’t need a generic method. You could simply use a regular method

	public static void closeAll(AutoCloseable[] elems) throws Exception

- This works because an array type such as PrintStream[] is a subtype of AutoCloseable[]. 
	
	However, as you will see in the following section, 
	An ArrayList<PrintStream> is not a subtype of ArrayList<AutoCloseable>. Using a bounded type parameter solves this problem.

	// This API will not work for ArrayList<PrintStream>
	public static void closeAll(ArrayList<AutoCloseable> elems) throws Exception


- A type parameter can have multiple bounds,

		T extends Runnable & AutoCloseable

- This syntax is similar to that for catching multiple exceptions, the only difference being that the types are combined with an “and” operator, whereas multiple exceptions are combined with an “or” operator.

- You can have as many interface bounds as you like, but at most one of the bounds can be a class. If you have a class as a bound, it must be the first one in the bounds list.


# Type Variance and Wildcards
________________________________________________________________

- Suppose you need to implement a method that processes an array of objects that are subclasses of the class Employee. You simply declare the parameter to have type Employee[]:

- If Manager is a subclass of Employee, you can pass a Manager[] array to the method since Manager[] is a subtype of Employee[]

	- This behavior is called covariance. 
	- Arrays vary in the same way as the element types.

- Now, suppose you want to process an array list instead. However, there is a problem: 
	
	The type ArrayList<Manager> is not a subtype of ArrayList<Employee>.

Why In Java Generics Are Invariant? 
________________________________________________________________
- There is a reason for this restriction. 
	
	If it were legal to assign an ArrayList<Manager> to a variable of type ArrayList<Employee>, you could corrupt the array list by storing nonmanagerial employees:


		ArrayList<Manager> bosses = new ArrayList<>();
		ArrayList<Employee> empls = bosses; // Error 
		empls.add(new Employee(...));

- Since conversion from ArrayList<Manager> to ArrayList<Employee> is disallowed, this error cannot occur.

- NOTE: Can you generate the same error with arrays, where the conversion from Manager[] to Employee[] is permitted?

	- Java arrays are covariant, which is convenient but unsound.

	- When you store a mere Employee in a Manager[] array, an ArrayStoreException is thrown.

________________________________________________________________
- In contrast, all generic types in Java are invariant.
________________________________________________________________

- In Java, you use wildcards to specify how method parameter and return types should be allowed to vary. This mechanism is sometimes called use-site variance.

- In many situations it is perfectly safe to convert between different array lists. 
	- Suppose a method never writes to the array list, so it cannot corrupt its argument.

	public static void printNames(ArrayList<? extends Employee> staff) { 
		for (int i = 0; i < staff.size(); i++) {
           Employee e = staff.get(i);
           System.out.println(e.getName());
       }
	}

- The wildcard type ? extends Employee indicates some unknown subtype of Employee. You can call this method with an ArrayList<Employee> or an array list of a subtype, such as ArrayList<Manager>.


- The get method of the class ArrayList<? extends Employee> has return type ? extends Employee. The statement

 		Employee e = staff.get(i);

	- is perfectly legal

- Whatever type ? denotes, it is a subtype of Employee, and the result of staff.get(i) can be assigned to the Employee variable e.


// Following is Not Possible
 	// T[] result = new T[n];










- Since conversion from ArrayList<Manager> to ArrayList<Employee> is disallowed, this error cannot occur.

- NOTE: Can you generate the same error with arrays, where the conversion from Manager[] to Employee[] is permitted?

- Java arrays are covariant, which is convenient but unsound.

- When you store a mere Employee in a Manager[] array, an ArrayStoreException is thrown.

- In contrast, all generic types in Java are invariant.

- In Java, you use wildcards to specify how method parameter and return types should be allowed to vary. This mechanism is sometimes called use-site variance.

- In many situations it is perfectly safe to convert between different array lists. Suppose a method never writes to the array list, so it cannot corrupt its argument.

- The wildcard type ? extends Employee indicates some unknown subtype of Employee. You can call this method with an ArrayList<Employee> or an array list of a subtype, such as ArrayList<Manager>.

- The get method of the class ArrayList<? extends Employee> has return type ? extends Employee. The statement


