package com.example.lab5;

import com.example.lab5.books.Book;
import com.example.lab5.users.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class UserGenerator {
    private ThreadLocalRandom random;
    private String[]          arrayMaleNames;
    private String[]          arrayMiddleNames;
    private String[]          arraySurnames;
    private String[]          arrayProfSurnames;
    private String[]          arrayFemaleNames;

    public UserGenerator(File file) throws IOException, InvalidFormatException {
        this.random = ThreadLocalRandom.current();
        setUp(file);
    }

    public User createUser(Map<Book, Boolean> books) {
        if (random.nextInt(0, 100) > 80) {
            if (random.nextInt(0, 100) > 70) {
                ProfFemale profFemale = (ProfFemale) GetUserFactory.getUser(UserType.PROFESSOR_FEMALE);
                profFemale.setUserName(arrayFemaleNames[random.nextInt(0, arrayFemaleNames.length)]);
                profFemale.setMiddleName(arrayMiddleNames[random.nextInt(0, arrayMiddleNames.length)]);
                profFemale.setUserSurname(arraySurnames[random.nextInt(0, arraySurnames.length)]);
                profFemale.setBooks(generateBookSet(books));
                return profFemale;
            } else {
                ProfMale profMale = (ProfMale) GetUserFactory.getUser(UserType.PROFESSOR_MALE);
                profMale.setUserName(arrayMaleNames[random.nextInt(0, arrayMaleNames.length)]);
                profMale.setMiddleName(arrayMiddleNames[random.nextInt(0, arrayMiddleNames.length)]);
                profMale.setUserSurname(arraySurnames[random.nextInt(0, arraySurnames.length)]);
                profMale.setBooks(generateBookSet(books));
                return profMale;
            }
        } else {
            if (random.nextInt(0, 100) > 70) {
                User studentFemale = GetUserFactory.getUser(UserType.STUDENT_FEMALE);
                studentFemale.setUserName(arrayFemaleNames[random.nextInt(0, arrayFemaleNames.length)]);
                studentFemale.setUserSurname(arraySurnames[random.nextInt(0, arraySurnames.length)]);
                studentFemale.setBooks(generateBookSet(books));
                return studentFemale;
            } else {
                User studentMale = GetUserFactory.getUser(UserType.STUDENT_MALE);
                studentMale.setUserName(arrayMaleNames[random.nextInt(0, arrayMaleNames.length)]);
                studentMale.setUserSurname(arraySurnames[random.nextInt(0, arraySurnames.length)]);
                studentMale.setBooks(generateBookSet(books));
                return studentMale;
            }
        }
    }

    private String[] readData(XSSFWorkbook workbook, int num) {
        XSSFSheet sheet     = workbook.getSheetAt(num);
        String[]  dataArray = new String[sheet.getLastRowNum() + 1];
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            if (sheet.getRow(i).getCell(0) != null) {
                dataArray[i] = sheet.getRow(i).getCell(0).getStringCellValue();
            }
        }
        return dataArray;
    }

    private void setUp(File file) throws IOException, InvalidFormatException {
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        arrayMaleNames = readData(workbook, 0);
        arrayFemaleNames = readData(workbook, 1);
        arraySurnames = readData(workbook, 2);
        arrayProfSurnames = readData(workbook, 3);
        arrayMiddleNames = buildMiddleNames();
        workbook.close();
    }

    private String[] buildMiddleNames() {
        String[] arrMiddleNames = new String[arrayMaleNames.length];
        int      i              = 0;
        for (String name : arrayMaleNames) {
            if (name.endsWith("ья")) {
                arrMiddleNames[i] = name.substring(0, name.length() - 1) + "ич";
            } else if (name.endsWith("ж") || name.endsWith("ш") || name.endsWith("ч") || name.endsWith("щ") || name.endsWith("ц") || name.endsWith("и") || name.endsWith("э") || name.endsWith("я") || name.endsWith("ю") || name.endsWith("е") || name.endsWith("ё")) {
                arrMiddleNames[i] = name + "евич";
            } else if (name.endsWith("н") || name.endsWith("р") || name.endsWith("м") || name.endsWith("л") || name.endsWith("с") || name.endsWith("б")) {
                arrMiddleNames[i] = name + "ович";
            } else if (name.endsWith("а") || name.endsWith("у") || name.endsWith("ы")) {
                arrMiddleNames[i] = name.substring(0, name.length() - 1) + "ович";
            } else if (name.endsWith("о")) {
                arrMiddleNames[i] = name.substring(0, name.length() - 1) + "вич";
            } else if (name.endsWith("ь") || name.endsWith("й")) {
                arrMiddleNames[i] = name.substring(0, name.length() - 1) + "евич";
            } else {
                arrMiddleNames[i] = name + "евич";
            }
            i++;
        }
        return arrMiddleNames;
    }

    public Set<Book> generateBookSet(Map<Book, Boolean> booksList) {
        int        num       = random.nextInt(3, 11);
        Set<Book>  books     = new HashSet<>();
        List<Book> freeBooks = booksList.entrySet().stream().filter(entry -> !entry.getValue()).map(Map.Entry::getKey).toList();
        for (int i = 0; i < num; i++) {
            Book bookToTake = freeBooks.get(random.nextInt(0, freeBooks.size()));
            books.add(bookToTake);
            booksList.put(bookToTake, true);
        }
        return books;
    }
}
