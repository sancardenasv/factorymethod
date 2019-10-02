package com.electroeing.factorymethod.factory;

import com.electroeing.factorymethod.enumerators.DBType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBAdapterFactory {
    private static MySQLAdapter mySQLAdapter;
    private static MongoDBAdapter mongoDBAdapter;

    @Autowired
    public DBAdapterFactory(MySQLAdapter mySQLAdapter, MongoDBAdapter mongoDBAdapter) {
        this.mySQLAdapter = mySQLAdapter;
        this.mongoDBAdapter = mongoDBAdapter;
    }

    public static IDBAdapter getAdapter(DBType dbType) {
        switch (dbType) {
            case MYSQL:
                return mySQLAdapter;
            case MONGODB:
                return mongoDBAdapter;
            default:
                return null;
        }
    }
}
