package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import sample.db.DataBase;
import sample.objjects.Contact;

import java.io.IOException;
import java.util.LinkedList;

//контроллер главного окна
public class MainSceneController {

    LinkedList<Contact> contacts = null;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button addBtn;

    private final FlowPane container = new FlowPane();

    @FXML
    void initialize(){
        //загружаем данные
        initData();
        scrollPane.setContent(container);

        // Pannable.
        scrollPane.setPannable(true);

        //обработчик нажатия на кнопку "добавить контакт"
        addBtn.setOnAction(event -> {

            //обработчик исключений, на случай если fxml файл не будет найдет
            addContactStage();
        });
    }

    public void addContactStage() {
        try {
            //создаем новое окно из зарание созданного интерфейса
            //Parent addContactView = FXMLLoader.load(getClass().getResource("../add_contact.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../add_contact.fxml"));

            Parent addContactView = loader.load();
            Scene secondScene = new Scene(addContactView, 430, 650);
            AddContactController addContactController = loader.getController();
            addContactController.setMainSceneController(this);

            // Само окно(Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Новый контакт");
            newWindow.setScene(secondScene);
            // Стартовое положение окна, относительно левого верхнего угла экрана
            newWindow.setX( 200);
            newWindow.setY( 100);


            //отобразить
            newWindow.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initData(){
        contacts = DataBase.get();
        for (Contact c : contacts) {
            addContact(c);
        }
    }

    public void addContact(Contact c) {
        Label label = new Label(c.getNameText()+": "+c.getNumberText());
        label.setPrefSize(300, 50);
        label.setOnMouseClicked(event -> {
            Label l = (Label) event.getSource();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../edite.fxml"));
                Parent editeContactView = loader.load();
                Scene secondScene = new Scene(editeContactView, 430, 650);
                EditeController editController = loader.getController();

                // Само окно(Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Новый контакт");
                newWindow.setScene(secondScene);
                // Стартовое положение окна, относительно левого верхнего угла экрана
                newWindow.setX(700);
                newWindow.setY(300);


                //отобразить
                newWindow.show();

                editController.setMainSceneController(this,findContact(l.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        container.getChildren().add(label);
    }

    private Contact findContact(String text) {
        String[] arr = text.split(": ");
        for (Contact c:contacts) {
            if(arr[1].equals(c.getNumberText()))
                return c;
        }
        return null;
    }

    public void updateWindow() {
        container.getChildren().clear();
        initData();
    }
}
