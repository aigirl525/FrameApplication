package com.example.sqliteapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sqliteapplication.sqlite.BaseDao;
import com.example.sqliteapplication.sqlite.BaseDaoFactory;
import com.example.sqliteapplication.sqlite.entity.User;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view){
        BaseDao<User> dao = BaseDaoFactory.getInstance().getBaseDao(User.class);
        dao.insert(new User(100,"ytf","123456"));
        dao.insert(new User(111,"yangtianfu","000000"));
        dao.insert(new User(100,"杨天福","999999"));
        Toast.makeText(this, "建表成功", Toast.LENGTH_SHORT).show();
    }
}
