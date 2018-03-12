package com.github.makosful.gui.controller;

import com.github.makosful.gui.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    @FXML
    private Button btnDeleteAll;

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
        setupKeyCombinations();
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

    @FXML
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

    private void setupKeyCombinations() {
        bdpRoot.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.Z) && event.isControlDown()) // Ctrl + Z
                {
                    model.undoChange();
                }
                if (event.getCode().equals(KeyCode.Y) && event.isControlDown()) // Ctrl + Y
                {
                    model.redoChange();
                }
            }
        });
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

    @FXML
    private void sendMsg(ActionEvent event) {
        handleSend(event);
    }
}
