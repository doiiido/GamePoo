package unb.cic.poo.game2d.bullets;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.util.modifier.IModifier;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;

public class FlamethrowerBullet extends Bullet{
	public static final int BULLET_SPEED = 0;
	public static final int BULLET_DAMAGE = 1;
	private float totalElapsedSeconds = 0f;
	private IUpdateHandler flameHandler;
	private long[] mFrame1EndsInNanoseconds = {50, 50, 50, 50, 50, 50, 50, 50};
	private long[] mFrame2EndsInNanoseconds = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
	
	public FlamethrowerBullet(float pX, float pY, boolean isEnemyBullet) {
		super(pX+220, pY-80, ResourceManager.flameTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
	
		if(GameManager.getInstance().getPlayer().isScreenBeingPressed())
			this.animate(mFrame1EndsInNanoseconds, 11, 18, true);
		else
			this.animate(mFrame2EndsInNanoseconds, 1, 11, true);
		
		this.setRotation(90);
		this.setScale(3);
			
		this.damage = BULLET_DAMAGE;
		this.enemyBullet = isEnemyBullet;
		
		//UpdateHandler que cuida do tempo de duracao do laser
		this.flameHandler = new IUpdateHandler(){
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

	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
	}

	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
	}
}
