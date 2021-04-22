package cbsc.cha6.refactory_2;
// 重构的二部分

public abstract class Book{
	private String title;
	private double price;

	public Book(String name, double aPrice){
		title = name;
		price = aPrice;
	}
	public double getPrice(){
		return price;
	}
	public String getTitle(){
		return title;
	}
	public String toString(){
		return getCategory()+"《"+getTitle()+"》"+"(价格"+price+")";
	}
	abstract double getFine();
	abstract String getCategory();
	abstract int getBonus();
	abstract double baseFine();
}

class TextBook extends Book{
	public TextBook(String name, double aPrice){
		super(name,aPrice);
	}
	public double getFine(){
		return getPrice()*0.001;
	}
	public String getCategory(){
		return "教材";
	}
	public int getBonus(){
		return 1;
	}
	public double baseFine(){
		return 1;
	}
}
class Reference extends Book{
	public Reference(String name, double aPrice){
		super(name,aPrice);
	}
	public double getFine(){
		return getPrice()*0.005;
	}
	public String getCategory(){
		return "参考书";
	}
	public int getBonus(){
		return 2;
	}
	public double baseFine(){
		return 1.5;
	}
}
class NewBook extends Book{
	public NewBook(String name, double aPrice){
		super(name,aPrice);
	}
	public double getFine(){
		return getPrice()*0.01;
	}
	public String getCategory(){
		return "新书";
	}
	public int getBonus(){
		return 3;
	}
	public double baseFine(){
		return 3;
	}
}