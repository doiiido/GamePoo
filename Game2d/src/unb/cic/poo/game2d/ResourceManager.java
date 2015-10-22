package unb.cic.poo.game2d;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
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


public class ResourceManager {
	private static final ResourceManager INSTANCE = new ResourceManager();
	  
	// Todas as imagens animadas devem ser declaradas com Tiled, especificando quantas versï¿½es do sprite tem
	// na textura especificada. Tambï¿½m tomar cuidado para elas estarem bem alinhadas.
	public ITextureRegion introTextureRegion;
	BitmapTextureAtlas introTexture;
	
	/*Menu Principal*/
	public ITextureRegion menuBackgroundTextureRegion;
	public ITextureRegion playTextureRegion;
	public ITextureRegion optionsTextureRegion;
	BuildableBitmapTextureAtlas menuTexture;
	
	/*Menu Configurações*/
	BuildableBitmapTextureAtlas settingsTexture;
	public TextureRegion settingsBackgroundTextureRegion;
	public TextureRegion backMenuTextureRegion;
	
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
	
	/*Bullets*/
	BitmapTextureAtlas bulletTexture;
	public static ITiledTextureRegion bulletTextureRegion;
	private static int BULLET_COLUMN = 2, BULLET_ROW = 1;
	BitmapTextureAtlas enemybulletTexture;
	public static ITiledTextureRegion enemybulletTextureRegion;
	private static int ENEMY_BULLET_COLUMN = 2, ENEMY_BULLET_ROW = 1;
	BitmapTextureAtlas laserBulletTexture;
	public static ITiledTextureRegion laserBulletTextureRegion;
	private static int LASER_BULLET_COLUMN = 1, LASER_BULLET_ROW = 4;
	
	/*Musica e sons*/
	public static Sound mSound;
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

	BitmapTextureAtlas backTexture;
	public static ITextureRegion backTextureRegion;
	
	  //common objects
	  public GameActivity activity;
	  public Engine engine;
	  public Camera camera = GameActivity.mCamera;
	  public VertexBufferObjectManager vbom;
	  
	  /*Enemys dimensï¿½es de animaï¿½ï¿½o*/
	  private static int WALKER_COLUMN = 6, WALKER_ROW = 1;
	  private static int SHOOTER_COLUMN = 9, SHOOTER_ROW = 1;
	  private static int LASER_COLUMN = 5, LASER_ROW = 1;
	  private static int PLAYER_COLUMN = 8, PLAYER_ROW = 1;
	  private static int SWITCH_COLUMN = 2, SWITCH_ROW = 1;
	  private static int PAUSE_COLUMN = 2, PAUSE_ROW = 1;
	  
	  public Font font;
	  
	  public static ResourceManager getInstance() {
		  return INSTANCE;
	  }
	  
	  public void prepare(GameActivity activity) {
			this.activity = activity;
			this.engine = activity.getEngine();
	  }

	  public synchronized void loadIntro() {
		  	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
		  	introTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		  	introTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(introTexture, activity, "logo.png", 0, 0);
		  	introTexture.load();
	  }
	  
	  public synchronized void unloadIntro() {
		  	introTexture.unload();
		  	introTextureRegion = null;
	  }
	  
	  public synchronized void loadFonts(){
		  	FontFactory.setAssetBasePath("font/");
		  	final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		    font = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "font_loading.ttf", 70, true, Color.WHITE, 2, Color.BLACK);
		    font.load();
	  }
	  
	  public synchronized void unloadFonts() {
		  	font.unload();
	  }
	  
	  public void loadGameResource(Engine mEngine,Context mContext){
		  
		  SoundFactory.setAssetBasePath("sfx/");
		  try{
			  mBullet = SoundFactory.createSoundFromAsset(mEngine.getSoundManager(),mContext,"battleplane_lazer.ogg");
		  }catch(final IOException e){}
		  
		  MusicFactory.setAssetBasePath("sfx/");
		  try{
			  mMusic = MusicFactory.createMusicFromAsset(mEngine.getMusicManager(), mContext,"twisted.mp3");
			  mMusic.setLooping(true);
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
	  }
	  
	  public synchronized void loadSettings() {
		  	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
		  	settingsTexture = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 1024, TextureOptions.BILINEAR);
		 	settingsBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(settingsTexture, activity, "Fundo.bmp");
		 	backMenuTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(settingsTexture, activity, "Back.png");
		 			 	       
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
	  }
	  
	 // Classe para carregar as texturas da pasta asset
	 public synchronized void loadGameTextures(){
			  // Set our game assets folder in "assets/gfx/game/"
		 
		 	/* Ao declarar novas sprites, acoplar juntamente aos antigos aqui, devido ao caminho da pasta assets*/
		 	/* PASTA JOGADOR*/
		 	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/player/");
		 	
		 	// Ao carregar imagens, colocar potï¿½ncias de 2 maiores do que a resoluï¿½ï¿½o da mesma
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
	        
	        lifeTexture = new BitmapTextureAtlas(engine.getTextureManager(), 1024, 124);
			lifeTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(lifeTexture, activity,"lifebar.png",0,0);
			lifeTexture.load();
			
			lifemoldTexture = new BitmapTextureAtlas(engine.getTextureManager(), 1024, 124);
			lifemoldTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(lifemoldTexture, activity,"lifebar_mold.png",0,0);
			lifemoldTexture.load();
			
			switchTexture = new BitmapTextureAtlas(engine.getTextureManager(), 1024, 512);
			switchTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(switchTexture, activity,"switcher.png", 0, 0, SWITCH_COLUMN, SWITCH_ROW);
			switchTexture.load();
			
			backTexture = new BitmapTextureAtlas(engine.getTextureManager(), 128, 128);
			backTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(backTexture, activity,"back.png",0,0);
			backTexture.load();	
			
			pauseTexture = new BitmapTextureAtlas(engine.getTextureManager(), 1024, 512);
			pauseTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(pauseTexture, activity,"play_pause.png", 0, 0, PAUSE_COLUMN, PAUSE_ROW);
			pauseTexture.load();
	 }
	 
	 public synchronized void unloadEnemys(){
		 walkerTexture.unload(); walkerTextureRegion = null;
		 shooterTexture.unload(); shooterTextureRegion = null;
		 laserTexture.unload(); laserTextureRegion = null;
	 }
	 
	 public synchronized void unloadIcons(){
		  gameoverTexture.unload(); gameoverTextureRegion = null;
		  winnerTexture.unload(); winnerTextureRegion = null;
		  lifeTexture.unload(); lifeTextureRegion = null;
		  lifemoldTexture.unload(); lifemoldTextureRegion = null;
		  switchTexture.unload(); switchTextureRegion = null;
		  backTexture.unload(); backTextureRegion = null;
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
