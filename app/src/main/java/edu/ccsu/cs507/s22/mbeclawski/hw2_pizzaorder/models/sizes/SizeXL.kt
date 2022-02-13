package edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.models.sizes

import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces.Size

class SizeXL : Size() {
	override val name: String
		get() = "X-Large"
	override val price: Double
		get() = 15.99
	override val imageScale: Float
		get() = 1.0f
}
