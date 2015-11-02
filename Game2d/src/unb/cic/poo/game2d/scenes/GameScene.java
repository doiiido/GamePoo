package unb.cic.poo.game2d.scenes;

import java.util.ArrayList;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
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

public class GameScene extends BaseScene implements IOnMenuItemClickListener{
	private Sprite end;
	MenuScene mPauseScene;
	
	private static Sprite lifebar;
	private static Sprite lifebarmold;
	private static TiledSprite switcher;
	private static TiledSprite pause;
	private static Sprite backgroundPause;
	
	private static float tamOrigLB; private static float adjLB;
	private static int change;
	private static boolean stop; private static boolean endGame;
	private static final int posX = 10; private static final int deltaX = 15;
	private static final int posY = 600;
	private static float lifebarHeight; private static float varHeight = 2;
	
	private final int PAUSE_BACK = 0; private final int PAUSE_RESTART = 1;
	private final int PAUSE_MENU = 2;
	private static final int pausePosY = 30; private static final int butPosY = 100;
	
	
    public GameScene() {
		createScene();		
	}
    
    @Override
    public void createScene() {
		createBackground();
        createHUD();
        createPauseScene();
				
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
    	mPauseScene.detachSelf();
    	mPauseScene.dispose();
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
		
		/* Para determinar a posição, sendo N_ELEM o número de elementos que estão posicionados a direita deste: 
		SPRITE.setPosition((camera.getWidth()- SPRITE.getWidth()) - posX - N_ELEM*(varHeight*lifebarHeight + deltaX), 
    			(camera.getHeight() - SPRITE.getHeight()) - posY);
    	- Se desejar levar o HUD à esquerda, aumentar o valor de posX
    	- Se desejar levar o HUD para baixo, aumentar o valor de posY
    	- Se desejar aumentar a distância entre os elementos do HUD, aumentar o valor de deltaX
    	- Se desejar aumentar o tamanho dos botões do HUD, aumentar o valor de varHeight (segue como padrão o tamanho
    	da altura da barra de vida - 50)*/
    	
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
		switcher.setWidth(varHeight*lifebarHeight); switcher.setHeight(varHeight*lifebarHeight);
		switcher.setPosition((camera.getWidth()- switcher.getWidth()) - posX - (varHeight*lifebarHeight + deltaX), 
    			(camera.getHeight() - switcher.getHeight()) - posY);
		
		pause = new PauseButton((float)(GameActivity.CAMERA_WIDTH/3), 0f, ResourceManager.pauseTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager(), this);		
		pause.setWidth(varHeight*lifebarHeight); pause.setHeight(varHeight*lifebarHeight);
		pause.setPosition((camera.getWidth() - pause.getWidth()) - posX, 
    			(camera.getHeight() - pause.getHeight()) - posY);
		
		this.registerTouchArea(switcher); this.registerTouchArea(pause);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.attachChild(switcher); this.attachChild(pause);
    }
    
    private void disposeHUD(){
    	this.setTouchAreaBindingOnActionDownEnabled(false);
    	this.unregisterTouchArea(switcher); this.unregisterTouchArea(pause);
    	this.detachChild(lifebarmold); this.detachChild(lifebar);
    	this.detachChild(switcher); this.detachChild(pause);
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
    				setChildScene(mPauseScene);
    			} 
    			/*O que acontece quando clica no botão de pause com o jogo pausado*/
    			else {	
    				stop = false;
    				scene.setIgnoreUpdate(false);
    				clearChildScene();
    			}
    		}
    		return true;
    	}
    }
    
    public static boolean getStop(){
    	return stop;
    }
    
    public void createPauseScene() {
    	mPauseScene = new MenuScene(camera);
    	
    	backgroundPause = new Sprite(0, 0, resourceManager.stopBackgroundTextureRegion, vbom);
    	mPauseScene.setPosition((camera.getWidth() - backgroundPause.getWidth())/2,
    			(camera.getHeight() - backgroundPause.getHeight())/2 + pausePosY);
    	mPauseScene.attachChild(backgroundPause);
	    
	    final IMenuItem backMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem
	    		(PAUSE_BACK, resourceManager.backTextureRegion, vbom), 1.2f, 1);
	    final IMenuItem restartMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem
	    		(PAUSE_RESTART, resourceManager.restartTextureRegion, vbom), 1.2f, 1);
	    final IMenuItem menuMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem
	    		(PAUSE_MENU, resourceManager.menuTextureRegion, vbom), 1.2f, 1);
	    
	    mPauseScene.addMenuItem(backMenuItem); mPauseScene.addMenuItem(restartMenuItem);
	    mPauseScene.addMenuItem(menuMenuItem);
	    mPauseScene.setBackgroundEnabled(false);
	    mPauseScene.setOnMenuItemClickListener(this);
	    
	    // Garantir que todos os botões tenham o mesmo tamanho
	    
	    backMenuItem.setPosition((float) ((backgroundPause.getWidth() - backMenuItem.getWidth())/2 + 1.5*backMenuItem.getWidth()),
	    		backMenuItem.getHeight() + butPosY);
	    restartMenuItem.setPosition((backgroundPause.getWidth() - backMenuItem.getWidth())/2,
	    		backMenuItem.getHeight() + butPosY);
	    menuMenuItem.setPosition((float) ((backgroundPause.getWidth() - backMenuItem.getWidth())/2 - 1.5*backMenuItem.getWidth()),
	    		backMenuItem.getHeight() + butPosY);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
	        case PAUSE_BACK:
	        	stop = false;
				this.setIgnoreUpdate(false);
				clearChildScene();
	            return true;
	        case PAUSE_RESTART:
	        	sceneManager.restartGameScene(engine);
	            return true;
	        case PAUSE_MENU:
	        	sceneManager.loadMenuScene(engine);
	            return true;
	        default:
	            return false;
		}
	}

}