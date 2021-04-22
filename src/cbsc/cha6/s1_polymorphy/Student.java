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
			  // 奖励积分7点可以换1元罚金
	        while (getBonus()>=7 && totalAmount>1) {
	        	addBonus(-7);
	        	totalAmount--;
	        }
	        message += aRental+"\n";      
	     }
	     message += String.format("总共缴纳罚金: %.2f元.\n", totalAmount);
	     message += "还书奖励: "+getBonus()+"点. \n";        
	     return message;
		}
	*/
	//代码6.8: 用函数抽象把循环封装到一个方法中
	  //构造还书的清单,refactor 6.1
	  public String getRentalList(){
			String result = "";	
			for (Rental aRental:rentals) {
				result += aRental+"\n";
			}
			return result;
	}
	//构造所有罚金的方法,refactor 6.2
	public double getTotalFine(){
		double totalAmount = 0;
	  		for (Rental aRental:rentals){	
	        	totalAmount += aRental.calculateFine(); 
	  	}	
	  	return totalAmount;
	}
	//奖励积分抵消罚金的代码,refactor 6.3
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
	   	message += String.format("缴纳罚金:%.2f元.\n",totalAmount);
	 	message += "还书奖励:"+getBonus()+"点.\n";       
	 	return message;	
	}
	
}
