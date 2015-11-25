package unb.cic.poo.game2d.enemies;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.IEntityFactory;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.particle.ParticleSystem;
import org.andengine.entity.particle.emitter.PointParticleEmitter;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.content.ClipData.Item;
import unb.cic.poo.game2d.EnemyHandler;
import unb.cic.poo.game2d.ResourceManager;
import unb.cic.poo.game2d.SpaceshipAnimated;
import unb.cic.poo.game2d.items.ItemGen;
import unb.cic.poo.game2d.items.LaserBulletGen;
import unb.cic.poo.game2d.items.LaserBulletItem;
import unb.cic.poo.game2d.scenes.GameScene;

//Superclasse dos inimigos.

public abstract class Enemy extends SpaceshipAnimated implements IEntityModifierListener{
	public boolean movementFinished;
	protected boolean dropsItem;
	protected ItemGen ItemDropped;
	
	public static final int INFINITY = 2000000;
	
	public Enemy(float pX, float pY, ITiledTextureRegion texture,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, texture, pVertexBufferObjectManager);
		movementFinished = false;
		dropsItem = true;
		ItemDropped = new LaserBulletGen();
		this.registerUpdateHandler(new EnemyHandler(this));
	}
	
	public void decrementLife(int decrement){
		super.decrementLife(decrement);
		if(this.life <= 0){
			this.kill();
			ResourceManager.mXplosion.play();
		}
	}
	
	public void kill(){
		if(this.dropsItem){
			if(this.ItemDropped != null){
				ItemDropped.getItem(this.getX(), this.getY()+(this.getHeight()/2)).drop();
			}
			else{
				//código para selecionar item aleatório aqui.
			}
		}
		createExplosion(this.getX(), this.getY(), this.getParent());
		this.removeEnemy();
		this.clearEntityModifiers();
	}

	@Override
	public abstract void shoot();
	
	public abstract void removeEnemy();
	
	private static void createExplosion(final float posX, final float posY,
			final IEntity target) {

		// Podemos tentar aplicar a ideia de despixeliza��o, por�m n�o consegui divider o sprite em pequenos peda�os
		int mNumPart = 1; // n�mero de part�culas/sprites que ser�o repetidos
		float mTimePart = (float) 0.5; // tempo que as part�culas permanecer�o na tela
									   // 1.6 corresponde ao tempo da anima��o aplicada

		PointParticleEmitter particleEmitter = new PointParticleEmitter(posX,
				posY);
		
		IEntityFactory<AnimatedSprite> recFact = new IEntityFactory<AnimatedSprite>() {

			@Override
			public AnimatedSprite create(float pX, float pY) {
				AnimatedSprite rect = new AnimatedSprite(posX, posY, ResourceManager.explosionTextureRegion,
						ResourceManager.getInstance().engine.getVertexBufferObjectManager());
				rect.animate(40);
				return rect;
			}

		};
		
		// Rate m�nimo e m�ximo da emiss�o de part�culas
		final ParticleSystem<AnimatedSprite> particleSystem = new ParticleSystem<AnimatedSprite>(
				recFact, particleEmitter, 100, 500, mNumPart);

		// Velocidade das part�culas, m�nima e m�xima, em X e Y
		//particleSystem
				//.addParticleInitializer(new VelocityParticleInitializer<AnimatedSprite>(
				//		-50, 50, -50, 50));

		// N�o modificar direto aqui, mas na vari�vel mTimePart. Aplica o Fade Out 
		particleSystem
				.addParticleModifier(new AlphaParticleModifier<AnimatedSprite>(0,
						0.6f * mTimePart, 1, 0));
		
		// Qual o �ngulo de rota��o das part�culas
		//particleSystem
		//		.addParticleModifier(new RotationParticleModifier<AnimatedSprite>(0,
			//			mTimePart, 0, 360));

		target.attachChild(particleSystem);
		target.registerUpdateHandler(new TimerHandler(mTimePart,
				new ITimerCallback() {
					@Override
					public void onTimePassed(final TimerHandler pTimerHandler) {
						particleSystem.detachSelf();
						target.sortChildren();
						target.unregisterUpdateHandler(pTimerHandler);
					}
				}));

	}
}