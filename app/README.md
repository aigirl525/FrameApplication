Mvp + 网络请求封装
https://www.jianshu.com/p/2fd2d5dac94e
 * https://blog.csdn.net/qq122627018/article/details/51684882

greendao
 https://www.jianshu.com/p/39db996be365

手写数据库框架
https://blog.csdn.net/ytfunnysite/article/details/80716487
https://www.2cto.com/kf/201709/681135.html

Android Studio打包全攻略---从入门到精通
https://blog.csdn.net/zivensonice/article/details/51672846

数据库
androi4.2中引入了多用户机制，普通用户无法访问根目录下的/data/data目录，因而无法创建数据库文件。

其实是当.db-shw和.db-wal缓存到一定的大小后,就会写入到.db里面去.

如果我现在就要看数据咋办?
Android设备,连上adb,debug环境,打开Device File Explorer,打开对应的包名,database.
一次性把3个文件全部Save As出来,打开.db,就会发现很神奇的能看到所有数据了.

数据库正常关闭后，.db-shw和.db-wal缓存就会写入到.db里面去

SQLiteOpenHelper
该类是SQLiteDatabase一个辅助类。这个类主要生成一  个数据库，并对数据库的版本进行管理。当在程序当中调用这个类的方法getWritableDatabase()或者 getReadableDatabase()方法的时候，如果当时没有数据，那么Android系统就会自动生成一个数据库。 SQLiteOpenHelper 是一个抽象类，我们通常需要继承它，并且实现里面的3个函数：

修改第一次

修改第四次


修改第一次