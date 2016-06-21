package com.demo.fragments;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

import com.assoc.adherentespace.SessionManager;
import com.demo.slidingmenu_tabhostviewpager.R;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfilAdherent extends  FragmentActivity {
	SessionManager manager;
	String image ;
	Bitmap b , circularBitmap;
 
    ImageView  photo ;
	
	
	   
	
	//onBackpressed to stop user from going MainActivity to Login Screen on back button press 
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compte_user);
   
		Intent intent = getIntent();
		String email = intent.getStringExtra("USER_email");
        String nom = intent.getStringExtra("USER_NOM");
        String prenom = intent.getStringExtra("USER_PRRENOM");
        String st = intent.getStringExtra("USER_St");
        String dte = intent.getStringExtra("USER_DN");
        image = intent.getStringExtra("USER_PHOTO");
        
        
        //TextView textprofil = (TextView) findViewById(R.id.textprenom);TextView textnom = (TextView) findViewById(R.id.textnom);
        TextView textemail = (TextView) findViewById(R.id.TextViewemail);
        TextView textprofil = (TextView) findViewById(R.id.textprenom);
        TextView textsituation = (TextView) findViewById(R.id.textstiuatio);
        TextView textdatens = (TextView) findViewById(R.id.textdatens);
        photo = (ImageView) findViewById(R.id.imageprofil);
        
     

       
        //textnom.setText(nom);
        textprofil.setText(prenom +" "+nom);
        textsituation.setText(st);
        textdatens.setText(dte);
        textemail.setText(email);
        information info = new information();
        info.execute("");
       
        
        manager = new SessionManager();

        Button button = (Button) findViewById(R.id.btn_logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	manager.setPreferences(ProfilAdherent.this, "status", "0");
            	String status= manager.getPreferences(ProfilAdherent.this, "status");
              //  Intent i = new Intent(ProfilAdherent.this, Login.class);
               // Log.d("status", status);
            	finish();
				//startActivity(i);setFragment
            	Fragment login ;
            	onAttachFragment(new Login());
            }
        });
	}
	
	
	public class information extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... arg0) {

            try
            {
                URL url = new URL(image);
                InputStream is = new BufferedInputStream(url.openStream());
                b = BitmapFactory.decodeStream(is);
                circularBitmap = ImageConverter.getRoundedCornerBitmap(b, 100);

            } catch(Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            photo.setImageBitmap(circularBitmap);
        }
    }
	
	
	public void setFragment(Fragment frag)
    {    
         android.app.FragmentManager fm = getFragmentManager();   
         if (fm.findFragmentById(R.id.main_content) == null) {
            fm.beginTransaction().add(R.id.main_content, frag).commit();
        }
     
    }
	
	// pour prensenter l'image dans la circle 
	public static class ImageConverter {

	    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
	        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
	        Canvas canvas = new Canvas(output);

	        final int color = 0xff424242;
	        final Paint paint = new Paint();
	        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
	        final RectF rectF = new RectF(rect);
	        final float roundPx = pixels;

	        paint.setAntiAlias(true);
	        canvas.drawARGB(0, 0, 0, 0);
	        paint.setColor(color);
	        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

	        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
	        canvas.drawBitmap(bitmap, rect, rect, paint);

	        return output;
	    }
	}
}



    
    
    


