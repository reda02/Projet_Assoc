package com.demo.inner.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.view.ViewGroup;
import android.widget.Button;
import com.demo.slidingmenu_tabhostviewpager.R;

public class FragmentPdfView extends Fragment {
	Button savoirplus ;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		

		WebView webView;
		View v = inflater.inflate(R.layout.webviewpdf, container, false);

		webView = (WebView) v.findViewById(R.id.webView2);
		webView.getSettings().setJavaScriptEnabled(true);
		//webView.loadUrl("http://kiaai.com/files/pdf/0340481628886f7f2197bf133f37636c.pdf");
		String pdf = "http://associationcomores.com/pdf/guide.pdf";
		//String pdf = "http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf";
		webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);
		return v;
	}
	
}
