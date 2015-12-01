package unb.cic.poo.game2d.waves;

import java.util.ArrayList;

import unb.cic.poo.game2d.enemies.*;
import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.waves.Wave;

public class Wave2 extends Wave {

	public Wave2(){
		super();
		this.enemies = new ArrayList<Enemy>();
		//this.enemies.add(new VerticalMovementEnemy(GameActivity.CAMERA_WIDTH, (float) (GameActivity.CAMERA_HEIGHT*0.5),  GameActivity.CAMERA_WIDTH*0.9f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+200, (float) (GameActivity.CAMERA_HEIGHT*0.8)));
		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH+200,(float) (GameActivity.CAMERA_HEIGHT*0.2), GameActivity.CAMERA_WIDTH*0.9f));
		this.enemies.add(new ChasingYEnemy(GameActivity.CAMERA_WIDTH+50, (float) (GameActivity.CAMERA_HEIGHT*0.2), GameActivity.CAMERA_WIDTH*0.9f, GameManager.getInstance().getPlayer().getSpeed()*0.1f));


		
	}
}



