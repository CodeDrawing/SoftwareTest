
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ���֤��ع�����
 *
 * @author zhaojiaxing
 * @version 1.0
 * @since 2020/01/07 14:40
 */
public class IdCardVerification {

    /**
     * �Ƿ���Ч���֤��
     *
     * @param idCard ���֤�ţ�֧��18λ��15λ�͸۰�̨��10λ
     * @return �Ƿ���Ч
     */
    public static boolean isValidCard(String idCard) {
        idCard = idCard.trim();
        int length = idCard.length();
        switch (length) {
            // 18λ���֤
            case 18:
                return isValidCard18(idCard);
            // 15λ���֤
            case 15:
                return isValidCard15(idCard);
            // 10λ���֤���۰�̨����
            case 10: {
                String[] cardval = isValidCard10(idCard);
                return null != cardval && cardval[2].equals("true");
            }
            default:
                return false;
        }
    }

    /**
     * ��15λ���֤����ת��Ϊ18λ
     *
     * @param idCard 15λ��ݱ���
     * @return 18λ��ݱ���
     */
    public static String convertIdCard(String idCard) {
        StringBuilder idCard18;
        if (idCard.length() != CHINA_ID_MIN_LENGTH) {
            return null;
        }
        if (isMatch(NUMBERS, idCard)) {
            // ��ȡ����������
            String birthday = idCard.substring(6, 12);
            Date birthDate = strToDate(birthday, YY_MM_DD);
            // ��ȡ������
            int sYear = year(birthDate);
            // ������2000��֮�󲻴���15λ���֤�����Բ�Ҫ���ж�
            if (sYear > 2000) {
                sYear -= 100;
            }
            idCard18 = new StringBuilder().append(idCard, 0, 6).append(sYear).append(idCard.substring(8));
            // ��ȡУ��λ
            char sVal = getCheckCode18(idCard18.toString());
            idCard18.append(sVal);
        } else {
            return null;
        }
        return idCard18.toString();
    }

    /**
     * �����֤�����л�ȡ�������ڣ�ֻ֧��15��18λ���֤����
     *
     * @param idCard ���֤����
     * @return ����
     */
    public static Date getBirthDate(String idCard) {
        final String birthByIdCard = getBirth(idCard);
        return null == birthByIdCard ? null : strToDate(birthByIdCard, YYYY_MM_DD);
    }

    /**
     * ������ݱ�Ż�ȡ���䣬ֻ֧��15��18λ���֤����
     *
     * @param idCard ���֤��
     * @return ����
     */
    public static int getAgeByCard(String idCard) {
        String birth = getBirth(idCard);
        return getAge(strToDate(birth, YYYY_MM_DD), new Date());
    }

