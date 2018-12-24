package com.example.lll.gdsappproject.core.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

import java.security.PublicKey;

import io.reactivex.internal.util.OpenHashSet;
// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * Master of DAO (schema version 1): knows all DAOs.
 */
public class DaoMaster extends AbstractDaoMaster {

    public static void createAllTables(Database db, boolean isNotExists) {

    }

    /**
     * Drops underlying database table using DAOs.
     */
    public static void dropAllTables(Database db, boolean ifExists) {
        HistoryDataDao.dropTable(db, ifExists);
    }


    public static final int SCHEMA_VERSION = 1;

    public DaoMaster(SQLiteDatabase db) {
        this(new StandardDatabase(db));
    }
    public DaoMaster(Database db){
        super(db,SCHEMA_VERSION);
        registerDaoClass(HistoryDataDao.class);
    }

    public DaoSession newSession() {
        return new DaoSession(db,IdentityScopeType.Session,daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType type){
        return  new DaoSession(db,type,daoConfigMap);
    }


    public static abstract class OpenHelper extends DatabaseOpenHelper {

        public OpenHelper(Context context, String name) {
            super(context, name, SCHEMA_VERSION);
        }

        public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(Database db) {
            super.onCreate(db);
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, true);
        }
    }

    public static class DevOpenHelper extends OpenHelper {

        public DevOpenHelper(Context context, String name) {
            super(context, name);

        }

        public DevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            super.onUpgrade(db, oldVersion, newVersion);
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }
}
