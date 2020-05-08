
// In Java Classes and Members Are Open By Default
// 		Subclassable as well as Inheritable

// In Kotlin Classes and Members Are Final By Default
// 		Based On Sound Fundamentals 
//		Best Practices Are Becoming Part of Language Design

class Something {
	// Instance Member Function
	fun click() {  println("View Clicked") }
}

// Instance Member Function
fun Something.doSomething() { println("Something Done!") }

fun playWithSomething() {
	var some = Something()
	
	some.click()
	some.doSomething()
}

// Inherited Functions
// 		Message to Method Mapping Decided Based On Type of Reciever
//		As Well Search Order: Fixed

open class View {
	open fun click() {  println("View Clicked") }
}

class Button : View() {
	override fun click() {  println("Button Clicked") }
	// fun click() {  println("Button Clicked") }
	fun magic() {  println("Magic Done!") }
}

// Extension Functions
// 		Message to Method Mapping Decided Based On Type of Reciever

fun View.showOff() {  println("View Show Off!") }
fun Button.showOff() {  println("Button Show Off!") }

fun playWithInheritance() {
	val v: View = View()
	val b: Button = Button()

	v.click()
	b.click()

	v.showOff()
	b.showOff()

	val vv: View = Button()
	vv.click()
	vv.showOff()

	// View Clicked
	// Button Clicked
	// View Show Off!
	// Button Show Off!

	// override fun click() {  println("Button Clicked") }
	// View Clicked
	// View Clicked
	// View Show Off!
	// Button Show Off!
}


fun main() {
	// playWithSomething()
	playWithInheritance()
}
