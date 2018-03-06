package com.github.makosful.gui.controller;

import com.github.makosful.gui.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author Axl
 */
public class LogController implements Initializable
{

    private Model model;

    @FXML
    private ListView<String> lstLog;
    @FXML
    private TextField txtMessage;
    @FXML
    private Button btnSend;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnDeleteAll;
    @FXML
    private Button btnUndo;

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

        lstLog.setItems(model.getLogList());
    }

    @FXML
    private void handleSend(ActionEvent event)
    {
        model.fxmlSend(txtMessage.getText());
        lstLog.scrollTo(lstLog.getItems().size());
    }

    @FXML
    private void handleDelete(ActionEvent event)
    {
        model.fxmlDelete(lstLog.getSelectionModel().getSelectedItem());
        lstLog.getSelectionModel().select(-1);
    }

    @FXML
    private void handleDeleteAll(ActionEvent event)
    {
        model.fxmlDeleteAll();
    }

}
