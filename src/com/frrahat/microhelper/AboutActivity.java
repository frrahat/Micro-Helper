package com.frrahat.microhelper;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		EditText editText=(EditText) findViewById(R.id.editTextForAbout);
		editText.setText("Micro Helper is a simple app with some specific functionalities, and with hope that it will be helpful for students "
				+ "performing experiments with MTS-88.C microcomputer in the Course 'CSE 316 : Microprocessor And Microcontroller Sessional' in BUET."
				+ "\n\n"
				+ "\n\nApp icon courtesy : http://www.freepik.com"
				+ "\nImage of 0 and 1 courtesy : http://www.flaticon.com/authors/alessio-atzeni"
				+ "\n\n"
				+ "The source code of this app is open for all and can be found at : https://github.com/frrahat/Micro-Helper "
				+ "\n\nFor feedback or bug fixing please contact : fr.rahat@gmail.com"
				+ "\nThank you for using this app.");
		Linkify.addLinks(editText, Linkify.ALL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}*/
		return super.onOptionsItemSelected(item);
	}
}
