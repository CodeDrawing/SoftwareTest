package cbsc.cha6.s1_refectory4;

public class BookTester2 {

	public static void main(String[] args) {
        Student student = new Student("Johson");
        test_case_one(student);
    }
	private static void test_case_one(Student student) {
		Book aBook;
		aBook = new Book("Python进阶",35.5); 
        student.addBook(aBook,12);
     
        aBook = new Book("Java导论",37.5,1);
        student.addBook(aBook,45);
        
        aBook = new Book("C#秘笈",41.7,3);
        student.addBook(aBook,38);
        
        aBook = new Book("软件构造",29.8,3);
        student.addBook(aBook,28);
        
        System.out.println("Tester2:学生 "+student.getName()+" 目前借阅图书信息如下： ");
        System.out.println(student.returnedMessage());
	}
}

