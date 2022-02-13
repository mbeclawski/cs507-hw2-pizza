package edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.models.pizzas

import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.R
import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces.Pizza

class Hawaiian : Pizza() {
	override var name: String = "Hawaiian"
	override var imageId: Int = R.drawable.hawaiian
}
