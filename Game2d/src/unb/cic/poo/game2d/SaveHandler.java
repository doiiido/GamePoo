package unb.cic.poo.game2d;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveHandler {
	private static final String PREFS_NAME = "GAME_USERDATA";
	
	private static final String UNLOCKED_FASES_KEY = "unlockedFasesKey";
	private static final String CURRENT_FASE_KEY = "currentFaseKey";
	private static final String MAX_STORY_SCORE = "maxStoryScoreKey";
	private static final String[] FASES_SCORE = {"fase1Key", "fase2Key", "fase3Key", "fase4Key"};
	
	private int unlockedFases;
	private int currentFase;
	private int maxStoryScore;
	private int fasesScore[]; 
	
	private SharedPreferences mSettings;
	private SharedPreferences.Editor mEditor;

	public synchronized void init(Context pContext) {
		if(mSettings == null){
			mSettings = pContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
			
			mEditor = mSettings.edit();
			
			unlockedFases = mSettings.getInt(UNLOCKED_FASES_KEY, 1);
			currentFase = mSettings.getInt(CURRENT_FASE_KEY, 1);
			maxStoryScore = mSettings.getInt(MAX_STORY_SCORE, 0);
			fasesScore = new int[FASES_SCORE.length];
			for(int i = 0; i < FASES_SCORE.length; i++){
				fasesScore[i] = mSettings.getInt(FASES_SCORE[i], 0);
			}
		}
	}
	
	public synchronized void unlocknNextFase(){
		this.unlockedFases++;
		
		mEditor.putInt(UNLOCKED_FASES_KEY, this.unlockedFases);
		mEditor.commit();
	}
	
	public synchronized int getUnlockedFases() {
		return unlockedFases;
	}
	public synchronized void setUnlockedFases(int unlockedFases) {
		this.unlockedFases = unlockedFases;
		
		mEditor.putInt(UNLOCKED_FASES_KEY, this.unlockedFases);
		mEditor.commit();
	}
	
	public synchronized int getCurrentFase() {
		return currentFase;
	}
	
	public synchronized void setCurrentFase(int currentFase) {
		this.currentFase = currentFase;
		
		mEditor.putInt(CURRENT_FASE_KEY, this.currentFase);
		mEditor.commit();
	}
	
	public synchronized void nextFase(){
		this.currentFase++;
		if(this.currentFase > this.unlockedFases){
			this.unlocknNextFase();
		}
		
		mEditor.putInt(CURRENT_FASE_KEY, this.currentFase);
		mEditor.commit();
	}
	
	public synchronized int getStoryMaxScore() {
		return maxStoryScore;
	}
	public synchronized void setStoryMaxScore(int maxScore) {
		this.maxStoryScore = maxScore;
		mEditor.putInt(MAX_STORY_SCORE, this.maxStoryScore);
	}
	
	public synchronized SharedPreferences getmSettings() {
		return mSettings;
	}

	public synchronized void setmSettings(SharedPreferences mSettings) {
		this.mSettings = mSettings;
	}
	public synchronized SharedPreferences.Editor getmEditor() {
		return mEditor;
	}

	public synchronized void setmEditor(SharedPreferences.Editor mEditor) {
		this.mEditor = mEditor;
	}

	public int[] getFasesScore() {
		return fasesScore;
	}

	public void setFasesScore(int fasesScore[]) {
		this.fasesScore = fasesScore;
	}
	
	public synchronized void setFaseScore(int faseIndex, int score){
		this.fasesScore[faseIndex] += score;
		
		mEditor.putInt(FASES_SCORE[faseIndex], fasesScore[faseIndex]);
		mEditor.commit();
		
		int totalScore = 0;
		
		for(int i = 0; i < fasesScore.length;i++){
			totalScore = fasesScore[i];
		}
		
		if(totalScore > this.maxStoryScore){
			this.setStoryMaxScore(totalScore);
		}
	}
}
