package unb.cic.poo.game2d.waves.fase1;

import java.util.ArrayList;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.enemies.CommonEnemy;
import unb.cic.poo.game2d.enemies.ConstantXLaser;
import unb.cic.poo.game2d.enemies.Enemy;
import unb.cic.poo.game2d.enemies.FreezedShootingEnemy;
import unb.cic.poo.game2d.enemies.VerticalMovementEnemy;
import unb.cic.poo.game2d.enemies.VerticalMovementLaser;
import unb.cic.poo.game2d.enemies.VerticalShooting;
import unb.cic.poo.game2d.items.MachineGunGen;
import unb.cic.poo.game2d.waves.Wave;

public class Wave4 extends Wave{
	public Wave4(){
		super();
		this.enemies = new ArrayList<Enemy>();
		this.enemies.add(new ConstantXLaser(GameActivity.CAMERA_WIDTH*0.9f, (float) (GameActivity.CAMERA_HEIGHT), ConstantXLaser.goUpOrDown.goUp, 0.5f, new MachineGunGen()));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH, (float) (GameActivity.CAMERA_HEIGHT*0.1)));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH, (float) (GameActivity.CAMERA_HEIGHT*0.5)));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH, (float) (GameActivity.CAMERA_HEIGHT*0.9)));
		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH+200, GameActivity.CAMERA_HEIGHT*0.25f, GameActivity.CAMERA_WIDTH*0.8f));
		this.enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH+200, GameActivity.CAMERA_HEIGHT*0.75f, GameActivity.CAMERA_WIDTH*0.8f));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+500, (float) (GameActivity.CAMERA_HEIGHT*0.1)));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+500, (float) (GameActivity.CAMERA_HEIGHT*0.5)));
		this.enemies.add(new CommonEnemy(GameActivity.CAMERA_WIDTH+500, (float) (GameActivity.CAMERA_HEIGHT*0.9)));
		this.enemies.add(new VerticalShooting(GameActivity.CAMERA_WIDTH+700, (float) (GameActivity.CAMERA_HEIGHT*0.8),GameActivity.CAMERA_WIDTH, 0));
		this.enemies.add(new VerticalMovementLaser(GameActivity.CAMERA_WIDTH+700, (float) (GameActivity.CAMERA_HEIGHT*0.6), GameActivity.CAMERA_WIDTH*0.9f));
		this.enemies.add(new VerticalShooting(GameActivity.CAMERA_WIDTH+700, (float) (GameActivity.CAMERA_HEIGHT*0.2),GameActivity.CAMERA_WIDTH, 0));
	}
}
