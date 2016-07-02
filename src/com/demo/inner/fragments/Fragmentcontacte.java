package com.demo.inner.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.assoc.Activity.WebViewActivity;
import com.assoc.adherentespace.Adherent;
import com.demo.fragments.MyProfil;
import com.demo.fragments.Register;

import com.demo.slidingmenu_tabhostviewpager.R;

public class Fragmentcontacte extends Fragment implements OnClickListener {
	Button savoirplus ;

    EditText editTextEmail, editTextSubject, editTextMessage;
    Button btnSend, btnAttachment;
    String email, subject, message, attachmentFile;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    int columnIndex;
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		

		WebView webView;
		View v = inflater.inflate(R.layout.fragment_contacte, container, false);

		   editTextSubject = (EditText) v.findViewById(R.id.editTextSubject);
           editTextMessage = (EditText) v.findViewById(R.id.editTextMessage);
           btnAttachment = (Button) v.findViewById(R.id.buttonAttachment);
           btnSend = (Button) v.findViewById(R.id.buttonSend);

           btnSend.setOnClickListener(this);
           btnAttachment.setOnClickListener(this);
		return v;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
           if (requestCode == PICK_FROM_GALLERY && resultCode == getActivity().RESULT_OK) {
                  /**
                   * Get Path
                   */
                  Uri selectedImage = data.getData();
                  String[] filePathColumn = { MediaStore.Images.Media.DATA };

                  Cursor cursor =  getActivity().getContentResolver().query(selectedImage,filePathColumn, null, null, null);
                  cursor.moveToFirst();
                  columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                  attachmentFile = cursor.getString(columnIndex);
                  Log.e("Attachment Path:", attachmentFile);
                  URI = Uri.parse("file://" + attachmentFile);
                  cursor.close();
               
           }
    }

    @Override
    public void onClick(View v) {

           if (v == btnAttachment) {
                  openGallery();

           }
           if (v == btnSend) {
                  try {
                        email = "reda.bok@gmail.com";
                        subject = editTextSubject.getText().toString();
                        message = editTextMessage.getText().toString();

                        final Intent emailIntent = new Intent(
                                      android.content.Intent.ACTION_SEND);
                        emailIntent.setType("plain/text");
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                                      new String[] { email });
                        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                                      subject);
                        if (URI != null) {
                               emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
                        }
                        emailIntent
                                      .putExtra(android.content.Intent.EXTRA_TEXT, message);
                        this.startActivity(Intent.createChooser(emailIntent,
                                      "Sending email..."));

                  } catch (Throwable t) {
                        Toast.makeText(getActivity(),
                                      "Request failed try again: " + t.toString(),
                                      Toast.LENGTH_LONG).show();
                  }
           }

    }

    public void openGallery() {
           Intent intent = new Intent();
           intent.setType("image/*");
           intent.setAction(Intent.ACTION_GET_CONTENT);
           intent.putExtra("return-data", true);
           startActivityForResult(
                        Intent.createChooser(intent, "Complete action using"),
                        PICK_FROM_GALLERY);

    }

}
