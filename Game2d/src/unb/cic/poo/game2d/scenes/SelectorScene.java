package unb.cic.poo.game2d.scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.LevelSelector;
import unb.cic.poo.game2d.scenes.SceneManager.SceneType;
import unb.cic.poo.game2d.ResourceManager;

public class SelectorScene extends BaseScene implements IOnMenuItemClickListener{
	//---------------------------------------------
    // SCENES
    //---------------------------------------------
	
	private MenuScene selectorChildScene;
	
	//---------------------------------------------
    // ENTITIES
    //---------------------------------------------
	
	private Sprite background;
	private SpriteMenuItem backMenu;
	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	private final int MENU_BACK = 0;
	
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public SelectorScene() {
		createScene();
	}
	
	//---------------------------------------------
    // ABSTRACTION
    //---------------------------------------------

	@Override
	public void createScene() {
		createBackground();
		createSelectorChildScene();	 
		
		/* Define the level selector properties */ 
		//Scene mScene = new SelectorScene();
		final int maxUnlockedLevel = 1; 
		final int levelSelectorChapter = 1;
		final int cameraWidth = GameActivity.CAMERA_WIDTH;
		final int cameraHeight = GameActivity.CAMERA_HEIGHT;
								
		/* Create a new level selector */ 
		LevelSelector levelSelector = new LevelSelector(maxUnlockedLevel, levelSelectorChapter, 
				cameraWidth, cameraHeight, this , GameManager.getInstance().getGameEngine());
		/* Generate the level tiles for the levelSelector object */ 
		levelSelector.createTiles(ResourceManager.getInstance().planet, ResourceManager.getInstance().starTextureRegion);
				/* Display the levelSelector object on the scene */ 
		levelSelector.show();
	}
	
	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_SELECTOR;
	}
	
	//---------------------------------------------
    // METHODS
    //---------------------------------------------

	@Override
	public void disposeScene() {
		selectorChildScene.detachSelf();
        selectorChildScene.dispose();
        super.disposeScene();
	}
	
	private void createBackground() {
		background = new Sprite(0, 0, resourceManager.selectorBackgroundTextureRegion, vbom) {
	        @Override
	        protected void preDraw(GLState pGLState, Camera pCamera) {
	            super.preDraw(pGLState, pCamera);
	            pGLState.enableDither();
	        }
	    };
	    attachChild(background); entitiesList.add(background);
	}
	
	private void createSelectorChildScene() {
		selectorChildScene = new MenuScene(camera);
	    selectorChildScene.setPosition((float) (camera.getWidth()/3.0), (float) -(camera.getHeight()/4.0));
	    
	    backMenu = new SpriteMenuItem(MENU_BACK, resourceManager.returnTextureRegion, vbom);
	    final IMenuItem backMenuItem = new ScaleMenuItemDecorator(backMenu, 1.2f, 1);
    
	    selectorChildScene.addMenuItem(backMenuItem); entitiesList.add(backMenu);

	    selectorChildScene.setBackgroundEnabled(false);   
	    
	    selectorChildScene.setOnMenuItemClickListener(this);
	    
	    setChildScene(selectorChildScene);
	       
	    backMenuItem.setPosition(camera.getWidth()/2, camera.getHeight()/2 + 380);
	    
//	    final int pChapter = 1;
//	    final int pCameraWidth = camera.getSurfaceWidth();
//	    final int pCameraHeight = camera.getSurfaceHeight();
	    
	    //LevelSelector select = new LevelSelector(pMaxLevel, pChapter, pCameraWidth, pCameraHeight, pScene, pEngine);
	    
	}
	
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
			float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
	        case MENU_BACK:
	        	SceneManager.getInstance().loadMenufromSelector();
	        	return true;
	        default:
	            return false;
		}
	}
	

}