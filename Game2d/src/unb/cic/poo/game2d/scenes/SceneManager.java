package unb.cic.poo.game2d.scenes;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.opengl.texture.atlas.bitmap.source.ResourceBitmapTextureAtlasSource;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;
import android.os.AsyncTask;

import unb.cic.poo.game2d.enemies.*;
import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;

public class SceneManager {
    //---------------------------------------------
    // SCENES
    //---------------------------------------------
    
    private BaseScene introScene;
    private BaseScene menuScene;
    private BaseScene settingsScene;
    public static BaseScene gameScene;
    private BaseScene loadScene;
    
    //---------------------------------------------
    // VARIABLES
    //---------------------------------------------
    
    protected GameActivity mActivity;
    private static SceneManager INSTANCE = new SceneManager();
    private SceneType currentSceneType = SceneType.SCENE_INTRO;
    private BaseScene currentScene;
    private Engine engine;
    private boolean introClosed = false;
    private int op = -1;

    public void prepare(GameActivity activity, Engine eng, Camera cam) {
		this.mActivity = activity;
		this.engine = activity.getEngine();
	    this.engine = eng;
	    //this.camera = cam;
    }
    
    public enum SceneType {
        SCENE_INTRO,
        SCENE_MENU,
        SCENE_GAME,
        SCENE_LOADING,
        SCENE_SETTINGS,
    }
    
    //---------------------------------------------
    // CLASS LOGIC
    //---------------------------------------------
    
    public void setScene(BaseScene scene) {
        engine.setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }
    
    public void setScene(SceneType sceneType) {
    	this.currentSceneType = sceneType;
        switch (sceneType) {
            case SCENE_MENU:
                setScene(menuScene);
                break;
            case SCENE_GAME:
                setScene(gameScene);
                break;
            case SCENE_INTRO:
                setScene(introScene);
                break;
            case SCENE_LOADING:
                setScene(loadScene);
                break;
            case SCENE_SETTINGS:
                setScene(settingsScene);
                break;
            default:
                break;
        }
    }
    
    
    //---------------------------------------------
    // GETTERS AND SETTERS
    //---------------------------------------------
    
    public static SceneManager getInstance() {
        return INSTANCE;
    }
    
    public SceneType getCurrentSceneType() {
        return currentSceneType;
    }
    
    public BaseScene getCurrentScene() {
        return currentScene;
    }
    
    public void createIntroScene(OnCreateSceneCallback pOnCreateSceneCallback) {
        //ResourceManager.getInstance().loadIntro();
        introScene = new IntroScene();
        SceneManager.getInstance().setScene(introScene);
        pOnCreateSceneCallback.onCreateSceneFinished(introScene);
    }
    
    private void disposeIntroScene() {
        ResourceManager.getInstance().unloadIntro();
        introScene.disposeScene();
        introScene = null;
        introClosed = true;
    }
    
    public void createMenuScene() {
    	ResourceManager.getInstance().loadMenu();
    	ResourceManager.getInstance().loadFonts();
        menuScene = new MainMenuScene();
        SceneManager.getInstance().setScene(menuScene);
        if(!introClosed) disposeIntroScene();
    }
    
    private void disposeMenuScene() {
        ResourceManager.getInstance().unloadMenu();
        //menuScene.disposeScene();
        menuScene = null;
    }
    
    public void createLoadScene() {
    	loadScene = new LoadScene();
    	SceneManager.getInstance().setScene(loadScene);
    }
    
    private void disposeLoadScene() {
    	loadScene.disposeScene();
    	loadScene = null;
    }
    
    private void createGameScene() {
    	ResourceManager.getInstance().loadGameTextures();
    	ResourceManager.getInstance().loadGamePause();
        gameScene = new GameScene();
        SceneManager.getInstance().setScene(gameScene);
        ((GameScene) gameScene).setGameScene();
        disposeMenuScene();
    }
    
    private void disposeGameScene() {
    	ResourceManager.getInstance().unloadGameTextures();
    	ResourceManager.getInstance().unloadGamePause();
        gameScene.disposeScene();
        gameScene = null;
    }
    
    public void loadGameScene(){
    	op = 1;
    	new ExecuteChange().execute();
    }
    
    public void restartGameScene() {
    	op = 2;
    	new ExecuteChange().execute();
    }
    
    public void loadMenuScene() {
    	op = 3;
    	new ExecuteChange().execute();
    }
    
    private class ExecuteChange extends AsyncTask<Void, Void, Void> {

    		@Override
			protected Void doInBackground(Void... params) {
				// Conferir essa parte
    			engine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
    	            public void onTimePassed(final TimerHandler pTimerHandler) {
    	                engine.unregisterUpdateHandler(pTimerHandler);
		    			switch(op){
		    			case 1:
		    				disposeMenuScene();
		    				createGameScene();
		    				break;
		    			case 2:
		    				disposeGameScene();
		    				createGameScene();
		    	            break;
		    			case 3:
		    				disposeGameScene();
		                    createMenuScene();
		                    break;
		    			default:
		    				break;
		    			}
    	            }
    	        }));
    			return null;
			}
    		
            @Override
            protected void onPostExecute(Void result) {
            	disposeLoadScene();
            }
			
			@Override
	        protected void onPreExecute() {
				createLoadScene();
	        }
            
    }
    
    public void loadMenufromSettings(){
    	disposeSettingsScene();
    	ResourceManager.getInstance().loadMenu();
        createMenuScene();
    }
    
    public void createSettingsScene() {
    	disposeMenuScene();
    	ResourceManager.getInstance().loadSettings();
    	settingsScene = new SettingsScene();
        SceneManager.getInstance().setScene(settingsScene);
    }
    
    public void returnSettingsScene(OnCreateSceneCallback pOnCreateSceneCallback) {
    	ResourceManager.getInstance().loadSettings();
    	settingsScene = new SettingsScene();
        SceneManager.getInstance().setScene(settingsScene);
        pOnCreateSceneCallback.onCreateSceneFinished(settingsScene);
    }
    
    private void disposeSettingsScene() {
        ResourceManager.getInstance().unloadSettings();
        settingsScene.disposeScene();
        settingsScene = null;
    }
    
}