
public class soudjouk implements CustomerDAO{

	public int price = 3;
	public String toppings = "Soudjouk ";
	public soudjouk(CustomerDAO topping){ // sums price and toppings
		price += topping.cost();
		toppings += topping.toppings();
	}
	public soudjouk(){
		
	}
	public int cost() {
		return price;	
	}
	public String toppings() {
		return toppings;
	}
	
}
