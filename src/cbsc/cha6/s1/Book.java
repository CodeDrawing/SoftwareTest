package cbsc.cha6.s1;

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
 * Book dispaly �����˵���
 */
public abstract class Book{

	private String title;
	private double price;
	
	public Book(String name, double aPrice ){
		title=name;
		price=aPrice;
	}
	public double getPrice(){
		return price;
	}
	public abstract String getCategory();
	public abstract double getFine();
	public abstract double baseFine();
	public abstract int baseBnous();
	
	public String getTitle(){
		return title;
	}
	public String toString(){
		return getCategory()+"��"+title+"��"+"���۸�"+price+"��";
	}	
}