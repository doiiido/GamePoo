package unb.cic.poo.game2d;



import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.modifier.IModifier;

import unb.cic.poo.game2d.Bullet;
import unb.cic.poo.game2d.Enemy;
import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;

public class LaserBullet extends Bullet implements IOnSceneTouchListener{
	public static final int BULLET_HEIGHT = GameActivity.CAMERA_HEIGHT/180; // 4
	public static float bullet_width;
	public static final int BULLET_SPEED = 0;
	public static final int BULLET_DAMAGE = 1;
	private float totalElapsedSeconds = 0f;
	
	private MoveByModifier lastMoveByModifier; // Armazena o ultimo modificador de movimento utilizado na classe.
	private float targetX; // targetX e targetY armazenam as coordenadas para as quais a nave esta se movendo.
	private float targetY;
	
	public LaserBullet(float pX, float pY, boolean isEnemyBullet) {
		super(pX, pY-5*BULLET_HEIGHT, ResourceManager.laserBulletTextureRegion, 
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
						if(totalElapsedSeconds > 0.7f){
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
	
	public float getTargetY() {
		return targetY;
	}

	public void setTargetY(float targetY) {
		this.targetY = targetY;
	}

	public float getTargetX() {
		return targetX;
	}

	public void setTargetX(float targetX) {
		this.targetX = targetX;
	}
	
	public MoveByModifier getLastMoveByModifier() {
		return lastMoveByModifier;
	}

	public void setLastMoveByModifier(MoveByModifier lastMoveByModifier) {
		this.lastMoveByModifier = lastMoveByModifier;
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		if(pScene == GameManager.getInstance().getGameScene()){
			if(pSceneTouchEvent.getX() <= (GameManager.getInstance().getGameCamera().getWidth()/2) - GameManager.getInstance().DX){
					//Update dos atributos do objeto Player.
				
					if(this.getLastMoveByModifier() != null){
						this.unregisterEntityModifier(this.getLastMoveByModifier());
					}
					this.setTargetX(pSceneTouchEvent.getX() + GameManager.getInstance().getPlayer().getWidth());
					this.setTargetY(pSceneTouchEvent.getY());
					
				
					//	Calcula o tempo de duração de cada movimento com base na velocidade configurada no Player.
					//	Foi feita uma simplificação no cálculo da distância para evitar o uso de raiz quadrada.
					//	Além disso, para evitar calculos quando o valor float é muito pequeno, foi utilizado uma duração 
					//	fixa para quando as distâncias são muito curtas.
					
					float durationTime;
					float deltaX = pSceneTouchEvent.getX()-this.getX() + GameManager.getInstance().DX;
					float deltaY = pSceneTouchEvent.getY()-this.getY() + GameManager.getInstance().DY;
					float absDistance = Math.abs(deltaX) + Math.abs(deltaY);
					if(absDistance <= 0.5){
						durationTime = 0.0001f;
					}
					else{
						durationTime = (absDistance)/GameManager.getInstance().getPlayer().getSpeed();
					}
					
					// Utiliza um MoveByModifier para alterar o caminho seguido pela nave.
					MoveByModifier moveByModifier = new MoveByModifier(durationTime, deltaX, deltaY);
					this.setLastMoveByModifier(moveByModifier);
					this.registerEntityModifier(moveByModifier);
			}
		}
		return false;
	}
}
