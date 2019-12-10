package com.example.demo1.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @Description: 随机数工具类
 * @Author: LiuZW
 * @Date: 2019/12/4/004 15:16
 **/
public class RandomUtils {

	
	
	public static void main(String[] args) {
		String a = getRandomNumberNotStartWithZeroByLength(6);
		System.out.println(a);
	}

	/**
	 * 使用jdk api生成标准UUID (已过滤所有的中划线)
	 * 
	 * @return
	 */
	public static String getUUIDByJdk() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}


	
	/**
	 * 生成给定长度的随机数字字符串（数字类型）
	 *
	 * @param len
	 *            长度
	 * @return
	 */
	public static String getRandomNumberByLength(int len) {
		StringBuffer sb = new StringBuffer();
		while (sb.length() < len) {
			int bn = new Random().nextInt(10);
			sb.append(bn);
		}
		return sb.toString();
	}

	/**
	 * 生成给定长度的随机数字字符串（数字类型）,不是0开头
	 *
	 * @param len
	 *            长度
	 * @return
	 */
	public static String getRandomNumberNotStartWithZeroByLength(int len) {
		StringBuffer sb = new StringBuffer();
		while (sb.length() < len) {
			int bn = new Random().nextInt(9) + 1;
			sb.append(bn);
		}
		return sb.toString();
	}

	/**
	 * 生成给定长度的随机字符串（数字+字母）
	 * 
	 * @param len
	 *            长度
	 * @return
	 */
	public static String getRandomStrByLength(int len) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			if ("char".equalsIgnoreCase(charOrNum)) {
				int temp = 65;
				sb.append((char) (random.nextInt(26) + temp));
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				sb.append(String.valueOf(random.nextInt(10)));
			}
		}
		return sb.toString();
	}

}

