package cbsc.cha6.s1_polymorphy;

public class NewBook extends Book{
	public NewBook(String name, double aPrice) {
		super(name, aPrice);
	}

	public String getCategory(){
		return "ÐÂÊé";
	}
    double getFine() {
    	return getPrice()*0.01;
    }
    double baseFine(){
    	return 3;
    }
    int baseBonus() {
    	return 3;
    }
}
