package unb.cic.poo.game2d;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.content.Context;
import android.graphics.Color;

import org.andengine.util.debug.Debug;

import unb.cic.poo.game2d.fases.Fase;
import unb.cic.poo.game2d.fases.FaseManager;


public class ResourceManager {
	private static final ResourceManager INSTANCE = new ResourceManager();
	  
	// Todas as imagens animadas devem ser declaradas com Tiled, especificando quantas versoes do sprite tem
	// na textura especificada. Tambem tomar cuidado para elas estarem bem alinhadas.
	public ITextureRegion introTextureRegion;
	BitmapTextureAtlas introTexture;
	
	/*Menu Principal*/
	public ITextureRegion menuBackgroundTextureRegion;
	public ITextureRegion playTextureRegion;
	public ITextureRegion optionsTextureRegion;
	public ITextureRegion selectTextureRegion;
	BuildableBitmapTextureAtlas menuTexture;
	
	/*Menu Configurações*/
	BuildableBitmapTextureAtlas settingsTexture;
	public TextureRegion settingsBackgroundTextureRegion;
	public TextureRegion backMenuTextureRegion;
	public TextureRegion scoreMenuTextureRegion;
	
	/*Menu de Seleção de fases*/
	BuildableBitmapTextureAtlas selectorTexture;
	public TextureRegion selectorBackgroundTextureRegion;
	public TextureRegion returnTextureRegion;
	public LinkedList<ITextureRegion> planet = new LinkedList<ITextureRegion>();
	private static final int STAR_COLUMN = 5, STAR_ROW = 1;
	BitmapTextureAtlas starTexture;
	public ITiledTextureRegion starTextureRegion;
	
	/*Player*/
	BitmapTextureAtlas playerTexture;
	public static ITiledTextureRegion playerTextureRegion;
	
	/*Enemys*/
	BitmapTextureAtlas walkerTexture;
	public static ITiledTextureRegion walkerTextureRegion;
	BitmapTextureAtlas shooterTexture;
	public static ITiledTextureRegion shooterTextureRegion;
	BitmapTextureAtlas laserTexture;
	public static ITiledTextureRegion laserTextureRegion;
	
	/*Explosion*/
	BitmapTextureAtlas explosionTexture;
	public static ITiledTextureRegion explosionTextureRegion;
	
	/*Bullets*/
	BitmapTextureAtlas bulletTexture;
	public static ITiledTextureRegion bulletTextureRegion;
	BitmapTextureAtlas enemybulletTexture;
	public static ITiledTextureRegion enemybulletTextureRegion;
	BitmapTextureAtlas laserBulletTexture;
	public static ITiledTextureRegion laserBulletTextureRegion;
	BitmapTextureAtlas enemyLaserBulletTexture;
	public static ITiledTextureRegion enemyLaserBulletTextureRegion;
	
	
	/*Musica e sons*/
	public static Sound mSound;
	public static Music mIntro;
	public static Music Mmenu;
	public static Sound mBullet;
	public static Sound mXplosion;
	public static Music mMusic;
	
	/*Backgrounds*/
	BitmapTextureAtlas backgroundTexture;
	public static ITextureRegion backgroundTextureRegion;
	
	/*Icons*/
	BitmapTextureAtlas gameoverTexture;
	public ITextureRegion gameoverTextureRegion;
	BitmapTextureAtlas winnerTexture;
	public ITextureRegion winnerTextureRegion;
	BitmapTextureAtlas lifeTexture;
	public static ITextureRegion lifeTextureRegion;
	BitmapTextureAtlas lifemoldTexture;
	public static ITextureRegion lifemoldTextureRegion;
	BitmapTextureAtlas switchTexture;
	public static ITiledTextureRegion switchTextureRegion;
	BitmapTextureAtlas pauseTexture;
	public static ITiledTextureRegion pauseTextureRegion;
	
	/*Menu de Pausa*/
	BuildableBitmapTextureAtlas stopTexture;
	public TextureRegion stopBackgroundTextureRegion;
	public TextureRegion backTextureRegion;
	public ITextureRegion restartTextureRegion;
	public ITextureRegion menuTextureRegion;
	
	/*Loading*/
	BitmapTextureAtlas loadTexture;
	public TextureRegion loadTextureRegion;
	
	/*Fonte*/
	public Font font;
	
	/*common objects*/
	public GameActivity activity;
	public Engine engine;
	public Camera camera = GameActivity.mCamera;
	public VertexBufferObjectManager vbom;
	  
