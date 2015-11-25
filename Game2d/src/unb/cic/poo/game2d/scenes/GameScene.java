package unb.cic.poo.game2d.scenes;

import java.util.ArrayList;
import java.util.LinkedList;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
<<<<<<< HEAD
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
=======
import org.andengine.entity.Entity;
>>>>>>> origin/master
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
import org.andengine.ui.activity.BaseGameActivity;

import android.util.Log;
import unb.cic.poo.game2d.enemies.Enemy;
import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ParallaxApplication;
import unb.cic.poo.game2d.Player;
import unb.cic.poo.game2d.ResourceManager;
import unb.cic.poo.game2d.fases.Fase;
import unb.cic.poo.game2d.fases.Fase1;
import unb.cic.poo.game2d.fases.FaseManager;
import unb.cic.poo.game2d.scenes.SceneManager.SceneType;

public class GameScene extends BaseScene implements IOnMenuItemClickListener, IOnSceneTouchListener{
	//---------------------------------------------
    // SCENES AND MANAGERS
    //---------------------------------------------
	
	MenuScene mPauseScene;
	private FaseManager faseManager;
	
	//---------------------------------------------
    // ENTITIES
    //---------------------------------------------
	
	private ParallaxApplication parallaxLayer;
	private Sprite backSprite;
	private static Sprite lifebar;
	private static Sprite lifebarmold;
	private static TiledSprite switcher;
	private static TiledSprite pause;
	private static Sprite backgroundPause;
	private SpriteMenuItem backMenu;
	private SpriteMenuItem restartMenu;
	private SpriteMenuItem menuMenu;
	private Sprite end;
	
	//---------------------------------------------
    // CONSTANTS
    //---------------------------------------------
	
	private final int PAUSE_BACK = 0; private final int PAUSE_RESTART = 1;
	private final int PAUSE_MENU = 2;
	private static final int pausePosY = 30; private static final int butPosY = 100;
	private static final int posX = 10; private static final int deltaX = 15;
	private static final int posY = 600; private static float varHeight = 2;
	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	private static float tamOrigLB; private static float adjLB;
	private static int change;
	private static boolean stop; private static boolean endGame;
	private static float lifebarHeight;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
    public GameScene() {
		createScene();		
	}
    
    //---------------------------------------------
    // ABSTRACTION
    //---------------------------------------------
    
    @Override
    public void createScene() {
    	endGame = false; stop = false;
    	createBackground();
		createHUD();
        createPauseScene();
		
		// Configurando atributos de GameManager
		GameManager.getInstance().setPlayer(new Player());
		GameManager.getInstance().setEnemies(new ArrayList<Enemy>());
		GameManager.getInstance().setGameScene(this);
		
		// Fazer com que a GameScene seja um listener para toques na tela
		//this.setOnSceneTouchListener pois é pra setar o listener nesta scene
		//argumento (this) porque eh a propria scene que tem o metodo da interface implementado (onSceneTouchEvent())
		this.setOnSceneTouchListener(this);
		
		//Insere o Player na Scene.
		this.attachChild(GameManager.getInstance().getPlayer()); entitiesList.add(GameManager.getInstance().getPlayer());
    }
    
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		
		//Chama o handle para o player
		GameManager.getInstance().getPlayer().handleTouchEvent(pSceneTouchEvent);

