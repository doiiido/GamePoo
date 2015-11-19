package unb.cic.poo.game2d.scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

import android.content.Intent;
import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.parse.LoginSignupActivity;
import unb.cic.poo.game2d.parse.ScoreTableActivity;
import unb.cic.poo.game2d.scenes.SceneManager.SceneType;

public class SettingsScene extends BaseScene implements IOnMenuItemClickListener{
	
	private MenuScene settingsChildScene;
	
	private final int MENU_BACK = 0;
	private final int MENU_SCORE = 1;
	
	private static final int posY = 85;
	private static final int posX = 380;
	private static final int deltaX = 50;
	
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
	        case MENU_SCORE:
	        	login();
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
	    settingsChildScene.setPosition((float) (camera.getWidth()/3.0), (float) -(camera.getHeight()/4.0));
	    
	    final IMenuItem backMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_BACK, resourceManager.backMenuTextureRegion, vbom), 1.2f, 1);
	    final IMenuItem scoreMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_SCORE, resourceManager.scoreMenuTextureRegion, vbom), 1.2f, 1);
	    
	    settingsChildScene.addMenuItem(backMenuItem);
	    settingsChildScene.addMenuItem(scoreMenuItem);

	    settingsChildScene.setBackgroundEnabled(false);   
	    
	    settingsChildScene.setOnMenuItemClickListener(this);
	    
	    setChildScene(settingsChildScene);
	    
	    /* Para determinar a posição, sendo N_ELEM o número de elementos que estão posicionados a direita deste: 
		SPRITE.setPosition((float) (camera.getWidth()/2 - posX - N_ELEM*(deltaX + backMenuItem.getWidth())),
	    		camera.getWidth()/2 - backMenuItem.getHeight() + posY);
    	- Se desejar levar o HUD à esquerda, aumentar o valor de posX
    	- Se desejar levar o HUD para baixo, aumentar o valor de posY
    	- Se desejar aumentar a distância entre os elementos do HUD, aumentar o valor de deltaX*/
	    
	    backMenuItem.setPosition((float) (camera.getWidth()/2 - posX - (deltaX + backMenuItem.getWidth())),
	    		camera.getWidth()/2 - backMenuItem.getHeight() + posY);
	    scoreMenuItem.setPosition((float) (camera.getWidth()/2 - posX),
	    		camera.getWidth()/2 - backMenuItem.getHeight() + posY);
	    
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
	
	private void login(){
		GameActivity act = SceneManager.getInstance().mActivity;
		// Determine whether the current user is an anonymous user
		if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
		// If user is anonymous, send the user to LoginSignupActivity.class
			Intent intent = new Intent(act, LoginSignupActivity.class);
			act.startActivity(intent);
			act.finish();
		} else {
			// If current user is NOT anonymous user
			// Get current user data from Parse.com
			ParseUser currentUser = ParseUser.getCurrentUser();
			if (currentUser != null) {
				// Send logged in users to Welcome.class
				Intent intent = new Intent(act, ScoreTableActivity.class);
				act.startActivity(intent);
				act.finish();
			} else {
				// Send user to LoginSignupActivity.class
				Intent intent = new Intent(act, LoginSignupActivity.class);
				act.startActivity(intent);
				act.finish();
			}
		}
	}

}