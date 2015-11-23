package unb.cic.poo.game2d.scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import unb.cic.poo.game2d.ResourceManager;
import unb.cic.poo.game2d.scenes.SceneManager.SceneType;

public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener {
	//---------------------------------------------
    // SCENES
    //---------------------------------------------
	
	private MenuScene menuChildScene;
	
	//---------------------------------------------
    // ENTITIES
    //---------------------------------------------
	
	private Sprite backMenu;
	private SpriteMenuItem playMenu;
	private SpriteMenuItem optMenu;
	private SpriteMenuItem selectMenu;
	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	private final int MENU_PLAY = 0;
	private final int MENU_OPTIONS = 1;
	private final int MENU_SELECTOR = 2;
	
	private final float dy = 85;
	private final float dx = 190;
	private final float Delta = 390;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public MainMenuScene() {
		createScene();		
	}
	
	//---------------------------------------------
    // ABSTRACTION
    //---------------------------------------------
	
	@Override
	public void createScene() {
		createBackground();
		createMenuChildScene();		
	}
	
	@Override
	public SceneType getSceneType() {
	    return SceneType.SCENE_MENU;
	}

	@Override
	public void disposeScene() {
		menuChildScene.detachSelf();
        menuChildScene.dispose();
        super.disposeScene();
	}
	
	
	//---------------------------------------------
    // METHODS
    //---------------------------------------------
	
	private void createBackground() {
		backMenu = new Sprite(0, 0, resourceManager.menuBackgroundTextureRegion, vbom) {
	        @Override
	        protected void preDraw(GLState pGLState, Camera pCamera) {
	            super.preDraw(pGLState, pCamera);
	            pGLState.enableDither();
	        }
	    };
	    attachChild(backMenu); entitiesList.add(backMenu);
	}
	
	private void createMenuChildScene() {
		menuChildScene = new MenuScene(camera);
	    menuChildScene.setPosition((float) (camera.getWidth()/3.0), (float) -(camera.getHeight()/4.0));
	    
	    playMenu = new SpriteMenuItem(MENU_PLAY, resourceManager.playTextureRegion, vbom);
	    final IMenuItem playMenuItem = new ScaleMenuItemDecorator(playMenu, 1.2f, 1);
	    optMenu = new SpriteMenuItem(MENU_OPTIONS, resourceManager.optionsTextureRegion, vbom);
	    final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(optMenu, 1.2f, 1);
	    selectMenu = new SpriteMenuItem(MENU_SELECTOR, resourceManager.selectTextureRegion, vbom);
	    final IMenuItem selectMenuItem = new ScaleMenuItemDecorator(selectMenu, 1.2f, 1);
	    
	    menuChildScene.addMenuItem(playMenuItem); entitiesList.add(playMenu);
	    menuChildScene.addMenuItem(optionsMenuItem); entitiesList.add(optMenu);
	    menuChildScene.addMenuItem(selectMenuItem); entitiesList.add(selectMenu);
	    
	    menuChildScene.buildAnimations();
	    menuChildScene.setBackgroundEnabled(false);
    
	    menuChildScene.setOnMenuItemClickListener(this);
	    
	    setChildScene(menuChildScene);
	    
	    
	    /*Para deslocar todo o HUD horizontalmente, varie Delta
	     * Para aumentar o espaço entre os elementos do HUD, varie dx
	     * Para deslocar todo o HUD verticalmente, varie dy
	     * */
	    playMenuItem.setPosition(camera.getWidth()/2 - playMenuItem.getWidth()/2 - dx - Delta,
	    						 camera.getWidth()/2 - playMenuItem.getHeight() + dy);
	    
	    optionsMenuItem.setPosition(camera.getWidth()/2 - optionsMenuItem.getWidth()/2 - Delta,
	    							camera.getWidth()/2 - optionsMenuItem.getHeight() + dy);
	    
	    selectMenuItem.setPosition(camera.getWidth()/2 - selectMenuItem.getWidth()/2 + dx - Delta,
				camera.getWidth()/2 - selectMenuItem.getHeight() + dy);
	    
	    ResourceManager.Mmenu.play();
	    ResourceManager.Mmenu.setVolume(1);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
			float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
	        case MENU_PLAY:
	        	SceneManager.getInstance().loadGameScene();
	        	ResourceManager.Mmenu.stop();
	            return true;
	        case MENU_OPTIONS:
	        	SceneManager.getInstance().createSettingsScene();
	            return true;
	        case MENU_SELECTOR:
	        	SceneManager.getInstance().createSelectorScene();
	            return true;
	        default:
	            return false;
		}
	}
}
