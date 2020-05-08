
package learnKotlin;

// Function Are First Class Citizen
//	1. Can Be Assigned Value
// 	2. Can Be Passed To Function
// 	3. Can Be Returned From Function

//	1. Can Be Assigned Value
fun add( x: Int, y: Int) = x + y

fun sum(x: Int, y: Int ) : Int {  return x + y  }
fun sub(x: Int, y: Int ) : Int {  return x - y  }

// Function Which Takes Function As Argument
// 	2. Can Be Passed To Function
fun calculator(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
	return operation(x, y) ;
}

// 	3. Can Be Returned From Function
fun doSomething(talent: Int) : () -> String {
	fun dancing(): String { return "Dancing... Zhoom Zhoooom!!!" }
	fun singing(): String { return "Ye Mera Dil Diwanaa!!!....."}
	
	return if (talent == 0)  ::dancing  else ::singing 
}

// Higher Order Function
//		1. First Class Citizen Functions 
// 		2. Which Doesn't Creates Any Side Effects
//			For Same Arguments Return Values Must Be Same

fun String.filter(predicate: (Char) -> Boolean ) : String {
	// Not Create Side Effects
	val sb = StringBuilder()

	for (index in 0 until length ) {
		val element = get(index) 
		if ( predicate(element) ) sb.append(element)
	}
	return sb.toString()
}

fun playingWithFilter() {
	println("DingDonG7384378PingPong".filter { it in 'a'..'z'} )
	//ingoningong
}

fun main() {
	val a = 100
	val b = 120

	var result = calculator(a, b, ::sum)
	println("Result: $result ")

	val something: (Int, Int, (Int, Int) -> Int ) -> Int = ::calculator
	
	result = something(a, b, ::sub)
	println("Result: $result ")

	val doSome: () -> String = doSomething(1)
	println(doSome())

	// What's the Data Type?
	val somethingOnceMore: (Int) -> () -> String = ::doSomething

	playingWithFilter()
	println(add(1000, 20000))
}




