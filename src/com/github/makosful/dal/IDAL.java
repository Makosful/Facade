package com.github.makosful.dal;

import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Axl
 */
public interface IDAL
{

    public List<String> loadLog(String file) throws DALException;

    public void saveMessages(ObservableList<String> logList, String filePath) throws DALException;

}
