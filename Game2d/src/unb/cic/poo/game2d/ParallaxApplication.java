package unb.cic.poo.game2d;

import android.annotation.SuppressLint;

import java.util.ArrayList;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.Entity;
import org.andengine.entity.shape.IAreaShape;
import org.andengine.opengl.util.GLState;

@SuppressLint("WrongCall")
public class ParallaxApplication extends Entity{
	
    // Constants
    
    // Fields
    
    private final ArrayList<ParallaxEntity> mParallaxEntities = new ArrayList<ParallaxEntity>();
    private int mParallaxEntityCount;

    protected float mParallaxValue;
    protected float mParallaxScrollValue;
   
    protected float mParallaxChangePerSecond;
   
    protected float mParallaxScrollFactor = 0.2f;
   
    private Camera mCamera;
   
    private float mCameraPreviousPos;
    private float mCameraOffsetPos;
   
    private float   mLevelScale = 0;
   
    private boolean mIsScrollable = false;
    private static boolean mParallaxOnAxisX = true;

   
    // Constructors
    public ParallaxApplication() {
    }

    public ParallaxApplication(final Camera camera, final boolean mIsScrollable){
            this.mCamera = camera;
            this.mIsScrollable = mIsScrollable;
           
            mCameraPreviousPos = camera.getCenterX();
    }
   
    public ParallaxApplication(final Camera camera, final boolean mIsScrollable, final int mLevelScale){
            this.mCamera = camera;
            this.mIsScrollable = mIsScrollable;
            this.mLevelScale = mLevelScale;
           
            mCameraPreviousPos = camera.getCenterX();
    }
   
    public ParallaxApplication(final Camera camera, final boolean mIsScrollable, final int mLevelWidth, final boolean mParallaxOnAxisX){
            this.mCamera = camera;
            this.mIsScrollable = mIsScrollable;
            this.mLevelScale = mLevelWidth;
            ParallaxApplication.mParallaxOnAxisX = mParallaxOnAxisX;
           
            if(mParallaxOnAxisX)    mCameraPreviousPos = camera.getCenterX();
            else                                    mCameraPreviousPos = camera.getCenterY();
    }
   
    // Getter & Setter
    public void setParallaxValue(final float pParallaxValue) {
            this.mParallaxValue = pParallaxValue;
    }
   
    public void setParallaxChangePerSecond(final float pParallaxChangePerSecond) {
            this.mParallaxChangePerSecond = pParallaxChangePerSecond;
    }

    public void setParallaxScrollFactor(final float pParallaxScrollFactor){
            this.mParallaxScrollFactor = pParallaxScrollFactor;
    }
   
    public void setParallaxAxisX(final boolean mParallaxOnAxisX){
            ParallaxApplication.mParallaxOnAxisX = mParallaxOnAxisX;
    }
   
    // Methods for/from SuperClass/Interfaces
    @Override
    public void onManagedDraw(GLState pGLState, Camera pCamera) {
            super.preDraw(pGLState, pCamera);

           
            final float parallaxValue = this.mParallaxValue;
            final float parallaxScrollValue = this.mParallaxScrollValue;
            final ArrayList<ParallaxEntity> parallaxEntities = this.mParallaxEntities;

            for(int i = 0; i < this.mParallaxEntityCount; i++) {
                    if(parallaxEntities.get(i).mIsScrollable){
                            parallaxEntities.get(i).onDraw(pGLState, pCamera, parallaxScrollValue, mLevelScale, mParallaxOnAxisX);
                    } else {
                            parallaxEntities.get(i).onDraw(pGLState, pCamera, parallaxValue, mLevelScale, mParallaxOnAxisX);
                    }

            }
    }
   
    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
           
            final float cameraCenterPos = (mParallaxOnAxisX)? this.mCamera.getCenterX() : this.mCamera.getCenterY();
           
            if(mIsScrollable && mCameraPreviousPos != cameraCenterPos){
                            mCameraOffsetPos = mCameraPreviousPos - cameraCenterPos;
                            mCameraPreviousPos = cameraCenterPos;
                           
                            this.mParallaxScrollValue += mCameraOffsetPos * this.mParallaxScrollFactor;
                            mCameraOffsetPos = 0;
            }
           
