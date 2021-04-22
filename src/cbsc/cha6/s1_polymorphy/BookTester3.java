package cbsc.cha6.s1_polymorphy;

public class BookTester3 {

	public static void main(String[] args) {
        Student student = new Student("Johson");
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
        
        System.out.println("Tester3:ѧ�� "+student.getName()+" Ŀǰ����ͼ����Ϣ���£� ");
        System.out.println("-----------------------------------------");
        System.out.println(student.returnedMessage());
	}
}
