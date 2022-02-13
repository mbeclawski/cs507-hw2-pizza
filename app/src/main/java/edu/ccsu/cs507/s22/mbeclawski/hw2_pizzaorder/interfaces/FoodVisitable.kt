package edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces

interface FoodVisitable {
	fun accept(visitor: FoodVisitor)
}
