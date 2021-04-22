package cbsc.cha6.s1_2;

/**
* @(#)RefactoryingExample.java
* 学校图书馆计算学生借书的罚款：
* 				教材、参考、新书
* 免费借阅天数：50   40    30  
* 每超过一天罚款：书价 0.001,0.005元，0.01元，
* 超时的基本罚款：1，1.5，3
* 奖励：提前还书奖励点数 1, 2, 3; 7点可抵消1元罚款
*
* 扩展：大学生免费借阅天数 30   40   50
* @author 
* @version 1.00 2015/3/14
*/

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
		return "《"+title+"》";
	}	
}
