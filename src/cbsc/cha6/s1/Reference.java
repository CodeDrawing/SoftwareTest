package cbsc.cha6.s1;

public class Reference extends Book{
	public Reference(String name, double aPrice) {
		super(name, aPrice);
	}

	public String getCategory(){
		return "²Î¿¼Êé";
	}
	public double getFine() {
    	return getPrice()*0.005;
    }
	public double baseFine(){
    	return 1.5;
    }
	public int baseBnous() {
		return 2;
	}
}