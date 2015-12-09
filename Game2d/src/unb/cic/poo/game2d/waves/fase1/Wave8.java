package unb.cic.poo.game2d.waves.fase1;

import java.util.ArrayList;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.enemies.CommonEnemy;
import unb.cic.poo.game2d.enemies.ConstantXLaser;
import unb.cic.poo.game2d.enemies.Enemy;
import unb.cic.poo.game2d.enemies.FreezedShootingEnemy;
import unb.cic.poo.game2d.enemies.VerticalMovementEnemy;
import unb.cic.poo.game2d.waves.Wave;

public class Wave8 extends Wave{
	public Wave8(){
		super();
		this.enemies = new ArrayList<Enemy>();
		
		this.enemies.add(new VerticalMovementEnemy((float)GameActivity.CAMERA_WIDTH+500,GameActivity.CAMERA_HEIGHT*0.7f, GameActivity.CAMERA_WIDTH*0.6f, 2f));
		this.enemies.add(new VerticalMovementEnemy((float)GameActivity.CAMERA_WIDTH+550,GameActivity.CAMERA_HEIGHT*0.5f, GameActivity.CAMERA_WIDTH*0.6f, 2f));
		this.enemies.add(new VerticalMovementEnemy((float)GameActivity.CAMERA_WIDTH+600,GameActivity.CAMERA_HEIGHT*0.3f, GameActivity.CAMERA_WIDTH*0.6f, 2f));
		
		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH+1100, GameActivity.CAMERA_HEIGHT*0.8f, GameActivity.CAMERA_WIDTH*0.8f));
		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH+1100, GameActivity.CAMERA_HEIGHT*0.2f, GameActivity.CAMERA_WIDTH*0.8f));
		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH+1200, GameActivity.CAMERA_HEIGHT*0.5f, GameActivity.CAMERA_WIDTH*0.9f));
		
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+900, GameActivity.CAMERA_HEIGHT*0.1f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+900, GameActivity.CAMERA_HEIGHT*0.22f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+900, GameActivity.CAMERA_HEIGHT*0.34f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+900, GameActivity.CAMERA_HEIGHT*0.46f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+900, GameActivity.CAMERA_HEIGHT*0.58f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+900, GameActivity.CAMERA_HEIGHT*0.70f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+900, GameActivity.CAMERA_HEIGHT*0.82f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+900, GameActivity.CAMERA_HEIGHT*0.95f));
		
		this.enemies.add(new ConstantXLaser(GameActivity.CAMERA_WIDTH*0.9f, GameActivity.CAMERA_HEIGHT, ConstantXLaser.goUpOrDown.goDown, 0.f));
		this.enemies.add(new ConstantXLaser(GameActivity.CAMERA_WIDTH*0.9f, GameActivity.CAMERA_HEIGHT*0, ConstantXLaser.goUpOrDown.goUp, 0.f));

	}	
}
