package com.hex.bigdata.metadata.db.util;

import java.sql.SQLException;

/**
 * Created by hex on 16/6/22.
 */
public interface Function<T1,Ret> {
    Ret call(T1 arg) throws SQLException;
}
