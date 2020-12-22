package com.gmibank.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// We can use this excel utilities class to read and write excel files (for xls, xlsx files).
// These are the reusable custom methods we can use in our test classes
public class ExcelUtilities {

    public Workbook workBook;
    public Sheet workSheet;
    private String path;

    /**
     * eger workbook icinde birden fazla sheet uzerinde calisilacaksa diye bu methdu ekledim.
     * ayni zamanda getter ve setter'lar ile ugrasmmamak icin public yaptim.
     * eger const icinde verdiginden farkli sheet uzerinde calisacaksan bu method ile once
     * calisacagin sheet'i set etmelisin.
     *
     * @param sheetName
     */
    public void setWorkSheet(String sheetName) {
        this.workSheet = workBook.getSheet(sheetName);
    }

    /**
     * const cagrildiginda dosya acilip erisime sunuluyor, tekrardan  acmaya gerek yok
     *
     * @param path
     * @param sheetName
     */
    public ExcelUtilities(String path, String sheetName) {//This Constructor is to open and access the excel file
        this.path = path;
        try {
            // Opening the Excel file
            FileInputStream fileInputStream = new FileInputStream(path);
            // accessing the workbook
            workBook = WorkbookFactory.create(fileInputStream);
            //getting the worksheet
            workSheet = workBook.getSheet(sheetName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * o anda aktif olan (set edilmis olan) sheet kolon sayisini doner. Ilk satirdan hesaplama
     * yapar.
     *
     * @return
     */
    //===============Getting the number of columns in a specific single row=================
    public int columnCount() {
        //getting how many numbers in row 1
        return workSheet.getRow(0).getLastCellNum();
    }

    //===============how do you get the last row number?Index start at 0.====================
    public int rowCount() {
        return workSheet.getLastRowNum() + 1;
    }//adding 1 to get the actual count


    public Map<String, String> getLastRow(){
        List<String> columns = getColumnsNames();
        int lastRow = rowCount();
        Row row = workSheet.getRow(lastRow - 1);
        // creating map of the row using the column and value
        // key=column, value=cell
        Map<String, String> rowMap = new HashMap<String, String>();
        for (Cell cell : row) {
            int columnIndex = cell.getColumnIndex();
            rowMap.put(columns.get(columnIndex), cell.toString());
        }
        return rowMap;
    }

    public User getLastRegistrantAsUser(){
        List<String> columns = getColumnsNames();
        int lastRow = rowCount();
        Row row = workSheet.getRow(lastRow - 1);
        // creating map of the row using the column and value
        // key=column, value=cell
        Map<String, String> rowMap = new HashMap<String, String>();
        for (Cell cell : row) {
            int columnIndex = cell.getColumnIndex();
            rowMap.put(columns.get(columnIndex), cell.toString());
        }
        User user = new User(rowMap);
        return user;
    }


    //==============When you enter row and column number, then you get the data==========
    public String getCellData(int rowNum, int colNum) {
        Cell cell;
        try {
            cell = workSheet.getRow(rowNum).getCell(colNum);
            String cellData = cell.toString();
            return cellData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //============getting all data into two dimentional array and returning the data===
    // okudugumuz tüm verileri iki boyutlu diziye alma ve verileri döndürme
    public String[][] getDataArray() {
        String[][] data = new String[rowCount()][columnCount()];
        for (int i = 0; i < rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                String value = getCellData(i, j);
                data[i][j] = value;
            }
        }
        return data;
    }

    //This will get the list of the data in the excel file
    //This is a list of map. This takes the data as string and will return the data as a Map of String
    public List<Map<String, String>> getDataList() {
        // getting all columns
        List<String> columns = getColumnsNames();
        // method will return this
        List<Map<String, String>> data = new ArrayList<>();
        for (int i = 1; i < rowCount(); i++) {
            // get each row
            Row row = workSheet.getRow(i);
            // creating map of the row using the column and value
            // key=column, value=cell
            Map<String, String> rowMap = new HashMap<String, String>();
            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                rowMap.put(columns.get(columnIndex), cell.toString());
            }
            data.add(rowMap);
        }
        return data;
    }

    /**
     * check whether there exist any row except for header
     * @return
     */
    public boolean doesExistAnyRowExceptForHeader(){
        if (rowCount() == 1){
            System.out.println(rowCount());
            return false;
        }
        System.out.println(rowCount());
        return true;
    }



    //==============going to the first row and reading each row one by one==================//
    public List<String> getColumnsNames() {
        List<String> columns = new ArrayList<>();
        for (Cell cell : workSheet.getRow(0)) {
            columns.add(cell.toString());
        }
        return columns;
    }

    //=========When you enter the row and column number, returning the value===============//
    public void setCellData(String value, int rowNum, int colNum) {
        Cell cell;
        Row row;
        try {
            row = workSheet.getRow(rowNum);
            cell = row.getCell(colNum);
            if (cell == null) {//if there is no value, create a cell.
                cell = row.createCell(colNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            workBook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCellData(String value, String columnName, int row) {
        int column = getColumnsNames().indexOf(columnName);
        setCellData(value, row, column);
    }

    public void writeUserIntoExcel(User user, String sheetName){
        setWorkSheet(sheetName);
        int rowIndex = rowCount();
        Row row = workSheet.createRow(rowIndex);
        List<String> columnNames = getColumnsNames();
        for (String columnName : columnNames) {
            switch (columnName){
                case "firstName":
                    setCellData(user.getFirstName(),columnName,rowIndex);
                    break;
                case "lastName":
                    setCellData(user.getLastName(),columnName,rowIndex);
                    break;
                case "ssnNumber":
                    setCellData(user.getSsnNumber(),columnName,rowIndex);
                    break;
                case "address":
                    setCellData(user.getAddress(),columnName,rowIndex);
                    break;
                case "zipCode":
                    setCellData(user.getZipCode(),columnName,rowIndex);
                    break;
                case "state":
                    setCellData(user.getState(),columnName,rowIndex);
                    break;
                case "mobilePhoneNumber":
                    setCellData(user.getMobilePhoneNumber(),columnName,rowIndex);
                    break;
                case "phoneNumber":
                    setCellData(user.getPhoneNumber(),columnName,rowIndex);
                    break;
                case "city":
                    setCellData(user.getCity(),columnName,rowIndex);
                    break;
                case "userName":
                    setCellData(user.getUserName(),columnName,rowIndex);
                    break;
                case "email":
                    setCellData(user.getEmail(),columnName,rowIndex);
                    break;
                case "password":
                    setCellData(user.getPassword(),columnName,rowIndex);
                    break;
                case "activation":
                    setCellData(user.getActivation(),columnName,rowIndex);
                    break;
                case "profiles":
                    setCellData(user.getProfiles(),columnName,rowIndex);
                    break;
                case "id":
                    setCellData(user.getId(),columnName,rowIndex);
                    break;
                default:
                    System.out.println("there does not exist such a column name!!!");
                    break;
            }
        }
        saveWorkBook();
    }

    public void writeUserIntoExcel(Map<String, String> userInfoMap) {

        int rowIndex = rowCount();
        Row row = workSheet.createRow(rowIndex);
        for (String key : userInfoMap.keySet()) {
            setCellData(userInfoMap.get(key), key, rowIndex);
        }
        saveWorkBook();
    }

    public void removeLastRow() {
        int lastRowNumIndex = rowCount() - 1;
        if (lastRowNumIndex != 0) {
            Row row = workSheet.getRow(lastRowNumIndex);
            workSheet.removeRow(row);
        } else {
            System.out.println("just headers exist!!");
        }

    }

    public void removeLastRowAndSave(){
        removeLastRow();
        saveWorkBook();
    }

    /**
     * dosya uzerinde okuma manipulasyn yaptiktan sonra kaydetmen lazim. Aksi halde degisiklik
     * geri alinir.
     */
    public void saveWorkBook() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            workBook.write(fileOutputStream);
            fileOutputStream.close();

            System.out.println(path + " i[enter link description here][1]s successfully written");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map convertUserToMap(User user){
        ObjectMapper oMapper = new ObjectMapper();
        return oMapper.convertValue(user, Map.class);
    }

    public static void putAdditionalInformationForUser(String activation, String profiles, String id, Map<String, String> map){
        map.put("activation", activation);
        map.put("profiles", profiles);
        map.put("id", id);
    }

    public static void putAdditionalInformationForUser(boolean activated, String userType, String id, User user){
        user.setActivation(String.valueOf(activated));
        user.setId(id);
        String authority = null;
        switch (userType){
            case "admin":
                authority = "ROLE_ADMIN";
                break;
            case "employee":
                authority = "ROLE_EMPLOYEE";
                break;
            case "user":
                authority = "ROLE_USER";
                break;
        }
        user.setProfiles(authority);
    }

}
