package edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces

abstract class Topping : FoodVisitable {
	//region fields
	abstract var name: String
	open var price: Double = 1.69
	//endregion

	//region public methods
	override fun accept(visitor: FoodVisitor) {
		visitor.visitTopping(this)
	}

	override fun toString(): String {
		return name
	}
	//endregion
}
