package com.electroeing.factorymethod.factory;

import com.electroeing.factorymethod.enumerators.DBType;

public class DBAdapterFactory {
    public static IDBAdapter getAdapter(DBType dbType) {
        switch (dbType) {
            case MYSQL:
                return new MySQLAdapter();
            case MONGODB:
                return new MongoDBAdapter();
            default:
                return null;
        }
    }
}
