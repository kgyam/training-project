package org.example.user.web.sql;

import org.example.user.web.context.ComponentContext;

import javax.sql.DataSource;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author kg yam
 * @date 2021-03-02 17:41
 * @since
 */
public class DBConnectionManager {

    private static final Logger LOGGER = Logger.getLogger (DBConnectionManager.class.getName ());
    private Connection connection;
    public static final String SCHEMA = "jdbc/UserPlatformDB";
    public static final String CONNECTION_MANAGER="bean/DBConnectionManager";
    private static String url = "jdbc:derby:/db/user-platform;create=true";


    public static final String DROP_USERS_TABLE_DDL_SQL = "DROP TABLE users";

    public static final String CREATE_USERS_TABLE_DDL_SQL = "CREATE TABLE users(" +
            "id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
            "name VARCHAR(16) NOT NULL, " +
            "password VARCHAR(64) NOT NULL, " +
            "email VARCHAR(64) NOT NULL, " +
            "phoneNumber VARCHAR(64) NOT NULL" +
            ")";


    public DBConnectionManager() {

//        DataSource ds = ComponentContext.getInstance (SCHEMA);
//        try {
//            this.connection = ds.getConnection ();
//        } catch (SQLException throwables) {
//            LOGGER.log (Level.SEVERE, throwables.getMessage (), throwables);
//            throw new RuntimeException (throwables);
//        }
    }

//    private static Connection loadByJndi() throws NamingException, SQLException {
//        Context ic = new InitialContext ();//
//        ic = (Context) ic.lookup ("java:comp/env");
//        DataSource ds = (DataSource) ic.lookup ("jdbc/UserPlatformDB");
//
//        return ds.getConnection ();
//    }

//
//    private void init() {
//        Statement statement = null;
//        try {
//            statement = connection.createStatement ();
//            // 删除 users 表
//            // false
//            LOGGER.info ("" + statement.execute (DROP_USERS_TABLE_DDL_SQL));
//            // 创建 users 表
//            // false
//            LOGGER.info ("" + statement.execute (CREATE_USERS_TABLE_DDL_SQL));
//        } catch (SQLException e) {
//            throw new RuntimeException (e.getCause ());
//        }
//    }

    public void releaseConnection() {
        if (connection != null) {
            try {
                connection.close ();
            } catch (SQLException e) {
                throw new RuntimeException (e.getCause ());
            }
        }
    }


    /**
     * INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
     *
     * @param o
     * @param tableName
     * @return
     * @throws Exception
     */
    public boolean save(Object o, String tableName) throws Exception {

        Statement statement = connection.createStatement ();
        Class clazz = o.getClass ();
        LOGGER.info ("class :" + clazz);
        BeanInfo userBeanInfo = Introspector.getBeanInfo (clazz, Object.class);
        StringBuilder insertUsersSQLBuilder = new StringBuilder ("INSERT INTO ");
        insertUsersSQLBuilder.append (tableName).append (" (");
        for (PropertyDescriptor propertyDescriptor : userBeanInfo.getPropertyDescriptors ()) {
            String fieldName = propertyDescriptor.getName ();
            if ("id".equals (fieldName)) {
                continue;
            }
            Class fieldType = propertyDescriptor.getPropertyType ();
            insertUsersSQLBuilder.append (fieldName).append (",");
        }
        insertUsersSQLBuilder.deleteCharAt (insertUsersSQLBuilder.length () - 1)
                .append (") VALUES (");

        for (PropertyDescriptor propertyDescriptor : userBeanInfo.getPropertyDescriptors ()) {
            if ("id".equals (propertyDescriptor.getName ())) {
                continue;
            }
            Object value = propertyDescriptor.getReadMethod ().invoke (o);
            insertUsersSQLBuilder.append ("'").append (value).append ("'").append (",");
        }
        insertUsersSQLBuilder.deleteCharAt (insertUsersSQLBuilder.length () - 1)
                .append (")");

        String insertSQL = insertUsersSQLBuilder.toString ();
        LOGGER.info ("save SQL: [" + insertSQL + "]");
        int i = statement.executeUpdate (insertSQL);
        return true;
    }
}
