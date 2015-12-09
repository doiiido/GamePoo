package unb.cic.poo.game2d.parse;

import java.util.List;

import com.example.game2d.R;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import unb.cic.poo.game2d.GameActivity;

public class ScoreTableActivity extends Activity {
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	Button logout;
	Button back;
	private int position = 0;
	private static List<ParseObject> scoreList = null;
	
	//---------------------------------------------
    // CLASS LOGIC
    //---------------------------------------------
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		// Determina o xml
		setContentView(R.layout.welcome);
 
		// Retorna a conta do usuário do Parse
		final ParseUser currentUser = ParseUser.getCurrentUser();
		String struser = currentUser.getUsername().toString();

		position = UpdateActivity.getPosition();
		scoreList = UpdateActivity.getScores();
		
		TextView txtpos = (TextView) findViewById(R.id.txtwelcome);
		if(position != 0){
			txtpos.setText(Integer.toString(position)+"º");
		} else {
			txtpos.setText("Let's start playing!");
		}
		Log.d("PosTable", Integer.toString(position));
 
		// Localiza as caixas de texto		
		TextView txtuser = (TextView) findViewById(R.id.txtuser);
		txtuser.setText("You are logged in as " + struser);
		
		/*if(scoreList != null){
			TableLayout userTable = (TableLayout) findViewById(R.id.tableLayout1);
			TableLayout scoreTable = (TableLayout) findViewById(R.id.tableLayout2);
			TableLayout stageTable = (TableLayout) findViewById(R.id.tableLayout3);		
			
			for(int i = 0; i < scoreList.size(); i++){
	        	addRow(userTable, ((HighScore) scoreList.get(i)).getUser().getUsername());
	        	addRow(scoreTable, Integer.toString(((HighScore) scoreList.get(i)).getScore()));
	        	addRow(stageTable, Integer.toString(((HighScore) scoreList.get(i)).getStage()));
	        }
		}*/
 
		// Localiza os botoes
		logout = (Button) findViewById(R.id.logout);
		back = (Button) findViewById(R.id.back);
 
		// Logout Button Click Listener
		logout.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				ParseUser.logOut();
				Intent intent = new Intent(ScoreTableActivity.this,	LoginSignupActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		// Back Button Click Listener
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(ScoreTableActivity.this,	GameActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(ScoreTableActivity.this,	GameActivity.class);
		startActivity(intent);
		finish();
	}
	
	private void addRow(TableLayout tLayout, String text){
		TableRow tr = new TableRow(this);
		TextView txt = new TextView(this);
		
		txt.setText(text);
		tr.addView(txt);
		tLayout.addView(tr);
	}
	
	/*
	 <TableLayout
        android:id="@+id/tableLayout1"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_above="@id/logout"
        android:layout_below="@id/txtuser"
        android:layout_toLeftOf="@id/tableLayout2" >

        <TextView
                android:id="@+id/textView1"
                android:layout_width="fill_parent"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:text="@string/User" />
    </TableLayout>
    
    <TableLayout
        android:id="@+id/tableLayout2"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_above="@id/logout"
        android:layout_below="@id/txtuser"
        android:layout_toLeftOf="@id/tableLayout3" >

        <TextView
                android:id="@+id/textView2"
                android:layout_width="fill_parent"
                android:layout_height="wrap_content"
                android:gravity="center"
                android:text="@string/Score" />
    </TableLayout>
    
    <TableLayout
        android:id="@+id/tableLayout3"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_above="@id/logout"
        android:layout_below="@id/txtuser" >
        
        <TextView
	        android:id="@+id/textView3"
	        android:layout_width="fill_parent"
	        android:layout_height="wrap_content"
	        android:gravity="center"
	        android:text="@string/Stage" />
    </TableLayout>
	 */
	
}
