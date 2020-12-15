package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.CheckIn;
import sample.db.DataBase;
import sample.objjects.Contact;

public class EditeController {

    //инициализируем переменные
    @FXML
    private Label txtNameError;
    @FXML
    private Label txtNumberError;
    @FXML
    private Label txtCompanyError;
    @FXML
    private Label txtDescriptionError;


    @FXML
    private TextField txtName;
    @FXML
    private TextField txtNumber;
    @FXML
    private TextField txtCompany;
    @FXML
    private TextArea txtDescription;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;

    private MainSceneController mainSceneController;
    private Contact contact;

    @FXML
    void initialize() {
        //добавляем слушатели нажатий

        btnUpdate.setOnAction(event -> {
            if(!validation())
                return;
            contact.setNameText(txtName.getText());
            contact.setNumberText(txtNumber.getText());
            contact.setCompanyText(txtCompany.getText());
            contact.setDescriptionText(txtDescription.getText());
            DataBase.update(contact);
            mainSceneController.updateWindow();

            Stage stage = (Stage) btnUpdate.getScene().getWindow();
            stage.close();
        });

        btnDelete.setOnAction(event -> {
            DataBase.delete(contact);
            mainSceneController.updateWindow();
            Stage stage = (Stage) btnDelete.getScene().getWindow();
            stage.close();
        });
    }

    private boolean validation() {
        if(txtName.getText().equals("")) {
            txtNameError.setText("введите имя");
            return false;
        }
        else
        if(!CheckIn.validationTxtLength(txtName.getText())){
            txtNameError.setText("короткое имя");
            return false;
        }
        else
            txtNameError.setText("");

        if(txtNumber.getText().equals("")){
            txtNumberError.setText("введите номер");
            return false;
        }
        else
        if(!CheckIn.validationNumber(txtNumber.getText())){
            txtNumberError.setText("введите только цыфры");
            return false;
        }
        else
            txtNumberError.setText("");

        if(txtCompany.getText().equals("")){
            txtCompanyError.setText("введите компанию");
            return false;
        }
        else
        if(!CheckIn.validationTxtLength(txtCompany.getText())){
            txtDescriptionError.setText("короткое название");
            return false;
        }
        else
            txtCompanyError.setText("");

        if(txtDescription.getText().equals("")){
            txtDescriptionError.setText("введите описание");
            return false;
        }
        else
        if(!CheckIn.validationTxtDescription(txtDescription.getText())){
            txtDescriptionError.setText("минимум 15 символов");
            return false;
        }
        else
            txtDescriptionError.setText("");

        return true;
    }

    public void setMainSceneController(MainSceneController mainSceneController, Contact contact) {
        this.mainSceneController = mainSceneController;
        this.contact = contact;
        txtName.setText(contact.getNameText());
        txtNumber.setText(contact.getNumberText());
        txtCompany.setText(contact.getCompanyText());
        txtDescription.setText(contact.getDescriptionText());
    }
}
