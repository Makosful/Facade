package com.github.makosful.gui.controller;

import com.github.makosful.be.Scenes;
import com.github.makosful.gui.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class SignUpController implements Initializable
{

    private Model model;

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnCreate;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPass1;
    @FXML
    private PasswordField txtPass2;
    @FXML
    private TextField txtUserName;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = Model.getInstance();
    }

    @FXML
    private void handleCancel(ActionEvent event)
    {
        model.changeScene(Scenes.LOGIN);
    }

    @FXML
    private void handleCreate(ActionEvent event)
    {
        model.changeScene(Scenes.CHAT);
    }

}
