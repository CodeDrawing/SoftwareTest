package cbsc.cha6.s1;

/**
 * @(#)Refactorying Example 6.1
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
public class Example6_1 {	
	public static void main(String[] args) {
        Student student = new Student("Johson");
        test_case_one(student);
        
        Student student2 = new Student("Marry");
        test_case_two(student2);
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
        
        System.out.println("ѧ�� "+student.getName()+" Ŀǰ����ͼ����Ϣ���£� ");
        System.out.println(student.returnedMessage());
	}
	
	private static void test_case_two(Student student) {
		Book aBook;
		aBook = new NewBook("Python����",35.5); 
        student.addBook(aBook,18);
     
        aBook = new Reference("�㷨��������",48.5);
        student.addBook(aBook,48);
        
        aBook = new TextBook("R������ݻ���",41.7);
        student.addBook(aBook,38);
        
        aBook = new TextBook("�������",29.8);
        student.addBook(aBook,32);
        
        System.out.println("ѧ�� "+student.getName()+" Ŀǰ����ͼ����Ϣ���£� ");
        System.out.println(student.returnedMessage());
	}
}
