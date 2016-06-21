package com.demo.slidingmenu_tabhostviewpager;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;


/**
 * C'est l'activité Splash qui est chargé lorsque l'application est appelée
 */
public class SplashActivity extends Activity
{
	// Réglage de  la durée, en millisecondes (ou l'extraire comme un paramètre configurable)
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
		//Obtenir la préférence partagée, par défaut is  true si elle n'est pas disponible
		boolean isSplashEnabled = sp.getBoolean("isSplashEnabled", true);

		if (isSplashEnabled)
		{
			new Handler().postDelayed(new Runnable()
			{
				public void run()
				{
					//Terminez l'activité de démarrage de sorte qu'il ne peut pas être retourné à.
					SplashActivity.this.finish();
					// Créer une intention qui va commencer l'activité principale.
					Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
					SplashActivity.this.startActivity(mainIntent);
				}
			}, SPLASH_DISPLAY_LENGTH);
		}
		else
		{
			// si la tache n'est pas activé, puis terminez l'activité immédiatement et aller au menu principal
			finish();
			Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
			SplashActivity.this.startActivity(mainIntent);
		}
	}
}