package unb.cic.poo.game2d.scenes;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.color.Color;

import unb.cic.poo.game2d.scenes.SceneManager.SceneType;

public class LoadScene extends BaseScene{
	//---------------------------------------------
    // ENTITIES
    //---------------------------------------------
	
	//private Text loadText;
	private Sprite loadText;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public LoadScene() {
		createScene();		
	}
	
	//---------------------------------------------
    // ABSTRACTION
    //---------------------------------------------
	
    @Override
    public void createScene() {
    	setBackground(new Background(Color.BLACK));
    	
    	// Terminar de ajustar essa classe e ver se funciona melhor com sprite ou texto
    	loadText = new Sprite(0, 0, resourceManager.loadTextureRegion,vbom);
    	loadText.setPosition((camera.getWidth()- loadText.getWidth())/2, (camera.getHeight() - loadText.getHeight())/2);
    	//loadText = new Text(camera.getWidth()*3/4-200, camera.getHeight()*3/4, resourceManager.font, "Loading...", vbom);
		attachChild(loadText); entitiesList.add(loadText);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_LOADING;
    }

}