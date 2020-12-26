package com.gmibank.utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Stream;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PrintOutAsPdf {

    public static void main(String[] args) {
        java.util.List<Map<String, String>> listOfMap = new ArrayList<>();

        Map<String, String> row = new HashMap<>();
        row.put("firstName", "Neena");
        row.put("lastName", "Kochhar");
        row.put("profiles", "17000");
        listOfMap.add(row);
        createPdf("deneme.pdf", listOfMap);

    }

    public static void createPdf(String fileName, java.util.List<Map<String, String>> listOfMap) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            PdfPTable table = createTable(listOfMap);
            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PdfPTable createTable(java.util.List<Map<String, String>> listOfMap) {
        PdfPTable table = new PdfPTable(3);
        addTableHeader(table);
        addRows(table, listOfMap);
        return table;
    }

    /**
     * Tablonun basliklari yazilir dinamik olarak.
     *
     * @param table
     * @param
     */
    private static void addTableHeader(PdfPTable table) {
        Stream.of("First Name", "Last Name", "User Role")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table, java.util.List<Map<String, String>> listOfMap) {
        for (Map<String, String> map : listOfMap) {
            table.addCell(map.get("firstName"));
            table.addCell(map.get("lastName"));
            table.addCell(map.get("profiles"));
        }

    }


}
