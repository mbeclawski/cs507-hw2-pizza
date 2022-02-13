package edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.models.sizes

import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces.Size

class SizeLarge : Size() {
	override val name: String
		get() = "Large"
	override val price: Double
		get() = 13.99
	override val imageScale: Float
		get() = 0.8f
}