		//Chama o handle para os inimigos
		for(Enemy enemy:GameManager.getInstance().getEnemies()){
			enemy.handleTouchEvent(pSceneTouchEvent);
		}
		return false;
	}

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_GAME;
    }
    
    public void onBackKeyPressed() {
    	pausePressed();
    }

    @Override
    public void disposeScene() {
    	if (stop == true) {
    		((BaseGameActivity) activity).runOnUpdateThread(new Runnable() {
    		    @Override
    		    public void run() {
    		    	clearChildScene();
    		    	mPauseScene.detachSelf();
    		    	mPauseScene.dispose();
    		    }
    		});
    	}
    	super.disposeScene();
    }
    
    @Override
    public void cleanEntities() {
    	super.cleanEntities();
    	for (Enemy entity: GameManager.getInstance().getEnemies()) {
    		entity.clearEntityModifiers();
    		entity.clearUpdateHandlers();
    		entity.detachSelf();
    		
    		if (!entity.isDisposed()) {
    			entity.dispose();
    		}
    	}
    	// Ver detach das balas e dos itens, incluí-los em uma lista
    }
    
    //---------------------------------------------
    // GETTERS AND SETTERS
    //---------------------------------------------
    
    public static Sprite getLifeBar() {
    	return lifebar;
    }
    
    public static void setLifeBar(float lifewidth) {
    	lifebar.setWidth(lifewidth);
    	lifebar.setPosition((camera.getWidth() - tamOrigLB) - posX - 2*(varHeight*lifebarHeight + deltaX) + adjLB, 
    			(camera.getHeight() - lifebar.getHeight()) - posY);
    	adjLB += (float) ((tamOrigLB/24)/Player.DEFAULT_PLAYER_LIFE);
    }
    
    public static boolean getGameStop(){
    	if (endGame == false && stop == false){
    		return false;
    	}
    	else{
    		return true;
    	}
    }
    
    //---------------------------------------------
    // METHODS
    //---------------------------------------------
    
    private void createBackground() {
    	parallaxLayer = new ParallaxApplication(GameActivity.mCamera, true);
		parallaxLayer.setParallaxChangePerSecond(3);
		parallaxLayer.setParallaxScrollFactor(1);
		backSprite = new Sprite(0, 0, ResourceManager.backgroundTextureRegion, engine.getVertexBufferObjectManager());
		parallaxLayer.attachParallaxEntity(new ParallaxApplication.ParallaxEntity(-15, backSprite, false));
		
		this.attachChild(parallaxLayer); entitiesList.add(backSprite); entitiesList.add(parallaxLayer);
    }

    private void createHUD() {
		
		/* Para determinar a posiï¿½ï¿½o, sendo N_ELEM o nï¿½mero de elementos que estï¿½o posicionados a direita deste: 
		SPRITE.setPosition((camera.getWidth()- SPRITE.getWidth()) - posX - N_ELEM*(varHeight*lifebarHeight + deltaX), 
    			(camera.getHeight() - SPRITE.getHeight()) - posY);
    	- Se desejar levar o HUD ï¿½ esquerda, aumentar o valor de posX
    	- Se desejar levar o HUD para baixo, aumentar o valor de posY
    	- Se desejar aumentar a distï¿½ncia entre os elementos do HUD, aumentar o valor de deltaX
    	- Se desejar aumentar o tamanho dos botï¿½es do HUD, aumentar o valor de varHeight (segue como padrï¿½o o tamanho
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
    	
    	this.attachChild(lifebarmold); entitiesList.add(lifebarmold);
    	this.attachChild(lifebar); entitiesList.add(lifebar);
    	
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
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());		
		pause.setWidth(varHeight*lifebarHeight); pause.setHeight(varHeight*lifebarHeight);
		pause.setPosition((camera.getWidth() - pause.getWidth()) - posX, 
    			(camera.getHeight() - pause.getHeight()) - posY);
		
		this.registerTouchArea(switcher); this.registerTouchArea(pause);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.attachChild(switcher); entitiesList.add(switcher);
		this.attachChild(pause); entitiesList.add(pause);
    }
    
    public void createPauseScene() {
    	mPauseScene = new MenuScene(camera);
    	
    	backgroundPause = new Sprite(0, 0, resourceManager.stopBackgroundTextureRegion, vbom);
    	mPauseScene.setPosition((camera.getWidth() - backgroundPause.getWidth())/2,
    			(camera.getHeight() - backgroundPause.getHeight())/2 + pausePosY);
    	mPauseScene.attachChild(backgroundPause); entitiesList.add(backgroundPause);
	    
    	backMenu = new SpriteMenuItem(PAUSE_BACK, resourceManager.backTextureRegion, vbom);
	    final IMenuItem backMenuItem = new ScaleMenuItemDecorator(backMenu, 1.2f, 1);
	    restartMenu = new SpriteMenuItem(PAUSE_RESTART, resourceManager.restartTextureRegion, vbom);
	    final IMenuItem restartMenuItem = new ScaleMenuItemDecorator(restartMenu, 1.2f, 1);
	    menuMenu = new SpriteMenuItem(PAUSE_MENU, resourceManager.menuTextureRegion, vbom);
	    final IMenuItem menuMenuItem = new ScaleMenuItemDecorator(menuMenu, 1.2f, 1);
	    
	    mPauseScene.addMenuItem(backMenuItem); entitiesList.add(backMenu);
	    mPauseScene.addMenuItem(restartMenuItem); entitiesList.add(restartMenu);
	    mPauseScene.addMenuItem(menuMenuItem); entitiesList.add(menuMenu);
	    mPauseScene.setBackgroundEnabled(false);
	    mPauseScene.setOnMenuItemClickListener(this);
	    
	    // Garantir que todos os botoes tenham o mesmo tamanho
	    
	    backMenuItem.setPosition((float) ((backgroundPause.getWidth() - backMenuItem.getWidth())/2 + 1.5*backMenuItem.getWidth()),
	    		backMenuItem.getHeight() + butPosY);
	    restartMenuItem.setPosition((backgroundPause.getWidth() - backMenuItem.getWidth())/2,
	    		backMenuItem.getHeight() + butPosY);
	    menuMenuItem.setPosition((float) ((backgroundPause.getWidth() - backMenuItem.getWidth())/2 - 1.5*backMenuItem.getWidth()),
	    		backMenuItem.getHeight() + butPosY);
	}
    
    // Prepara as ondas de inimigo de cada fase
    public void setGameScene(){
		// Cria os inimigos para teste
		// Valores alinhados: dividir por 5, 2 e 1.25 // 2, 1.67 e 1.25 
    	
   	 	LinkedList<Fase> fases = new LinkedList<Fase>();
   	 	fases.add(new Fase1());
   	 	fases.add(new Fase1());
    	faseManager = new FaseManager(fases);
    	GameManager.getInstance().setFaseManager(faseManager);
    	faseManager.start();
    	
   	 	ResourceManager.mMusic.play();
		ResourceManager.mMusic.setVolume(1);
	}
    
    // Finalizacao do jogo
    public void gameOver(boolean winner) {
    	this.setIgnoreUpdate(true);
    	disposeHUD();
    	endGame = true;
    	
    	if(!winner){
    		end = new Sprite(0, 0, resourceManager.gameoverTextureRegion, vbom);
    	}
    	else {
    		end = new Sprite(0, 0, resourceManager.winnerTextureRegion, vbom);
    	}
    	end.setPosition((camera.getWidth() - end.getWidth())/2, (camera.getHeight() - end.getHeight())/2);
    	this.attachChild(end); entitiesList.add(end);
    	
    	engine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() {
            public void onTimePassed(final TimerHandler pTimerHandler) {
                engine.unregisterUpdateHandler(pTimerHandler);
                sceneManager.loadMenufromPause();
            }
    	}));
    }
    
    private void disposeHUD(){
    	this.setTouchAreaBindingOnActionDownEnabled(false);
    	this.detachChild(lifebarmold); entitiesList.remove(lifebarmold);
    	this.detachChild(lifebar); entitiesList.remove(lifebar);
    	this.unregisterTouchArea(switcher); this.detachChild(switcher); entitiesList.remove(switcher);
    	this.unregisterTouchArea(pause); this.detachChild(pause); entitiesList.remove(pause);
    }
    
    // Quando o botao de pausa for ativado
    private class PauseButton extends TiledSprite{    	
    	public PauseButton(float pX, float pY,
    			ITiledTextureRegion pTiledTextureRegion,
    			VertexBufferObjectManager pTiledSpriteVertexBufferObject) {
    		super(pX, pY, pTiledTextureRegion, pTiledSpriteVertexBufferObject);
    	}
    	
    	@Override
    	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
    		if (pSceneTouchEvent.isActionDown()) {
    			pausePressed();
    		}
    		return true;
    	}
    }
    
    private void pausePressed(){
    	if(endGame == false){
	    	/*O que acontece quando clica no botao de pause durante o jogo*/
			if(stop == false){
		    	stop = true;				
				this.setIgnoreUpdate(true); 			
				setChildScene(mPauseScene);
				ResourceManager.mMusic.pause();
			}
			/*O que acontece quando clica no botao de pause com o jogo pausado*/
			else {	
				stop = false;
				this.setIgnoreUpdate(false);
				clearChildScene();
				ResourceManager.mMusic.resume();
			}
    	}
    }

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
	        case PAUSE_BACK:
	        	stop = false;
				this.setIgnoreUpdate(false);
				clearChildScene();
				ResourceManager.mMusic.resume();
	            return true;
	        case PAUSE_RESTART:
	        	sceneManager.restartGameScene();
	        	ResourceManager.mMusic.seekTo(0);
	            return true;
	        case PAUSE_MENU:
	        	sceneManager.loadMenuScene();
	        	ResourceManager.mMusic.seekTo(0);
	            return true;
	        default:
	            return false;
		}
	}
}