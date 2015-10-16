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

public class SettingsScene extends BaseScene implements IOnMenuItemClickListener{
	
	private MenuScene settingsChildScene;
	private final int MENU_BACK = 0;
	
	public SettingsScene() {
		createScene();		
	}
	
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
			float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
	        case MENU_BACK:
	        	//dispose();
	        	SceneManager.getInstance().loadMenufromSettings();
	            return true;
	        default:
	            return false;
		}
	}

	@Override
	public void createScene() {
		createBackground();
		createSettingsChildScene();	
	}
	
	private void createBackground() {
	    attachChild(new Sprite(0, 0, resourceManager.settingsBackgroundTextureRegion, vbom) {
	        @Override
	        protected void preDraw(GLState pGLState, Camera pCamera) {
	            super.preDraw(pGLState, pCamera);
	            pGLState.enableDither();
	        }
	    });
	}
	
	private void createSettingsChildScene() {
		settingsChildScene = new MenuScene(camera);
	    settingsChildScene.setPosition(camera.getWidth()/(float)3, -(camera.getHeight()/(float)4));
	    
	    final IMenuItem backMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_BACK, resourceManager.backMenuTextureRegion, vbom), 1.2f, 1);
	    
	    settingsChildScene.addMenuItem(backMenuItem);

	    settingsChildScene.setBackgroundEnabled(false);
	    	    
	    
	    settingsChildScene.setOnMenuItemClickListener(this);
	    
	    setChildScene(settingsChildScene);
	    
	    backMenuItem.setPosition(camera.getWidth()/2 - 480,
	    						 camera.getWidth()/2 - backMenuItem.getHeight() + 85);
	    
	}

	@Override
	public void onBackKeyPressed() {
		System.exit(0);			
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_SETTINGS;
	}

	@Override
	public void disposeScene() {
		settingsChildScene.detachSelf();
        settingsChildScene.dispose();
        this.detachSelf();
        this.dispose();
	}

}
