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

import unb.cic.poo.game2d.enemies.Enemy;
import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.Player;
import unb.cic.poo.game2d.ResourceManager;
import unb.cic.poo.game2d.SpaceshipAnimated;

public class LaserBullet extends Bullet{
	public static final int BULLET_HEIGHT = GameActivity.CAMERA_HEIGHT/180; // 4
	public static final int BULLET_WIDTH = GameActivity.CAMERA_WIDTH;
	public static final int BULLET_DAMAGE = 1;
	private float totalElapsedSeconds = 0f;
	private IUpdateHandler laserHandler;
	private SpaceshipAnimated nave;
	
	public LaserBullet(SpaceshipAnimated nave, float pX, float pY, boolean isEnemyBullet) {
		super((isEnemyBullet)? pX-1280:pX, pY-5*BULLET_HEIGHT, (isEnemyBullet)? ResourceManager.enemyLaserBulletTextureRegion:ResourceManager.laserBulletTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		
		this.animate(120);
		this.damage = BULLET_DAMAGE;
		this.nave = nave;
		this.enemyBullet = isEnemyBullet;
		
		//UpdateHandler que cuida do tempo de duracao e do movimento do laser
		this.laserHandler = new IUpdateHandler(){
			public void onUpdate(float pSecondsElapsed){
				totalElapsedSeconds += pSecondsElapsed;
				GameManager.getInstance().getGameEngine().runOnUpdateThread(new Runnable() {			
					@Override
					public void run() {
						if(totalElapsedSeconds > 0.3f){
							removeBullet();
						}
						setMovement(enemyBullet);
					}
				});
			}

			@Override
			public void reset() {
			}
		};
		this.registerUpdateHandler(laserHandler);
	}
	
	public LaserBullet(SpaceshipAnimated nave, float pX, float pY, boolean isEnemyBullet, int angleOfTheLaser) {
		this(nave, pX, pY, isEnemyBullet);
		this.setRotation(angleOfTheLaser);
	}

	@Override
	public void removeBullet() {
		GameManager.getInstance().getGameScene().detachChild(this);
		this.unregisterUpdateHandler(laserHandler);
		this.unregisterUpdateHandler(updateHandler);
	}

	@Override
	public void setMovement(float pX, float pY, boolean isEnemyBullet) {
	}
	
	public void setMovement(boolean isEnemyBullet) {
		if(!isEnemyBullet){
			this.setPosition(nave.getX() + nave.getWidth() , nave.getY() + (nave.getHeight()/2) - 5*BULLET_HEIGHT);
		} else{
			this.setPosition(nave.getX() - 1280 , nave.getY() + (nave.getHeight()/2) - 5*BULLET_HEIGHT);
		}
	}

	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
	}

	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {	
	}
}
