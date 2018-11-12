package com.gkr.util.web.validator;

/**
 * 主要实现各种数据类型格式校验
 * 
 * @author Nate
 *
 */
public class ValueRuleValidator {
	public static boolean isBoolean(String str) {
		if (isString(str)) {
			if ("true".equals(str) || "false".equals(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否是一个字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isString(String str) {
		if ("".equals(str) || null == str) {
			return false;
		} else
			return true;
	}

	/**
	 * 进行验证码检测
	 * 
	 * @param str
	 * @param rand
	 * @return
	 */
	public static boolean isRand(String str, String rand) {
		if (isString(str) && isString(rand)) {
			return str.equalsIgnoreCase(rand);//不区分大小写
		}
		return false;
	}

	/**
	 * 是不是一个datetime：2018-10-31
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDate(String str) {
		if (isString(str)) {
			return str.matches("\\d{4}-\\d{2}-\\d{2}");
		}
		return false;
	}

	/**
	 * 是不是一个datetime：2018-10-31 10
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDatetime(String str) {
		if (isString(str)) {
			return str.matches("\\d{4}-\\d{2}-\\d{2}: \\d{2}:\\d{2}:\\d{2}");
		}
		return false;
	}

	/**
	 * 是不是一个浮点数据 100.52
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		if (isString(str)) {
			return str.matches("\\d+(\\.\\d+)?");
		}
		return false;
	}

	/**
	 * 是不是长整型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isLong(String str) {
		if ("".equals(str) || null == str) {
			return str.matches("\\d+");
		}
		return false;
	}

	/**
	 * 是不是整形
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInt(String str) {
		if ("".equals(str) || null == str) {
			return isInt(str);
		}
		return false;
	}

	/**
	 * 是不是上传的合法文件类型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMime(String rule, String contentType) {
		if (isString(contentType) && isString(rule)) {
			String ruleResult[] = rule.split("\\|");
			for (int x = 0; x < ruleResult.length; x++) {
				if (contentType.equals(ruleResult[x]))
					return true;
			}
		}
		return false;
	}
}
