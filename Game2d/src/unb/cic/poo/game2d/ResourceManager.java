package unb.cic.poo.game2d;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import android.content.Context;


public class ResourceManager {
	private static final ResourceManager INSTANCE = new ResourceManager();
	  
	BitmapTextureAtlas playerTexture;
	public static ITextureRegion playerTextureRegion;
	BitmapTextureAtlas enemyTexture;
	public static ITextureRegion enemyTextureRegion;
	BitmapTextureAtlas bulletTexture;
	public static ITextureRegion bulletTextureRegion;
	BitmapTextureAtlas backgroundTexture;
	public static ITextureRegion backgroundTextureRegion;
	  //common objects
	  public GameActivity activity;
	  public Engine engine;
	  public Camera camera;
	  public VertexBufferObjectManager vbom;
	  
	  private ResourceManager() {}
	  
	  public static ResourceManager getInstance() {
		  return INSTANCE;
	  }

	  
	 // Classe para carregar as texturas da pasta asset
	 public synchronized void loadGameTextures(Engine pEngine, Context pContext){
			  // Set our game assets folder in "assets/gfx/game/"
		 	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		 	
			playerTexture = new BitmapTextureAtlas(pEngine.getTextureManager(), 64, 64);
			playerTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(playerTexture, pContext,"player.png",0,0);
			playerTexture.load();
			
			enemyTexture = new BitmapTextureAtlas(pEngine.getTextureManager(), 64, 64);
			enemyTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(enemyTexture, pContext,"enemy.png",0,0);
			enemyTexture.load();
	 
			bulletTexture = new BitmapTextureAtlas(pEngine.getTextureManager(), 64, 64);
			bulletTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(bulletTexture, pContext,"fire.png",0,0);
			bulletTexture.load();
			
			backgroundTexture = new BitmapTextureAtlas(pEngine.getTextureManager(), 1024, 1024);
	        backgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory
	                .createFromAsset(backgroundTexture, pContext, "background.png",0,0);
	        backgroundTexture.load();

	 }
}
