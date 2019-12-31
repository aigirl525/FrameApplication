package com.example.sqliteapplication.mvp.view;

import com.example.sqliteapplication.mvp.VersionBean;

public interface LoginView {
    void showVersionBean(VersionBean versionBean);
    void showGetServiceSucess(String responseStr);
}
