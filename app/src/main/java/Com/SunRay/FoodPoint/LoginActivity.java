package Com.SunRay.FoodPoint;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    EditText Log_email, Log_password;
    TextView Not_registered, Forget_Password;
    Button Login_Now;

    FirebaseAuth mAuth;
    String email, password;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log_email = findViewById(R.id.Input_Email);
        Log_password = findViewById(R.id.Input_Password);
        Not_registered = findViewById(R.id.Not_registered);
        Forget_Password = findViewById(R.id.Forget_Password);
        Login_Now = findViewById(R.id.Login);

        Not_registered.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,Registration.class);
            startActivity(intent);
        });

        Forget_Password.setOnClickListener(view -> {
            Toast.makeText(this, "Under Processing", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(LoginActivity.this,ForgetActivity.class);
//            startActivity(intent);
        });

        Login_Now.setOnClickListener(v -> {
            email = Objects.requireNonNull(Log_email.getText().toString().trim());
            password = Objects.requireNonNull(Log_password.getText().toString().trim());

            if (email.isEmpty()){
                Log_email.setError("Empty");
                Log_email.requestFocus();

            } else if (password.isEmpty()){
                Log_password.setError("Empty");
                Log_password.requestFocus();

            } else {
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Login....");
                progressDialog.show();

                mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, task -> {
                    if (task.isSuccessful()) {

                        progressDialog.dismiss();
                        Intent intent = new Intent(LoginActivity.this, HomeScreen.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }
}