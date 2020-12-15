package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.controller.AddContactController;
import sample.controller.MainSceneController;
import sample.db.DataBase;
import sample.objjects.Contact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_scene.fxml"));
        Parent root = loader.load();
        MainSceneController mainSceneController = loader.getController();
        primaryStage.setTitle("Hello World");


        //Создание и настройка меню
        MenuBar menuBar = new MenuBar();

        Menu programMenu = new Menu("программа");
        Menu contactsMenu = new Menu("Контакты");
        Menu aboutMenu = new Menu("О разработчике");

        MenuItem resItem = new MenuItem("Про программу");
        resItem.setOnAction(event ->  {
            AddCoderInfoStage();
        });
        aboutMenu.getItems().add(resItem);


        MenuItem exitItem = new MenuItem("выход");
        exitItem.setOnAction(event ->  {
                System.exit(0);
        });
        programMenu.getItems().addAll(exitItem);
        programMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));

        MenuItem newContact = new MenuItem("Добавить контакт");
        newContact.setOnAction(event ->  {
            mainSceneController.addContactStage();
        });
        contactsMenu.getItems().add(newContact);

        menuBar.getMenus().add(programMenu);
        menuBar.getMenus().add(contactsMenu);
        menuBar.getMenus().add(aboutMenu);

        //добавление на экран меню и основные
        BorderPane stackPane = new BorderPane();
        stackPane.setTop(menuBar);
        stackPane.setCenter(root);
        //stackPane.getChildren().addAll(menuBar,root);

        Scene scene = new Scene(stackPane, 600, 900);
        primaryStage.setScene(scene);

        primaryStage.setResizable(false);
        primaryStage.show();

    }

    private void AddCoderInfoStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("about.fxml"));
            Parent parent = loader.load();
            Scene secondScene = new Scene(parent, 430, 650);

            // Само окно(Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Про программу");
            newWindow.setScene(secondScene);
            // Стартовое положение окна, относительно левого верхнего угла экрана
            newWindow.setX(200);
            newWindow.setY(100);


            //отобразить
            newWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

