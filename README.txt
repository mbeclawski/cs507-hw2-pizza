CS507-Homework#2 - Pizza Order

Maciej Beclawski

Pizza Order app lets the user select a type of pizza from four available options (Hawaiian, BBQ Chicken, Margherita, and Pepperoni).
Selecting a type updates the pizza image, but does not affect price.
Selecting size changes price and image to reflect selected product.
The app will not start calculating price until the size is selected because that would not be a valid order in the real world.
Extra topping can be selected, but they increase the price.
Delivery switch add additional $2.00 to the final price if in ON state.
The final price is shown at the bottom of the screen.

EXTRA FUNCTIONALITY
The sizes and toppings are dynamically added at runtime and their prices are set in each corresponding objects,
so each topping's price can be different. Visitor Design Pattern is used to parse and calculate their prices.
Changing pizza size adjusts the image size to show the user an approximate size differences.
