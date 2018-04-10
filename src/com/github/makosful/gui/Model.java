package com.github.makosful.gui;

import com.github.makosful.bll.BLLException;
import com.github.makosful.bll.BLLManager;
import com.github.makosful.bll.IBLL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Axl
 */
public class Model
{

    //<editor-fold defaultstate="collapsed" desc="Singleton">
    private static final Model INSTANCE = new Model();

    public static Model getInstance()
    {
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
    Stack DeletedStack = new Stack();

    /**
     * Singleton Constructor
     */
    private Model()
    {
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
    public ObservableList<String> getLogList()
    {
        return this.logList;
    }

    /**
     * Saves the message
     *
     * @param message The message to save
     */
    public void fxmlSend(String message)
    {
        ArrayList<String> prev = new ArrayList(logList);
        undoStack.push(prev);
        logList.add(message);
        saveMessages();
        //undoStack.push(message);
    }

    private void saveMessages()
    {
        try
        {
            bll.saveMessages(logList, filePath);
        }
        catch (BLLException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fxmlDelete(String message)
    {
        ArrayList<String> prev = new ArrayList<>(logList);
        undoStack.push(prev);
        logList.remove(message);
        saveMessages();
    }

    public void fxmlDeleteAll()
    {
        DeletedStack.addAll(logList);
        logList.clear();
        saveMessages();
    }

    private void loadLogs(String file)
    {
        try
        {
            logList.setAll(bll.loadLog(file));
        }
        catch (BLLException ex)
        {
            System.out.println(ex);
        }
    }

    public void undoChange()
    {
        if (undoStack.empty())
        {
            System.out.println("Not undoing");
            return;
        }
        System.out.println("Undo");

        ArrayList temp = new ArrayList(logList);
        redoStack.add(temp);
        logList.setAll((Collection<? extends String>) undoStack.peek());
        undoStack.pop();

        saveMessages();
    }

    public void redoChange()
    {
        if (redoStack.empty())
        {
            System.out.println("not redoing");
            return;
        }
        System.out.println("Redo");

        ArrayList temp = new ArrayList(logList);
        undoStack.add(redoStack.peek());
        logList.setAll((Collection<? extends String>) redoStack.peek());
        redoStack.pop();

        saveMessages();
    }
}
