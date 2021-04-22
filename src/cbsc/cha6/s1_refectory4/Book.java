package cbsc.cha6.s1_refectory4;

class Book{
	public final static int TEXT_BOOK=1;
	public final static int REFERENCE=3;
	public final static int NEW_BOOK=5;
	
	private String title;
	private double price;
	private int category=NEW_BOOK;
	
	public Book(String name, double aPrice, int aCode){
		title = name;
		price = aPrice;
		category = aCode;
	}
	public Book(String name, double aPrice ){
		title=name;
		price=aPrice;
	}
	public double getPrice(){
		return price;
	}
	public int getCategory(){
		return category;
	}
	public String getTitle(){
		return title;
	}
	public String toString(){
		return "¡¶"+title+"¡·";
	}	
}
