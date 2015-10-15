package unb.cic.poo.game2d.scenes;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.color.Color;

import unb.cic.poo.game2d.scenes.SceneManager.SceneType;

public class LoadScene extends BaseScene{
	
	public LoadScene() {
		createScene();		
	}
	
    @Override
    public void createScene() {    	
    	setBackground(new Background(Color.BLACK));		
		attachChild(new Text(camera.getWidth()*3/4-200, camera.getHeight() *3/4, resourceManager.font, "loading;;;", vbom));
    }

    @Override
    public void onBackKeyPressed() {
        return;
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_LOADING;
    }

    @Override
    public void disposeScene() {
        this.detachSelf();
        this.dispose();
    }
}
