package com.hex.bigdata.metadata.db.util;

import java.sql.SQLException;

/**
 * Created by hex on 16/6/22.
 */
public interface Action<T> {
    void call(T arg) throws SQLException;
}
