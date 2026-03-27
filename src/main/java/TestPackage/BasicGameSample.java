package TestPackage;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//GameApplication is used to start the game instead of
//JavaFX's native Application class
public class BasicGameSample extends GameApplication {

    // Variable to hold our controllable entity
    private Entity player;

    @Override
    protected void initSettings(GameSettings gameSettings) {

    }

    @Override
    //Overriden to set up the factory and
    //spawn the entities in the specific x and y coordinates in pixels.
    protected void initGame() {
        //Used to initialize the world and start to add entities
        FXGL.getGameWorld().addEntityFactory(new SimpleFactory());

        // add entities

        // IMPORTANT: entityName String must be the same as in the Spawn annotation.
        FXGL.spawn("enemy", 100, 100); //buildAndAttatch() method is not necessary if this is called
        FXGL.spawn("movingEntity", 200, 200);
        
        // Create a player entity that we can control
        player = FXGL.entityBuilder()
                .at(300, 300)
                .viewWithBBox(new Rectangle(40, 40, Color.GREEN))  // Green rectangle as player
                .buildAndAttach();
                
        // Initialize input handling
        initInput();
    }
    
    // Method to handle input/key listeners
    @Override
    protected void initInput() {
        FXGL.getAudioPlayer();
        // Move up
        FXGL.getInput().addAction(
            action -> player.translateY(-5),
            KeyCode.W
        );
        
        // Move down
        FXGL.getInput().addAction(
            action -> player.translateY(5),
            KeyCode.S
        );
        
        // Move left
        FXGL.getInput().addAction(
            action -> player.translateX(-5),
            KeyCode.A
        );
        
        // Move right
        FXGL.getInput().addAction(
            action -> player.translateX(5),
            KeyCode.D
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}