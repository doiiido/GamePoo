package unb.cic.poo.game2d;



import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;

import unb.cic.poo.game2d.Bullet;
import unb.cic.poo.game2d.Enemy;
import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;

public class LaserBullet extends Bullet{
	public static final int BULLET_HEIGHT = GameActivity.CAMERA_HEIGHT/180; // 4
	public static float bullet_width;
	public static final int BULLET_SPEED = 0;
	public static final int BULLET_DAMAGE = 1;
	private float totalElapsedSeconds = 0f;
	
	public LaserBullet(float pX, float pY, boolean isEnemyBullet) {
		super(pX, pY-BULLET_HEIGHT, ResourceManager.laserBulletTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		
		this.damage = BULLET_DAMAGE;
		this.bullet_width = GameActivity.CAMERA_WIDTH - pX;
		
		this.registerUpdateHandler(new IUpdateHandler() {
			
			@Override
			public void reset() {
			}
			
			@Override
			public void onUpdate(float pSecondsElapsed) {
				totalElapsedSeconds += pSecondsElapsed;
				GameManager.getInstance().getGameEngine().runOnUpdateThread(new Runnable() {			
					@Override
					public void run() {
						if(totalElapsedSeconds > 1.0f){
							removeBullet();
						}
					}
				});
			}
		});
		
		
		
	}

	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
	}

	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
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

}
