package cbsc.cha6.refactory_2;

public class BookTester {
	public static void main(String[] args) {
        Student student = new Student("zhangsan");
        test_case_one(student);
    }

	private static void test_case_one(Student student) {
		Book aBook;
		
		aBook = new NewBook("Python����",35.5); 
        student.addBook(aBook,12);
        
        aBook = new TextBook("Java����",37.5);
        student.addBook(aBook,45);
        
        aBook = new Reference("C#����",41.7);
        student.addBook(aBook,38);
        
        aBook = new Reference("�������",29.8);
        student.addBook(aBook,28);
		
        /* 
        for (Rental aRental:student.getRentals()){  		
        	System.out.println(aRental);   
        }
        */
        System.out.println(student.returnedMessage());
	}

}
