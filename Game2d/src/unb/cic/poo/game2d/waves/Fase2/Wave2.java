package unb.cic.poo.game2d.waves.Fase2;

import java.util.ArrayList;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.enemies.*;
import unb.cic.poo.game2d.items.LifeItemGen;
import unb.cic.poo.game2d.waves.Wave;

public class Wave2 extends Wave{
	
	public Wave2(){
		super();
		this.enemies = new ArrayList<Enemy>();
		
		enemies.add(new ChasingYEnemy(GameActivity.CAMERA_WIDTH+100, GameActivity.CAMERA_HEIGHT*0.3f, GameActivity.CAMERA_WIDTH*0.3f, 250));
		enemies.add(new ChasingYEnemy(GameActivity.CAMERA_WIDTH+100, GameActivity.CAMERA_HEIGHT*0.5f, GameActivity.CAMERA_WIDTH*0.5f, 340));
		enemies.add(new ChasingYEnemy(GameActivity.CAMERA_WIDTH+100, GameActivity.CAMERA_HEIGHT*0.7f, GameActivity.CAMERA_WIDTH*0.7f, 300));
		enemies.add(new ChasingYEnemy(GameActivity.CAMERA_WIDTH+100, GameActivity.CAMERA_HEIGHT*0.6f, GameActivity.CAMERA_WIDTH*0.9f, 420));
		enemies.add(new VerticalMovementLaser(GameActivity.CAMERA_WIDTH+200f, GameActivity.CAMERA_HEIGHT*0.4f, GameActivity.CAMERA_WIDTH*0.8f, 1.1f));
		enemies.add(new VerticalMovementLaser(GameActivity.CAMERA_WIDTH+150f, GameActivity.CAMERA_HEIGHT*0.9f, GameActivity.CAMERA_WIDTH*0.8f, 0.9f, new LifeItemGen()));
		
	}

}
