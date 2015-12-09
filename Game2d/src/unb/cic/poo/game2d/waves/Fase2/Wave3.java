package unb.cic.poo.game2d.waves.Fase2;

import java.util.ArrayList;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.enemies.*;
import unb.cic.poo.game2d.items.*;
import unb.cic.poo.game2d.waves.Wave;

public class Wave3 extends Wave{
	public Wave3(){
		super();
		this.enemies = new ArrayList<Enemy>();
		
		enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH + 100f, GameActivity.CAMERA_HEIGHT*0.1f, GameActivity.CAMERA_WIDTH*0.5f));
		enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH + 100f, GameActivity.CAMERA_HEIGHT*0.3f, GameActivity.CAMERA_WIDTH*0.5f));
		enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH + 100f, GameActivity.CAMERA_HEIGHT*0.5f, GameActivity.CAMERA_WIDTH*0.5f));
		enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH + 100f, GameActivity.CAMERA_HEIGHT*0.7f, GameActivity.CAMERA_WIDTH*0.5f));
		enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH + 100f, GameActivity.CAMERA_HEIGHT*0.9f, GameActivity.CAMERA_WIDTH*0.5f));
		
		enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH + 200f, GameActivity.CAMERA_HEIGHT*0.2f, GameActivity.CAMERA_WIDTH*0.7f));
		enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH + 200f, GameActivity.CAMERA_HEIGHT*0.4f, GameActivity.CAMERA_WIDTH*0.7f));
		enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH + 200f, GameActivity.CAMERA_HEIGHT*0.6f, GameActivity.CAMERA_WIDTH*0.7f));
		enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH + 200f, GameActivity.CAMERA_HEIGHT*0.8f, GameActivity.CAMERA_WIDTH*0.7f));
		
		enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH + 300f, GameActivity.CAMERA_HEIGHT*0.1f, GameActivity.CAMERA_WIDTH*0.9f));
		enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH + 300f, GameActivity.CAMERA_HEIGHT*0.3f, GameActivity.CAMERA_WIDTH*0.9f));
		enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH + 300f, GameActivity.CAMERA_HEIGHT*0.5f, GameActivity.CAMERA_WIDTH*0.9f));
		enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH + 300f, GameActivity.CAMERA_HEIGHT*0.7f, GameActivity.CAMERA_WIDTH*0.9f));
		enemies.add(new FreezedShootingEnemy(GameActivity.CAMERA_WIDTH + 300f, GameActivity.CAMERA_HEIGHT*0.9f, GameActivity.CAMERA_WIDTH*0.9f));
		
	}
}