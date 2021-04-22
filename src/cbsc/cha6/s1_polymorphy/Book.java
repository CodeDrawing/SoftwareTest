package cbsc.cha6.s1_polymorphy;
// 运用多套消除分之语句

abstract class Book{
	public final static int TEXT_BOOK=1;
	public final static int REFERENCE=3;
	// public final static int NEW_BOOK=5;
	
	private String title;
	private double price;
	// private int category=NEW_BOOK;
	
	/*
	 public Book(String name, double aPrice, int aCode){
	 
		title = name;
		price = aPrice;
		// category = aCode;
	}
	*/
	public Book(String name, double aPrice ){
		title=name;
		price=aPrice;
	}
	public double getPrice(){
		return price;
	}
	/*
	public int getCategory(){
		return category;
	}
	*/
	public String getTitle(){
		return title;
	}
	public String toString(){
		return getCategory()+"《"+title+"》"+"（价格"+price+"）";
	}	
	
	abstract String getCategory();
	abstract double getFine();
	abstract double baseFine();
	abstract int baseBonus();
	

}
