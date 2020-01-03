package com.example.sqliteapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqliteapplication.greendao.Person;
import com.example.sqliteapplication.greendao.PersonUtils;
import com.example.sqliteapplication.sqlite.BaseDao;
import com.example.sqliteapplication.sqlite.BaseDaoFactory;
import com.example.sqliteapplication.sqlite.entity.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{
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
        textView = findViewById(R.id.gradle);
        textView.setText(Constants.URL);
        mPersonUtils = new PersonUtils(this);
    }

    public void click(View view){
        BaseDao<User> dao = BaseDaoFactory.getInstance().getBaseDao(User.class);
        Long l = dao.insert(new User(102,"ytf","123456"));
       // dao.insert(new User(111,"yangtianfu","000000"));
       // dao.insert(new User(100,"杨天福","999999"));
        Toast.makeText(this, "建表成功"+l, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
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
}
