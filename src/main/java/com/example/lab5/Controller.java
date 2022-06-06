package com.example.lab5;

import com.example.lab5.books.*;
import com.example.lab5.users.StudentFemale;
import com.example.lab5.users.StudentMale;
import com.example.lab5.users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {


    ArrayList<User>    users = new ArrayList<>();
    Map<Book, Boolean> books = new HashMap();

    @FXML
    private TreeView<String> tree;

    @FXML
    private Button buttonUser;

    public void initialize() {
        buttonUser.setDisable(true);
    }

    void error(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    @FXML
    void impBook(ActionEvent event) {
        try {
            FileChooser                 fc        = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel", "*.xlsx");
            fc.getExtensionFilters().add(extFilter);
            File          file             = fc.showOpenDialog(null);
            BookGenerator bookGenerator    = new BookGenerator(file);
            int           edCounter        = 0;
            int           edCounterEn      = 0;
            int           edCounterRu      = 0;
            int           fictionCounter   = 0;
            int           fictionCounterEn = 0;
            int           fictionCounterRu = 0;

            while (edCounter < 500
                    || edCounterRu < 20
                    || edCounterEn < 15
                    || fictionCounter < 500
                    || fictionCounterEn < 15
                    || fictionCounterRu < 20) {
                Book book = bookGenerator.createBook();
                if (book instanceof EducationBookEn) {
                    edCounter++;
                    edCounterEn++;
                } else if (book instanceof EducationBookRu) {
                    edCounter++;
                    edCounterRu++;
                } else if (book instanceof FictionBookEn) {
                    fictionCounter++;
                    fictionCounterEn++;
                } else if (book instanceof FictionBookRu) {
                    fictionCounter++;
                    fictionCounterRu++;
                }
                books.put(book, false);
            }

            buttonUser.setDisable(false);
        } catch (Exception e) {
            error(e);
        }
    }

    @FXML
    void impUser(ActionEvent event) {
        try {
            FileChooser                 fc        = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel", "*.xlsx");
            fc.getExtensionFilters().add(extFilter);
            File          file          = fc.showOpenDialog(null);
            UserGenerator userGenerator = new UserGenerator(file);

            for (int i = 0; i < 50; i++) {
                users.add(userGenerator.createUser(books));
            }

            initializeTree();

        } catch (Exception e) {
            error(e);
        }
    }

    @FXML
    void start(ActionEvent event) {
        try {
            File          fileB         = new File((getClass().getResource("books.xlsx").getFile()));
            BookGenerator bookGenerator = new BookGenerator(fileB);
            int           edCounter        = 0;
            int           edCounterEn      = 0;
            int           edCounterRu      = 0;
            int           fictionCounter   = 0;
            int           fictionCounterEn = 0;
            int           fictionCounterRu = 0;

            while (edCounter < 50
                    || edCounterRu < 20
                    || edCounterEn < 15
                    || fictionCounter < 50
                    || fictionCounterEn < 15
                    || fictionCounterRu < 20) {
                Book book = bookGenerator.createBook();
                if (book instanceof EducationBookEn) {
                    edCounter++;
                    edCounterEn++;
                } else if (book instanceof EducationBookRu) {
                    edCounter++;
                    edCounterRu++;
                } else if (book instanceof FictionBookEn) {
                    fictionCounter++;
                    fictionCounterEn++;
                } else if (book instanceof FictionBookRu) {
                    fictionCounter++;
                    fictionCounterRu++;
                }
                books.put(book, false);
            }

            File          fileU       = new File((getClass().getResource("names.xlsx").getFile()));
            UserGenerator userFactory = new UserGenerator(fileU);
            for (int i = 0; i < 50; i++) {
                users.add(userFactory.createUser(books));
            }
            initializeTree();
        } catch (Exception e) {
            error(e);
        }
    }

    public void initializeTree() {

        int stNum = 0;
        int prNum = 0;

        TreeItem<String> rootItem = new TreeItem<>("Пользователи");
        rootItem.setExpanded(true);
        TreeItem<String> studentBranchItem = new TreeItem<>();
        rootItem.getChildren().add(studentBranchItem);
        TreeItem<String> professorBranchItem = new TreeItem<>();
        rootItem.getChildren().add(professorBranchItem);

        for (User user : users) {

            TreeItem<String> branchItem = new TreeItem<>(user.getFullName() + " (" + user.getBooks().size() + ")");
            if (user instanceof StudentFemale || user instanceof StudentMale) {
                studentBranchItem.getChildren().add(branchItem);
                stNum++;
            } else {
                professorBranchItem.getChildren().add(branchItem);
                prNum++;
            }

            // Нужно делать отдельно генерацию и отдельно вывод
            user.getBooks().forEach((obj) -> {
                Book book = (Book) obj;

                TreeItem<String> bookItem = new TreeItem<>(book.getName());
                branchItem.getChildren().add(bookItem);
                TreeItem<String> leafItemType = new TreeItem<>("type : " + (book instanceof EducationBookRu || book instanceof EducationBookEn? "Education" : "Fiction"));
                bookItem.getChildren().add(leafItemType);
                TreeItem<String> leafItemLang = new TreeItem<>("lang : " + (book instanceof EducationBookEn || book instanceof FictionBookEn ? "EN" : "RU"));
                bookItem.getChildren().add(leafItemLang);

                if (book instanceof EducationBookEn b) {
                    TreeItem<String> leafItemAuthor = new TreeItem<>("author : " + b.getAuthor());
                    bookItem.getChildren().add(leafItemAuthor);
                    TreeItem<String> leafItemUniversity = new TreeItem<>("university : " + b.getUniversity());
                    bookItem.getChildren().add(leafItemUniversity);
                } else if (book instanceof FictionBookEn b) {
                    TreeItem<String> leafItemAuthor = new TreeItem<>("author : " + b.getAuthor());
                    bookItem.getChildren().add(leafItemAuthor);
                } else if (book instanceof FictionBookRu b) {
                    TreeItem<String> leafItemAuthor = new TreeItem<>("author : " + b.getAuthor());
                    bookItem.getChildren().add(leafItemAuthor);
                }

            });

        }

        studentBranchItem.setValue("Студенты (" + stNum + ")");
        professorBranchItem.setValue("Преподаватели (" + prNum + ")");

        tree.setRoot(rootItem);

    }
}