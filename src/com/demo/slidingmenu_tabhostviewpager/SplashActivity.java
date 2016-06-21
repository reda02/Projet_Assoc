package com.demo.slidingmenu_tabhostviewpager;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;


/**
 * C'est l'activit� Splash qui est charg� lorsque l'application est appel�e
 */
public class SplashActivity extends Activity
{
	// R�glage de  la dur�e, en millisecondes (ou l'extraire comme un param�tre configurable)
	private final int SPLASH_DISPLAY_LENGTH = 4000;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		//Obtenir la pr�f�rence partag�e, par d�faut is  true si elle n'est pas disponible
		boolean isSplashEnabled = sp.getBoolean("isSplashEnabled", true);

		if (isSplashEnabled)
		{
			new Handler().postDelayed(new Runnable()
			{
				public void run()
				{
					//Terminez l'activit� de d�marrage de sorte qu'il ne peut pas �tre retourn� �.
					SplashActivity.this.finish();
					// Cr�er une intention qui va commencer l'activit� principale.
					Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
					SplashActivity.this.startActivity(mainIntent);
				}
			}, SPLASH_DISPLAY_LENGTH);
		}
		else
		{
			// si la tache n'est pas activ�, puis terminez l'activit� imm�diatement et aller au menu principal
			finish();
			Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
			SplashActivity.this.startActivity(mainIntent);
		}
	}
}