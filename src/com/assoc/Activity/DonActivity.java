package com.assoc.Activity;

import com.demo.slidingmenu_tabhostviewpager.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class DonActivity extends Activity implements OnSeekBarChangeListener {

      
       Button btnSend;
       String email, subject, message, attachmentFile;
       SeekBar seekbar1;
       int value;
       TextView result;
       int columnIndex;

       @Override
       protected void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.fragment_don);
              
              
               result = (TextView) findViewById(R.id.TextViewdonresult);
              TextView textemail = (TextView) findViewById(R.id.textViewdon);
              TextView textville = (TextView) findViewById(R.id.textViewdon2);
              SeekBar seekbar1 = (SeekBar) findViewById(R.id.seekBar1);
              EditText textsituation = (EditText) findViewById(R.id.editTextmntdon);
              TextView textdatens = (TextView) findViewById(R.id.editTextMessage);
              
              btnSend = (Button) findViewById(R.id.buttonSend);
              seekbar1.setOnSeekBarChangeListener(this);
       }

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		 value = progress;
         result.setText (value+" Euro");
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}


       

}
