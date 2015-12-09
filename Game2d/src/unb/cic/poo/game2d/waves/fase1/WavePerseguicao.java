package unb.cic.poo.game2d.waves.fase1;

import java.util.ArrayList;

import unb.cic.poo.game2d.enemies.*;
import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.waves.Wave;

public class WavePerseguicao extends Wave{
	
	/*Wave de ChasingEnemy*/
	public WavePerseguicao(){
		super();
		this.enemies = new ArrayList<Enemy>();
		this.enemies.add(new ChasingYEnemy(GameActivity.CAMERA_WIDTH+50, (float) (GameActivity.CAMERA_HEIGHT*0.2), GameActivity.CAMERA_WIDTH*0.9f, GameManager.getInstance().getPlayer().getSpeed()*0.2f));
		this.enemies.add(new ChasingYEnemy(GameActivity.CAMERA_WIDTH+50, (float) (GameActivity.CAMERA_HEIGHT*0.2), GameActivity.CAMERA_WIDTH*0.8f, GameManager.getInstance().getPlayer().getSpeed()*0.18f));
		this.enemies.add(new ChasingYEnemy(GameActivity.CAMERA_WIDTH+50, (float) (GameActivity.CAMERA_HEIGHT*0.2), GameActivity.CAMERA_WIDTH*0.7f, GameManager.getInstance().getPlayer().getSpeed()*0.16f));
		this.enemies.add(new ChasingYEnemy(GameActivity.CAMERA_WIDTH+50, (float) (GameActivity.CAMERA_HEIGHT*0.2), GameActivity.CAMERA_WIDTH*0.6f, GameManager.getInstance().getPlayer().getSpeed()*0.14f));
		this.enemies.add(new ChasingYEnemy(GameActivity.CAMERA_WIDTH+50, (float) (GameActivity.CAMERA_HEIGHT*0.2), GameActivity.CAMERA_WIDTH*0.5f, GameManager.getInstance().getPlayer().getSpeed()*0.12f));
	}
	

}
