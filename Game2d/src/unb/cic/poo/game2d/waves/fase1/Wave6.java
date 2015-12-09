package unb.cic.poo.game2d.waves.fase1;


import java.util.ArrayList;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.enemies.CommonEnemy;
import unb.cic.poo.game2d.enemies.ConstantXLaser;
import unb.cic.poo.game2d.enemies.Enemy;
import unb.cic.poo.game2d.enemies.FreezedShootingEnemy;
import unb.cic.poo.game2d.enemies.VerticalMovementEnemy;
import unb.cic.poo.game2d.enemies.VerticalShooting;
import unb.cic.poo.game2d.waves.Wave;

public class Wave6 extends Wave {
	public Wave6(){
		super();
		this.enemies = new ArrayList<Enemy>();
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+500, GameActivity.CAMERA_HEIGHT*0.75f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+600, GameActivity.CAMERA_HEIGHT*0.63f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+500, GameActivity.CAMERA_HEIGHT*0.5f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+600, GameActivity.CAMERA_HEIGHT*0.37f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+500, GameActivity.CAMERA_HEIGHT*0.25f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+600, GameActivity.CAMERA_HEIGHT*0.13f));
		this.enemies.add(new ConstantXLaser(GameActivity.CAMERA_WIDTH*0.9f, GameActivity.CAMERA_HEIGHT, ConstantXLaser.goUpOrDown.goDown, 0.f));
		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH+1100, GameActivity.CAMERA_HEIGHT*0.8f, GameActivity.CAMERA_WIDTH*0.8f));
		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH+1100, GameActivity.CAMERA_HEIGHT*0.2f, GameActivity.CAMERA_WIDTH*0.8f));
		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH+1200, GameActivity.CAMERA_HEIGHT*0.5f, GameActivity.CAMERA_WIDTH*0.9f));
		this.enemies.add(new VerticalMovementEnemy((float)GameActivity.CAMERA_WIDTH+900,GameActivity.CAMERA_HEIGHT*0.7f, GameActivity.CAMERA_WIDTH*0.6f, 2f));
		this.enemies.add(new VerticalMovementEnemy((float)GameActivity.CAMERA_WIDTH+900,GameActivity.CAMERA_HEIGHT*0.3f, GameActivity.CAMERA_WIDTH*0.6f, 2f));
	}
}
