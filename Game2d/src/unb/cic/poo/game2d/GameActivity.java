package unb.cic.poo.game2d;

import java.io.IOException;

import org.andengine.engine.Engine;
import org.andengine.engine.FixedStepEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import com.parse.ParseAnalytics;
import com.facebook.FacebookSdk;

import android.os.Bundle;
import android.view.KeyEvent;
import unb.cic.poo.game2d.scenes.SceneManager;
import unb.cic.poo.game2d.scenes.SelectorScene;


public class GameActivity extends BaseGameActivity {
	
	public static final int CAMERA_WIDTH = 1280;
    public static final int CAMERA_HEIGHT = 720;
    public static Camera mCamera;
        
    private static boolean iniciado = false;

	private ResourceManager mResourceManager;
    private SceneManager mSceneManager;
    
    // Caso queiram fazer padding nas bordas, ao inves de ajustar a tela: RatioResolutionPolicy
    FillResolutionPolicy crp;
    
    public static boolean getIniciado() {
		return iniciado;
	}

	public static void setIniciado(boolean iniciado) {
		GameActivity.iniciado = iniciado;
	}
	
	@Override
	protected void onCreate(final Bundle pSavedInstanceState) {
		super.onCreate(pSavedInstanceState);
		ParseAnalytics.trackAppOpenedInBackground(getIntent());
		FacebookSdk.sdkInitialize(getApplicationContext()); // O app ja vai ter que estar publicado
	}
    
	@Override
	public EngineOptions onCreateEngineOptions() {
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		
		crp = new FillResolutionPolicy();
		EngineOptions options = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
				crp, mCamera);
        
        options.getTouchOptions().setNeedsMultiTouch(true);
        options.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        options.getAudioOptions().setNeedsMusic(true);
        options.getAudioOptions().setNeedsSound(true);
        return options;
    }
	
	public Engine onCreateEngine(EngineOptions pEngineOptions) {	
		//Cria uma engine com um step de simulacao de 60 steps por segundo.
		return new FixedStepEngine(pEngineOptions, 60);
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		mResourceManager = ResourceManager.getInstance(); mResourceManager.prepare(this);
        mSceneManager = SceneManager.getInstance(); mSceneManager.prepare(this, mEngine);
          
        mResourceManager.loadGameResource(mEngine, this);
        
        if(iniciado != true){
        	mResourceManager.loadIntro();
        }
        else{
        	mResourceManager.loadSettings();
        }
        
        pOnCreateResourcesCallback.onCreateResourcesFinished(); 
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		GameManager.getInstance().setGameEngine(this.mEngine);
		GameManager.getInstance().setGameCamera(this.mEngine.getCamera());
		
		if(iniciado != true){
			mSceneManager.createIntroScene(pOnCreateSceneCallback);
			
		}
		else{
			//Ver como o som está sendo chamado. Associar as cenas
			mSceneManager.returnSettingsScene(pOnCreateSceneCallback);
		}
						
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
				if(iniciado != true){
				    mEngine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
				            public void onTimePassed(final TimerHandler pTimerHandler) {
				                mEngine.unregisterUpdateHandler(pTimerHandler);
				                mSceneManager.createMenuScene();
				                GameActivity.setIniciado(true);
				            }
				    }));
				}
		
		// Populate the Scene here and then provide the callback
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
	
	@Override
	public synchronized void onResumeGame() {
	    if (this.mEngine != null)
	        super.onResumeGame();
	}
	
	/*@Override
    public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
            if(pKeyCode == KeyEvent.KEYCODE_MENU && pEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if(!this.mEngine.isRunning()) {
                        this.mEngine.start();
                    	
                        //this.mMainScene.setChildScene(this.mPauseScene, false, true, true);
                        //this.mEngine.stop();
                    } 
                    return true;
            } else {
                    return super.onKeyDown(pKeyCode, pEvent);
            }
<<<<<<< HEAD
    }
	
	public Engine getEngine(){
		return this.mEngine;
=======
    }*/
	
	@Override
	public void onBackPressed() {
		if (mSceneManager.getCurrentScene() != null) {
			mSceneManager.getCurrentScene().onBackKeyPressed();
		}
//>>>>>>> f9b9b02b7090ba5f174df05c29c3b18047ed4dac
	}
}