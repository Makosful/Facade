package com.github.makosful.gui;

import com.github.makosful.bll.BLLException;
import com.github.makosful.bll.BLLManager;
import com.github.makosful.bll.IBLL;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Axl
 */
public class Model {

    //<editor-fold defaultstate="collapsed" desc="Singleton">
    private static final Model INSTANCE = new Model();

    public static Model getInstance() {
        return INSTANCE;
    }
    //</editor-fold>

    // Objects
    private final IBLL bll;

    // Data
    private final ObservableList<String> logList;
    private final String filePath;

    Stack undoStack = new Stack();
    Stack redoStack = new Stack();

    /**
     * Singleton Constructor
     */
    private Model() {
        bll = new BLLManager();
        logList = FXCollections.observableArrayList();

        filePath = "messages.msg";

        loadLogs(filePath);
    }

    /**
     * Gets the log of messages
     *
     * @return Returns an Observable List of Strings with the messages
     */
    public ObservableList<String> getLogList() {
        return this.logList;
    }

    /**
     * Saves the message
     *
     * @param message The message to save
     */
    public void fxmlSend(String message) {
        logList.add(message);
        saveMessages();
        undoStack.push(message);
    }

    private void saveMessages() {
        try {
            bll.saveMessages(logList, filePath);
        } catch (BLLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fxmlDelete(String selectedItem) {
        logList.remove(selectedItem);
        saveMessages();
    }

    public void fxmlDeleteAll() {
        logList.clear();
        saveMessages();
    }

    private void loadLogs(String file) {
        try {
            logList.setAll(bll.loadLog(file));
        } catch (BLLException ex) {
            System.out.println(ex);
        }
    }

    public void undoChange() {
        if (undoStack.empty()) {
            return;
        } else {
            redoStack.add(undoStack.peek());
            logList.remove(undoStack.peek());
            undoStack.pop();

            saveMessages();
        }

    }

    public void redoChange() {
        if (redoStack.empty()) {
            return;
        } else {
            undoStack.add(redoStack.peek());

            logList.add(redoStack.peek().toString());

            redoStack.pop();
            saveMessages();
        }
    }
}
