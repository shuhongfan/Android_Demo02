package com.shf.app35_data_storage;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.InstrumentationRegistry.getContext;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DaoTest{

    @Test
    public void insert() {
        Dao dao = new Dao(getApplicationContext());
        dao.insert();
    }

    @Test
    public void delete() {
        Dao dao = new Dao(getApplicationContext());
        dao.delete();
    }

    @Test
    public void update() {
        Dao dao = new Dao(getApplicationContext());
        dao.update();
    }

    @Test
    public void query() {
        Dao dao = new Dao(getApplicationContext());
        dao.query();
    }
}