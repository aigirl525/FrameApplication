package com.example.sqliteapplication.retrofithttp;

/**
 * Created by Mr.Wang on 2017/9/26.
 * http://blog.csdn.net/jdsjlzx/article/details/51882661
 * 约定异常
 */

public class ERROR {
    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    public static final int NETWORD_ERROR = 1002;
    /**
     * 协议出错
     */
    public static final int HTTP_ERROR = 1003;
    /**
     * 服务器返回的错误出错
     */
    //public static final int HTTP_SERVICE_ERROR = 0;
}
