package com.zhchong.common.enums;
/**
 * 
 * @description 所有枚举接口
 * @projectname blog
 * @packagename com.zhchong.common.enums
 * @typename BaseEnum
 * @filename BaseEnum.java
 * @author zhchong
 * @date 2020年7月25日 下午11:43:49
 */
public interface BaseEnum {
	
	/**
	 * 
	 * @description 获取枚举值对应的描述信息
	 * @projectname blog
	 * @packagename com.zhchong.common.enums
	 * @filename BaseEnum.java
	 * @return String
	 * @author zhchong
	 * @date 2020年7月25日 下午11:44:17
	 */
	public String getDesc();
	
	/**
	 * 
	 * @description 获取枚举值
	 * @projectname blog
	 * @packagename com.zhchong.common.enums
	 * @filename BaseEnum.java
	 * @return String
	 * @author zhchong
	 * @date 2020年7月25日 下午11:44:28
	 */
	public String getValue();
	/**
	 * 
	 * @description 枚举对外的类型名称，外部可通过类型名称获取到该枚举，一般可以通过this.getClass().getSimpleName()获取名称，如果有名字冲突可指定特殊名称
	 * @projectname blog
	 * @packagename com.zhchong.common.enums
	 * @filename BaseEnum.java
	 * @return String
	 * @author zhchong
	 * @date 2020年7月25日 下午11:43:14
	 */
	public String enumTypeName();
	
}
