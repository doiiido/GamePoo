package unb.cic.poo.game2d;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;

public class VerticalMovementEnemy extends Enemy{
	private static final int COMMON_ENEMY_HEIGHT = GameActivity.CAMERA_HEIGHT/22; //32
	//private static final int COMMON_ENEMY_WIDTH = GameActivity.CAMERA_WIDTH/40; //32
	private static final int DEFAULT_COMMON_ENEMY_SPEED = 150;
	private static final int COMMON_ENEMY_LIFE = 1;
	private BulletType bulletType;
	private float pontosY[];
	private float pontosX[];
	private float timer;
	
	public VerticalMovementEnemy(float pX, float pY, ITiledTextureRegion texture, VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, texture, pVertexBufferObjectManager);
		this.life = COMMON_ENEMY_LIFE;
		this.speed = DEFAULT_COMMON_ENEMY_SPEED;
		//this.posX = pX;
		//this.posX = pY;
		
		this.pontosY[0] = (pY);
		this.pontosY[1] = 0f;
		this.pontosY[2] = (float)(22*COMMON_ENEMY_HEIGHT-COMMON_ENEMY_HEIGHT);
		this.pontosY[3] = (pY);
		this.pontosX[0] = (pX);
		this.pontosX[1] = (pX);
		this.pontosX[2] = (pX);
		this.pontosX[3] = (pX);
		
		this.setMovement();
this.registerUpdateHandler(new IUpdateHandler(){
			
			public void onUpdate(float pSecondsElapsed){
				timer -= pSecondsElapsed;
				if(timer <= 0){
					shoot();
					timer = 2f;
				}
			}

			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	private void setMovement() {
		float distance = GameManager.getInstance().getGameCamera().getHeight();
		float durationTime = distance/this.speed;
		
		Path path = new Path(4);
		for(int i = 0; i < 4; ++i){
			float posX = this.pontosX[i];
			float poxY = this.pontosY[i];
			
			path.to(posX, poxY);
		}
		
		PathModifier pathModifier = new PathModifier(durationTime, path);
		LoopEntityModifier loopEntityModifier = new LoopEntityModifier(pathModifier);
		this.registerEntityModifier(loopEntityModifier);
	}

	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shoot() {
		Bullet bullet = this.bulletType.getBullet(this.getX()+this.getWidth(), this.getY()+(this.getHeight()/2), false);
		GameManager.getInstance().getGameScene().attachChild(bullet);
	}

	@Override
	public void removeEnemy() {
		GameManager.getInstance().getEnemies().remove(this);
		GameManager.getInstance().getGameScene().detachChild(this);		
	}

}
