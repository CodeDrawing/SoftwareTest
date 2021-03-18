import java.util.ArrayList;
import java.util.Scanner;

public class Square {
    //输入三角形三边，并且判断是否为三角形
    public void inputSquareThreeNumber(){
        Scanner scanner = new Scanner(System.in);

        try{
            System.out.println("请输入一个整数");
            int a = scanner.nextInt();
            System.out.println("请输入一个整数");
            int b = scanner.nextInt();
            System.out.println("请输入一个整数");
            int c = scanner.nextInt();

            if(a<0||b<0||c<0){
                System.out.println("输入数据有误，不能为负数");
                return;
            }

            if (a - b < c && b - a < c &&
                    a - c < b && c - a < b &&
                    b - c < a && c - b < a) {
                System.out.println("这三个数可以构成三角形");
            } else {
                System.out.println("这三个数不可以构成三角形");
                return;
            }
            if(a==b&&b==c){
                System.out.println("该三角形为等边三角形");
            }else if(a==b||a==c||b==c){
                System.out.println("该三角形为等腰三角形");
            }else{
                System.out.println("该三角形不等边");
            }
        }
        catch (Exception e){
            System.out.println("请不要输入非整数的其他类型");
            //处理机制
            //Exception是所有异常类的父类
        }



    }

    public static void main(String[] args) {
        Square square = new Square();
        square.inputSquareThreeNumber();
    }
}
