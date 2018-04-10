package com.github.makosful.dal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author Axl
 */
public class DALManager implements IDAL
{

    private DatabaseConnector db;

    public DALManager()
    {
        db = new DatabaseConnector();
    }

    /**
     *
     * @param file
     *
     * @return
     *
     * @throws DALException
     */
    @Override
    public List<String> loadLog(String file) throws DALException
    {
        ArrayList<String> messages = new ArrayList<>();

        File f = new File(file);
        if (f.exists())
            try (BufferedReader br = new BufferedReader(new FileReader(file)))
            {
                while (br.ready())
                {
                    String line = br.readLine();
                    messages.add(line);
                }
            }
            catch (IOException ex)
            {
                throw new DALException(ex.getLocalizedMessage(), ex);
            }

        return messages;
    }

    @Override
    public void saveMessages(ObservableList<String> logList, String filePath) throws DALException
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath)))
        {
            for (String s : logList)
            {
                bw.append(s);
                bw.newLine();
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
