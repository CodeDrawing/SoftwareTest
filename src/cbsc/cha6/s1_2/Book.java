package cbsc.cha6.s1_2;

/**
* @(#)RefactoryingExample.java
* ѧУͼ��ݼ���ѧ������ķ��
* 				�̲ġ��ο�������
* ��ѽ���������50   40    30  
* ÿ����һ�췣���� 0.001,0.005Ԫ��0.01Ԫ��
* ��ʱ�Ļ������1��1.5��3
* ��������ǰ���齱������ 1, 2, 3; 7��ɵ���1Ԫ����
*
* ��չ����ѧ����ѽ������� 30   40   50
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
		return "��"+title+"��";
	}	
}
