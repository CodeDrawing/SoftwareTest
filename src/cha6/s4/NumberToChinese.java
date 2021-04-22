package cha6.s4;

/*
 * 把32位以内的整数转换成中文，例如12  十二或  壹拾贰
 * 采用测试驱动开发的方式，由简单到复杂
 */
public class NumberToChinese {

	public String numberToChinese(int number){
		if (Integer.MAX_VALUE < number || number < 0 ){
			throw new RuntimeException(number+" 不在32位正整数范围");
		}
		
		String infor="";
		
		if (number < 10){
			infor += basicNumber(number);
		}else if (number < 100){
			infor = tensNumber(number);
		} else if (number < 1000){
			infor = hundredNumber(number);
		} 
		else if (number < 10000){  // 万以内的数
			infor = thousandsNumber(number);
			infor = infor.replaceAll("零+","零");
		} else if (number < 100000000){  // 亿以内的数
			infor = wansNumber(number);
			infor = clearChineseZeros(infor); 
		} else if (number <= Integer.MAX_VALUE ){  // 略大以20亿以内的数
			int yis = number /100000000;
			int rest = number - yis*100000000;
			if (yis != 0){
				infor += tensNumber(yis)+"亿";
			}else {
				infor += "零";
			}
			if (rest != 0){
				infor += wansNumber(rest);		
			}
			infor = clearChineseZeros(infor); 	
		} 	
		return infor;
	}
	private String clearChineseZeros(String infor) {
		// 删除重复的零
		infor = infor.replaceAll("零+","零");	
		// 去掉头部的零
		if (infor.indexOf("零") == 0){
			infor = infor.substring(1);
		}
		return infor;
	}
	private String wansNumber(int number) {
		
		String infor="";
		int wans = number / 10000;
		int rest = number - wans*10000;
		if (wans != 0){
			infor += thousandsNumber(wans)+"万";
		} else{
			infor += "零";
		}
		if (rest != 0){
			infor += thousandsNumber(rest);
		}
		return infor;
	}
	private String thousandsNumber(int number) {
		
		String infor="";
		int thousands = number/1000;
		int rest = number - thousands*1000;
		if (thousands !=0){
			infor += basicNumber(thousands)+"仟";
		} else{
			infor += "零";
		}
		if (rest != 0){
			infor += hundredNumber(rest);
		}
		return infor;
	}
	
