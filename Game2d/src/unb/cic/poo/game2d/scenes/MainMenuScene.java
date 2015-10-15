package unb.cic.poo.game2d.scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import unb.cic.poo.game2d.scenes.SceneManager.SceneType;

public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener {
	private MenuScene menuChildScene;
	private final int MENU_PLAY = 0;
	private final int MENU_OPTIONS = 1;
	
	public MainMenuScene() {
		createScene();		
	}
	
	@Override
	public SceneType getSceneType() {
	    return SceneType.SCENE_MENU;
	}

	@Override
	public void createScene() {
		createBackground();
		createMenuChildScene();		
	}

	@Override
	public void onBackKeyPressed() {
		System.exit(0);		
	}

	@Override
	public void disposeScene() {
		menuChildScene.detachSelf();
        menuChildScene.dispose();
        this.detachSelf();
        this.dispose();
	}
	
	private void createBackground() {
	    attachChild(new Sprite(0, 0, resourceManager.menuBackgroundTextureRegion, vbom) {
	        @Override
	        protected void preDraw(GLState pGLState, Camera pCamera) {
	            super.preDraw(pGLState, pCamera);
	            pGLState.enableDither();
	        }
	    });
	}
	
	private void createMenuChildScene() {
		menuChildScene = new MenuScene(camera);
	    menuChildScene.setPosition(camera.getWidth()/(float)3, -(camera.getHeight()/(float)4));
	    
	    final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, resourceManager.playTextureRegion, vbom), 1.2f, 1);
	    final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, resourceManager.optionsTextureRegion, vbom), 1.2f, 1);
	    
	    menuChildScene.addMenuItem(playMenuItem);
	    menuChildScene.addMenuItem(optionsMenuItem);
	    
	    menuChildScene.buildAnimations();
	    menuChildScene.setBackgroundEnabled(false);
	    
	    playMenuItem.setPosition(camera.getWidth()/2 - playMenuItem.getWidth() -480, 
	    						 camera.getWidth()/2 - playMenuItem.getHeight() + 60);
	    
	    optionsMenuItem.setPosition(camera.getWidth()/2 - optionsMenuItem.getWidth() -280, 
	    							camera.getWidth()/2 - optionsMenuItem.getHeight() + 60);
	    
	    menuChildScene.setOnMenuItemClickListener(this);
	    
	    setChildScene(menuChildScene);
	    
	    playMenuItem.setPosition(camera.getWidth()/2 - playMenuItem.getWidth() - 435,
	    						 camera.getWidth()/2 - playMenuItem.getHeight() + 85);
	    
	    optionsMenuItem.setPosition(camera.getWidth()/2 - optionsMenuItem.getWidth() - 245,
	    							camera.getWidth()/2 - optionsMenuItem.getHeight() + 85);
	    
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
			float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
	        case MENU_PLAY:
	        	SceneManager.getInstance().loadGameScene(engine);
	            return true;
	        case MENU_OPTIONS:
	            return true;
	        default:
	            return false;
		}
	}
}