            this.mParallaxValue += this.mParallaxChangePerSecond * pSecondsElapsed;
            super.onManagedUpdate(pSecondsElapsed);
    }

    // Methods
    
    public void attachParallaxEntity(final ParallaxEntity parallaxEntity) {
            this.mParallaxEntities.add(parallaxEntity);
            this.mParallaxEntityCount++;
    }

    public boolean detachParallaxEntity(final ParallaxEntity pParallaxEntity) {
            this.mParallaxEntityCount--;
            final boolean success = this.mParallaxEntities.remove(pParallaxEntity);
            if(!success) {
                    this.mParallaxEntityCount++;
            }
            return success;
    }
   
    // Inner and Anonymous Classes
    
    public static class ParallaxEntity {
    
            // Constants
    
            // Fields
    
            final float mParallaxFactor;
            final IAreaShape mAreaShape;
            final boolean mIsScrollable;

            final float shapeScaled;

            // Constructors
    
            public ParallaxEntity(final float pParallaxFactor, final IAreaShape pAreaShape) {
                    this.mParallaxFactor = pParallaxFactor;
                    this.mAreaShape = pAreaShape;
                    this.mIsScrollable = false;
                   
                    shapeScaled = (mParallaxOnAxisX)? this.mAreaShape.getWidthScaled() : this.mAreaShape.getHeightScaled();
            }
           
            public ParallaxEntity(final float pParallaxFactor, final IAreaShape pAreaShape, final boolean mIsScrollable) {
                    this.mParallaxFactor = pParallaxFactor;
                    this.mAreaShape = pAreaShape;
                    this.mIsScrollable = mIsScrollable;

                    shapeScaled = (mParallaxOnAxisX)? this.mAreaShape.getWidthScaled() : this.mAreaShape.getHeightScaled();
            }
           
            public ParallaxEntity(final float pParallaxFactor, final IAreaShape pAreaShape, final boolean mIsScrollable, final int mReduceFrequency) {
                    this.mParallaxFactor = pParallaxFactor;
                    this.mAreaShape = pAreaShape;
                    this.mIsScrollable = mIsScrollable;

                    shapeScaled = (mParallaxOnAxisX)? this.mAreaShape.getWidthScaled() * mReduceFrequency : this.mAreaShape.getHeightScaled() * mReduceFrequency;
            }

            // Getter & Setter
    
            // Methods for/from SuperClass/Interfaces
    
            // Methods
    
            public void onDraw(final GLState pGLState, final Camera pCamera, final float pParallaxValue, final float mLevelScale, final boolean mParallaxOnAxisX) {
                    pGLState.pushModelViewGLMatrix();
                    {
                            float range;
                           
                            if(mLevelScale != 0){
                                    range = mLevelScale;
                            } else {
                                    range = (mParallaxOnAxisX)? pCamera.getWidth() : pCamera.getHeight();
                            }

                            float baseOffset = (pParallaxValue * this.mParallaxFactor) % shapeScaled;

                            while(baseOffset > 0) {
                                    baseOffset -= shapeScaled;
                            }
                            if(mParallaxOnAxisX)    pGLState.translateModelViewGLMatrixf(baseOffset, 0, 0);
                            else                                    pGLState.translateModelViewGLMatrixf(0, baseOffset, 0);

                            float currentMaxDimension = baseOffset;
                           
                            do {
                                    this.mAreaShape.onDraw(pGLState, pCamera);
                                   
                                    if(mParallaxOnAxisX)    pGLState.translateModelViewGLMatrixf(shapeScaled - 1, 0, 0);
                                    else                                    pGLState.translateModelViewGLMatrixf(0, shapeScaled - 1, 0);
                                   
                                    currentMaxDimension += shapeScaled;
                            } while(currentMaxDimension < range);
                    }
                    pGLState.popModelViewGLMatrix();
            }

            // Inner and Anonymous Classes
    
    }
    
}