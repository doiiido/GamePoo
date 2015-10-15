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

import unb.cic.poo.game2d.scenes.SceneManager;


public class GameActivity extends BaseGameActivity {
	
	public static final int CAMERA_WIDTH = 1280;
    public static final int CAMERA_HEIGHT = 720;
    public static Camera mCamera;
    
    private ResourceManager mResourceManager;
    private SceneManager mSceneManager;
    
    // Caso queiram fazer padding nas bordas, ao inv�s de ajustar a tela: RatioResolutionPolicy
    FillResolutionPolicy crp;
    
	@Override
	public EngineOptions onCreateEngineOptions() {
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		
		crp = new FillResolutionPolicy();
		EngineOptions options = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
				crp, mCamera);
        
        options.getTouchOptions().setNeedsMultiTouch(true);
        options.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        
        return options;
	}
	
	public Engine onCreateEngine(EngineOptions pEngineOptions) {
		//Cria uma engine com um step de simulação de 60 steps por segundo.
		return new FixedStepEngine(pEngineOptions, 60);
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		mResourceManager = ResourceManager.getInstance(); mResourceManager.prepare(this);
        mSceneManager = SceneManager.getInstance(); mSceneManager.prepare(this, mEngine, mCamera);
        
        mResourceManager.loadIntro();
        pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		GameManager.getInstance().setGameEngine(this.mEngine);
		GameManager.getInstance().setGameCamera(this.mEngine.getCamera());
		
		mSceneManager.createIntroScene(pOnCreateSceneCallback);
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
			    mEngine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
			            public void onTimePassed(final TimerHandler pTimerHandler) {
			                mEngine.unregisterUpdateHandler(pTimerHandler);
			                mSceneManager.createMenuScene();
			            }
			    }));
		
		// Populate the Scene here
		// and then provide the callback
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
}