package unb.cic.poo.game2d.scenes;

import org.andengine.engine.Engine;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;
import android.os.AsyncTask;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.ResourceManager;

public class SceneManager {
    //---------------------------------------------
    // SCENES
    //---------------------------------------------
    
    private BaseScene introScene;
    private BaseScene menuScene;
    private BaseScene settingsScene;
    private BaseScene selectorScene;
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
    public enum OpAsyncTask {
    	DEFAULT,
    	LOAD_GAME,
    	RESTART_GAME,
    	LOAD_MENU,
    	LOAD_GAME_SELECTOR
    }
    private static OpAsyncTask opTask = OpAsyncTask.DEFAULT;

    public void prepare(GameActivity activity, Engine eng) {
		this.mActivity = activity;
		this.engine = activity.getEngine();
	    this.engine = eng;
    }
    
    public enum SceneType {
        SCENE_INTRO,
        SCENE_MENU,
        SCENE_GAME,
        SCENE_LOADING,
        SCENE_SETTINGS,
        SCENE_SELECTOR,
        
    }
    
    //---------------------------------------------
    // GETTERS AND SETTERS
    //---------------------------------------------
    
    public static SceneManager getInstance() {
        return INSTANCE;
    }
    
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
            case SCENE_SELECTOR:
                setScene(selectorScene);
                break;
            default:
                break;
        }
    }
    
    public SceneType getCurrentSceneType() {
        return currentSceneType;
    }
    
    public BaseScene getCurrentScene() {
        return currentScene;
    }
    
    public void setOpTask(OpAsyncTask op){
    	opTask = op;
    }
    
    public OpAsyncTask getOpTask(){
    	return opTask;
    }
    
    //---------------------------------------------
    // METHODS
    //---------------------------------------------
    
    public void createIntroScene(OnCreateSceneCallback pOnCreateSceneCallback) {
        introScene = new IntroScene();
        SceneManager.getInstance().setScene(introScene);
        pOnCreateSceneCallback.onCreateSceneFinished(introScene);
    }
    
    private void disposeIntroScene() {
        ResourceManager.getInstance().unloadIntro();
        introScene.disposeScene();
        introScene = null;
    }
    
    public void createMenuScene() {
    	ResourceManager.getInstance().loadMenu();
    	ResourceManager.getInstance().loadFonts();
        menuScene = new MainMenuScene();
        SceneManager.getInstance().setScene(menuScene);
        if(!GameActivity.getIniciado()) disposeIntroScene();
    }
    
    private void disposeMenuScene() {
    	menuScene.setIgnoreUpdate(true);
        ResourceManager.getInstance().unloadMenu();
        menuScene.disposeScene();
        menuScene = null;
    }
    
    public void loadMenuScene() {
    	opTask = OpAsyncTask.LOAD_MENU;
    	new ExecuteChange().execute();
    }
    
    public void loadMenufromPause(){
        createMenuScene();
    	disposeGameScene();
    }
    
    public void loadMenufromSettings(){
        createMenuScene();
    	disposeSettingsScene();
    }
    
    public void loadMenufromSelector(){
        createMenuScene();
    	disposeSelectorScene();
    	
    } 
    
    private void createGameScene() {
    	ResourceManager.getInstance().loadGameTextures();
    	ResourceManager.getInstance().loadGamePause();
        gameScene = new GameScene();
        ((GameScene) gameScene).setGameScene();
        SceneManager.getInstance().setScene(gameScene);
    }
    
    private void disposeGameScene() {
    	gameScene.setIgnoreUpdate(true);
    	ResourceManager.getInstance().unloadGameTextures();
    	ResourceManager.getInstance().unloadGamePause();
        gameScene.disposeScene();
        gameScene = null;
    }
    
    public void loadGameScene(){
    	opTask = OpAsyncTask.LOAD_GAME;
    	new ExecuteChange().execute();
    }
    
    public void loadGameSceneFromSelector(){
    	opTask = OpAsyncTask.LOAD_GAME_SELECTOR;
    	new ExecuteChange().execute();
    }
    
    public void restartGameScene() {
    	opTask = OpAsyncTask.RESTART_GAME;
    	new ExecuteChange().execute();
    }
    
    public void createLoadScene() {
    	ResourceManager.getInstance().loadFonts();
    	loadScene = new LoadScene();
    	SceneManager.getInstance().setScene(loadScene);
    }
    
    private void disposeLoadScene() {
    	ResourceManager.getInstance().unloadFonts();
    	loadScene.disposeScene();
    	loadScene = null;
    }
    
    public void createSettingsScene() {
    	ResourceManager.getInstance().loadSettings();
    	settingsScene = new SettingsScene();
        SceneManager.getInstance().setScene(settingsScene);
    	disposeMenuScene();
    }
    
    public void disposeSettingsScene() {
        ResourceManager.getInstance().unloadSettings();
        settingsScene.disposeScene();
        settingsScene = null;
    }
    
    public void createSelectorScene() {
    	ResourceManager.getInstance().loadSelector();
    	selectorScene = new SelectorScene();
    	((SelectorScene) selectorScene).initScene();
        SceneManager.getInstance().setScene(selectorScene);
    	disposeMenuScene();
    }
    
    public void disposeSelectorScene() {
        ResourceManager.getInstance().unloadSelector();
        selectorScene.disposeScene();
        selectorScene = null;
    }
    
    public void returnSettingsScene(OnCreateSceneCallback pOnCreateSceneCallback) {
    	settingsScene = new SettingsScene();
        SceneManager.getInstance().setScene(settingsScene);
        pOnCreateSceneCallback.onCreateSceneFinished(settingsScene);
    }
    
    private class ExecuteChange extends AsyncTask<Void, Boolean, Boolean> {
    		@Override
			protected Boolean doInBackground(Void... params) {
    			switch(opTask){
    			case LOAD_GAME:
    				disposeMenuScene();
    				createGameScene();
    				break;
    			case RESTART_GAME:
    				disposeGameScene();
    				createGameScene();
    	            break;
    			case LOAD_MENU:
    				disposeGameScene();
                    createMenuScene();
                    break;
    			case LOAD_GAME_SELECTOR:
    				disposeSelectorScene();
    				createGameScene();
    				break;
    			default:
    				break;
    			}
    			opTask = OpAsyncTask.DEFAULT;
    			return true;
			}
    		
            @Override
            protected void onPostExecute(Boolean result) {
            	super.onPostExecute(result);
            	disposeLoadScene();
            }
			
			@Override
	        protected void onPreExecute() {
				super.onPreExecute();
				createLoadScene();
	        }
            
    }
    
}