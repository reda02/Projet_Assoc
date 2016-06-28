package com.demo.inner.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.assoc.Activity.WebViewActivity;
import com.assoc.adherentespace.Adherent;
import com.demo.fragments.MyProfil;
import com.demo.fragments.Register;

import com.demo.slidingmenu_tabhostviewpager.R;

public class Tab1Fragment extends Fragment {
	Button savoirplus ;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_home, container, false);
		 savoirplus = (Button) v.findViewById(R.id.buttonhome);
		ImageView image = (ImageView) v.findViewById(R.id.imageViewhome);
		Button descption = (Button) v.findViewById(R.id.buttonhome);
        TextView titre = (TextView) v.findViewById(R.id.textViewhome);
        TextView date = (TextView) v.findViewById(R.id.textView2home);
        date.setText(Html.fromHtml(""
        		+ "<h3>Assistance rapatriement</h3>Décès en France Métropolitaine (à plus de 50 km du domicile habituel): <br> "
        		+ "Organisation et prise en charge des frais de transport et de rapatriement du défunt jusqu’au domicile habituel. <br>  <br> Décès dans les DOM/TOM et à l'étranger :<br> "
        		+ "Intervention à la demande de la famille, dans les démarches à entreprendre auprès des administrations, consulats … afin de permettre le rapatriement du défunt dans les meilleures conditions,<br> "
        		+ "Organisation et prise en charge du rapatriement du défunt du lieu du décès jusqu’au lieu d’inhumation en France Métropolitaine,<br> "
        		+ "Mise à la disposition pour un membre de la famille d’un titre de transport A/R, en cas d’inhumation provisoire ou définitive sur place, si un membre de la famille n’est pas déjà sur les lieux.<br> "
        		+ "<h3>Assistance morale 24h/24</h3>Une équipe d’assistance :<ul><li>Apporte aide et réconfort à la famille proche,<br>Renseigne sur les formalités et démarches administratives à accomplir en cas de décès, "
        		+ " auprès des autorités publiques et des entreprises de pompes funèbres. "));
		
        savoirplus.setOnClickListener(new OnClickListener() {

  		  @Override
  		  public void onClick(View arg0) {
  		    Intent intent = new Intent(getActivity(), WebViewActivity.class);
  		    startActivity(intent);
  		  }

  		});

        
        
        return v;
	}
	
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonhome:
	
			Intent i = new Intent(getActivity(), WebViewActivity.class);
			startActivity(i);
			break;
		case R.id.notreg_btn:
			
		default:
			break;
		}
	}
}
