package com.github.makosful.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author Axl
 */
public class DatabaseConnector
{

    public Connection getConnection() throws SQLServerException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("EASV-DB2");
        ds.setPortNumber(1433);
        ds.setDatabaseName("malth24_MechaChat");
        ds.setUser("CS2017A_24_java");
        ds.setPassword("javajava");

        return ds.getConnection();
    }
}
