package unb.cic.poo.game2d;

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
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import android.graphics.Color;
import org.andengine.util.debug.Debug;


public class ResourceManager {
	private static final ResourceManager INSTANCE = new ResourceManager();
	  
	// Todas as imagens animadas devem ser declaradas com Tiled, especificando quantas versões do sprite tem
	// na textura especificada. Também tomar cuidado para elas estarem bem alinhadas.
	public ITextureRegion introTextureRegion;
	BitmapTextureAtlas introTexture;
	
	public ITextureRegion menuBackgroundTextureRegion;
	public ITextureRegion playTextureRegion;
	public ITextureRegion optionsTextureRegion;
	BuildableBitmapTextureAtlas menuTexture;
	
	BitmapTextureAtlas playerTexture;
	public static ITextureRegion playerTextureRegion;
	BitmapTextureAtlas enemyTexture;
	public static ITiledTextureRegion enemyTextureRegion;
	BitmapTextureAtlas bulletTexture;
	public static ITextureRegion bulletTextureRegion;
	BitmapTextureAtlas backgroundTexture;
	public static ITextureRegion backgroundTextureRegion;
	  //common objects
	  public GameActivity activity;
	  public Engine engine;
	  public Camera camera = GameActivity.mCamera;
	  public VertexBufferObjectManager vbom;
	  
	  private static int ENEMY_COLUMN = 8;
	  private static int ENEMY_ROW = 1;
	  
	  public Font font;
	  
	  private ResourceManager() {}
	  
	  public static ResourceManager getInstance() {
		  return INSTANCE;
	  }
	  
	  public void prepare(GameActivity activity) {
			this.activity = activity;
			this.engine = activity.getEngine();
	  }

	  public synchronized void loadIntro() {
		  	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		  	introTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		  	introTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(introTexture, activity, "logo.png", 0, 0);
		  	introTexture.load();
	  }
	  
	  public synchronized void unloadIntro() {
		  introTexture.unload();
		  introTextureRegion = null;
	  }
	  	  
	  public synchronized void loadMenu() {
		  	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		  	menuTexture = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 1024, TextureOptions.BILINEAR);
		 	menuBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTexture, activity, "background.bmp");
		 	playTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTexture, activity, "play.png");
		 	optionsTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTexture, activity, "options.png");
		 	       
		 	try 
		 	{
		 	    this.menuTexture.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
		 	    this.menuTexture.load();
		 	} 
		 	catch (final TextureAtlasBuilderException e)
		 	{
		 	        Debug.e(e);
		 	}
		 	
		 	FontFactory.setAssetBasePath("font/");
		     final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		     font = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "04B30.ttf", 70, true, Color.WHITE, 2, Color.BLACK);
		     font.load();
	  }
	  
	  public synchronized void unloadMenu() {
		  menuTexture.unload();
		  /*menuBackgroundTextureRegion = null;
		  playTextureRegion = null;
		  //optionsTextureRegion = null;
		  font.unload();*/
	  }
	  
	 // Classe para carregar as texturas da pasta asset
	 public synchronized void loadGameTextures(Engine pEngine){
			  // Set our game assets folder in "assets/gfx/game/"
		 	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		 	
		 	// Ao carregar imagens, colocar potências de 2 maiores do que a resolução da mesma
		 	// Evitar colocar imagens maiores que 1024
		 		 	
			playerTexture = new BitmapTextureAtlas(pEngine.getTextureManager(), 128, 128);
			playerTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(playerTexture, activity,"player.png",0,0);
			playerTexture.load();
			
			enemyTexture = new BitmapTextureAtlas(pEngine.getTextureManager(), 512, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			enemyTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(enemyTexture, activity,"enemy_animation.png", 0, 0, ENEMY_COLUMN, ENEMY_ROW);
			enemyTexture.load();
	 
			bulletTexture = new BitmapTextureAtlas(pEngine.getTextureManager(), 32, 32);
			bulletTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(bulletTexture, activity,"fire.png",0,0);
			bulletTexture.load();
			
			backgroundTexture = new BitmapTextureAtlas(pEngine.getTextureManager(), 2048, 1024, TextureOptions.BILINEAR);
	        backgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory
	                .createFromAsset(backgroundTexture, activity, "background.png",0,0);
	        backgroundTexture.load();

	 }
	 
	 public synchronized void unloadGameTextures() {
		  playerTexture.unload();
		  enemyTexture.unload();
		  bulletTexture.unload();
		  backgroundTexture.unload();
	  }
}
