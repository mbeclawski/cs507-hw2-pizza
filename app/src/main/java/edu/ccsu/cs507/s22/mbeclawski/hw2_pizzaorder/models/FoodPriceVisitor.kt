package edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.models

import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces.FoodVisitor
import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces.Pizza
import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces.Size
import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces.Topping

class FoodPriceVisitor : FoodVisitor {
	private var price: Double = 0.0

	override fun visitPizza(pizza: Pizza) {
		// ** pizza type does not affect pricing
	}

	override fun visitTopping(topping: Topping) {
		price += topping.price
	}

	override fun visitSize(size: Size?) {
		if(size != null){
			price += size.price
		}
	}

	fun getPrice() : Double{
		return this.price
	}
}
