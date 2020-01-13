package com.example.sqliteapplication.sqlite;

import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

/**
 * 封装修改的语句
 */
class Condition {
    private String whereClause ;

    private String[] whereArgs ;

    public Condition(Map<String, String> whereClause) {
        ArrayList<String> list = new ArrayList<>() ;
        StringBuilder sb = new StringBuilder() ;

        sb.append("1=1") ;
        for(String key : whereClause.keySet()){
            String value = whereClause.get(key);
            if(value != null){
                //拼接条件查询语句
                sb.append(" and ").append(key).append(" =?");
                //查询条件
                list.add(value);
            }
        }
        this.whereClause = sb.toString() ;
        this.whereArgs = list.toArray(new String[list.size()]);
    }

    public String getWhereClause() {
        Log.e("123",whereClause);
        return whereClause;
    }

    public String[] getWhereArgs() {
        return whereArgs;
    }
}
