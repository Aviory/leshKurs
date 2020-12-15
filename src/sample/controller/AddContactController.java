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

//контроллер окна добавления контакта
public class AddContactController {

    MainSceneController mainSceneController;


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
    private Button btnOk;
    @FXML
    private Button btnClose;

    public void setMainSceneController(MainSceneController mainSceneController) {
        this.mainSceneController = mainSceneController;
    }


    @FXML
    void initialize(){
        //добавляем слушатели нажатий

        btnOk.setOnAction(event -> {
            if(!validation())
                return;
            Contact contact = new Contact(txtName.getText(),txtNumber.getText(),txtCompany.getText(),txtDescription.getText());
            //добавляем на экран, и в базу данных
            addUserToDB(contact);
            addToMainWindow(contact);


            Stage stage = (Stage) btnOk.getScene().getWindow();
            stage.close();
        });

        btnClose.setOnAction(event -> {
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();

        });
    }

    private void addUserToDB(Contact contact) {
        DataBase.add(contact);
    }

    private void addToMainWindow(Contact contact) {
        //todo add Contact on Window
        if(mainSceneController !=null)
            mainSceneController.updateWindow();
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

}
