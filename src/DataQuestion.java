import java.util.Scanner;

/**
 * @Author: CodeDrawing
 * @Date: 2021/4/1 8:15
 * @Package: PACKAGE_NAME
 * 功能：
 * 细节：
 * 注意：
 */

public class DataQuestion {

    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("请输入年份");
        int year = scanner.nextInt();
        System.out.println("请输入月份");
        int mouth = scanner.nextInt();
        if(mouth<1||mouth>12){
            System.out.println("输入的月份不对");
            return;
        }
        System.out.println("请输天");
        int day = scanner.nextInt();
        if(day<1||day>31){
            System.out.println("输入的日期不对");
            return;
        }else{
            if ((mouth == 2 || mouth == 4 || mouth == 6 || mouth == 9 || mouth == 11)&&day>30) {
                System.out.println("该月没有这一天");
                return;
            }
        }
        day=day+2;
        if(day==31) {
            if (mouth == 2 || mouth == 4 || mouth == 6 || mouth == 9 || mouth == 11) {
                day = 1;
                mouth++;
                if (mouth > 12) {
                    year++;
                    mouth = 1;
                }
            }
        }else if(day==32){
            if(mouth==2||mouth==4||mouth==6||mouth==9||mouth==11){
                day=2;
                mouth++;
                if(mouth>12){
                    year++;
                    mouth=1;
                }
                }else {
                day = 1;
                mouth++;
                if (mouth > 12) {
                    year++;
                    mouth = 1;
                }
            }
        }else if(day==33){
            day=2;
            mouth++;
            if(mouth>12){
                year++;
                mouth=1;
            }
        }
        System.out.println(year+"-"+mouth+"-"+day);

    }

}
