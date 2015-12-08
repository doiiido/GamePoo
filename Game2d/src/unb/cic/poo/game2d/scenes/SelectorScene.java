package unb.cic.poo.game2d.scenes;

import java.util.ArrayList;

import org.andengine.engine.camera.Camera;
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
    // CONSTANTS
    //---------------------------------------------
	
	private int maxUnlockedLevel; 
	private static ArrayList<Integer> numStars = new ArrayList<Integer>();
	private final int cameraWidth = GameActivity.CAMERA_WIDTH;
	private final int cameraHeight = GameActivity.CAMERA_HEIGHT;
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
		
		// Para teste
		maxUnlockedLevel = GameManager.getInstance().getSaveHandler().getUnlockedFases();
		setNumStars(0,1);
	}
	
	public void initScene(){
		/* Cria um novo Level Selector */ 
		LevelSelector levelSelector = new LevelSelector(maxUnlockedLevel, numStars, 
				cameraWidth, cameraHeight, this , GameManager.getInstance().getGameEngine());
		entitiesList.add(levelSelector);
		/* Gera os tiles para o objeto do LevelSelector */ 
		levelSelector.createTiles(ResourceManager.getInstance().planet, ResourceManager.getInstance().starTextureRegion);
		/* Mostra o LevelSelector na cena */ 
		levelSelector.show();
	}
	
	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_SELECTOR;
	}
	
	public void onBackKeyPressed() {
    	sceneManager.loadMenufromSelector();
    }
	
	@Override
	public void disposeScene() {
		selectorChildScene.detachSelf();
        selectorChildScene.dispose();
        super.disposeScene();
	}
	
	//---------------------------------------------
    // GETTERS AND SETTERS
    //---------------------------------------------
	
	public int getMaxUnlockedLevel() {
		return maxUnlockedLevel;
	}
	
	public void setMaxUnlockedLevel(int maxUnlockedLevel) {
		this.maxUnlockedLevel = maxUnlockedLevel;
	}

	public static ArrayList<Integer> getNumStars() {
		return numStars;
	}
	
	public static void setAllNumStars(ArrayList<Integer> numStars) {
		SelectorScene.numStars = numStars;
	}
	
	public static void setNumStars(int index, int numStars) {
		SelectorScene.numStars.add(index, numStars);
	}
	
	//---------------------------------------------
    // METHODS
    //---------------------------------------------
	
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
	    resourceManager.Mmenu.pause();
	    
	    backMenuItem.setPosition(camera.getWidth()/2, camera.getHeight()/2 + 380);    
	}
	
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
			float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
	        case MENU_BACK:
	        	onBackKeyPressed();
	        	resourceManager.Mmenu.seekTo(0);
	        	return true;
	        default:
	            return false;
	            
		}
	}
	
}