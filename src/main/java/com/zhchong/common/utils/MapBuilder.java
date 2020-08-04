package com.zhchong.common.utils;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder {
	private Map<String, String> map;

	private MapBuilder()
	{
		if (null == map)
		{
			map = new HashMap<String, String>();
		}
	}

	public static MapBuilder newMap()
	{
		return new MapBuilder();
	}

	public MapBuilder put(String key, String value)
	{
		this.map.put(key, value);
		return this;
	}

	public MapBuilder putAll(Map<String, String> map)
	{
		this.map.putAll(map);
		return this;
	}

	public Map<String, String> build()
	{
		return this.map;
	}
}
