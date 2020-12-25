package com.gmibank.utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DatabaseUtility {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void createConnection() {
        String url = ConfigurationReader.getProperty("dbUrl");
        String user = ConfigurationReader.getProperty("dbUser");
        String password = ConfigurationReader.getProperty("dbPassword");
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void createConnection(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param query
     * @return returns a single cell value. If the results in multiple rows and/or
     * columns of data, only first column of the first row will be returned.
     * The rest of the data will be ignored
     */
    public static Object getCellValue(String query) {
        return getQueryResultList(query).get(0).get(0);
    }

    /**
     * @param query
     * @return returns a list of Strings which represent a row of data. If the query
     * results in multiple rows and/or columns of data, only first row will
     * be returned. The rest of the data will be ignored
     */
    public static List<Object> getRowList(String query) {
        return getQueryResultList(query).get(0);
    }

    /**
     * @param query
     * @return returns a map which represent a row of data where key is the column
     * name. If the query results in multiple rows and/or columns of data,
     * only first row will be returned. The rest of the data will be ignored
     */
    public static Map<String, Object> getRowMap(String query) {
        return getQueryResultMap(query).get(0);
    }

    /**
     * @param query
     * @return returns query result in a list of lists where outer list represents
     * collection of rows and inner lists represent a single row
     */
    public static List<List<Object>> getQueryResultList(String query) {
        executeQuery(query);
        List<List<Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                rowList.add(row);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }

    /**
     * @param query
     * @param column
     * @return list of values of a single column from the result set
     */
    public static List<Object> getColumnData(String query, String column) {
        executeQuery(query);
        List<Object> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                rowList.add(resultSet.getObject(column));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }

    /**
     * @param query
     * @return returns query result in a list of maps where the list represents
     * collection of rows and a map represents represent a single row with
     * key being the column name
     */
    public static List<Map<String, Object>> getQueryResultMap(String query) {
        executeQuery(query);
        List<Map<String, Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, Object> colNameValueMap = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    colNameValueMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                rowList.add(colNameValueMap);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }

    /**
     * @param query
     * @return List of columns returned in result set
     */
    public static List<String> getColumnNames(String query) {
        executeQuery(query);
        List<String> columns = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return columns;
    }

    private static void executeQuery(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static int getRowCount() throws Exception {
        resultSet.last();
        int rowCount = resultSet.getRow();
        return rowCount;
    }

    //database'e has custom methodlar *************************************************************


    public static Map<String, Object> selectAndGetUserInformation(String tableName, String queryParameter, Object value) {
        String query = "select * from " + tableName + " where " + queryParameter + "='" + value + "';";
        if (doesExistAnyRow(query)) {
            Map<String, Object> rowMap = getRowMap(query);
            return rowMap;
        }
        return null;
    }

    /**
     * @param email
     * @return verilen email'e sahip user'in id'sini doner. Eger boyle bir user yoksa -1 doner.
     */
    public static long getUserIdOfUser(String email) {
        //    select * from tpaccount_registration where email = 'donnell.marvin@yahoo.com';
        String query = "select * from tpaccount_registration where email = '" + email + "';";
        if (doesExistAnyRow(query)) {
            Map<String, Object> rowMap = getRowMap(query);
            return (long) rowMap.get("user_id");
        }
        return -1;
    }

    /**
     * @param query
     * @return database'e gonderilen query sonucunda gelen tablonun bos olup olmaidingi doner
     */
    public static boolean doesExistAnyRow(String query) {
        executeQuery(query);
        int rowCount = 0;
        try {
            rowCount = getRowCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount > 0;
    }

    public static Map<String, Object> getUserInformationWithEmailFromGivenTable(String tableName, String email){
        //   select * from tpaccount_registration where email = 'donnell.marvin@yahoo.com';
        String query = "select * from " + tableName + " where email = '" + email + "';";
        if (!doesExistAnyRow(query)){
            System.out.println("satir yok");
            return null;
        }
        System.out.println("satir var");
        return getRowMap(query);
    }

    public static Map<String, Object> getUserInformationIncludingAuthorityAndActivation(String email) {
        /*
        select * from jhi_user ju join jhi_user_authority jua on (ju.id = jua.user_id) where ju.email = 'email';
         */

        String query = "select * from jhi_user ju join jhi_user_authority jua on (ju.id = jua.user_id) where ju.email = '" + email + "';";
        if (!doesExistAnyRow(query)){
            System.out.println("satir yok");
            return null;
        }
        System.out.println("satir var");
        return getRowMap(query);
    }

    public static boolean compareMapFromDbAndUserObject(Map<String, Object> map, User user){
        Map<String, Boolean> compareResultMap = new HashMap<>();
        compareResultMap.put("equalFirstName", ((String) map.get("first_name")).equals(user.getFirstName()));
        compareResultMap.put("equalUserName", ((String) map.get("login")).equals(user.getUserName()));
        compareResultMap.put("equalActivationStatus", (String.valueOf(map.get("activated"))).equals(user.getActivation()));
        compareResultMap.put("equalAuthority", ((String) map.get("authority_name")).equals(user.getProfiles()));
        compareResultMap.put("equalLastName", ((String) map.get("last_name")).equals(user.getLastName()));
        compareResultMap.put("equalId", String.valueOf(map.get("user_id")).equals(user.getId()));

        for (String key : compareResultMap.keySet()) {
            if (!compareResultMap.get(key)){
                System.out.println(key + " information does not match!!!");
                return false;
            }
        }
        return true;

    }

    public static boolean compareMapFromDbAndUserObjectForNewRegistration(Map<String, Object> dbMap, User user){
        Map<String, Boolean> compareResultMap = new HashMap<>();
        compareResultMap.put("equalFirstName", ((String) dbMap.get("first_name")).equals(user.getFirstName()));
        compareResultMap.put("equalUserName", ((String) dbMap.get("user_name")).equals(user.getUserName()));
        compareResultMap.put("equalLastName", ((String) dbMap.get("last_name")).equals(user.getLastName()));
        compareResultMap.put("equalMobilePhoneNumber", ((String) dbMap.get("mobile_phone_number")).equals(user.getMobilePhoneNumber()));

        for (String key : compareResultMap.keySet()) {
            if (!compareResultMap.get(key)){
                System.out.println(key + " information does not match!!!");
                return false;
            }
        }
        return true;

    }

    public static List<String> getCountryNames(){
        String query = "select * from tp_country;";
        List<Map<String, Object>> queryResultMap = getQueryResultMap(query);
        List<String> countryNameList = new ArrayList<>();
        for (Map<String, Object> map : queryResultMap) {
            countryNameList.add((String)map.get("name"));
        }
        return countryNameList;
    }

    public static boolean compareMapFromDbAndMapFromExcel(Map<String, Object> map, Map<String, String> mapFromExcel){
        Map<String, Boolean> compareResultMap = new HashMap<>();
        compareResultMap.put("equalFirstName", ((String) map.get("first_name")).equals(mapFromExcel.get("firstName")));
        compareResultMap.put("equalUserName", ((String) map.get("login")).equals(mapFromExcel.get("userName")));
        compareResultMap.put("equalActivationStatus", (String.valueOf(map.get("activated"))).equals(mapFromExcel.get("activation")));
        compareResultMap.put("equalAuthority", ((String) map.get("authority_name")).equals(mapFromExcel.get("profiles")));
        compareResultMap.put("equalLastName", ((String) map.get("last_name")).equals(mapFromExcel.get("lastName")));
        compareResultMap.put("equalId", String.valueOf(map.get("user_id")).equals(mapFromExcel.get("id")));

        for (String key : compareResultMap.keySet()) {
            if (!compareResultMap.get(key)){
                System.out.println(key + " information does not match!!!");
                return false;
            }
        }
        return true;

    }


    public static Map<String, Object> getAccountInfoWithGivenDescription(String description){
        //  select * from tp_account where description = 'Saving';

        String query = "select * from tp_account where description = '" + description + "';";
        if (!doesExistAnyRow(query)){
            System.out.println("satir yok");
            return null;
        }
        System.out.println("satir var");
        return getRowMap(query);
    }

    public static boolean compareMapsForAccoutnsFromDbAndUI(Map<String, Object> dbMap, Map<String, String> uiMap){
        Map<String, Boolean> compareResultMap = new HashMap<>();
        compareResultMap.put("equalId", uiMap.get("ID").equals(String.valueOf(dbMap.get("id"))));
        compareResultMap.put("equalBalance", uiMap.get("Balance").equals(String.valueOf(dbMap.get("balance"))));
        compareResultMap.put("equalAccountType", uiMap.get("Account Type").equals(dbMap.get("account_type")));


        for (String key : compareResultMap.keySet()) {
            if (!compareResultMap.get(key)){
                System.out.println(key + " information does not match!!!");
                return false;
            }
        }
        return true;

    }

}
