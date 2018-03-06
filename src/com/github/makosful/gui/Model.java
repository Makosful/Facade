package com.github.makosful.gui;

import com.github.makosful.bll.BLLException;
import com.github.makosful.bll.BLLManager;
import com.github.makosful.bll.IBLL;
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
        try
        {
            logList.add(message);
            bll.saveMessages(logList, filePath);
        }
        catch (BLLException ex)
        {
            System.out.println(ex);
        }
    }

    public void fxmlDelete(String selectedItem)
    {
        try
        {
            logList.remove(selectedItem);
            bll.saveMessages(logList, filePath);
        }
        catch (BLLException ex)
        {
            System.out.println(ex);
        }
    }

    public void fxmlDeleteAll()
    {
        try
        {
            logList.clear();
            bll.saveMessages(logList, filePath);
        }
        catch (BLLException ex)
        {
            System.out.println(ex);
        }
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
}
