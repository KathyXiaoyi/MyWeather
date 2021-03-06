package com.yiguy.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yiguy.bean.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiaoyi on 2016/10/3.
 */
public class CityDB {
    public static final String CITY_DB_NAME = "city.db";
    private static final String CITY_TABLE_NAME ="city";
    private SQLiteDatabase db;

    public CityDB(Context context, String path){
        db = context.openOrCreateDatabase(path, Context.MODE_PRIVATE, null);
    }

    public List<City> getAllCity(){
        List<City> list = new ArrayList<City>();
        Cursor c = db.rawQuery("SELECT * from " + CITY_TABLE_NAME, null);
        while(c.moveToNext()){
            String province = c.getString(c.getColumnIndex("province"));
            String city = c.getString(c.getColumnIndex("city"));
            String number = c.getString(c.getColumnIndex("number"));
            String allPY = c.getString(c.getColumnIndex("allpy"));
            String allFristPY = c.getString(c.getColumnIndex("allfirstpy"));
            String firstPY = c.getString(c.getColumnIndex("firstpy"));
            City item = new City(province, city, number, firstPY, allPY, allFristPY);
            list.add(item);
        }
        return list;
    }

    public String getCityCode (String provinceParam, String cityParam,String districtParam){
        Cursor c = db.rawQuery("SELECT * from " + CITY_TABLE_NAME + " where province like '%" + provinceParam +"%' and city like '%" + districtParam + "%'", null);
        int count = c.getCount();
        if(count == 1){
            c.moveToNext();
            String number = c.getString(c.getColumnIndex("number"));
            return number;
        }

        Cursor cc = db.rawQuery("SELECT * from " + CITY_TABLE_NAME + " where province like '%" + provinceParam +"%' and city like '%" + cityParam + "%'", null);
        count = cc.getCount();
        if(count == 1){
            c.moveToNext();
            String number = c.getString(c.getColumnIndex("number"));
            return number;
        }
        return null;
    }
}
