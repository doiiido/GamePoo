package unb.cic.poo.game2d.scenes;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;
import org.andengine.ui.activity.BaseGameActivity;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.ResourceManager;

public class SceneManager {
    //---------------------------------------------
    // SCENES
    //---------------------------------------------
    
    private BaseScene introScene;
    private BaseScene menuScene;
    private BaseScene gameScene;
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

    public SceneManager() {

    }
    
    public SceneManager(BaseGameActivity act, Engine eng, Camera cam) {
    	//this.activity = act;
    	this.engine = eng;
    	//this.camera = cam;
    }

    public void prepare(GameActivity activity) {
		this.mActivity = activity;
		this.engine = activity.getEngine();
    }
    
    public enum SceneType {
        SCENE_INTRO,
        SCENE_MENU,
        SCENE_GAME,
        SCENE_LOADING,
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
        currentScene = introScene;
        pOnCreateSceneCallback.onCreateSceneFinished(introScene);
    }
    
    private void disposeIntroScene() {
        ResourceManager.getInstance().unloadIntro();
        introScene.disposeScene();
        introScene = null;
    }
    
    public void createMenuScene() {
    	ResourceManager.getInstance().loadMenu();
    	//pOnCreateResourcesCallback.onCreateResourcesFinished();
        menuScene = new MainMenuScene();
        SceneManager.getInstance().setScene(menuScene);
        disposeIntroScene();
    }
    
    public void loadGameScene(final Engine mEngine) {
    	loadScene = new LoadScene();
    	SceneManager.getInstance().setScene(loadScene);
        ResourceManager.getInstance().unloadMenu();
        mEngine.registerUpdateHandler(new TimerHandler(0.3f, true, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourceManager.getInstance().loadGameTextures(mEngine);
                gameScene = new GameScene();
                setScene(gameScene);
            }
        }));
    }
    
    public void loadMenuScene(final Engine mEngine) {
        setScene(loadScene);
        gameScene.disposeScene();
        ResourceManager.getInstance().unloadGameTextures();
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourceManager.getInstance().loadMenu();
                setScene(menuScene);
            }
        }));
    }
}
