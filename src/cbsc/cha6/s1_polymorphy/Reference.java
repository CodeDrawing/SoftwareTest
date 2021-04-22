package cbsc.cha6.s1_polymorphy;

public class Reference extends Book{
	public Reference(String name, double aPrice) {
		super(name, aPrice);
	}

	public String getCategory(){
		return "�ο���";
	}
	double getFine() {
    	return getPrice()*0.005;
    }
	double baseFine(){
    	return 1.5;
    }
	int baseBonus() {
    	return 2;
    }
}
