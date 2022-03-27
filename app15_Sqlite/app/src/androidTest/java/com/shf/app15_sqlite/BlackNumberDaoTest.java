package com.shf.app15_sqlite;

import android.util.Log;
import androidx.test.InstrumentationRegistry;
import org.junit.Test;

import java.util.List;

import static androidx.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.*;

/**
 * 在AndroidJUnit4直接通过getContext()获取到的context并不是当前APP的context，而是instrumentation的context，应使用以下方法来获取context。
 *
 * InstrumentationRegistry.getInstrumentation().targetContext
 * 或者
 *
 * InstrumentationRegistry.getTargetContext()
 */
public class BlackNumberDaoTest {

    @Test
    public void add() {
        BlackNumberDao dao = new BlackNumberDao(InstrumentationRegistry.getTargetContext());
        dao.add(new BlackNumber(-1,"world"));
    }

    @Test
    public void deleteById() {
        BlackNumberDao dao = new BlackNumberDao(InstrumentationRegistry.getTargetContext());
        dao.deleteById(1);
    }

    @Test
    public void update() {
        BlackNumberDao dao = new BlackNumberDao(InstrumentationRegistry.getTargetContext());
        dao.update(new BlackNumber(2,"hello"));
    }

    @Test
    public void getAll() {
        BlackNumberDao dao = new BlackNumberDao(InstrumentationRegistry.getTargetContext());
        List<BlackNumber> list = dao.getAll();
        Log.i("TAG", list.toString());
    }
}