    /**
     * ���ݳ������ڼ�����ĳ�����ڵ�����
     *
     * @param birthDay      ����
     * @param dateToCompare ��Ҫ�Աȵ�����
     * @return ����
     */
    public static int getAge(Date birthDay, Date dateToCompare) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateToCompare);

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("Birthday is after date " + dateToCompare + " !");
        }

        //�Ȼ�ȡָ�����ڵ�������
        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        final boolean isLastDayOfMonth = dayOfMonth == cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        //��ȡ�������ڵ�����
        cal.setTime(birthDay);
        int age = year - cal.get(Calendar.YEAR);
        final int monthBirth = cal.get(Calendar.MONTH);

        if (month == monthBirth) {
            //��ȡ�������ڵ���
            final int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
            final boolean isLastDayOfMonthBirth = dayOfMonthBirth == cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            // ��������ڵ��£�����δ�ﵽ���յ�������ڣ������һ(�ж��Ƿ������һ����Ϊ��ȥ�����µ�Ӱ��)
            if ((false == isLastDayOfMonth || false == isLastDayOfMonthBirth) && dayOfMonth < dayOfMonthBirth) {
                age--;
            }
        } else if (month < monthBirth) {
            // �����ǰ�·�δ�ﵽ���յ��·ݣ���������һ
            age--;
        }

        return age;
    }


    /**
     * �������֤�����ȡ�����ֻ֧꣬��15��18λ���֤����
     *
     * @param idCard ��ݱ��
     * @return ����(yyyy)
     */
    public static Short getYearByIdCard(String idCard) {
        final int len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = convertIdCard(idCard);
        }
        return Short.valueOf(idCard.substring(6, 10));
    }

    /**
     * �������֤�����ȡ�����£�ֻ֧��15��18λ���֤����
     *
     * @param idCard ��ݱ��
     * @return ����(MM)
     */
    public static Short getMonthByIdCard(String idCard) {
        final int len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = convertIdCard(idCard);
        }
        return Short.valueOf(idCard.substring(10, 12));
    }

    /**
     * �������֤�����ȡ�����죬ֻ֧��15��18λ���֤����
     *
     * @param idCard ��ݱ��
     * @return ����(dd)
     */
    public static Short getDayByIdCard(String idCard) {
        final int len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = convertIdCard(idCard);
        }
        return Short.valueOf(idCard.substring(12, 14));
    }

    /**
     * �������֤�����ȡ�Ա�ֻ֧��15��18λ���֤����
     *
     * @param idCard ��ݱ��
     * @return �Ա�(1 : �� �� 0 : Ů)
     */
    public static int getGender(String idCard) {
        if (idCard == null || idCard.trim().length() == 0) {
            throw new IllegalArgumentException("ID Card is must not null");
        }
        final int len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            throw new IllegalArgumentException("ID Card length must be 15 or 18");
        }

        if (len == CHINA_ID_MIN_LENGTH) {
            idCard = convertIdCard(idCard);
        }
        char sCardChar = idCard.charAt(16);
        return (sCardChar % 2 != 0) ? 1 : 0;
    }

    /**
     * �������֤�����ȡ����ʡ�ݣ�ֻ֧��15��18λ���֤����
     *
     * @param idCard ��ݱ���
     * @return ʡ�����롣
     */
    public static String getProvince(String idCard) {
        int len = idCard.length();
        if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
            String sProvinNum = idCard.substring(0, 2);
            return cityCodes.get(sProvinNum);
        }
        return null;
    }

    /**
     * �ж�18λ���֤�ĺϷ���
     *
     * ������ݺ�������������룬��ʮ��λ���ֱ������һλ����У������ɣ�����˳�������������Ϊ����λ���ֵ�ַ�룬��λ���ֳ��������룬��λ����˳�����һλ����У���롣
     * ˳����: ��ʾ��ͬһ��ַ������ʶ������Χ�ڣ���ͬ�ꡢͬ�¡�ͬ �ճ������˱ඨ��˳��ţ�˳�����������������ԣ�ż������ ��Ů�ԡ�
     * ��1��2λ���ֱ�ʾ������ʡ�ݵĴ��룻��3��4λ���ֱ�ʾ�����ڳ��еĴ��룻��5��6λ���ֱ�ʾ���������صĴ��룬��7~14λ���ֱ�ʾ�������ꡢ�¡���
     * ��15��16λ���ֱ�ʾ�����ڵص��ɳ����Ĵ��룻��17λ���ֱ�ʾ�Ա�������ʾ���ԣ�ż����ʾŮ��
     * ��18λ������У���룬�����������֤����ȷ�ԡ�У���������0~9�����֣���ʱҲ��x��ʾ
     * ��ʮ��λ����(У����)�ļ��㷽��Ϊ��
     *
     * @param idCard ����֤�����֤
     * @return �Ƿ���Ч��18λ���֤
     */
    private static boolean isValidCard18(String idCard) {
        if (CHINA_ID_MAX_LENGTH != idCard.length()) {
            return false;
        }

        //У������
        if (false == isBirthday(idCard.substring(6, 14))) {
            return false;
        }

        // ǰ17λ
        String code17 = idCard.substring(0, 17);
        // ��18λ
        char code18 = Character.toLowerCase(idCard.charAt(17));
        if (isMatch(NUMBERS, code17)) {
            // ��ȡУ��λ
            char val = getCheckCode18(code17);
            return val == code18;
        }
        return false;
    }

    /**
     * ��֤15λ��ݱ����Ƿ�Ϸ�
     *
     * @param idCard ��ݱ���
     * @return �Ƿ�Ϸ�
     */
    private static boolean isValidCard15(String idCard) {
        if (CHINA_ID_MIN_LENGTH != idCard.length()) {
            return false;
        }
        if (isMatch(NUMBERS, idCard)) {
            // ʡ��
            String proCode = idCard.substring(0, 2);
            if (null == cityCodes.get(proCode)) {
                return false;
            }

            //У�����գ���λ��ݣ�����Ϊ19XX��
            return false != isBirthday("19" + idCard.substring(6, 12));
        } else {
            return false;
        }
    }

    /**
     * ��֤10λ��ݱ����Ƿ�Ϸ�
     *
     * @param idCard ��ݱ���
     * @return ���֤��Ϣ����
     * [0] - ̨�塢���š���� [1] - �Ա�(��M,ŮF,δ֪N) [2] - �Ƿ�Ϸ�(�Ϸ�true,���Ϸ�false) ���������֤�������򷵻�null
     */
    private static String[] isValidCard10(String idCard) {
        if (idCard == null || idCard.trim().length() == 0) {
            return null;
        }
        String[] info = new String[3];
        String card = idCard.replaceAll("[()]", "");
        if (card.length() != 8 && card.length() != 9 && idCard.length() != 10) {
            return null;
        }
        // ̨��
        if (idCard.matches("^[a-zA-Z][0-9]{9}$")) {
            info[0] = "̨��";
            String char2 = idCard.substring(1, 2);
            if (char2.equals("1")) {
                info[1] = "M";
            } else if (char2.equals("2")) {
                info[1] = "F";
            } else {
                info[1] = "N";
                info[2] = "false";
                return info;
            }
            info[2] = isValidTWCard(idCard) ? "true" : "false";
        } else if (idCard.matches("^[157][0-9]{6}\\(?[0-9A-Z]\\)?$")) {
            // ����
            info[0] = "����";
            info[1] = "N";
        } else if (idCard.matches("^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?$")) {
            // ���
            info[0] = "���";
            info[1] = "N";
            info[2] = isValidHKCard(idCard) ? "true" : "false";
        } else {
            return null;
        }
        return info;
    }

    /**
     * ��̨֤�����֤����
     *
     * @param idCard ���֤����
     * @return ��֤���Ƿ����
     */
    private static boolean isValidTWCard(String idCard) {
        if (idCard == null || idCard.trim().length() == 0) {
            return false;
        }
        String start = idCard.substring(0, 1);
        Integer iStart = twFirstCode.get(start);
        if (null == iStart) {
            return false;
        }
        String mid = idCard.substring(1, 9);
        String end = idCard.substring(9, 10);
        int sum = iStart / 10 + (iStart % 10) * 9;
        final char[] chars = mid.toCharArray();
        int iflag = 8;
        for (char c : chars) {
            sum += Integer.valueOf(String.valueOf(c)) * iflag;
            iflag--;
        }
        return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.valueOf(end);
    }

    /**
     * ��֤������֤����
     * ���֤ǰ2λΪӢ���ַ������ֻ����һ��Ӣ���ַ����ʾ��һλ�ǿո񣬶�Ӧ����58 ǰ2λӢ���ַ�A-Z�ֱ��Ӧ����10-35 ���һλУ����Ϊ0-9�����ּ����ַ�"A"��"A"����10
     * �����֤����ȫ��ת��Ϊ���֣��ֱ��Ӧ��9-1��ӵ��ܺͣ�����11��֤��������Ч
     * @param idCard ���֤����
     * @return ��֤���Ƿ����
     */
    private static boolean isValidHKCard(String idCard) {
        String card = idCard.replaceAll("[()]", "");
        int sum;
        if (card.length() == 9) {
            sum = (Character.toUpperCase(card.charAt(0)) - 55) * 9 + (Character.toUpperCase(card.charAt(1)) - 55) * 8;
            card = card.substring(1, 9);
        } else {
            sum = 522 + (Character.toUpperCase(card.charAt(0)) - 55) * 8;
        }
        String start = idCard.substring(0, 1);
        Integer iStart = hkFirstCode.get(start);
        if (null == iStart) {
            return false;
        }
        String mid = card.substring(1, 7);
        String end = card.substring(7, 8);
        char[] chars = mid.toCharArray();
        int iflag = 7;
        for (char c : chars) {
            sum = sum + Integer.valueOf(String.valueOf(c)) * iflag;
            iflag--;
        }
        if ("A".equals(end.toUpperCase())) {
            sum += 10;
        } else {
            sum += Integer.parseInt(end);
        }
        return sum % 11 == 0;
    }

    /**
     * ������ݱ�Ż�ȡ���գ�ֻ֧��15��18λ���֤����
     *
     * @param idCard ��ݱ��
     * @return ����(yyyyMMdd)
     */
    public static String getBirth(String idCard) {
        final int len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = convertIdCard(idCard);
        }
        return idCard.substring(6, 14);
    }

    /**
     * ���18λ���֤У����
     * ���㷽ʽ��
     * ��ǰ������֤����17λ���ֱ���Բ�ͬ��ϵ�����ӵ�һλ����ʮ��λ��ϵ���ֱ�Ϊ��7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * ����17λ���ֺ�ϵ����˵Ľ�����
     * �üӳ����ͳ���11���������Ƕ���
     * ����ֻ������0 1 2 3 4 5 6 7 8 9 10��11�����֡���ֱ��Ӧ�����һλ���֤�ĺ���Ϊ1 0 X 9 8 7 6 5 4 3 2
     * ͨ�������֪���������2���ͻ������֤�ĵ�18λ�����ϳ����������ֵĢ������������10�����֤�����һλ�������2
     * @param code17 18λ���֤���е�ǰ17λ
     * @return ��18λ
     */
    private static char getCheckCode18(String code17) {
        int sum = getPowerSum(code17.toCharArray());
        return getCheckCode18(sum);
    }

    /**
     * ��power��ֵ��11ȡģ�����������У�����ж�
     *
     * @param iSum ��Ȩ��
     * @return У��λ
     */
    private static char getCheckCode18(int iSum) {
        switch (iSum % 11) {
            case 10:
                return '2';
            case 9:
                return '3';
            case 8:
                return '4';
            case 7:
                return '5';
            case 6:
                return '6';
            case 5:
                return '7';
            case 4:
                return '8';
            case 3:
                return '9';
            case 2:
                return 'x';
            case 1:
                return '0';
            case 0:
                return '1';
            default:
                return SPACE;
        }
    }

    /**
     * �����֤��ÿλ�Ͷ�Ӧλ�ļ�Ȩ�������֮���ٵõ���ֵ
     *
     * @param iArr ���֤���������
     * @return ���֤����
     */
    private static int getPowerSum(char[] iArr) {
        int iSum = 0;
        if (power.length == iArr.length) {
            for (int i = 0; i < iArr.length; i++) {
                iSum += Integer.valueOf(String.valueOf(iArr[i])) * power[i];
            }
        }
        return iSum;
    }

    /**
     * �������ڻ�ȡ��
     *
     * @param date ����
     * @return ��Ĳ���
     */
    public static int year(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.YEAR);
    }

    /**
     * ��֤�Ƿ�Ϊ����<br>
     * ֻ֧�����¼��ָ�ʽ��
     * <ul>
     * <li>yyyyMMdd</li>
     * <li>yyyy-MM-dd</li>
     * <li>yyyy/MM/dd</li>
     * <li>yyyy.MM.dd</li>
     * <li>yyyy��MM��dd��</li>
     * </ul>
     *
     * @param value ֵ
     * @return �Ƿ�Ϊ����
     */
    public static boolean isBirthday(CharSequence value) {
        if (isMatch(BIRTHDAY, value)) {
            Matcher matcher = BIRTHDAY.matcher(value);
            if (matcher.find()) {
                int year = Integer.parseInt(matcher.group(1));
                int month = Integer.parseInt(matcher.group(3));
                int day = Integer.parseInt(matcher.group(5));
                return isBirthday(year, month, day);
            }
        }
        return false;
    }

    /**
     * ��֤�Ƿ�Ϊ����
     *
     * @param year  �꣬��1900�꿪ʼ����
     * @param month �£���1��ʼ����
     * @param day   �գ���1��ʼ����
     * @return �Ƿ�Ϊ����
     */
    public static boolean isBirthday(int year, int month, int day) {
        // ��֤��
        int thisYear = year(new Date());
        if (year < 1900 || year > thisYear) {
            return false;
        }

        // ��֤��
        if (month < 1 || month > 12) {
            return false;
        }

        // ��֤��
        if (day < 1 || day > 31) {
            return false;
        }
        // ��鼸�������µ��������
        if (day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            return false;
        }
        if (month == 2) {
            // ��2�£����������28���������29
            return day < 29 || (day == 29 && isLeapYear(year));
        }
        return true;
    }

    /**
     * �Ƿ�����
     *
     * @param year ��
     * @return �Ƿ�����
     */
    private static boolean isLeapYear(int year) {
        return new GregorianCalendar().isLeapYear(year);
    }

    /**
     * ���ַ���ת����ָ����ʽ������
     *
     * @param str        �����ַ���.
     * @param dateFormat ���ڸ�ʽ. ���Ϊ�գ�Ĭ��Ϊ:yyyy-MM-dd HH:mm:ss.
     * @return
     */
    private static Date strToDate(final String str, String dateFormat) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        try {
            if (dateFormat == null || dateFormat.length() == 0) {
                dateFormat = DATE_FORMAT;
            }
            DateFormat fmt = new SimpleDateFormat(dateFormat);
            return fmt.parse(str.trim());
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * ���������Ƿ�ƥ������
     *
     * @param pattern ģʽ
     * @param content ����
     * @return ����Ϊnull����""�򲻼�飬����true������Ϊnull����false
     */
    private static boolean isMatch(Pattern pattern, CharSequence content) {
        if (content == null || pattern == null) {
            // �ṩnull���ַ���Ϊ��ƥ��
            return false;
        }
        return pattern.matcher(content).matches();
    }


    /**
     * �й��������֤������С���ȡ�
     */
    private static final int CHINA_ID_MIN_LENGTH = 15;
    /**
     * �й��������֤������󳤶ȡ�
     */
    private static final int CHINA_ID_MAX_LENGTH = 18;
    /**
     * ÿλ��Ȩ����
     */
    private static final int[] power = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * ʡ�д����
     */
    private static Map<String, String> cityCodes = new HashMap<>();
    /**
     * ̨���������ĸ��Ӧ����
     */
    private static Map<String, Integer> twFirstCode = new HashMap<>();
    /**
     * ����������ĸ��Ӧ����
     */
    private static Map<String, Integer> hkFirstCode = new HashMap<>();

    /**
     * Ĭ������ģ��
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String YYYY_MM_DD = "yyyyMMdd";
    private static final String YY_MM_DD = "yyMMdd";
    public static final char SPACE = ' ';
    /**
     * ����
     */
    public final static Pattern NUMBERS = Pattern.compile("\\d+");
    /**
     * ����
     */
    public final static Pattern BIRTHDAY = Pattern.compile("^(\\d{2,4})([/\\-.��]?)(\\d{1,2})([/\\-.��]?)(\\d{1,2})��?$");

    static {
        cityCodes.put("11", "����");
        cityCodes.put("12", "���");
        cityCodes.put("13", "�ӱ�");
        cityCodes.put("14", "ɽ��");
        cityCodes.put("15", "���ɹ�");
        cityCodes.put("21", "����");
        cityCodes.put("22", "����");
        cityCodes.put("23", "������");
        cityCodes.put("31", "�Ϻ�");
        cityCodes.put("32", "����");
        cityCodes.put("33", "�㽭");
        cityCodes.put("34", "����");
        cityCodes.put("35", "����");
        cityCodes.put("36", "����");
        cityCodes.put("37", "ɽ��");
        cityCodes.put("41", "����");
        cityCodes.put("42", "����");
        cityCodes.put("43", "����");
        cityCodes.put("44", "�㶫");
        cityCodes.put("45", "����");
        cityCodes.put("46", "����");
        cityCodes.put("50", "����");
        cityCodes.put("51", "�Ĵ�");
        cityCodes.put("52", "����");
        cityCodes.put("53", "����");
        cityCodes.put("54", "����");
        cityCodes.put("61", "����");
        cityCodes.put("62", "����");
        cityCodes.put("63", "�ຣ");
        cityCodes.put("64", "����");
        cityCodes.put("65", "�½�");
        cityCodes.put("71", "̨��");
        cityCodes.put("81", "���");
        cityCodes.put("82", "����");
        cityCodes.put("91", "����");

        twFirstCode.put("A", 10);
        twFirstCode.put("B", 11);
        twFirstCode.put("C", 12);
        twFirstCode.put("D", 13);
        twFirstCode.put("E", 14);
        twFirstCode.put("F", 15);
        twFirstCode.put("G", 16);
        twFirstCode.put("H", 17);
        twFirstCode.put("J", 18);
        twFirstCode.put("K", 19);
        twFirstCode.put("L", 20);
        twFirstCode.put("M", 21);
        twFirstCode.put("N", 22);
        twFirstCode.put("P", 23);
        twFirstCode.put("Q", 24);
        twFirstCode.put("R", 25);
        twFirstCode.put("S", 26);
        twFirstCode.put("T", 27);
        twFirstCode.put("U", 28);
        twFirstCode.put("V", 29);
        twFirstCode.put("X", 30);
        twFirstCode.put("Y", 31);
        twFirstCode.put("W", 32);
        twFirstCode.put("Z", 33);
        twFirstCode.put("I", 34);
        twFirstCode.put("O", 35);

        //����http://shenfenzheng.bajiu.cn/?rid=40
        // ��֤��ӵ����۾���Ȩ
        hkFirstCode.put("A", 1);
        // ��֤�������Ƶĳ������ڻ�ص����״εǼ��Ժ�����������
        hkFirstCode.put("B", 2);
        // ��֤�˵Ǽ���֤ʱ����۵ľ����ܵ��뾳���񴦴���������
        hkFirstCode.put("C", 3);
        // ��֤���������������״εǼ��Ժ�����������
        hkFirstCode.put("N", 14);
        // ��֤�˱�������ۡ����ż��й�����������������ҳ���
        hkFirstCode.put("O", 15);
        // ��֤��ӵ������뾳Ȩ
        hkFirstCode.put("R", 18);
        // ��֤�˵Ǽ���֤ʱ����۵ľ��������뾳���񴦴���������
        hkFirstCode.put("U", 21);
        // ��֤�˱����ڰ��ŵ�������
        hkFirstCode.put("W", 23);
        // ��֤�˱������й���½����
        hkFirstCode.put("X", 24);
        // ��֤�˱�������۳���
        hkFirstCode.put("Z", 26);
    }

    public static void main(String[] args) {
        String idCard="51012520000829041X";
        System.out.println(isValidCard(idCard));
        System.out.println(getAgeByCard(idCard));
        System.out.println(getProvince(idCard));
        System.out.println(getBirthDate(idCard));
    }
}
