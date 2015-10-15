package unb.cic.poo.game2d.scenes;

import java.util.ArrayList;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;

import unb.cic.poo.game2d.CommonEnemy;
import unb.cic.poo.game2d.Enemy;
import unb.cic.poo.game2d.FreezedShootingEnemy;
import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ParallaxApplication;
import unb.cic.poo.game2d.Player;
import unb.cic.poo.game2d.ResourceManager;
import unb.cic.poo.game2d.VerticalMovementEnemy;
import unb.cic.poo.game2d.scenes.SceneManager.SceneType;

public class GameScene extends BaseScene {
	private Sprite end;
    //private HUD gameHUD;
    //private Text scoreText;
	
    public GameScene() {
		createScene();		
	}
    
    @Override
    public void createScene() {
		createBackground();
        createHUD();
				
		// Fazer com que a classe GameManager seja um listener da Scene do jogo.
		this.setOnSceneTouchListener(GameManager.getInstance());
		
		// Configurando atributos de GameManager
		GameManager.getInstance().setPlayer(new Player());
		GameManager.getInstance().setGameScene(this);
		GameManager.getInstance().setEnemies(new ArrayList<Enemy>());
		
		// Cria os inimigos para teste
		// Valores alinhados: dividir por 5, 2 e 1.25
		/*GameManager.getInstance().getEnemies().add(new FreezedShootingEnemy(GameManager.getInstance().getGameCamera().getWidth(),
				(float) (GameActivity.CAMERA_HEIGHT/3.33), (GameManager.getInstance().getGameCamera().getWidth()-400f)));
		GameManager.getInstance().getEnemies().add(new VerticalMovementEnemy(GameManager.getInstance().getGameCamera().getWidth(),
				(float) (GameActivity.CAMERA_HEIGHT/1.67), (GameManager.getInstance().getGameCamera().getWidth()-250f)));*/
		GameManager.getInstance().getEnemies().add(new CommonEnemy(GameManager.getInstance().getGameCamera().getWidth(),
				(float) (GameActivity.CAMERA_HEIGHT/3.33)));
		GameManager.getInstance().getEnemies().add(new CommonEnemy(GameManager.getInstance().getGameCamera().getWidth(),
				(float) (GameActivity.CAMERA_HEIGHT/1.67)));
		GameManager.getInstance().getEnemies().add(new CommonEnemy(GameManager.getInstance().getGameCamera().getWidth(),
				(float) (GameActivity.CAMERA_HEIGHT/1.11)));
		
		//Insere o Player na Scene.
		this.attachChild(GameManager.getInstance().getPlayer());
		//Insere inimigos na Scene
		for(Enemy enemy: GameManager.getInstance().getEnemies()){
			this.attachChild(enemy);
		}
    }

    @Override
    public void onBackKeyPressed() {
    	SceneManager.getInstance().loadMenuScene(engine);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene() {
    	//camera.setHUD(null);
        this.detachSelf();
        this.dispose();
    }
    
    private void createBackground() {
    	final ParallaxApplication parallaxLayer = new ParallaxApplication(GameActivity.mCamera, true);
		parallaxLayer.setParallaxChangePerSecond(3);
		parallaxLayer.setParallaxScrollFactor(1);
		final Sprite backSprite = new Sprite(0, 0, ResourceManager.backgroundTextureRegion, engine.getVertexBufferObjectManager());
		parallaxLayer.attachParallaxEntity(new ParallaxApplication.ParallaxEntity(-15, backSprite, false));
		
		this.attachChild(parallaxLayer);
    }

    private void createHUD() {
    	/*int score = 0;
        gameHUD = new HUD();
        
        // CREATE SCORE TEXT
        scoreText = new Text(20, 420, resourceManager.font, "Score: 0", new TextOptions(HorizontalAlign.LEFT), vbom);
        scoreText.setSkewCenter(0, 0);    
        scoreText.setText("Score: " + score);
        gameHUD.attachChild(scoreText);
        
        camera.setHUD(gameHUD);*/
    }
    
    public void gameOver() {
    	this.setIgnoreUpdate(true);
    	
    	end = new Sprite(0, 0, resourceManager.gameoverTextureRegion, vbom);
    	end.setPosition((camera.getWidth()- end.getWidth())/2, (camera.getHeight() - end.getHeight())/2);
    	this.attachChild(end);
    	
    	engine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                engine.unregisterUpdateHandler(pTimerHandler);
                sceneManager.loadMenuScene(engine);
            }
    	}));
    }
}