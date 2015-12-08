package unb.cic.poo.game2d.bullets;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.util.modifier.IModifier;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;
import unb.cic.poo.game2d.SpaceshipAnimated;

public class FlamethrowerBullet extends Bullet{
	public static final int BULLET_SPEED = 0;
	public static final int BULLET_DAMAGE = 1;
	private float totalElapsedSeconds = 0f;
	private SpaceshipAnimated nave;
	private IUpdateHandler flameHandler;
	private long[] mFrame1EndsInNanoseconds = {50, 50, 50, 50, 50, 50, 50, 50};
	private long[] mFrame2EndsInNanoseconds = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
	
	public FlamethrowerBullet(SpaceshipAnimated nave, float pX, float pY, boolean isEnemyBullet) {
		super(pX+220, pY-80, ResourceManager.flameTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
	
		if(GameManager.getInstance().getPlayer().isScreenBeingPressed())
			this.animate(mFrame1EndsInNanoseconds, 11, 18, true);
		else
			this.animate(mFrame2EndsInNanoseconds, 1, 11, true);
		
		this.setRotation(90);
		this.setScale(3);
			
		this.nave = nave;
		this.damage = BULLET_DAMAGE;
		this.enemyBullet = isEnemyBullet;
		
		//UpdateHandler que cuida do tempo de duracao e do movimento do flamethrower
		this.flameHandler = new IUpdateHandler(){
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
		this.registerUpdateHandler(flameHandler);
	}
	
	@Override
	public void removeBullet() {
		GameManager.getInstance().getGameScene().detachChild(this);
		this.unregisterUpdateHandler(flameHandler);
		this.unregisterUpdateHandler(updateHandler);
	}
	
	@Override
	public void setMovement(float pX, float pY, boolean isEnemyBullet) {
	}
	
	public void setMovement(boolean isEnemyBullet) {
		this.setPosition(nave.getX() + nave.getWidth() + 220, nave.getY() + (nave.getHeight()/2) - 80);
	}

	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
	}

	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
	}
}