	/*Dimensoes das sprites animadas*/
	private static final int WALKER_COLUMN = 6, WALKER_ROW = 1;
	private static final int SHOOTER_COLUMN = 9, SHOOTER_ROW = 1;
	private static final int LASER_COLUMN = 5, LASER_ROW = 1;
	private static final int PLAYER_COLUMN = 8, PLAYER_ROW = 1;
	private static final int SWITCH_COLUMN = 2, SWITCH_ROW = 1;
	private static final int PAUSE_COLUMN = 2, PAUSE_ROW = 1;
	private static final  int BULLET_COLUMN = 2, BULLET_ROW = 1;
	private static final int ENEMY_BULLET_COLUMN = 2, ENEMY_BULLET_ROW = 1;
	private static final int LASER_BULLET_COLUMN = 1, LASER_BULLET_ROW = 4;
	private static final int EXPLOSION_COLUMN = 4, EXPLOSION_ROW = 4;
	  
	public static ResourceManager getInstance() {
		return INSTANCE;
	}
	  
	public void prepare(GameActivity activity) {
		this.activity = activity;
		this.engine = activity.getEngine();
	}
	
	public synchronized void loadFonts(){
	  	FontFactory.setAssetBasePath("font/");
	  	final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	  	font = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "font_pixel.ttf", 70, true, Color.WHITE, 2, Color.BLACK);
	    font = FontFactory.createFromAsset(activity.getFontManager(), activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR, activity.getAssets(), "font_pixel.ttf", 70, true, Color.WHITE);
	    font.load();
	}
	  
	public synchronized void unloadFonts() {
	  	font.unload();
	}
	
	public synchronized void loadIntro() {
	  	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
	  	introTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
	  	introTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(introTexture, activity, "logo.png", 0, 0);
	  	introTexture.load();
	  	ResourceManager.mIntro.play();	
	}
	  
	public synchronized void unloadIntro() {
	  	introTexture.unload();
	  	introTextureRegion = null;
	}
	  
	public void loadGameResource(Engine mEngine,Context mContext){
	  
	  SoundFactory.setAssetBasePath("sfx/");
	  try{
		  mBullet = SoundFactory.createSoundFromAsset(mEngine.getSoundManager(),mContext,"battleplane_lazer.ogg");
	  }catch(final IOException e){}
	  
	  MusicFactory.setAssetBasePath("sfx/");
	  try{
		  mMusic = MusicFactory.createMusicFromAsset(mEngine.getMusicManager(), mContext,"M-jogo.mp3");
		  mMusic.setLooping(true);
	  }catch(final IOException e){}
	  
	  try{
		  Mmenu = MusicFactory.createMusicFromAsset(mEngine.getMusicManager(), mContext, "M-menu.mp3");
	  }catch(final IOException e){
		  
	  }
	  
	  try{
		  mIntro = MusicFactory.createMusicFromAsset(mEngine.getMusicManager(), mContext, "oneup.wav");
	  }catch(final IOException e){}
	  
	  try{
		  mXplosion = SoundFactory.createSoundFromAsset(mEngine.getSoundManager(),mContext,"Explosion1.ogg");
	
	  }catch(final IOException e){}
	}
	  
	public synchronized void loadMenu() {
	  	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
	  	menuTexture = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 1024, TextureOptions.BILINEAR);
	 	menuBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTexture, activity, "background.bmp");
	 	playTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTexture, activity, "play.png");
	 	optionsTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTexture, activity, "options.png");
	 	selectTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTexture, activity, "selector.png");
	 	       
	 	try {
	 	    this.menuTexture.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
	 	    this.menuTexture.load();
	 	} 
	 	catch (final TextureAtlasBuilderException e) {
	 	        Debug.e(e);
	 	}
	 }
	 
	public void unloadGameResources(){ 
		mSound.release();
		mSound = null;
		  
		mMusic.stop();
		mMusic.release();
		mMusic = null;
	}
	  
	public synchronized void unloadMenu() {
		menuTexture.unload();
		menuBackgroundTextureRegion = null;
		playTextureRegion = null;
		optionsTextureRegion = null;
		selectTextureRegion = null;
	}
	
	//Configurações
	public synchronized void loadSettings() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
		settingsTexture = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 1024, TextureOptions.BILINEAR);
		settingsBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(settingsTexture, activity, "Fundo.bmp");
		backMenuTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(settingsTexture, activity, "Back.png");
		scoreMenuTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(settingsTexture, activity, "highscore.png");
		 			 	       
		try {
		    this.settingsTexture.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
		 	this.settingsTexture.load();
		} 
		catch (final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	  
	public synchronized void unloadSettings() {
		settingsTexture.unload();
		settingsBackgroundTextureRegion = null;
		backMenuTextureRegion = null;
		scoreMenuTextureRegion = null;
	 }
	
	//Seletor de Fases
	public synchronized void loadSelector() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/backgrounds/");
		selectorTexture = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 3000, 1024, TextureOptions.BILINEAR);
		selectorBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(selectorTexture, activity, "Selector.png");
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/"); 
		returnTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(selectorTexture, activity, "Back.png");

		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/Seletor/");
		
		/*Preenche a lista de ITiledTextureRegion contendo cada um dos 8 planetas com nome "planet(i).txt"*/
		for(int i = 1; i <= 8/*GameManager.getInstance().getFaseManager().getFases().size()*/; i++){
			planet.add(BitmapTextureAtlasTextureRegionFactory.createFromAsset(selectorTexture, activity, "planet("+i+").png"));
		}
		
		starTexture = new BitmapTextureAtlas(engine.getTextureManager(), 512, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		starTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(starTexture, activity,"stars.png", 0, 0, STAR_COLUMN, STAR_ROW);
		starTexture.load();
						
		
		try {
		    this.selectorTexture.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
		 	this.selectorTexture.load();
		} 
		catch (final TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}
	  
	public synchronized void unloadSelector() {
		selectorTexture.unload();
		selectorBackgroundTextureRegion = null;
		returnTextureRegion = null;
		planet.clear();
		//planet = null;
		starTexture.unload();
		starTextureRegion = null;
		
	 }
	  
	 // Classe para carregar as texturas da pasta asset
	public synchronized void loadGameTextures(){
		// Set our game assets folder in "assets/gfx/game/"
		 
		/* Ao declarar novas sprites, acoplar juntamente aos antigos aqui, devido ao caminho da pasta assets*/
		/* PASTA JOGADOR*/
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/player/");
		 	
		// Ao carregar imagens, colocar potencias de 2 maiores do que a resolucao da mesma
		// Evitar colocar imagens maiores que 1024
		 		 	
		 	
		playerTexture = new BitmapTextureAtlas(engine.getTextureManager(), 1536, 124, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		playerTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(playerTexture, activity,"player_animated.png", 0, 0, PLAYER_COLUMN, PLAYER_ROW);
		playerTexture.load();
			
		/* PASTA INIMIGOS*/
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/enemys/");
		
		walkerTexture = new BitmapTextureAtlas(engine.getTextureManager(), 512, 124, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		walkerTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(walkerTexture, activity,"walker_animation.png", 0, 0, WALKER_COLUMN, WALKER_ROW);
		walkerTexture.load();
			
		shooterTexture = new BitmapTextureAtlas(engine.getTextureManager(), 512, 124, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		shooterTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(shooterTexture, activity,"shooter_animation.png", 0, 0, SHOOTER_COLUMN, SHOOTER_ROW);
		shooterTexture.load();
			
		laserTexture = new BitmapTextureAtlas(engine.getTextureManager(), 512, 124, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		laserTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(laserTexture, activity,"laser_animation.png", 0, 0, LASER_COLUMN, LASER_ROW);
		laserTexture.load();
			
		explosionTexture = new BitmapTextureAtlas(engine.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		explosionTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(explosionTexture, activity,"explosion.png", 0, 0, EXPLOSION_COLUMN, EXPLOSION_ROW);
		explosionTexture.load();
			
			
		/* PASTA BALAS*/
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/bullets/");
	 
		bulletTexture = new BitmapTextureAtlas(engine.getTextureManager(), 64, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		bulletTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(bulletTexture, activity,"fire.png",0,0,BULLET_COLUMN,BULLET_ROW);
		bulletTexture.load();
	
		enemybulletTexture = new BitmapTextureAtlas(engine.getTextureManager(), 64, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		enemybulletTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(enemybulletTexture, activity,"enemyfire.png",0,0,ENEMY_BULLET_COLUMN,ENEMY_BULLET_ROW);
		enemybulletTexture.load();
			
		laserBulletTexture = new BitmapTextureAtlas(engine.getTextureManager(), 1280, 200, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		laserBulletTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(laserBulletTexture, activity,"laser.png", 0, 0, LASER_BULLET_COLUMN, LASER_BULLET_ROW);
		laserBulletTexture.load();
		
		enemyLaserBulletTexture = new BitmapTextureAtlas(engine.getTextureManager(), 1280, 200, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		enemyLaserBulletTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(enemyLaserBulletTexture, activity,"enemy_laser.png", 0, 0, LASER_BULLET_COLUMN, LASER_BULLET_ROW);
		enemyLaserBulletTexture.load();
			
		/* PASTA BACKGROUNDS*/
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/backgrounds/");
		backgroundTexture = new BitmapTextureAtlas(engine.getTextureManager(), 2048, 1024, TextureOptions.BILINEAR);
	    backgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory
	    		   .createFromAsset(backgroundTexture, activity, "background.png",0,0);
	    backgroundTexture.load();
	
	    /* PASTA ICONS*/
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/icons/");
	    gameoverTexture = new BitmapTextureAtlas(engine.getTextureManager(), 1024, 512, TextureOptions.BILINEAR);
	    gameoverTextureRegion = BitmapTextureAtlasTextureRegionFactory
	    		.createFromAsset(gameoverTexture, activity, "gameover.png",0,0);
	    gameoverTexture.load();
	        
	    winnerTexture = new BitmapTextureAtlas(engine.getTextureManager(), 1024, 512, TextureOptions.BILINEAR);
	    winnerTextureRegion = BitmapTextureAtlasTextureRegionFactory
	    		.createFromAsset(winnerTexture, activity, "winner.png",0,0);
	    winnerTexture.load();
	        
	    lifeTexture = new BitmapTextureAtlas(engine.getTextureManager(), 512, 64);
		lifeTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(lifeTexture, activity,"lifebar.png",0,0);
		lifeTexture.load();
			
		lifemoldTexture = new BitmapTextureAtlas(engine.getTextureManager(), 512, 64);
		lifemoldTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(lifemoldTexture, activity,"lifebar_mold.png",0,0);
		lifemoldTexture.load();
			
		switchTexture = new BitmapTextureAtlas(engine.getTextureManager(), 1024, 512);
		switchTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(switchTexture, activity,"switcher.png", 0, 0, SWITCH_COLUMN, SWITCH_ROW);
		switchTexture.load();
			
		pauseTexture = new BitmapTextureAtlas(engine.getTextureManager(), 1024, 512);
		pauseTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(pauseTexture, activity,"play_pause.png", 0, 0, PAUSE_COLUMN, PAUSE_ROW);
		pauseTexture.load();
	}
	 
	public synchronized void loadGamePause() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
		stopTexture = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 1024, TextureOptions.BILINEAR);
		stopBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(stopTexture, activity, "Pause.png");
		backTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(stopTexture, activity, "Back.png");
		restartTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(stopTexture, activity, "restart.png");
		menuTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(stopTexture, activity, "menu.png");
		 			 	       
		try {
		    this.stopTexture.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
		    this.stopTexture.load();
		} 
		catch (final TextureAtlasBuilderException e) {
			Debug.e(e);
		 }
	}
	 
	public synchronized void unloadGamePause() {
		stopTexture.unload();
		stopBackgroundTextureRegion = null;
		backTextureRegion = null;
		restartTextureRegion = null;
		menuTextureRegion = null;
	}
	 
	public synchronized void unloadEnemys(){
		walkerTexture.unload(); walkerTextureRegion = null;
		shooterTexture.unload(); shooterTextureRegion = null;
		laserTexture.unload(); laserTextureRegion = null;
		explosionTexture.unload(); explosionTextureRegion = null;
	}
	 
	public synchronized void unloadIcons(){
		gameoverTexture.unload(); gameoverTextureRegion = null;
		winnerTexture.unload(); winnerTextureRegion = null;
		lifeTexture.unload(); lifeTextureRegion = null;
		lifemoldTexture.unload(); lifemoldTextureRegion = null;
		switchTexture.unload(); switchTextureRegion = null;
		pauseTexture.unload(); pauseTextureRegion = null;
	}
	 
	public synchronized void unloadGameTextures() {
		playerTexture.unload();  playerTextureRegion = null;
		unloadEnemys();
		bulletTexture.unload(); bulletTextureRegion = null;
		laserBulletTexture.unload(); laserBulletTextureRegion = null;
		backgroundTexture.unload(); backgroundTextureRegion = null;
		unloadIcons();
	}
}
