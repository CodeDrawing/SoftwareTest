package cbsc.cha6.s1_polymorphy;

import java.util.ArrayList;

public class Student{
	
	private String name;
	private int bonus=0;
	private ArrayList<Rental> rentals = new ArrayList<Rental>(10);
	
	public Student(String aName){
		name = aName;
	}
	public String getName(){
		return name;
	}
	public ArrayList<Rental> getRentals(){
		return rentals;
	}
	public int getBonus(){
		return bonus;
	}
	public void addBonus(int reward){
		bonus += reward;
	}
	public void addBook(Book aBook, int days){
		rentals.add(new Rental(aBook, days, this));
	}
	public void addRental(Rental aRental){
		rentals.add(aRental);
	}
	/*
	public String returnedMessage(){
		double totalAmount = 0;
	 	// double finedAmount=0;
		// int bonus = 0;
		String message = new String();
	 	for (Rental aRental: rentals){
			// finedAmount = calculateFineAndBonus(aRental);	   	
	     	  totalAmount += aRental.calculateFine();
			  // ��������7����Ի�1Ԫ����
	        while (getBonus()>=7 && totalAmount>1) {
	        	addBonus(-7);
	        	totalAmount--;
	        }
	        message += aRental+"\n";      
	     }
	     message += String.format("�ܹ����ɷ���: %.2fԪ.\n", totalAmount);
	     message += "���齱��: "+getBonus()+"��. \n";        
	     return message;
		}
	*/
	//����6.8: �ú��������ѭ����װ��һ��������
	  //���컹����嵥,refactor 6.1
	  public String getRentalList(){
			String result = "";	
			for (Rental aRental:rentals) {
				result += aRental+"\n";
			}
			return result;
	}
	//�������з���ķ���,refactor 6.2
	public double getTotalFine(){
		double totalAmount = 0;
	  		for (Rental aRental:rentals){	
	        	totalAmount += aRental.calculateFine(); 
	  	}	
	  	return totalAmount;
	}
	//�������ֵ�������Ĵ���,refactor 6.3
	public double discountedBonus(double totalAmount){
		while (getBonus()>=7 && totalAmount >1){
	        	addBonus(-7);
	        	totalAmount --;
	 	} 
	 	return totalAmount;
	}
	public String returnedMessage(){
		String message = getRentalList();
		double totalAmount = getTotalFine();   
		totalAmount = discountedBonus(totalAmount);
	   	message += String.format("���ɷ���:%.2fԪ.\n",totalAmount);
	 	message += "���齱��:"+getBonus()+"��.\n";       
	 	return message;	
	}
	
}
