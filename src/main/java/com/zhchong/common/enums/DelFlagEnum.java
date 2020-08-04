package com.zhchong.common.enums;

public enum DelFlagEnum implements BaseEnum {
	NO("否", "0"),
	YES("是", "1");
	
	private String desc;
	private String value;

	private DelFlagEnum(String desc, String value)
	{
		this.desc = desc;
		this.value = value;
	}

	@Override
	public String getDesc()
	{
		return this.desc;
	}

	@Override
	public String getValue()
	{
		return this.value;
	}

	@Override
	public String enumTypeName()
	{
		return this.getClass().getSimpleName();
	}
}
