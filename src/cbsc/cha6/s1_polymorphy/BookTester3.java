package cbsc.cha6.s1_polymorphy;

public class BookTester3 {

	public static void main(String[] args) {
        Student student = new Student("Johson");
        test_case_one(student);
    }
	private static void test_case_one(Student student) {
		Book aBook;
		aBook = new NewBook("Python进阶",35.5); 
        student.addBook(aBook,12);
     
        aBook = new TextBook("Java导论",37.5);
        student.addBook(aBook,45);
        
        aBook = new Reference("C#秘笈",41.7);
        student.addBook(aBook,38);
        
        aBook = new Reference("软件构造",29.8);
        student.addBook(aBook,28);
        
        System.out.println("Tester3:学生 "+student.getName()+" 目前借阅图书信息如下： ");
        System.out.println("-----------------------------------------");
        System.out.println(student.returnedMessage());
	}
}
