package com.example.teamworks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class ContactFragment extends Fragment {
    TextView phone, email;
    Button logout;

    public ContactFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        phone = (TextView) view.findViewById(R.id.phoneNumber);
        email = (TextView) view.findViewById(R.id.emailId);
        logout = (Button) view.findViewById(R.id.logout);
        Intent intent = getActivity().getIntent();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String emailID = bundle.getString("email");
            String phoneNum = bundle.getString("phone");

        }
        MainActivity activity = (MainActivity) getActivity();
        String emailID = activity.getEmail();
        final String phoneNum = activity.getphone();

        Log.d("phphp", phoneNum);
        email.setText(emailID);
        phone.setText(phoneNum);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("pronecall", "");
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phone.getText().toString()));
                startActivity(intent);
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                intent.putExtra(Intent.EXTRA_TEXT, "message");
                intent.setType("message/rfc822");

                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });
        return view;
    }
}
