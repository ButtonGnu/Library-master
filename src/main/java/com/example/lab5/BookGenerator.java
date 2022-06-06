package com.example.lab5;

import com.example.lab5.books.Book;
import com.example.lab5.books.BookType;
import com.example.lab5.books.GetBookFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class BookGenerator {
    private ThreadLocalRandom random;

    private String[] arrayEnEdNames;
    private String[] arrayEnEdAuthors;
    private String[] arrayEnEdUniversities;

    private String[] arrayEnFicNames;
    private String[] arrayEnFicAuthors;

    private String[] arrayRuEdNames;

    private String[] arrayRuFicNames;
    private String[] arrayRuFicAuthors;

    public BookGenerator(File file) throws IOException, InvalidFormatException {
        this.random = ThreadLocalRandom.current();
        setUp(file);
    }

    public Book createBook() {
        int rand = random.nextInt(0, 100);
        if (rand < 25) {
            Book book = GetBookFactory.createBook(BookType.EDUCATION_EN);
            book.setBookData(arrayEnEdNames[ThreadLocalRandom.current().nextInt(0, arrayEnEdNames.length)],
                    arrayEnEdAuthors[ThreadLocalRandom.current().nextInt(0, arrayEnEdAuthors.length)],
                    arrayEnEdUniversities[ThreadLocalRandom.current().nextInt(0, arrayEnEdUniversities.length)]);
            return book;
        } else if (rand < 50) {
            Book book = GetBookFactory.createBook(BookType.FICTION_EN);
            book.setBookData(arrayEnFicNames[ThreadLocalRandom.current().nextInt(0, arrayEnFicNames.length)],
                    arrayEnFicAuthors[ThreadLocalRandom.current().nextInt(0, arrayEnFicAuthors.length)]);
            return book;
        } else if (rand < 75) {
            Book book = GetBookFactory.createBook(BookType.EDUCATION_RU);
            book.setBookData(arrayRuEdNames[ThreadLocalRandom.current().nextInt(0, arrayRuEdNames.length)],
                    arrayRuFicAuthors[ThreadLocalRandom.current().nextInt(0, arrayRuFicAuthors.length)]);
            return book;
        } else {
            Book book = GetBookFactory.createBook(BookType.FICTION_RU);
            book.setBookData(arrayRuFicNames[ThreadLocalRandom.current().nextInt(0, arrayRuFicNames.length)],
                    arrayRuFicAuthors[ThreadLocalRandom.current().nextInt(0, arrayRuFicAuthors.length)]);
            return book;
        }
    }

    private void setUp(File file) throws IOException, InvalidFormatException {
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        arrayEnEdNames = readData(workbook, 0);
        arrayEnEdAuthors = readData(workbook, 1);
        arrayEnEdUniversities = readData(workbook, 2);
        arrayEnFicNames = readData(workbook, 3);
        arrayEnFicAuthors = readData(workbook, 4);
        arrayRuEdNames = readData(workbook, 5);
        arrayRuFicNames = readData(workbook, 6);
        arrayRuFicAuthors = readData(workbook, 7);
        workbook.close();
    }

    private String[] readData(XSSFWorkbook workbook, int num) {
        XSSFSheet sheet = workbook.getSheetAt(num);
        String[]  arr   = new String[sheet.getLastRowNum() + 1];
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            if (sheet.getRow(i).getCell(0) != null) {
                arr[i] = sheet.getRow(i).getCell(0).getStringCellValue();
            }
        }
        return arr;
    }
}
