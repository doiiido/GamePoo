package unb.cic.poo.game2d.parse;

import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.GameManager;

public class UpdateActivity extends Activity {
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------

	private static int position = 0;
	private static List<ParseObject> scoreList = null;
	
	//---------------------------------------------
    // CLASS LOGIC
    //---------------------------------------------
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		// Retorna a conta do usuário do Parse
		
		final ParseUser currentUser = ParseUser.getCurrentUser();
		
		if(!SignUpActivity.getSigned()){
			ParseQuery<ParseObject> qscore = ParseQuery.getQuery("HighScore");
			qscore.whereEqualTo("User", currentUser);
			qscore.getFirstInBackground(new GetCallback<ParseObject>() {
				@Override
				public void done(ParseObject highScore, ParseException e) {
					if (e == null) {
				    	highScore.put("Score", GameManager.getInstance().getSaveHandler().getStoryMaxScore());//GameActivity.getScore());
				    	highScore.put("Stage", GameManager.getInstance().getSaveHandler().getUnlockedFases());//GameActivity.getStage());
				    	highScore.saveInBackground();
					} else {
			            Log.d("Score", "Error: " + e.getMessage());
			        }
				}
			});
		}
		
		ParseQuery<ParseObject> posQuery = ParseQuery.getQuery("HighScore");
		posQuery.orderByDescending("Score");
		posQuery.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> sList, ParseException e) {
		        if (e == null) {
		        	scoreList = sList;
		            Log.d("Position", "Retrieved " + scoreList.size() + " scores");
		            for(int i = 0; i < scoreList.size(); i++){
		            	if ( ((HighScore) scoreList.get(i)).getUser() == currentUser){
		            		position = i + 1;
		            		break;
		            	}
		            }
		            Log.d("Score", Integer.toString(((HighScore) scoreList.get(0)).getScore()));
		            Log.d("Position", Integer.toString(position));
		        } else {
		            Log.d("Position", "Error: " + e.getMessage());
		        }
		    }
		});
		
		Intent intent = new Intent(UpdateActivity.this, ScoreTableActivity.class);
		startActivity(intent);
		finish();
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(UpdateActivity.this,	GameActivity.class);
		startActivity(intent);
		finish();
	}
	
	public static int getPosition(){
		return position;
	}
	
	public static List<ParseObject> getScores(){
		return scoreList;
	}
	
}

