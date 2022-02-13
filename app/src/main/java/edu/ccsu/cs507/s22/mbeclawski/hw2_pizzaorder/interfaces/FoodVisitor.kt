package edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces

interface FoodVisitor {
	fun visitPizza(pizza: Pizza)
	fun visitTopping(topping: Topping)
	fun visitSize(size: Size?)
}
