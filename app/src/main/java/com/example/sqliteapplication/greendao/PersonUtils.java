package com.example.sqliteapplication.greendao;
import android.content.Context;
import android.util.Log;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class PersonUtils
{
    private static final String TAG = PersonUtils.class.getSimpleName();
    private DaoManager mManager;

    public PersonUtils(Context context){
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成Person记录的插入，如果表未创建，先创建Person表
     * @param Person
     * @return
     */
    public boolean insertPerson(Person Person){
        boolean flag = false;
        flag = mManager.getDaoSession().getPersonDao().insert(Person) == -1 ? false : true;
        Log.i(TAG, "insert Person :" + flag + "-->" + Person.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param PersonList
     * @return
     */
    public boolean insertMultPerson(final List<Person> PersonList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Person Person : PersonList) {
                        mManager.getDaoSession().insertOrReplace(Person);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     * @param Person
     * @return
     */
    public boolean updatePerson(Person Person){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(Person);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     * @param Person
     * @return
     */
    public boolean deletePerson(Person Person){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(Person);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     * @return
     */
    public boolean deleteAll(){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(Person.class);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<Person> queryAllPerson(){
        return mManager.getDaoSession().loadAll(Person.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public Person queryPersonById(long key){
        return mManager.getDaoSession().load(Person.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<Person> queryPersonByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(Person.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<Person> queryPersonByQueryBuilder(long id){
        QueryBuilder<Person> queryBuilder = mManager.getDaoSession().queryBuilder(Person.class);
        return queryBuilder.where(PersonDao.Properties.Id.eq(id)).list();
//        return queryBuilder.where(PersonDao.Properties._id.ge(id)).list();
    }
}