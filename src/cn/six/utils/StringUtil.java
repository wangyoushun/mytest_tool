package cn.six.utils;

public class StringUtil {

	public static final String EMPTY = "";
	
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		return str==null || str.length()==0;
	}
	
	/**
	 * 判断字符串不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
	/**
	 * 判断字符串是空字符串
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		int strLen = 0;
		if(str==null || (strLen=str.length())==0){
			return true;
		}
		for(int i=0; i<strLen; i++){
			if(Character.isWhitespace(str.charAt(i))==false){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断字符串不为空字符串
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str){
		return !isBlank(str);
	}
	
	//trim  start
	/**
	 * 去除字符串左右空字符
	 * @param str
	 * @return
	 */
	public static String trim(String str){
		return str==null ? null : str.trim();
	}
	
	/**
	 * trim后为空转为null
	 * @param str
	 * @return
	 */
	public static String trimToNull(String str){
		String trim = trim(str);
		return isEmpty(trim) ? null : trim;
	}
	
	
	/**
	 * trim后为null转为空
	 * @param str
	 * @return
	 */
	public static String trimToEmpty(String str){
		return str==null ? EMPTY : trim(str);
	}
	//trim  end
	
	/**
	 * 判断两个字符串是否相等
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equals(String str1, String str2){
		return str1==null ? str2==null : str1.equals(str2);
	}
	
	/**
	 * 忽视大小写判断两个字符串是否相等
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equalsIgnoreCase(String str1, String str2){
		return str1==null ? str2==null : str1.equalsIgnoreCase(str2);
	}
	
	/**
	 * 字符串转大写
	 * @param str
	 * @return
	 */
	public static String upperCase(String str){
		return str==null ? null : str.toUpperCase();
	}
	
	/**
	 * 字符串转小写
	 * @param str
	 * @return
	 */
	public static String lowerCase(String str){
		return str==null ? null : str.toLowerCase();
	}
}
