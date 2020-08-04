package com.zhchong.common.utils;

public class ArrayUtils {

	/**
	 * 把数组转换成以逗号分隔的字符串
	 * @description:
	 * @param array
	 * @return
	 * @author:OA项目组  连石生
	 * @createTime:2016年7月17日 下午6:59:44
	 */
	public static String toString(String[] array)
	{
		if (null == array || 0 == array.length)
		{
			return null;
		}

		StringBuilder sb = new StringBuilder();
		int length = array.length;
		for (int i = 0; i < length; i++)
		{
			sb.append(array[i]);
			if (i != (length - 1))
			{
				sb.append(",");
			}
		}

		return sb.toString();
	}

	/**
	 * 把数组转换成以逗号分隔的字符串
	 * @description:
	 * @param array
	 * @return
	 * @author:OA项目组  连石生
	 * @createTime:2016年7月17日 下午6:59:52
	 */
	public static String toString(Long[] array)
	{
		if (null == array || 0 == array.length)
		{
			return null;
		}

		StringBuilder sb = new StringBuilder();
		int length = array.length;
		for (int i = 0; i < length; i++)
		{
			sb.append(array[i]);
			if (i != (length - 1))
			{
				sb.append(",");
			}
		}

		return sb.toString();
	}

	/**
	 * 把字符串转换成数组，默认分隔符为","
	 * @description:
	 * @param strs
	 * @return
	 * @author:OA项目组  连石生
	 * @createTime:2016年7月17日 下午6:59:57
	 */
	public static String[] toArray(String strs)
	{
		return toArray(strs, ",");
	}

	/**
	 * 把字符串按指定的正则表达式分隔成数组
	 * @description:
	 * @param strs
	 * @param regex
	 * @return
	 * @author:OA项目组  连石生
	 * @createTime:2016年7月17日 下午7:00:03
	 */
	public static String[] toArray(String strs, String regex)
	{
		if (StringUtils.isEmpty(strs))
		{
			return null;
		}

		return strs.split(regex);
	}
	
	/**
	 * 判断一个字符串是否包含在一个字符串数组中
	 * @description:
	 * @param array
	 * @param element
	 * @return
	 * @author:OA项目组  连石生
	 * @createTime:2016年7月17日 下午7:00:09
	 */
	public static boolean containsElement(String[] array, String element)
	{
		if (null == array || 0 == array.length)
		{
			return false;
		}

		int length = array.length;
		for (int i = 0; i < length; i++)
		{
			String arr = array[i];
			if (arr.equals(element))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * 判断数据是否为空
	 * @description:
	 * @param arr
	 * @return
	 * @author:OA项目组  连石生
	 * @createTime:2016年7月17日 下午7:21:15
	 */
	public static boolean isEmpty(Object[] arr)
	{
		return null == arr || 0 == arr.length;
	}
}
