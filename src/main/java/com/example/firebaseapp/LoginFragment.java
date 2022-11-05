package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginFragment extends Fragment {

    View v;
    TextView loginError;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText loginUsername, loginPassword;
    Button loginBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_login_fragment, container, false);

        loginUsername = v.findViewById(R.id.loginUsername);
        loginPassword = v.findViewById(R.id.loginPassword);
        loginError = v.findViewById(R.id.loginError);
        loginBtn = v.findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndLoginUser(loginUsername.getText().toString(), loginPassword.getText().toString());

            }
        });

        return v;
    }

    public void checkAndLoginUser(String name, String password) {
        db.collection("Users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d("TAG", document.getId() + " => " + "name: " + document.get("name") + " password: " + document.get("password"));

                                Object pass = new Object();
                                if (name.equals(document.get("name")) && pass.equals(document.get("password"))) {
                                    Toast.makeText(getActivity(), "Logged in", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(), LoginFragment.class);
                                    intent.putExtra("userName", name);
                                    startActivity(intent);
                                }
                            }
                        } Log.d("TAG", "--------------------------------");
                    }
                });
    }
}

