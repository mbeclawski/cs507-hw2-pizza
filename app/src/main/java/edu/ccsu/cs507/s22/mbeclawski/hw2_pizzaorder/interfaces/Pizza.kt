package edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces

abstract class Pizza : FoodVisitable {
	//region fields
	abstract var name: String
	abstract var imageId: Int
	var size: Size? = null
	var toppings : ArrayList<Topping> = ArrayList<Topping>()
	//endregion

	//region public methods
	override fun accept(visitor: FoodVisitor) {
		visitor.visitPizza(this);
		visitor.visitSize(this.size);

		for (topping in this.toppings) {
			visitor.visitTopping(topping)
		}
	}


	override fun toString(): String {
		return name
	}
	//endregion
}
