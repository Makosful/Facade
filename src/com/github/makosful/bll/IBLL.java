package com.github.makosful.bll;

import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Axl
 */
public interface IBLL
{

    public List<String> loadLog(String filePath) throws BLLException;

    public void saveMessages(ObservableList<String> logList, String filePath) throws BLLException;

}
