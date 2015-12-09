package unb.cic.poo.game2d.waves.fase1;

import java.util.ArrayList;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.enemies.ConstantXLaser;
import unb.cic.poo.game2d.enemies.Enemy;
import unb.cic.poo.game2d.waves.Wave;

public class Wave5 extends Wave {
	public Wave5(){
		this.enemies = new ArrayList<Enemy>();
		this.enemies.add(new ConstantXLaser(GameActivity.CAMERA_WIDTH*0.9f, GameActivity.CAMERA_HEIGHT, ConstantXLaser.goUpOrDown.goDown, 0.3f));
		this.enemies.add(new ConstantXLaser(GameActivity.CAMERA_WIDTH*0.9f, -10, ConstantXLaser.goUpOrDown.goUp, 0.3f));
		
		
	}
}
