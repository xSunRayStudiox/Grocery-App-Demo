package Com.SunRay.FoodPoint;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

public class Registration extends AppCompatActivity {

    EditText Email, Name, Number, Password;
    Button Register, Verify;
    TextView Already_Register;

    String name, email, number, password;

    DocumentReference documentReference;
    FirebaseFirestore FireStore;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        Email = findViewById(R.id.Input_Email);
        Name = findViewById(R.id.Input_Name);
        Number = findViewById(R.id.Input_Number);
        Password = findViewById(R.id.Input_Password);
        Register = findViewById(R.id.Submit);
        Verify = findViewById(R.id.Verify_email);
        Already_Register = findViewById(R.id.Already_Registered);

        Already_Register.setOnClickListener(view -> {
            Intent intent = new Intent(Registration.this,LoginActivity.class);
            startActivity(intent);
        });

        Register.setOnClickListener(v -> {
            ClickButtonClass();
        });

    }

    private void ClickButtonClass() {

        name = Name.getText().toString().trim();
        email = Email.getText().toString().trim();
        number = Number.getText().toString().trim();
        password = Password.getText().toString().trim();

        if (name.isEmpty()) {
            Name.setError("Empty");
            Name.requestFocus();

        } else if (email.isEmpty()) {
            Email.setError("Empty");
            Email.requestFocus();

        } else if (password.isEmpty()) {
            Password.setError("Empty");
            Password.requestFocus();

        } else if (number.isEmpty()) {
            Number.setError("Empty");
            Number.requestFocus();

        }else {
            progressDialog = new ProgressDialog(Registration.this);
            progressDialog.setMessage("Creating Account....");
            progressDialog.show();

            mAuth = FirebaseAuth.getInstance();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if (task.isSuccessful()){

                    FireStore = FirebaseFirestore.getInstance();
                    documentReference=FireStore.collection("Users").document(email);

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("Email", email);
                    hashMap.put("Name", name);
                    hashMap.put("Number", number);

                    documentReference.set(hashMap).addOnSuccessListener(voidR -> {
                        progressDialog.dismiss();
                        Intent intent = new Intent(Registration.this, HomeScreen.class);
                        startActivity(intent);
                        finish();

                    }).addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        Toast.makeText(Registration.this, "Account Not Create: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(Registration.this, "Error" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            });



        }
    }
}