package unb.cic.poo.game2d.bullets;



import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;

import unb.cic.poo.game2d.Enemy;
import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;

public class LaserBullet extends Bullet{
	public static final int BULLET_HEIGHT = GameActivity.CAMERA_HEIGHT/180; // 4
	public static final int BULLET_WIDTH = GameActivity.CAMERA_WIDTH;
	public static final int BULLET_SPEED = 0;
	public static final int BULLET_DAMAGE = 1;
	private float totalElapsedSeconds = 0f;
	private IUpdateHandler laserHandler;
	
	public LaserBullet(float pX, float pY, boolean isEnemyBullet) {
		super(pX, pY-5*BULLET_HEIGHT, ResourceManager.laserBulletTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		
		this.animate(120);
		this.damage = BULLET_DAMAGE;
		
		this.laserHandler = new IUpdateHandler(){
			public void onUpdate(float pSecondsElapsed){
				totalElapsedSeconds += pSecondsElapsed;
				GameManager.getInstance().getGameEngine().runOnUpdateThread(new Runnable() {			
					@Override
					public void run() {
						if(totalElapsedSeconds > 0.3f){
							removeBullet();
						}
					}
				});
			}

			@Override
			public void reset() {
			}
		};
		this.registerUpdateHandler(laserHandler);
	}

	@Override
	public void removeBullet() {
		GameManager.getInstance().getGameScene().detachChild(this);
	}

	@Override
	public void setMovement(float pX, float pY, boolean isEnemyBullet) {
	}

	@Override
	public boolean checkEnemyHit() {
		
		for(Enemy enemy : GameManager.getInstance().getEnemies()){
			if(this.collidesWith(enemy)){
				enemy.decrementLife(this.damage);
				return true;
			}
		}
		return false;
	}

	@Override
	public void OnEnemyHit() {
	}

	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
		// TODO Auto-generated method stub
		
	}
}
