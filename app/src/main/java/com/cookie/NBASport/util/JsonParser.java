package com.cookie.NBASport.util;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.cookie.NBASport.bean.NewsDetail;
import com.cookie.NBASport.bean.NewsItemEntity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class JsonParser {
	private static Gson gson = new Gson();
	private static final com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();


	public static <T> T parserJson(String result, Type type) {
		return gson.fromJson(result, type);
	}

	public static <T> T parserJson(String result, Type type, String key) {
		JsonElement targerElement = null;
		JsonElement jsonElement = jsonParser.parse(result);
		try {
			Set<Entry<String, JsonElement>> setJsons = jsonElement.getAsJsonObject().entrySet();
			for (Entry<String, JsonElement> set : setJsons) {
				String name = set.getKey();
				if (name.equals(key)) {
					targerElement = set.getValue();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gson.fromJson(targerElement, type);
	}

	public static <T> T parserJson(String result, Class<T> cls) {
		return gson.fromJson(result, cls);
	};
	

	public static int getResultCode(String result) {
		try {
			JSONObject jsonObject = new JSONObject(result.toString());
			int code = jsonObject.getInt("code");
			return code;
		} catch (Exception e) {
		}
		return 3;
	}

	public static String getResultMessage(String result) {
		String message = null;
		try {
			JSONObject jsonObject = new JSONObject(result.toString());
			message = jsonObject.getString("message");
		} catch (Exception e) {
		}
		return message;
	}


	public static NewsItemEntity parseNewsItem(String jsonStr) {
		NewsItemEntity newsItem = new NewsItemEntity();
		List<NewsItemEntity.NewsItem> list = new ArrayList<NewsItemEntity.NewsItem>();
		int code = getResultCode(jsonStr);
		if(code==0){
			try {
				JSONObject jsonObject = new JSONObject(jsonStr.toString());
				String dataJson = jsonObject.optJSONObject("data").toString();
				com.alibaba.fastjson.JSONObject data = JSON.parseObject(dataJson); // articleIds=    NullPoint
				for (Map.Entry<String, Object> item : data.entrySet()) {
					Gson gson = new Gson();
					NewsItemEntity.NewsItem bean = gson.fromJson(item.getValue().toString(), NewsItemEntity.NewsItem.class);
					bean.index = item.getKey();
					list.add(bean);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

		// 由于fastjson获取出来的entrySet是乱序的  所以这边重新排序
		/*Collections.sort(list, new Comparator<NewsItemEntity.NewsItem>() {
			@Override
			public int compare(NewsItem.NewsItemBean lhs, NewsItem.NewsItemBean rhs) {
				return rhs.index.compareTo(lhs.index);
			}
		});*/
		newsItem.data = list;
		return newsItem;
	}



	public static NewsDetail parseNewsDetail(String jsonStr) {
		NewsDetail detail = new NewsDetail();
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(jsonStr.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String dataJson = jsonObject.optJSONObject("data").toString();
		com.alibaba.fastjson.JSONObject data = JSON.parseObject(dataJson);
		if (data != null) {
			for (Map.Entry<String, Object> entry : data.entrySet()) {
				if (entry.getKey().equals("title")) {
					detail.title = entry.getValue().toString();
				} else if (entry.getKey().equals("abstract")) {
					detail.abstractX = entry.getValue().toString();
				} else if (entry.getKey().equals("content")) {
					String contentStr = entry.getValue().toString();
					try {
						List<Map<String, String>> list = new LinkedList<>();
						JSONArray jsonArray = new JSONArray(contentStr);
						for (int i = 0; i < jsonArray.length(); i++) {
							org.json.JSONObject item = jsonArray.getJSONObject(i); // 得到每个对象
							Map<String, String> map = new HashMap<>();
							if (item.get("type").equals("text")) {
								map.put("text", item.get("info").toString());
							} else if (item.get("type").equals("img")) {
								String imgStr = item.get("img").toString();
								com.alibaba.fastjson.JSONObject imgObj = JSON.parseObject(imgStr);
								for (Map.Entry<String, Object> imgItem : imgObj.entrySet()) {
									if (imgItem.getKey().toString().startsWith("imgurl") && !TextUtils.isEmpty(imgItem.getValue().toString())) {
										com.alibaba.fastjson.JSONObject imgUrlObj = JSON.parseObject(imgItem.getValue().toString());
										String url = imgUrlObj.getString("imgurl");
										map.put("img", url);
										break;
									}
								}
							}
							list.add(map);
						}
						detail.content = list;
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (entry.getKey().equals("url")) {
					detail.url = entry.getValue().toString();
				} else if (entry.getKey().equals("imgurl")) {
					detail.imgurl = entry.getValue().toString();
				} else if (entry.getKey().equals("imgurl1")) {
					detail.imgurl1 = entry.getValue().toString();
				} else if (entry.getKey().equals("imgurl2")) {
					detail.imgurl2 = entry.getValue().toString();
				} else if (entry.getKey().equals("pub_time")) {
					detail.time = entry.getValue().toString();
				} else if (entry.getKey().equals("atype")) {
					detail.atype = entry.getValue().toString();
				} else if (entry.getKey().equals("commentId")) {
					detail.commentId = entry.getValue().toString();
				} else {
					detail.newsAppId = entry.getValue().toString();
				}
			}
		}
		return detail;
	}
}