	private String hundredNumber(int number) {
		String infor="";
		int hundreds= number/100;
		int rest = number - hundreds*100;
		if (hundreds !=0){
			infor += basicNumber(hundreds)+"佰";
		}else{
			infor += "零";
		}
		if (rest !=0){
			infor += tensNumber(rest);
		}
		return infor;
	}
	private String tensNumber(int number) {
		/*
		if (number == 0){
			return "";
		}
		*/
		String infor="";
		int units = number %10;
		int tens = number / 10;
		if (tens != 0) {
			infor += basicNumber(tens)+"拾";	
		} else {
			infor += "零";
		}
		if (units != 0) {
			infor +=  basicNumber(units);
		} 
		return infor;
	}
	private String basicNumber(int number) {
		String infor="";
		switch (number){
			case 0: infor += "零"; break;
			case 1: infor += "壹"; break;
			case 2: infor += "贰"; break;
			case 3: infor += "叁"; break;
			case 4: infor += "肆"; break;
			case 5: infor += "伍"; break;
			case 6: infor += "陆"; break;
			case 7: infor += "柒"; break;
			case 8: infor += "捌"; break;
			case 9: infor += "玖"; break;
		}
		return infor;
	}
	public static void main(String[] args) {
		NumberToChinese nc = new NumberToChinese();	
		System.out.println("1. test one digits number:");
		System.out.print(0+"\t");
		System.out.println(nc.numberToChinese(0));
		System.out.print(1+"\t");
		System.out.println(nc.numberToChinese(1));
		System.out.print(9+"\t");
		System.out.println(nc.numberToChinese(9));
		
		System.out.println("\n2. test 2 digits number:");
		System.out.print(10+"\t");
		System.out.println(nc.numberToChinese(10));
		System.out.print(11+"\t");
		System.out.println(nc.numberToChinese(11));
		System.out.print(90+"\t");
		System.out.println(nc.numberToChinese(90));
		System.out.print(99+"\t");
		System.out.println(nc.numberToChinese(99));
	
		System.out.println("\n3. test 3 digits number:");
		System.out.print(100+"\t");
		System.out.println(nc.numberToChinese(100));
		System.out.print(101+"\t");
		System.out.println(nc.numberToChinese(101));
		System.out.print(549+"\t");
		System.out.println(nc.numberToChinese(549));
		System.out.print(999+"\t");
		System.out.println(nc.numberToChinese(999));
		
		System.out.println("\n4. test 4 digits number:");
		System.out.print(1000+"\t");
		System.out.println(nc.numberToChinese(1000));
		System.out.print(1001+"\t");
		System.out.println(nc.numberToChinese(1001));
		System.out.print(1900+"\t");
		System.out.println(nc.numberToChinese(1900));
		System.out.print(9080+"\t");
		System.out.println(nc.numberToChinese(9080));
		System.out.print(9999+"\t");
		System.out.println(nc.numberToChinese(9999));
		
		System.out.println("\n6. test 5 digits number:");
		System.out.print(10000+"\t");
		System.out.println(nc.numberToChinese(10000));
		System.out.print(10001+"\t");
		System.out.println(nc.numberToChinese(10001));
		System.out.print(40506+"\t");
		System.out.println(nc.numberToChinese(40506));
		System.out.print(90900+"\t");
		System.out.println(nc.numberToChinese(90900));
		System.out.print(90080+"\t");
		System.out.println(nc.numberToChinese(90080));
		System.out.print(56789+"\t");
		System.out.println(nc.numberToChinese(56789));
		
		System.out.println("\n7. test 6 digits number:");
		System.out.print(100000+"\t");
		System.out.println(nc.numberToChinese(100000));
		System.out.print(600009+"\t");
		System.out.println(nc.numberToChinese(600009));
		System.out.print(608007+"\t");
		System.out.println(nc.numberToChinese(608007));
		System.out.print(600409+"\t");
		System.out.println(nc.numberToChinese(600409));
		System.out.print(230509+"\t");
		System.out.println(nc.numberToChinese(230509));
		
		System.out.println("\n8. test 7-8 digits number:");
		System.out.print(9000000+"\t");
		System.out.println(nc.numberToChinese(9000000));
		System.out.print(8000007+"\t");
		System.out.println(nc.numberToChinese(8000007));
		
		System.out.print(5006007+"\t");
		System.out.println(nc.numberToChinese(5006007));
		System.out.print(5046007+"\t");
		System.out.println(nc.numberToChinese(5046007));
		System.out.print(5040307+"\t");
		System.out.println(nc.numberToChinese(5040307));
		System.out.print(90001001+"\t");
		System.out.println(nc.numberToChinese(90001001));
		System.out.print(90500001+"\t");
		System.out.println(nc.numberToChinese(90500001));
		System.out.print(90504001+"\t");
		System.out.println(nc.numberToChinese(90504001));
		System.out.print(90000301+"\t");
		System.out.println(nc.numberToChinese(90000301));
		System.out.print(12345678+"\t");
		System.out.println(nc.numberToChinese(12345678));
		
		System.out.println("\n8. test 9-10 digits number:");
		System.out.print(100000000+"\t");
		System.out.println(nc.numberToChinese(100000000));
		System.out.print(100000001+"\t");
		System.out.println(nc.numberToChinese(100000001));
		System.out.print(102030405+"\t");
		System.out.println(nc.numberToChinese(102030405));
		System.out.print(900800007+"\t");
		System.out.println(nc.numberToChinese(900800007));
		System.out.print(300040005+"\t");
		System.out.println(nc.numberToChinese(300040005));
		System.out.print(400006005+"\t");
		System.out.println(nc.numberToChinese(400006005));
		System.out.print(2000000000+"\t");
		System.out.println(nc.numberToChinese(2000000000));
		System.out.print(1400000000+"\t");
		System.out.println(nc.numberToChinese(1400000000));
		System.out.print(1020304050+"\t");
		System.out.println(nc.numberToChinese(1020304050));
		System.out.print(1000300015+"\t");
		System.out.println(nc.numberToChinese(1000300015));
		System.out.print(2003307009+"\t");
		System.out.println(nc.numberToChinese(2003307009));
		System.out.print(Integer.MAX_VALUE+"\t");
		System.out.println(nc.numberToChinese(Integer.MAX_VALUE));
				
		System.out.println("\n........ end of  test ..........");

		
	}
}
