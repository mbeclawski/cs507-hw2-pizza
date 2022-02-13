package edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.models.toppings

import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces.FoodVisitable
import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces.FoodVisitor
import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces.Topping

class Mushroom : Topping(), FoodVisitable {
	override var name: String = "Mushroom"
	override var price: Double = 2.00
}
