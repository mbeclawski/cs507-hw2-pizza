package edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.collection.ArrayMap
import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.interfaces.*
import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.models.FoodPriceVisitor
import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.models.pizzas.*
import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.models.sizes.*
import edu.ccsu.cs507.s22.mbeclawski.hw2_pizzaorder.models.toppings.*

class MainActivity : AppCompatActivity() {
	//region fields
	var mSelectedPizza: Pizza? = null
	private var mAllToppings: ArrayMap<String, Topping> = ArrayMap<String, Topping>()
	private var mAllSizes: ArrayList<Size> = ArrayList()
	//endregion

	//region overridden methods
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		// ** populate pizzas
		val listPizzas: ArrayList<Pizza> = getPizzas()
		val spinnerPizza = findViewById<Spinner>(R.id.spinnerPizza)
		val pizzaAdapter = ArrayAdapter<Pizza>(this, android.R.layout.simple_spinner_dropdown_item, listPizzas)
		spinnerPizza.adapter = pizzaAdapter

		// ** populate sizes
		val sizesContainer = findViewById<RadioGroup>(R.id.radioGroupSizes)
		mAllSizes = getSizes()
		mAllSizes.forEach {
			val radio = RadioButton(this)
			radio.text = String.format("%s ($%.2f)", it.name, it.price)
			radio.tag = it.name
			radio.setOnClickListener { view -> onSizeChange(view) }

			sizesContainer.addView(radio)
		}

		// ** populate toppings
		val toppingsContainer = findViewById<LinearLayout>(R.id.linearListToppings)
		mAllToppings = getToppings()

		mAllToppings.forEach {
			val checkbox = CheckBox(this)
			checkbox.text = String.format("%s ($%.2f)", it.value.name, it.value.price)
			checkbox.tag = it.value.name
			checkbox.setOnClickListener { view -> onToppingClicked(view) }
			toppingsContainer.addView(checkbox)
		}

		spinnerPizza.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
			override fun onNothingSelected(parent: AdapterView<*>?) {
				Toast.makeText(this@MainActivity, "Select a Pizza", Toast.LENGTH_SHORT).show()
			}

			override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
				// ** keep size/toppings
				var size: Size? = null
				var toppings: ArrayList<Topping> = ArrayList()
				if(mSelectedPizza != null) {
					size = mSelectedPizza?.size
					toppings = mSelectedPizza?.toppings!!
				}

				mSelectedPizza = getPizza(spinnerPizza.selectedItem.toString())
				Toast.makeText(this@MainActivity, "Selected: $mSelectedPizza", Toast.LENGTH_SHORT).show()

				// restore size/toppings
				mSelectedPizza?.size = size
				mSelectedPizza?.toppings = toppings

				// ** update image
				val selectedImageId = mSelectedPizza?.imageId
				val imagePizza = findViewById<ImageView>(R.id.imagePizza)
				imagePizza.setImageResource(selectedImageId!!)

				updatePrice()
			}
		}
	}
	//endregion

	//region public methods
	fun onDeliveryChanged(view: View) {
		if (view is Switch) {
			if (view.isChecked) {
				view.text = resources.getString(R.string.delivery_not_free)
				Toast.makeText(this, "Delivery", Toast.LENGTH_SHORT).show()
			} else {
				view.text = resources.getString(R.string.delivery_free)
				Toast.makeText(this, "Pickup", Toast.LENGTH_SHORT).show()
			}
		}

		updatePrice()
	}

	fun updatePrice(){
		var price = 0.0

		// ** only calculate/show price when size is selected
		if(mSelectedPizza?.size != null){
			price += calculatePrice()

			if(findViewById<Switch>(R.id.switchDelivery).isChecked){
				price += 2.00
			}
		}

		val txtPrice = findViewById<TextView>(R.id.textPrice)
		txtPrice.text = if (price == 0.00) resources.getString(R.string.price_calculating) else String.format("$%.2f", price)
	}
	//endregion

	//region private methods
	private fun getPizzas(): ArrayList<Pizza> {
		val lstPizzas = arrayListOf<Pizza>()
		lstPizzas.add(Hawaiian())
		lstPizzas.add(BBQChickenPizza())
		lstPizzas.add(MargheritaPizza())
		lstPizzas.add(PepperoniPizza())

		return lstPizzas
	}

	private fun getPizza(name: String): Pizza? {
		return getPizzas().firstOrNull { it.name == name }
	}

	private fun getSizes(): ArrayList<Size> {
		val lstSizes = arrayListOf<Size>()
		lstSizes.add(SizeMedium())
		lstSizes.add(SizeLarge())
		lstSizes.add(SizeXL())

		return lstSizes
	}

	private fun getSize(name: String): Size? {
		return getSizes().firstOrNull { it.name == name }
	}

	private fun getToppings(): ArrayMap<String, Topping> {
		val lstToppings = ArrayMap<String, Topping>()
		lstToppings[ExtraCheese().name] = ExtraCheese()
		lstToppings[Mushroom().name] = Mushroom()
		lstToppings[Onions().name] = Onions()
		lstToppings[Spinach().name] = Spinach()
		lstToppings[Broccoli().name] = Broccoli()
		lstToppings[BlackOlives().name] = BlackOlives()
		lstToppings[Tomatoes().name] = Tomatoes()

		return lstToppings
	}

	private fun onToppingClicked(view: View) {
		if (view is CheckBox) {
			val toppingTag = view.tag
			val topping: Topping? = mAllToppings[toppingTag]

			if (topping != null) {
				if (view.isChecked) {
					mSelectedPizza?.toppings?.add(topping)
					Toast.makeText(this@MainActivity, "Added: ${topping.name}", Toast.LENGTH_SHORT).show()
				}
				else {
					mSelectedPizza?.toppings?.remove(topping)
					Toast.makeText(this@MainActivity, "Removed: ${topping.name}", Toast.LENGTH_SHORT).show()
				}
			}
		}

		for (selectedTopping in mSelectedPizza!!.toppings) {
			Log.d("selected: ", selectedTopping.toString())
		}

		updatePrice()
	}

	private fun onSizeChange(view: View) {
		if (view is RadioButton) {
			val name = view.tag
			val size: Size? = mAllSizes.firstOrNull { it.name == name }

			if (size != null) {
				mSelectedPizza?.size = size

				val imagePizza = findViewById<ImageView>(R.id.imagePizza)
				imagePizza.scaleX = size.imageScale
				imagePizza.scaleY = size.imageScale

				Toast.makeText(this, "Size: $size", Toast.LENGTH_SHORT).show()
			}
			else {
				mSelectedPizza?.size = null
			}
		}

		updatePrice()
	}

	private fun calculatePrice(): Double {
		val priceVisitor = FoodPriceVisitor()

		if (mSelectedPizza != null) {
			mSelectedPizza?.accept(priceVisitor)
		}

		return priceVisitor.getPrice()
	}
	//endregion
}
