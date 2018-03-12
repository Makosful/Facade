package com.github.makosful.gui.controller;

import com.github.makosful.gui.Model;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Axl
 */
public class LogController implements Initializable {

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
    private BorderPane bdpRoot;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = Model.getInstance();

        lstLog.setItems(model.getLogList());
    }

    @FXML
    private void handleSend(ActionEvent event) {
        model.fxmlSend(txtMessage.getText());
        lstLog.scrollTo(lstLog.getItems().size());
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        model.fxmlDelete(lstLog.getSelectionModel().getSelectedItem());
        lstLog.getSelectionModel().select(-1);
    }

    private void handleDeleteAll(ActionEvent event) {
        model.fxmlDeleteAll();
    }

    @FXML
    private void handleRedo(ActionEvent event) {
        model.redoChange();
        System.out.println("Redo");
    }

    @FXML
    private void handleUndo(ActionEvent event) {
        model.undoChange();
        System.out.println("Undo");
    }

    @FXML
    private void handleOpen(ActionEvent event) {
    }

    @FXML
    private void handleSave(ActionEvent event) {
    }

    @FXML
    private void handleClose(ActionEvent event) {
    }
}
