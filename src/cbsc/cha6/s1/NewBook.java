package cbsc.cha6.s1;

public class NewBook extends Book{
	
	public NewBook (String name, double aPrice ){
		super(name,aPrice);
	}
	public String getCategory() {
		return "ÐÂÊé";
	}
	public  double getFine() {
		return getPrice()*0.01;
	}
	public  double baseFine() {
		return 3;
	}
	public int baseBnous() {
		return 3;
	}
}
