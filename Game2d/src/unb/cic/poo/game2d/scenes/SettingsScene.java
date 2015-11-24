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
	//---------------------------------------------
    // SCENES
    //---------------------------------------------
	
	private MenuScene settingsChildScene;
	
	//---------------------------------------------
    // ENTITIES
    //---------------------------------------------
	
	private Sprite backSet;
	private SpriteMenuItem backMenu;
	private SpriteMenuItem scoreMenu;
	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	private final int MENU_BACK = 0;
	private final int MENU_SCORE = 1;
	
	private static final int posY = 85;
	private static final int posX = 380;
	private static final int deltaX = 50;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public SettingsScene() {
		createScene();
	}
	
	//---------------------------------------------
    // ABSTRACTION
    //---------------------------------------------

	@Override
	public void createScene() {
		createBackground();
		createSettingsChildScene();	
	}
	
	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_SETTINGS;
	}
	
	public void onBackKeyPressed() {
    	sceneManager.loadMenufromSettings();
    }
	
	@Override
	public void disposeScene() {
		settingsChildScene.detachSelf();
        settingsChildScene.dispose();
        super.disposeScene();
	}
	
	//---------------------------------------------
    // METHODS
    //---------------------------------------------
	
	private void createBackground() {
		backSet = new Sprite(0, 0, resourceManager.settingsBackgroundTextureRegion, vbom) {
	        @Override
	        protected void preDraw(GLState pGLState, Camera pCamera) {
	            super.preDraw(pGLState, pCamera);
	            pGLState.enableDither();
	        }
	    };
	    attachChild(backSet); entitiesList.add(backSet);
	}
	
	private void createSettingsChildScene() {
		settingsChildScene = new MenuScene(camera);
	    settingsChildScene.setPosition((float) (camera.getWidth()/3.0), (float) -(camera.getHeight()/4.0));
	    
	    backMenu = new SpriteMenuItem(MENU_BACK, resourceManager.backMenuTextureRegion, vbom);
	    final IMenuItem backMenuItem = new ScaleMenuItemDecorator(backMenu, 1.2f, 1);
	    scoreMenu = new SpriteMenuItem(MENU_SCORE, resourceManager.scoreMenuTextureRegion, vbom);
	    final IMenuItem scoreMenuItem = new ScaleMenuItemDecorator(scoreMenu, 1.2f, 1);
	    
	    settingsChildScene.addMenuItem(backMenuItem); entitiesList.add(backMenu);
	    settingsChildScene.addMenuItem(scoreMenuItem); entitiesList.add(scoreMenu);

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
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX,
			float pMenuItemLocalY) {
		switch(pMenuItem.getID()) {
	        case MENU_BACK:
	        	onBackKeyPressed();
	        	return true;
	        case MENU_SCORE:
	        	SceneManager.getInstance().disposeSettingsScene();
	        	login();
	            return true;
	        default:
	            return false;
		}
	}
	
	private void login(){
		GameActivity act = SceneManager.getInstance().mActivity;
		// Determina se o usuário é anônimo
		if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
		// If user is anonymous, send the user to LoginSignupActivity.class
			Intent intent = new Intent(act, LoginSignupActivity.class);
			act.startActivity(intent);
			act.finish();
		} else {
			// Se não é anônimo, resgata suas informações de Parse.com
			ParseUser currentUser = ParseUser.getCurrentUser();
			if (currentUser != null) {
				Intent intent = new Intent(act, ScoreTableActivity.class);
				act.startActivity(intent);
				act.finish();
			} else {
				Intent intent = new Intent(act, LoginSignupActivity.class);
				act.startActivity(intent);
				act.finish();
			}
		}
	}

}