package edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.models.sizes

import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces.Size

class SizeMedium : Size() {
	override val name: String
		get() = "Medium"
	override val price: Double
		get() = 9.99
	override val imageScale: Float
		get() = 0.6f
}
