package unb.cic.poo.game2d.scenes;

import java.util.ArrayList;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

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
	private static Sprite lifebar;
	private static Sprite lifebarmold;
	private static TiledSprite switcher;
	private static TiledSprite pause;
	private static Sprite back;
	private static float tamOrigLB; private static float adjLB;
	private static int change;
	private static boolean stop; private static boolean endGame;
	private static final int posX = 120; private static final int deltaX = 20;
	private static final int posY = 620;
	private static float lifebarHeight; private static float varHeight = 5/3;
		
	
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
		
		//Insere o Player na Scene.
		this.attachChild(GameManager.getInstance().getPlayer());
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
    	
		endGame = false; stop = false;
    	
    	lifebarmold = new Sprite(GameActivity.CAMERA_WIDTH/3, 0f, ResourceManager.lifemoldTextureRegion,
    			GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
    	lifebarmold.setPosition((camera.getWidth() - lifebarmold.getWidth()) - posX - 2*(varHeight*lifebarmold.getHeight() + deltaX), 
    			(camera.getHeight() - lifebarmold.getHeight()) - posY);
    	
    	lifebarHeight = lifebarmold.getHeight();
    	
    	lifebar = new Sprite(GameActivity.CAMERA_WIDTH/3, 0f, ResourceManager.lifeTextureRegion,
    			GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
    	lifebar.setPosition((camera.getWidth() - lifebar.getWidth()) - posX - 2*(varHeight*lifebarHeight + deltaX), 
    			(camera.getHeight() - lifebar.getHeight()) - posY);
    	
    	this.attachChild(lifebarmold); this.attachChild(lifebar);
    	
    	tamOrigLB = (float) lifebar.getWidth();
    	adjLB = (float) ((tamOrigLB/24)/Player.DEFAULT_PLAYER_LIFE);
    	
    	switcher = new TiledSprite(GameActivity.CAMERA_WIDTH/3, 0f, ResourceManager.switchTextureRegion,
    			GameManager.getInstance().getGameEngine().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()  && endGame == false) {
					change = GameManager.getInstance().getPlayer().changeBullet();
					if(change == 0){
						switcher.setCurrentTileIndex(0);
					} else {
						switcher.setCurrentTileIndex(1);
					}
				}
				return true;
			}
		};
		switcher.setWidth(varHeight*lifebarHeight+30); switcher.setHeight(varHeight*lifebarHeight+30);
		switcher.setPosition((camera.getWidth()- switcher.getWidth()) - posX - varHeight*lifebarHeight - deltaX + 10, 
    			(camera.getHeight() - switcher.getHeight()) - posY);
	
		back = new Sprite(GameActivity.CAMERA_WIDTH/3, 0f, ResourceManager.backTextureRegion,
    			GameManager.getInstance().getGameEngine().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown() && endGame == false) {
					this.setIgnoreUpdate(true);
			    	sceneManager.loadMenuScene(engine);
				}
				return true;
			}
		};
		back.setWidth(varHeight*lifebarHeight+30); back.setHeight(varHeight*lifebarHeight+30);
		back.setPosition((camera.getWidth()- back.getWidth()) - posX + posX, 
    			(camera.getHeight() - back.getHeight()) - posY);
		
		pause = new PauseButton((float)(GameActivity.CAMERA_WIDTH/3), 0f, ResourceManager.pauseTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager(), this);
		
				/*new TiledSprite(GameActivity.CAMERA_WIDTH/3, 0f, ResourceManager.pauseTextureRegion,
    			GameManager.getInstance().getGameEngine().getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if (pSceneTouchEvent.isActionDown()) {
					if(stop == false){
						pause.setCurrentTileIndex(1);
						pause.setWidth(300); pause.setHeight(300);
						pause.setPosition((camera.getWidth()/2)-(pause.getWidth()/2), (camera.getHeight()/2)-(pause.getHeight()/2));
										
						setIgnoreUpdate(true);
						//mChildScene.setIgnoreUpdate(true);
						//mChildScene.setChildScene(mPauseScene , false, true, true);
						
						//engine.stop();
						stop = true;
						
					} else {
						pause.setCurrentTileIndex(0);
						pause.setWidth(varHeight*lifebarHeight+30); pause.setHeight(varHeight*lifebarHeight+30);
						pause.setPosition((camera.getWidth() - pause.getWidth()) - posX - varHeight*lifebarHeight - deltaX + 100, 
				    			(camera.getHeight() - pause.getHeight()) - posY);				
						
						setIgnoreUpdate(false);
						//this.setChildrenIgnoreUpdate(false);
						
						//mChildScene.setIgnoreUpdate(false);
						
						//engine.start();
						stop = false;
						
					}
				}
				return true;
			}
		};*/
		pause.setWidth(varHeight*lifebarHeight+30); pause.setHeight(varHeight*lifebarHeight+30);
		pause.setPosition((camera.getWidth() - pause.getWidth()) - posX - varHeight*lifebarHeight - deltaX + 100, 
    			(camera.getHeight() - pause.getHeight()) - posY);
		
		this.registerTouchArea(switcher); this.registerTouchArea(back); this.registerTouchArea(pause);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.attachChild(switcher); this.attachChild(back); this.attachChild(pause);
    }
    
    private void disposeHUD(){
    	this.setTouchAreaBindingOnActionDownEnabled(false);
    	this.unregisterTouchArea(switcher); this.unregisterTouchArea(back); this.unregisterTouchArea(pause);
    	this.detachChild(lifebarmold); this.detachChild(lifebar);
    	this.detachChild(switcher); this.detachChild(back); this.detachChild(pause);
    }
    
    public static void setLifeBar(float lifewidth) {
    	lifebar.setWidth(lifewidth);
    	lifebar.setPosition((camera.getWidth() - tamOrigLB) - posX - 2*(varHeight*lifebarHeight + deltaX) + adjLB, 
    			(camera.getHeight() - lifebar.getHeight()) - posY);
    	adjLB += (float) ((tamOrigLB/24)/Player.DEFAULT_PLAYER_LIFE);
    }
    
    public static Sprite getLifeBar() {
    	return lifebar;
    }
    
    public void gameOver() {
    	this.setIgnoreUpdate(true);
    	disposeHUD();
    	endGame = true; stop = true;
    	
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
    
    public void gameFinished() {
    	this.setIgnoreUpdate(true);
    	disposeHUD();
    	endGame = true; stop = true;
    	
    	end = new Sprite(0, 0, resourceManager.winnerTextureRegion, vbom);
    	end.setPosition((camera.getWidth()- end.getWidth())/2, (camera.getHeight() - end.getHeight())/2);
    	this.attachChild(end);
    	
    	engine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                engine.unregisterUpdateHandler(pTimerHandler);
                sceneManager.loadMenuScene(engine);
            }
    	}));
    }
    
    public void setGameScene(){
		// Cria os inimigos para teste
		// Valores alinhados: dividir por 5, 2 e 1.25 // 2, 1.67 e 1.25 
    	GameManager.getInstance().getEnemies().add(new FreezedShootingEnemy(GameManager.getInstance().getGameCamera().getWidth(),
				(float) (GameActivity.CAMERA_HEIGHT/3.33), (GameManager.getInstance().getGameCamera().getWidth()-400f)));
    	GameManager.getInstance().getEnemies().add(new VerticalMovementEnemy(GameManager.getInstance().getGameCamera().getWidth(),
   			 (float) (GameActivity.CAMERA_HEIGHT/1.67), (GameManager.getInstance().getGameCamera().getWidth()-250f)));
		GameManager.getInstance().getEnemies().add(new CommonEnemy(GameManager.getInstance().getGameCamera().getWidth(),
				(float) (GameActivity.CAMERA_HEIGHT/1.25)));
   	 	for(Enemy enemy: GameManager.getInstance().getEnemies()){
			this.attachChild(enemy);
		}
   	 	
   	 	ResourceManager.mMusic.play();
		ResourceManager.mMusic.setVolume(1);
	}
    
    public class PauseButton extends TiledSprite{
    	private GameScene scene;

    	public GameScene getScene() {
    		return scene;
    	}

    	public void setScene(GameScene scene) {
    		this.scene = scene;
    	}

    	public PauseButton(float pX, float pY,
    			ITiledTextureRegion pTiledTextureRegion,
    			VertexBufferObjectManager pTiledSpriteVertexBufferObject, GameScene scene) {
    		super(pX, pY, pTiledTextureRegion, pTiledSpriteVertexBufferObject);
    		this.scene = scene;
    	}
    	
    	@Override
    	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
    		if (pSceneTouchEvent.isActionDown() && endGame == false) {
    			/*O que acontece quando clica no botão de pause durante o jogo*/
    			if(stop == false){
    				stop = true;				
    				scene.setIgnoreUpdate(true);  				
    			} 
    			/*O que acontece quando clica no botão de pause com o jogo pausado*/
    			else {	
    				stop = false;
    				scene.setIgnoreUpdate(false); 				
    			}
    		}
    		return true;
    	}
    }
    
    public static boolean getStop(){
    	return stop;
    }

}