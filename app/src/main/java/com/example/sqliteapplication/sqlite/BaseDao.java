package com.example.sqliteapplication.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.example.sqliteapplication.sqlite.annotations.DbField;
import com.example.sqliteapplication.sqlite.annotations.DbTable;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 顶层接口IBaseData的实现类，也就是Dao层
 * @param <T>
 */
public  class BaseDao<T> implements IBaseDao<T> {

    //持有一个操作数据库的引用
    private SQLiteDatabase sqLiteDatabase;
    //持有一个操作数据可对应的java类型
    private Class<T> entityClass;
    //表名（由框架来控制）
    private String tabName;
    //标记是否已经创建过数据库，保证只创建一次
    private boolean flag;
    //缓存，key == 成员变量 value == 字段名
    private HashMap<Field,String> cacheMap;

    public BaseDao(){

    }

    public boolean init(SQLiteDatabase sqLiteDatabase,Class<T> entityClass){
        this.sqLiteDatabase = sqLiteDatabase;
        this.entityClass = entityClass;
        //保证只初始化一次，自动建表，拿到表名
        if (!flag){
            tabName = entityClass.getAnnotation(DbTable.class).value();
            if (!sqLiteDatabase.isOpen()){
                return false;
            }
            //开始建表
            String sql = getCreateSql();
            sqLiteDatabase.execSQL(sql);
            cacheMap = new HashMap<>();
            //初始化缓存
            initCacheMap();
            flag = true;
        }
        return flag;
    }
    //cacheMap（_id，id）（pwd，password）
    private void initCacheMap(){
        //找到所有的字段名
        String sql = "select * from " + tabName + " limit 1,0";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        String[] columnNames = cursor.getColumnNames();
        //找到所有的成员变量，使用反射
        Field[] columnFields = entityClass.getDeclaredFields();
        //通过循环把对应关系存入到缓存
        for(String columnName : columnNames){
            Field resultField = null;
            for (Field columnField : columnFields ){
                String fieldAnnotionName = columnField.getAnnotation(DbField.class).value();
                if (columnName.equals(fieldAnnotionName)){
                    resultField = columnField;
                    break;
                }
            }
            if (resultField != null){
                cacheMap.put(resultField,columnName);
            }
        }


    }
    private ContentValues getContentValues(Map<String,String> map)
    {
        ContentValues contentValues = new ContentValues();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = map.get(key);
            if (value != null){
                contentValues.put(key,value);
            }
        }
        return contentValues;
    }


    //entity new User(100,"杨天福","999999")
    //Map<String,String>  （_id,100）(pwd,999999)
    private Map<String,String> getValues(Object entity){
        HashMap<String,String> map = new HashMap<>();
        Iterator<Field> fieldIterable = cacheMap.keySet().iterator();
        while (fieldIterable.hasNext()){
            Field field = fieldIterable.next();
            field.setAccessible(true);
            //获取成员变量的值
            Object obj = null;
            try {
                //取到变量中的值
                obj = field.get(entity);
                if (obj == null){
                    continue;
                }
                String value = obj.toString();
                //取列名
                String key = cacheMap.get(field);
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)){
                    map.put(key,value);
                }
            }catch (IllegalAccessException e){
               e.printStackTrace();
            }
        }

        return  map;
    }
    private String getCreateSql(){


        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table if not exists ");
        stringBuffer.append(tabName + "(");
        //通过反射拿到调用层实体类中的所有字段（成员变量）
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields){
            Class type = field.getType();
            if (type == String.class) {
                stringBuffer.append(field.getAnnotation(DbField.class).value() + " TEXT,");
            } else if (type == Integer.class) {
                stringBuffer.append(field.getAnnotation(DbField.class).value() + " INTEGER,");
            } else if (type == Double.class) {
                stringBuffer.append(field.getAnnotation(DbField.class).value() + " DOUBLE,");
            } else if (type == Long.class) {
                stringBuffer.append(field.getAnnotation(DbField.class).value() + " LONG,");
            } else if (type == byte[].class) {//图片格式
                stringBuffer.append(field.getAnnotation(DbField.class).value() + " BLOB,");
            } else {
//                不支持的类型
                continue;
            }
        }
        //去掉最后一个逗号
        if (stringBuffer.charAt(stringBuffer.length() - 1) == ','){
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }
    @Override
    public Long insert(T t) {
        Map<String,String> map = getValues(t);
        ContentValues contentValues = getContentValues(map);
        long result = sqLiteDatabase.insert(tabName,null ,contentValues);

        return result;
    }

    @Override
    public Long update(T entity, T where) {
        return null;
    }

    @Override
    public void close() {
        if (sqLiteDatabase.isOpen())sqLiteDatabase.close();
    }
}
