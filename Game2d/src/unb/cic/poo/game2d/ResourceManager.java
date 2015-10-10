package unb.cic.poo.game2d;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import android.content.Context;


public class ResourceManager {
	private static final ResourceManager INSTANCE = new ResourceManager();
	  
	// Todas as imagens animadas devem ser declaradas com Tiled, especificando quantas versões do sprite tem
	// na textura especificada. Também tomar cuidado para elas estarem bem alinhadas.
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
	  public Camera camera;
	  public VertexBufferObjectManager vbom;
	  
	  private static int ENEMY_COLUMN = 8;
	  private static int ENEMY_ROW = 1;
	  
	  private ResourceManager() {}
	  
	  public static ResourceManager getInstance() {
		  return INSTANCE;
	  }

	  
	 // Classe para carregar as texturas da pasta asset
	 public synchronized void loadGameTextures(Engine pEngine, Context pContext){
			  // Set our game assets folder in "assets/gfx/game/"
		 	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		 	
		 	// Ao carregar imagens, colocar potências de 2 maiores do que a resolução da mesma
		 	// Evitar colocar imagens maiores que 1024
		 	
			playerTexture = new BitmapTextureAtlas(pEngine.getTextureManager(), 128, 128);
			playerTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(playerTexture, pContext,"player.png",0,0);
			playerTexture.load();
			
			enemyTexture = new BitmapTextureAtlas(pEngine.getTextureManager(), 512, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			enemyTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(enemyTexture, pContext,"enemy_animation.png", 0, 0, ENEMY_COLUMN, ENEMY_ROW);
			enemyTexture.load();
	 
			bulletTexture = new BitmapTextureAtlas(pEngine.getTextureManager(), 32, 32);
			bulletTextureRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(bulletTexture, pContext,"fire.png",0,0);
			bulletTexture.load();
			
			backgroundTexture = new BitmapTextureAtlas(pEngine.getTextureManager(), 2048, 1024, TextureOptions.BILINEAR);
	        backgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory
	                .createFromAsset(backgroundTexture, pContext, "background.png",0,0);
	        backgroundTexture.load();

	 }
}
