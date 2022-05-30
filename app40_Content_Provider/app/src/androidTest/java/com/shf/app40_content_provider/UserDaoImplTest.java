package com.shf.app40_content_provider;

import android.content.Context;
import android.util.Log;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.shf.app40_content_provider.dao.impl.UserDaoImpl;
import com.shf.app40_content_provider.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class UserDaoImplTest {

    private static final String TAG = "UserDaoImplTest";
    private UserDaoImpl userDao;

    @Before
    public void testPrepare(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        userDao = new UserDaoImpl(appContext);
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setSex("female");
        user.setAge(18);
        user.setPassword("123456");
        user.setUserName("tom");
        long result = userDao.addUser(user);
        Log.d(TAG, "add user result===>"+result);
    }

    @Test
    public void delUserById() {

    }

    @Test
    public void updateUser() {
    }

    @Test
    public void getUserById() {
    }

    @Test
    public void listAllUser() {
        for (User user : userDao.listAllUser()) {
            Log.d(TAG, "listAllUser===>"+user);
        }

    }
}