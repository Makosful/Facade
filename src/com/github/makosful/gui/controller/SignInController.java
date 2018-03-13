package com.github.makosful.gui.controller;

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
public class SignInController implements Initializable
{

    @FXML
    private Button bntLogin;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnQuit;
    @FXML
    private PasswordField txtPass;
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
        // TODO
    }

    @FXML
    private void handleCreate(ActionEvent event)
    {
    }

    @FXML
    private void handleLogin(ActionEvent event)
    {
    }

    @FXML
    private void handleQuit(ActionEvent event)
    {
    }

}
