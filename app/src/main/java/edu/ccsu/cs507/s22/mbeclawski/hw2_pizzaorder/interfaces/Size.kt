package edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces

abstract class Size {
	//region fields
	abstract val name: String
	abstract val price: Double
	abstract val imageScale: Float
	//endregion

	//region public methods
	fun accept(visitor: FoodVisitor) {
		visitor.visitSize(this)
	}

	override fun toString(): String {
		return name
	}
	//endregion
}
