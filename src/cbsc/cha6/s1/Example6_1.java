package cbsc.cha6.s1;

/**
 * @(#)Refactorying Example 6.1
 * 学校图书馆计算学生借书的罚款：
 * 				教材、参考、新书
 * 免费借阅天数：50   40    30  
 * 每超过一天罚款：书价 0.001,0.005元，0.01元，
 * 超时的基本罚款：1，1.5，3
 * 奖励：提前还书奖励点数 1, 2, 3; 7点可抵消1元罚款
 *
 * 扩展：大学生免费借阅天数 30   40   50
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
		aBook = new NewBook("Python进阶",35.5); 
        student.addBook(aBook,12);
     
        aBook = new TextBook("Java导论",37.5);
        student.addBook(aBook,45);
        
        aBook = new Reference("C#秘笈",41.7);
        student.addBook(aBook,38);
        
        aBook = new Reference("软件构造",29.8);
        student.addBook(aBook,28);
        
        System.out.println("学生 "+student.getName()+" 目前借阅图书信息如下： ");
        System.out.println(student.returnedMessage());
	}
	
	private static void test_case_two(Student student) {
		Book aBook;
		aBook = new NewBook("Python进阶",35.5); 
        student.addBook(aBook,18);
     
        aBook = new Reference("算法设计与分析",48.5);
        student.addBook(aBook,48);
        
        aBook = new TextBook("R与大数据基础",41.7);
        student.addBook(aBook,38);
        
        aBook = new TextBook("软件构造",29.8);
        student.addBook(aBook,32);
        
        System.out.println("学生 "+student.getName()+" 目前借阅图书信息如下： ");
        System.out.println(student.returnedMessage());
	}
}
