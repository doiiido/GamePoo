package unb.cic.poo.game2d.waves.fase1;

import java.util.ArrayList;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.enemies.CommonEnemy;
import unb.cic.poo.game2d.enemies.ConstantXLaser;
import unb.cic.poo.game2d.enemies.Enemy;
import unb.cic.poo.game2d.enemies.FreezedShootingEnemy;
import unb.cic.poo.game2d.enemies.VerticalMovementEnemy;
import unb.cic.poo.game2d.waves.Wave;

public class Wave7 extends Wave{
	public Wave7(){
		super();
		this.enemies = new ArrayList<Enemy>();
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+500, GameActivity.CAMERA_HEIGHT*0.90f));
		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH+600, GameActivity.CAMERA_HEIGHT*0.8f, GameActivity.CAMERA_WIDTH*0.7f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+500, GameActivity.CAMERA_HEIGHT*0.70f));
		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH+600, GameActivity.CAMERA_HEIGHT*0.6f, GameActivity.CAMERA_WIDTH*0.7f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+500, GameActivity.CAMERA_HEIGHT*0.50f));
		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH+600, GameActivity.CAMERA_HEIGHT*0.4f, GameActivity.CAMERA_WIDTH*0.7f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+500, GameActivity.CAMERA_HEIGHT*0.30f));
		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH+600, GameActivity.CAMERA_HEIGHT*0.2f, GameActivity.CAMERA_WIDTH*0.7f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+500, GameActivity.CAMERA_HEIGHT*0.10f));
		
		this.enemies.add(new VerticalMovementEnemy((float)GameActivity.CAMERA_WIDTH+900,GameActivity.CAMERA_HEIGHT*0.7f, GameActivity.CAMERA_WIDTH*0.6f, 2f));
		this.enemies.add(new VerticalMovementEnemy((float)GameActivity.CAMERA_WIDTH+1000,GameActivity.CAMERA_HEIGHT*0.3f, GameActivity.CAMERA_WIDTH*0.6f, 2f));
		this.enemies.add(new VerticalMovementEnemy((float)GameActivity.CAMERA_WIDTH+900,GameActivity.CAMERA_HEIGHT*0.5f, GameActivity.CAMERA_WIDTH*0.6f, 2f));
	}
}
