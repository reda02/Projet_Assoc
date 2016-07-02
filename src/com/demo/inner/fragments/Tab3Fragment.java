package com.demo.inner.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.demo.slidingmenu_tabhostviewpager.R;
import com.vinci.serveme.Rotation;


public class Tab3Fragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener{
	private ListView mPhotosList;
	private ViewGroup mContainer;
	private ImageView mImageView;

	//Noms des photos, nous montrons dans la liste
	private static final String[] PHOTOS_NAMES = new String[] {
			"Président de l'association : M. Mohamed ali",
			"Comptable et trésorerie : M. ISSA MOGNIDAHO ",
			"Assistant : M. Ahmed\n Email: ahmed@gmail.com\n tel:+3306985643",
			
	};

	//Identificateurs de ressources pour les photos que nous voulons afficher
	private static final int[] PHOTOS_RESOURCES = new int[] {
			R.drawable.hm1,
			R.drawable.issa,
			R.drawable.hm3,
			
	};


	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.menu1, container, false);


		mPhotosList = (ListView) v.findViewById(android.R.id.list);
		mImageView = (ImageView) v.findViewById(R.id.picture);
		mContainer = (ViewGroup) v.findViewById(R.id.container);

		//Préparer le ListView
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, PHOTOS_NAMES);

		mPhotosList.setAdapter(adapter);
		mPhotosList.setOnItemClickListener(this);

		// Préparer the ImageView
		mImageView.setClickable(true);
		mImageView.setFocusable(true);
		mImageView.setOnClickListener(this);

		// Puisque nous sommes en cache des vues larges, nous voulons garder leur cache
		// Entre chaque animation
		mContainer.setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);
		return v;
	}

	/**
	 * Configurer une nouvelle rotation 3D sur la vue conteneur.
	 *
	 * @param position the item that was clicked to show a picture, or -1 to show the list
	 * @param start the start angle at which the rotation must begin
	 * @param end the end angle of the rotation
	 */
	private void applyRotation(int position, float start, float end) {
		// Find the center of the container
		final float centerX = mContainer.getWidth() / 2.0f;
		final float centerY = mContainer.getHeight() / 2.0f;

		// Créer une nouvelle rotation 3D avec le paramètre fourni
		// L'auditeur animation est utilisée pour déclencher l'animation suivante
		final Rotation rotation =
				new Rotation(start, end, centerX, centerY, 310.0f, true);
		rotation.setDuration(100);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(position));

		mContainer.startAnimation(rotation);
	}

	public void onItemClick(AdapterView parent, View v, int position, long id) {
		// Pré-charger l'image, puis lancer l'animation
		mImageView.setImageResource(PHOTOS_RESOURCES[position]);
		applyRotation(position, 0, 90);
	}

	public void onClick(View v) {
		applyRotation(-1, 180, 90);
	}

	/**
	 * Cette classe écoute pour la fin de la première moitié de l'animation.
  * Il publie ensuite une nouvelle action qui échange les points de vue de manière efficace lorsque le conteneur
  * Est tourné de 90 degrés et donc invisible.
	 */
	private final class DisplayNextView implements Animation.AnimationListener {
		private final int mPosition;

		private DisplayNextView(int position) {
			mPosition = position;
		}

		public void onAnimationStart(Animation animation) {
		}

		public void onAnimationEnd(Animation animation) {
			mContainer.post(new SwapViews(mPosition));
		}

		public void onAnimationRepeat(Animation animation) {
		}
	}

	/**
	 * Cette classe est responsable pour échanger les points de vue et de commencer la deuxième
  * La moitié de l'animation.
	 */
	private final class SwapViews implements Runnable {
		private final int mPosition;

		public SwapViews(int position) {
			mPosition = position;
		}

		public void run() {
			final float centerX = mContainer.getWidth() / 2.0f;
			final float centerY = mContainer.getHeight() / 2.0f;
			Rotation rotation;

			if (mPosition > -1) {
				mPhotosList.setVisibility(View.GONE);
				mImageView.setVisibility(View.VISIBLE);
				mImageView.requestFocus();

				rotation = new Rotation(90, 180, centerX, centerY, 310.0f, false);
			} else {
				mImageView.setVisibility(View.GONE);
				mPhotosList.setVisibility(View.VISIBLE);
				mPhotosList.requestFocus();

				rotation = new Rotation(90, 0, centerX, centerY, 310.0f, false);
			}

			rotation.setDuration(1000);
			rotation.setFillAfter(true);
			rotation.setInterpolator(new DecelerateInterpolator());

			mContainer.startAnimation(rotation);
		}
	}

}