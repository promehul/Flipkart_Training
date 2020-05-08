
package learnJava;

class StackString {
	private String[] store = new String[10];
	private int MAX = 10;

	public void push(String data) {
		// If Stack Not Full
		store.add(data);
	}

	public String pop() {
		// If Stack Is NOT Empty
		return store.removeLast();
	}
}

class StackInt {
	private Integer[] store = new Integer[10];
	private int MAX = 10;

	public void push(Integer data) {
		// If Stack Not Full
		store.add(data);
	}

	public Integer pop() {
		// If Stack Is NOT Empty
		return store.removeLast();
	}
}

// Generics: Generic Programming
//		Write Code To Generate Code

// 		T is placeholder for Type
class Stack<T> {
	private T[] store = new T[10];
	private int MAX = 10;

	public void push(T data) {
		// If Stack Not Full
		store.add(data);
	}

	public T pop() {
		// If Stack Is NOT Empty
		return store.removeLast();
	}
}

Stack<String> stringStack = new Stack<String>[10];
// After substiting T placeholder with Type String
// Generate Code

stringStack.push("Ding")
stringStack.push("Dong")
stringStack.push("Ting")
stringStack.push("Tong")
stringStack.pop()

Stack<Integer> integerStack = new Stack<Integer>[10];
// After substiting T placeholder with Type Integer and Generate Code

integerStack.push(10)
integerStack.push(20)
integerStack.push(30)
integerStack.push(40)
integerStack.pop()



