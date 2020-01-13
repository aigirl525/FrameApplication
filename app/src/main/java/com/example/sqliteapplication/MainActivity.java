package com.example.sqliteapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqliteapplication.greendao.Person;
import com.example.sqliteapplication.greendao.PersonUtils;
import com.example.sqliteapplication.mvp.view.base.BaseActivity;
import com.example.sqliteapplication.sqlite.BaseDao;
import com.example.sqliteapplication.sqlite.BaseDaoFactory;
import com.example.sqliteapplication.sqlite.entity.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity" ;
    PersonUtils mPersonUtils;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_insert_one).setOnClickListener(this);
        findViewById(R.id.btn_insert_many).setOnClickListener(this);
        findViewById(R.id.btn_alter).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_delete_all).setOnClickListener(this);
        findViewById(R.id.btn_check_one).setOnClickListener(this);
        findViewById(R.id.btn_check_all).setOnClickListener(this);
        findViewById(R.id.btn_query_native_sql).setOnClickListener(this);
        findViewById(R.id.btn_query_builder).setOnClickListener(this);



        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.deleteAll).setOnClickListener(this);
        findViewById(R.id.btn_select).setOnClickListener(this);
        findViewById(R.id.btn_selectAll).setOnClickListener(this);
        textView = findViewById(R.id.gradle);
       // textView.setText(Constants.URL);
        mPersonUtils = new PersonUtils(this);
    }
    BaseDao<User> dao;
    User where;
    User user;
    List<User> query;
    int delete;
    @Override
    public void onClick(View view)
    {
        dao = BaseDaoFactory.getInstance().getBaseDao(User.class);
        switch (view.getId()){
            case R.id.btn_save:
                Long l = dao.insert(new User(1,"luo1","luo123"));
                dao.insert(new User(2,"luo2","luo456"));
                dao.insert(new User(3,"luo3","luo789"));
                Toast.makeText(this, "建表成功"+l, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_update:
                //更新条件
                where = new User() ;
                where.setName("luo3");
                //更新为
                user = new User() ;
                user.setName("hanmeimei");
                int update = dao.update(user,where);
                Toast.makeText(this, "123update" + update, Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                //删除条件
                where = new User() ;
                where.setName( "luo2");
                delete = dao.delete(where);
                Toast.makeText(this, "123delete" + delete, Toast.LENGTH_SHORT).show();
                break;
            case R.id.deleteAll:
                //删除条件
                where = new User() ;
                delete = dao.delete(where);
                Toast.makeText(this, "123delete" + delete, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_select:
                where = new User(1);
                query = dao.query(where);
                for(User user : query){
                    Toast.makeText(this, "123select"+ "name:"+user.getName()+",Password:"+user.getPassword(), Toast.LENGTH_SHORT).show();
                    Log.e("123select","name:"+user.getName()+",Password:"+user.getPassword());
                }
                break;
            case R.id.btn_selectAll:
                where = new User();
                query = dao.query(where);
                for(User user : query){
                    Toast.makeText(this, "123select"+ "name:"+user.getName()+",Password:"+user.getPassword(), Toast.LENGTH_SHORT).show();
                    Log.e("123selectAll","name:"+user.getName()+",Password:"+user.getPassword());
                }
                break;






            case R.id.btn_insert_one:
                mPersonUtils.insertPerson(new Person(null, "Google",
                        "http://7xi8d6.48096_n.jpg"));
                break;

            case R.id.btn_insert_many:
                List<Person> PersonList = new ArrayList<>();
                PersonList.add(new Person(null, "18",
                        "luo1"));
                PersonList.add(new Person(null, "19",
                        "luo2"));
                PersonList.add(new Person(null, "20",
                        "luo3"));
                mPersonUtils.insertMultPerson(PersonList);
                break;

            case R.id.btn_alter:
                Person Person = new Person();
                Person.setId(1l);
                Person.setAge("21");
                Person.setName("luo4");
                mPersonUtils.updatePerson(Person);
                break;

            case R.id.btn_delete:
                Person Person1 = new Person();
                Person1.setId(1002l);
                mPersonUtils.deletePerson(Person1);
                break;

            case R.id.btn_delete_all:
                mPersonUtils.deleteAll();
                break;

            case R.id.btn_check_one:
                Log.i(TAG, mPersonUtils.queryPersonById(1002l).toString());
                break;

            case R.id.btn_check_all:
                List<Person> PersonList1 = mPersonUtils.queryAllPerson();
                for (Person Person2 : PersonList1) {
                    Log.i(TAG, Person2.toString());
                }
                break;

            case R.id.btn_query_native_sql:
                String sql = "where _id > ?";
                String[] condition = new String[]{"2"};
                List<Person> PersonList2 = mPersonUtils.queryPersonByNativeSql(sql, condition);
                for (Person Person2 : PersonList2) {
                    Log.i(TAG, Person2.toString());
                }
                break;

            case R.id.btn_query_builder:
                List<Person> PersonList3 = mPersonUtils.queryPersonByQueryBuilder(10);
                for (Person Person2 : PersonList3) {
                    Log.i(TAG, Person2.toString());
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dao.close();
    }
}
