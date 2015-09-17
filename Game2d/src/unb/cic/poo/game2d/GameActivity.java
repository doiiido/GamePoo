package unb.cic.poo.game2d;

import java.util.ArrayList;

import org.andengine.engine.Engine;
import org.andengine.engine.FixedStepEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.ui.activity.BaseGameActivity;


public class GameActivity extends BaseGameActivity {
	
	static final int CAMERA_WIDTH = 850;
    static final int CAMERA_HEIGHT = 500;
    
	@Override
	public EngineOptions onCreateEngineOptions() {
		Camera mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		
        EngineOptions options = new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR,
            new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
        
        options.getTouchOptions().setNeedsMultiTouch(true);
        
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
		// Load Resources here
		// and then provide the callback
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		// Create the Scene
		Scene mScene = new Scene();
		//Scene com o Background preto ainda...
		mScene.setBackground(new Background(0, 0, 0));
		
		//Fazer com que a classe GameManager seja um listener da Scene do jogo.
		mScene.setOnSceneTouchListener(GameManager.getInstance());
		
		//Configurando atributos de GameManager
		GameManager.getInstance().setGameEngine(this.mEngine);
		GameManager.getInstance().setGameCamera(this.mEngine.getCamera());
		GameManager.getInstance().setPlayer(new Player());
		GameManager.getInstance().setGameScene(mScene);
		GameManager.getInstance().setEnemies(new ArrayList<Enemy>());
		//Cria os inimigos para teste
		GameManager.getInstance().getEnemies().add(new CommonEnemy(GameManager.getInstance().getGameCamera().getWidth(), 300));
		GameManager.getInstance().getEnemies().add(new CommonEnemy(GameManager.getInstance().getGameCamera().getWidth(), 150));
		GameManager.getInstance().getEnemies().add(new CommonEnemy(GameManager.getInstance().getGameCamera().getWidth(), 450));
		
		// and then provide the callback
		//this callback requires a Scene parameter
		pOnCreateSceneCallback.onCreateSceneFinished(mScene);
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		//Insere o Player na Scene.
		pScene.attachChild(GameManager.getInstance().getPlayer());
		//Insere inimigos na Scene
		for(Enemy enemy: GameManager.getInstance().getEnemies()){
			pScene.attachChild(enemy);
		}
		// Populate the Scene here
		// and then provide the callback
		pOnPopulateSceneCallback.onPopulateSceneFinished();
		
	}

}