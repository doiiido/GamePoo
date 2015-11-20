package unb.cic.poo.game2d.scenes;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.color.Color;

import unb.cic.poo.game2d.scenes.SceneManager.SceneType;

public class LoadScene extends BaseScene{
	//---------------------------------------------
    // ENTITIES
    //---------------------------------------------
	
	private Text loadText;
	//private Sprite loadText;
	
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
    	
    	loadText = new Text(camera.getWidth()*3/4-200, camera.getHeight()*3/4, resourceManager.font, "Loading...", vbom);
		attachChild(loadText); entitiesList.add(loadText);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_LOADING;
    }

}