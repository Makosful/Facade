package com.github.makosful.bll;

import com.github.makosful.dal.DALException;
import com.github.makosful.dal.DALManager;
import com.github.makosful.dal.IDAL;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Axl
 */
public class BLLManager implements IBLL
{

    private final IDAL dal;

    public BLLManager()
    {
        this.dal = new DALManager();
    }

    @Override
    public List<String> loadLog(String file) throws BLLException
    {
        try
        {
            return dal.loadLog(file);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public void saveMessages(ObservableList<String> logList, String filePath) throws BLLException
    {
        try
        {
            dal.saveMessages(logList, filePath);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }
}
