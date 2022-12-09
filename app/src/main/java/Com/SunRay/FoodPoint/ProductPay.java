package Com.SunRay.FoodPoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class ProductPay extends AppCompatActivity implements PaymentResultListener {
    ImageView img;
    TextView proName, proPrice, proQty, proUnit;
    Button button;
    String name,price,qty,unit;
    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_pay);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        image = i.getIntExtra("image", R.drawable.mango);
        price = i.getStringExtra("price");
        qty = i.getStringExtra("qty");
        unit = i.getStringExtra("unit");

        proName = findViewById(R.id.productName);
        proPrice = findViewById(R.id.prodPrice);
        img = findViewById(R.id.big_image);
        button = findViewById(R.id.button);
        proQty = findViewById(R.id.qty);
        proUnit = findViewById(R.id.unit);

        proName.setText(name);
        proPrice.setText(price);
        proQty.setText(qty);
        proUnit.setText(unit);
        img.setImageResource(image);

        button.setOnClickListener(view -> {
            startPayment();
        });
    }

    public void startPayment() {

        Checkout.preload(getApplicationContext());
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_jR9aoZXWskmMhO");
        final Activity activity = this;

        int a = Integer.parseInt(price);
        int b = 100;
        double result = ((double) a)*b;

        try {
            JSONObject options = new JSONObject();
            options.put("name",name);
            options.put("description", "Reference No. #123456");
            options.put("currency", "INR");
            options.put("amount", result);//pass amount in currency subunits
//            options.put("prefill.email", "suryakashakti@gmail.com");
//            options.put("prefill.contact","9651100914");

            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);
            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Successful"+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Not Successful"+s, Toast.LENGTH_SHORT).show();
    }
}