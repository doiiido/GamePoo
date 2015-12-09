package unb.cic.poo.game2d.waves.fase1;

import java.util.ArrayList;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.enemies.CommonEnemy;
import unb.cic.poo.game2d.enemies.Enemy;
import unb.cic.poo.game2d.enemies.FreezedShootingEnemy;
import unb.cic.poo.game2d.enemies.VerticalMovementEnemy;
import unb.cic.poo.game2d.enemies.VerticalShooting;
import unb.cic.poo.game2d.waves.Wave;

public class Wave3 extends Wave {
	
	/*Wave de FreezedShootingEnemy*/
	public Wave3(){
		super();
		this.enemies = new ArrayList<Enemy>();
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH, (float) (GameActivity.CAMERA_HEIGHT*0.1)));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH, (float) (GameActivity.CAMERA_HEIGHT*0.9)));
		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH,(float) (GameActivity.CAMERA_HEIGHT*0.5), GameActivity.CAMERA_WIDTH*0.8f));
		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH,(float) (GameActivity.CAMERA_HEIGHT*0.3), GameActivity.CAMERA_WIDTH*0.6f));
		this.enemies.add(new VerticalMovementEnemy(GameActivity.CAMERA_WIDTH, (float) (GameActivity.CAMERA_HEIGHT*0.8), GameActivity.CAMERA_WIDTH*0.75f));
		this.enemies.add(new VerticalMovementEnemy(GameActivity.CAMERA_WIDTH, (float) (GameActivity.CAMERA_HEIGHT*0.2), GameActivity.CAMERA_WIDTH*0.9f));
		
	}
}
