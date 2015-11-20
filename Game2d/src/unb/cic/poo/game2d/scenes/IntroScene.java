package unb.cic.poo.game2d.scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import unb.cic.poo.game2d.scenes.SceneManager.SceneType;

public class IntroScene extends BaseScene {
	//---------------------------------------------
    // ENTITIES
    //---------------------------------------------
	
	private Sprite intro;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public IntroScene() {
		createScene();		
	}
	
	//---------------------------------------------
    // ABSTRACTION
    //---------------------------------------------
	
    @Override
    public void createScene() {
    	intro = new Sprite(0, 0, resourceManager.introTextureRegion, vbom) {
    	    @Override
    	    protected void preDraw(GLState pGLState, Camera pCamera) {
    	       super.preDraw(pGLState, pCamera);
    	       pGLState.enableDither();
    	    }
    	};
         
    	//intro.setScale(1.5f);
    	intro.setPosition((camera.getWidth()- intro.getWidth())/2, (camera.getHeight() - intro.getHeight())/2);
    	attachChild(intro); entitiesList.add(intro);
    }

    @Override
    public SceneType getSceneType() {
    	return SceneType.SCENE_INTRO;
    }

}