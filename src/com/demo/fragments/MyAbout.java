package com.demo.fragments;

import com.demo.slidingmenu_tabhostviewpager.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAbout extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_about, container, false);
		ImageView image = (ImageView) v.findViewById(R.id.imageViewabout);
	
        TextView titre = (TextView) v.findViewById(R.id.textViewabout);
        TextView date = (TextView) v.findViewById(R.id.textView2about);

        TextView titre1 = (TextView) v.findViewById(R.id.textViewabout2);
        TextView date1 = (TextView) v.findViewById(R.id.textView2about2);
        date.setText(Html.fromHtml("Notre Association vous proposent par l'intermédiaire de l'Association Conseil Assurance Décès (ACAD) un contrat d'assistance rapatriement de corps qui couvre les frais d'obsèques dans la limite du montant du capital garantit.Il s'adresse également à ceux qui souhaite être inhumer en France dans un carré musulman. Souscrire un contrat d'assistance  obsèques vous permet de soulager vos proches des contraintes financières liées aux funérailles et de bénéficier d’une tranquillité d’esprit.Du financement à l'organisation, nos conseillers spécialisés vous aident à tout prévoir, et de bénéficier d'une prise en charges complètes le jour venu.<br/><br/>"));
        date1.setText(Html.fromHtml("<h2>Cotisation</h2><br/>Somme d’argent que les membres versent en cette qualité périodiquement. Elle est la contrepartie du droit moral d’être membre. Elle n’est pas obligatoire mais conseillée car elle formalise l’engagement des membres et le renouvellement de leur adhésion.Il est possible de prévoir un montant de cotisation différent selon les catégories de membres"));

        return v;
	}
}
