package controllers;

import entity.baiviet.itemCacheBaiViet;

import java.util.Date;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 11/3/22
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class managerCacheBaiViet {

    private static HashMap<String, itemCacheBaiViet> cacheBaiViet;

    public static void khoiTaoCache()
    {
        if(cacheBaiViet == null)
        {
            cacheBaiViet = new HashMap<String, itemCacheBaiViet>();
        }
        if(cacheBaiViet!=null && cacheBaiViet.size()>100)
        {
            cacheBaiViet = new HashMap<String, itemCacheBaiViet>();
            cacheBaiViet.clear();
        }
    }

    public static void addCacheBaiViet(String key, String value)
    {
        khoiTaoCache();
        if(key!=null&&value!=null)
        {
            itemCacheBaiViet item = new itemCacheBaiViet();
            item.time = new Date().getTime();
            item.value = value;
            cacheBaiViet.put(key,item);
        }
    }

    public static itemCacheBaiViet getCacheBaiViet(String key)
    {
        khoiTaoCache();
        if(key!=null&&key!=""&&cacheBaiViet.containsKey(key))
        {
            return cacheBaiViet.get(key);
        }
        return null;
    }

    public static void resetCache()
    {
        cacheBaiViet = new HashMap<String, itemCacheBaiViet>();
        cacheBaiViet.clear();
    }
}
