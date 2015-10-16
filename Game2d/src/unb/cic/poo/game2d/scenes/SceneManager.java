package unb.cic.poo.game2d.scenes;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import unb.cic.poo.game2d.Enemy;
import unb.cic.poo.game2d.FreezedShootingEnemy;
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
    //private static BaseGameActivity activity;
    //private static Camera camera;
    private boolean introClosed = false;

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
        //curretScene = introScene;
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
        gameScene = new GameScene();
        SceneManager.getInstance().setScene(gameScene);
        ((GameScene) gameScene).setGameScene();
        disposeMenuScene();
    }
    
    private void disposeGameScene() {
    	ResourceManager.getInstance().unloadGameTextures();
        gameScene.disposeScene();
        gameScene = null;
    }
    
    public void loadGameScene(final Engine mEngine) {
    	createLoadScene();
    	disposeMenuScene();
        mEngine.registerUpdateHandler(new TimerHandler(0.3f, true, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                createGameScene();
                disposeLoadScene();
            }
        }));
    }
    
    public void loadMenuScene(final Engine mEngine) {
    	createLoadScene();
        disposeGameScene();
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourceManager.getInstance().loadMenu();
                createMenuScene();
                disposeLoadScene();
                //TODO: disposeSettingsScene();

            }
        }));
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
    
    private void disposeSettingsScene() {
        ResourceManager.getInstance().unloadSettings();
        settingsScene.disposeScene();
        settingsScene = null;
    }
    
}
