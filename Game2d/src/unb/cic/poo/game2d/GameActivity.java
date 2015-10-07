package unb.cic.poo.game2d;

import java.util.ArrayList;

import org.andengine.engine.Engine;
import org.andengine.engine.FixedStepEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.ui.activity.BaseGameActivity;


public class GameActivity extends BaseGameActivity {
	
	static final int CAMERA_WIDTH = 1280;
    static final int CAMERA_HEIGHT = 720;
    Camera mCamera;
    
    // Caso queiram fazer padding nas bordas, ao invés de ajustar a tela: RatioResolutionPolicy
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
		//Cria uma engine com um step de simulaÃ§Ã£o de 60 steps por segundo.
		return new FixedStepEngine(pEngineOptions, 60);
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		ResourceManager.getInstance().loadGameTextures(mEngine, this);
		// and then provide the callback
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		// Create the Scene
		Scene mScene = new Scene();

		// Fundo estático
		//mScene.setBackground(new Background(0,0,0));
		//Sprite sprImage = new Sprite(0, 0, ResourceManager.backgroundTextureRegion, this.getVertexBufferObjectManager());
		//mScene.attachChild(sprImage);
		
		final ParallaxApplication parallaxLayer = new ParallaxApplication(mCamera, true);
		parallaxLayer.setParallaxChangePerSecond(3);
		parallaxLayer.setParallaxScrollFactor(1);
		final Sprite backSprite = new Sprite(0, 0, ResourceManager.backgroundTextureRegion, this.getVertexBufferObjectManager());
		parallaxLayer.attachParallaxEntity(new ParallaxApplication.ParallaxEntity(-15, backSprite, false));
		
		mScene.attachChild(parallaxLayer);
		
		//Fazer com que a classe GameManager seja um listener da Scene do jogo.
		mScene.setOnSceneTouchListener(GameManager.getInstance());
		
		//Configurando atributos de GameManager
		GameManager.getInstance().setGameEngine(this.mEngine);
		GameManager.getInstance().setGameCamera(this.mEngine.getCamera());
		GameManager.getInstance().setPlayer(new Player());
		GameManager.getInstance().setGameScene(mScene);
		GameManager.getInstance().setEnemies(new ArrayList<Enemy>());
		//Cria os inimigos para teste
		//Valores alinhados: dividir por 5, 2 e 1.25
		GameManager.getInstance().getEnemies().add(new CommonEnemy(GameManager.getInstance().getGameCamera().getWidth(),
				(float) (CAMERA_HEIGHT/3.33)));
		GameManager.getInstance().getEnemies().add(new CommonEnemy(GameManager.getInstance().getGameCamera().getWidth(),
				(float) (CAMERA_HEIGHT/1.67)));
		GameManager.getInstance().getEnemies().add(new CommonEnemy(GameManager.getInstance().getGameCamera().getWidth(),
				(float) (CAMERA_HEIGHT/1.11)));
		
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