
public class salami implements CustomerDAO {
	public int price = 3;
	public String toppings = "Salami ";
	public salami(CustomerDAO topping){ // sums price and toppings
		price += topping.cost();
		toppings += topping.toppings();
	}
	public salami(){
		
	}
	public int cost() {
		return price;	
	}
	public String toppings() {
		return toppings;
	}
}
