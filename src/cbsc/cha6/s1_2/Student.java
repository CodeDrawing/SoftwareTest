package cbsc.cha6.s1_2;

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
	public String returnedMessage(){
		double totalAmount = 0;
	 	double finedAmount=0;
		int bonus = 0;
		String message = new String();
	 	for (Rental aRental: rentals){
	 		
			finedAmount=0;
			bonus = 0;
			switch (aRental.getBook().getCategory()) {
			  case Book.TEXT_BOOK:
				if (aRental.getDaysRented()> 30) {
					finedAmount += (aRental.getDaysRented()-30)* aRental.getBook().getPrice()*0.001; 
					finedAmount += 1;	// 基本罚金
				} else {
					addBonus(1);   // 提前还书的奖励分数
				}			
				break;
			  case Book.REFERENCE: 
				if (aRental.getDaysRented()> 30) {
					finedAmount += (aRental.getDaysRented()-30)* aRental.getBook().getPrice()*0.005; 
					finedAmount += 1.5;		// 基本罚金			
				} else {
					addBonus(2);  // 提前还书的奖励分数
				}				
				break;
			  case Book.NEW_BOOK: 
				if (aRental.getDaysRented()> 30) {
					finedAmount += (aRental.getDaysRented()-30)* aRental.getBook().getPrice()*0.01; 
					finedAmount += 3;  // 基本罚金
				}else {
					addBonus(3);  	// 提前还书的奖励分数
				}
				break;
			  }
			  addBonus(bonus);
			  
	     	  totalAmount += finedAmount;
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
}
