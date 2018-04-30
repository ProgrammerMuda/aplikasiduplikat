package maj.raga.aplikasiduplikat.activitiy;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import maj.raga.aplikasiduplikat.Function.functions;
import maj.raga.aplikasiduplikat.HomeActivity;
import maj.raga.aplikasiduplikat.R;


public class LoginActivity extends functions {

    @BindView(R.id.pgLoading)
    ProgressBar pgLoading;
    @BindView(R.id.edtUsername)
    EditText edtUsername;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener listener;
    FirebaseUser user;
    @BindView(R.id.btnexit)
    Button btnexit;
    @BindView(R.id.linear)
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    if (user.isEmailVerified()) {
                        myToast("Hai " + user.getEmail() + ".");
                        myIntent(HomeActivity.class);
                        finish();
                    } else if (!user.isEmailVerified()) {
                        myToast("hai " + user.getEmail() + " coba cek email anda apakah sudah di verifikasi?");
                        FirebaseAuth.getInstance().signOut();

                    }
                }
            }
        };

    }

    @OnClick({R.id.btnSignIn, R.id.btnSignUp})
    public void onViewClicked(View view) {
        final String userName = edtUsername.getText().toString();
        String passKey = edtPassword.getText().toString();
        switch (view.getId()) {
            case R.id.btnSignIn:
                if (TextUtils.isEmpty(userName)) {
                    edtUsername.setError("Put email is empty!");
                    edtUsername.requestFocus();
                } else if (TextUtils.isEmpty(passKey)) {
                    edtPassword.setError("Put password is empty!");
                    edtPassword.requestFocus();
                } else {
                    pgLoading.setVisibility(View.VISIBLE);
                    auth.signInWithEmailAndPassword(userName, passKey).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            pgLoading.setVisibility(View.GONE);
                            if (!task.isSuccessful()) {
                                myToast("Maaf! anda gagal login silahkan cek " + task.getException() + ".");
                                myIntent(LoginActivity.class);
                            } else {

                            }
                        }
                    });
                }
                break;
            case R.id.btnSignUp:
                myIntent(RegisterActivity.class);
                finish();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (listener != null) {
            auth.removeAuthStateListener(listener);
        }
    }

    @OnClick(R.id.btnexit)
    public void onViewClicked() {

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder
                .setMessage("Shutdown app???")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

    }
}
