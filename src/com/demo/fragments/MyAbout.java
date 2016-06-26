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
        date.setText(Html.fromHtml("Notre Association vous proposent par l'interm�diaire de l'Association Conseil Assurance D�c�s (ACAD) un contrat d'assistance rapatriement de corps qui couvre les frais d'obs�ques dans la limite du montant du capital garantit.Il s'adresse �galement � ceux qui souhaite �tre inhumer en France dans un carr� musulman. Souscrire un contrat d'assistance  obs�ques vous permet de soulager vos proches des contraintes financi�res li�es aux fun�railles et de b�n�ficier d�une tranquillit� d�esprit.Du financement � l'organisation, nos conseillers sp�cialis�s vous aident � tout pr�voir, et de b�n�ficier d'une prise en charges compl�tes le jour venu.<br/><br/>"));
        date1.setText(Html.fromHtml("<h2>Cotisation</h2><br/>Somme d�argent que les membres versent en cette qualit� p�riodiquement. Elle est la contrepartie du droit moral d��tre membre. Elle n�est pas obligatoire mais conseill�e car elle formalise l�engagement des membres et le renouvellement de leur adh�sion.Il est possible de pr�voir un montant de cotisation diff�rent selon les cat�gories de membres"));

        return v;
	}
}
