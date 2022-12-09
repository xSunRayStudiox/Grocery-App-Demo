package Com.SunRay.FoodPoint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This method will be Run after given millisecond
        new Handler().postDelayed(() -> {

            user = FirebaseAuth.getInstance().getCurrentUser();

            if(user != null){
                Intent i1 = new Intent(MainActivity.this, HomeScreen.class);
                startActivity(i1);
                finish();
            }
            else{
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }

        }, 3000);
    }
}