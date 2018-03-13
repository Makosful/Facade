package com.github.makosful;

import com.github.makosful.gui.Model;
import com.github.makosful.stage.entities.Docking;
import com.github.makosful.stage.exception.SceneIdAlreadyTakenException;
import com.github.makosful.stage.utils.StageManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Axl
 */
public class Facade extends Application
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    private StageManager sm;
    private Model model;

    @Override
    public void start(Stage stage) throws Exception
    {
        sm = new StageManager(stage);
        model = Model.getInstance();
        model.setStageManager(sm);

        registerScenes(sm);

        stage.show();
        sm.getPlacementUtil().alignStage(stage, Docking.CENTER);
    }

    private void registerScenes(StageManager sm)
    {
        try
        {
            sm.registerScene(1, "Login",
                             getClass().getResource("gui/view/SignIn.fxml"));
            sm.registerScene(2, "Sign Up",
                             getClass().getResource("gui/view/SignIn.fxml"));
            sm.registerScene(3, "Chat",
                             getClass().getResource("gui/view/SignIn.fxml"));

            sm.setActiveScene(1);
        }
        catch (SceneIdAlreadyTakenException | IOException ex)
        {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
