package unb.cic.poo.game2d.scenes;

import java.util.ArrayList;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.app.Activity;
import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.ResourceManager;
import unb.cic.poo.game2d.scenes.SceneManager.SceneType;

public abstract class BaseScene extends Scene {
    //---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
    protected static Engine engine;
    protected static Activity activity;
    protected ResourceManager resourceManager;
    protected SceneManager sceneManager;
    protected VertexBufferObjectManager vbom;
    protected static Camera camera;
    ArrayList<Entity> entitiesList = new ArrayList<Entity>();
    
    //---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
    
    public BaseScene() {
        this.resourceManager = ResourceManager.getInstance();
        BaseScene.activity = resourceManager.activity;
		BaseScene.engine = resourceManager.engine;
        this.vbom = resourceManager.vbom;
        BaseScene.camera = GameActivity.mCamera;
        this.sceneManager = SceneManager.getInstance();
        createScene();
    }

    //---------------------------------------------
    // ABSTRACTION
    //---------------------------------------------
    
    public abstract void createScene();
    
    public abstract SceneType getSceneType();
    
    //---------------------------------------------
    // METHODS
    //---------------------------------------------
    
    public void disposeScene() {
    	cleanEntities();
    	this.clearEntityModifiers();
        this.clearTouchAreas();
        this.clearUpdateHandlers();
        this.detachSelf();
        this.dispose();
    }
    
    public void cleanEntities() {	
    	for (Entity entity: entitiesList) {
    		entity.clearEntityModifiers();
    		entity.clearUpdateHandlers();
    		entity.detachSelf();
    		
    		if (!entity.isDisposed()) {
    			entity.dispose();
    		}
    	}
    	
    	entitiesList.clear();
    	entitiesList = null;
    }
}
