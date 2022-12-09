package Com.SunRay.FoodPoint;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import Com.SunRay.FoodPoint.adapter.CategoryAdapter;
import Com.SunRay.FoodPoint.adapter.DiscountedProductAdapter;
import Com.SunRay.FoodPoint.adapter.ImageAdapter;
import Com.SunRay.FoodPoint.adapter.RecentlyViewedAdapter;
import Com.SunRay.FoodPoint.model.Category;
import Com.SunRay.FoodPoint.model.DiscountedProducts;
import Com.SunRay.FoodPoint.model.RecentlyViewed;

public class HomeScreen extends AppCompatActivity {

    // Variable
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ViewPager View_Pager;

    RecyclerView discountRecyclerView, categoryRecyclerView, recentlyViewedRecycler;
    DiscountedProductAdapter discountedProductAdapter;
    List<DiscountedProducts> discountedProductsList;
    CategoryAdapter categoryAdapter;
    List<Category> categoryList;
    RecentlyViewedAdapter recentlyViewedAdapter;
    List<RecentlyViewed> recentlyViewedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        View_Pager= findViewById(R.id.viewPage);
        ImageAdapter adapterView = new ImageAdapter(this);
        View_Pager.setAdapter(adapterView);

        FindViewIdClass();
        ActionBarClass();
        RecyclerViewClass();
    }

    // Adding data in array for show in Recycler View
    private void RecyclerViewClass() {
        // Discount Product
        discountedProductsList = new ArrayList<>();
        discountedProductsList.add(new DiscountedProducts("Badam","200","1","Kg",R.drawable.badam,"15% off"));
        discountedProductsList.add(new DiscountedProducts("Banana","60","12","Piece",R.drawable.banana,"18% off"));
        discountedProductsList.add(new DiscountedProducts("Mango","70","10","Piece",R.drawable.mango,"16% off"));
        discountedProductsList.add(new DiscountedProducts("Daal","700","1","Kg",R.drawable.daals,"52% off"));
        discountedProductsList.add(new DiscountedProducts("Onion","25","1","Kg",R.drawable.onion,"10% off"));
        discountedProductsList.add(new DiscountedProducts("Kiwi","40","1","Piece",R.drawable.kiwi,"12% off"));
        discountedProductsList.add(new DiscountedProducts("Sarso Oil","200","1","Kg",R.drawable.oil,"15% off"));

        // Category
        categoryList = new ArrayList<>();
        categoryList.add(new Category("DRY FRUITS"));
        categoryList.add(new Category("ALL FOOD GRAINS & MASALAS"));
        categoryList.add(new Category("ALL STAPLES"));
        categoryList.add(new Category("ATTA FLOURS & SOOJI"));
        categoryList.add(new Category("DAALS & PULSES"));
        categoryList.add(new Category("MASALAS & SPICES"));
        categoryList.add(new Category("EDIBLE OILS & GHEE"));
        categoryList.add(new Category("SALT SUGAR & JAGGERY"));
        categoryList.add(new Category("RICE & RICE PRODUCTS"));

        // Recently Add Items
        recentlyViewedList = new ArrayList<>();
        recentlyViewedList.add(new RecentlyViewed("Banana","60","12","Piece",R.drawable.banana));
        recentlyViewedList.add(new RecentlyViewed("Badam","70","10","Piece",R.drawable.badam));
        recentlyViewedList.add(new RecentlyViewed("Mango","700","1","Kg",R.drawable.mango));
        recentlyViewedList.add(new RecentlyViewed("Onion","25","1","Kg",R.drawable.onion));
        recentlyViewedList.add(new RecentlyViewed("Kiwi","40","1","Piece",R.drawable.kiwi));
        recentlyViewedList.add(new RecentlyViewed("Daal","240","1","Kg",R.drawable.daals));
        recentlyViewedList.add(new RecentlyViewed("Sarso Oil","200","1","Kg",R.drawable.oil));
        recentlyViewedList.add(new RecentlyViewed("Sarso Oil","200","1","Kg",R.drawable.oil));
        recentlyViewedList.add(new RecentlyViewed("Sarso Oil","200","1","Kg",R.drawable.oil));
        recentlyViewedList.add(new RecentlyViewed("Sarso Oil","200","1","Kg",R.drawable.oil));
        recentlyViewedList.add(new RecentlyViewed("Sarso Oil","200","1","Kg",R.drawable.oil));
        recentlyViewedList.add(new RecentlyViewed("Sarso Oil","200","1","Kg",R.drawable.oil));
        recentlyViewedList.add(new RecentlyViewed("Sarso Oil","200","1","Kg",R.drawable.oil));
        recentlyViewedList.add(new RecentlyViewed("Sarso Oil","200","1","Kg",R.drawable.oil));
        recentlyViewedList.add(new RecentlyViewed("Sarso Oil","200","1","Kg",R.drawable.oil));

        // Set Array in Recycler View
        setDiscountedRecycler(discountedProductsList);
        setCategoryRecycler(categoryList);
        setRecentlyViewedRecycler(recentlyViewedList);
    }

    private void FindViewIdClass() {
        // Variable Initialize
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawer_Layout);
        toolbar = findViewById(R.id.toolbar);
        discountRecyclerView = findViewById(R.id.discountedRecycler);
        categoryRecyclerView = findViewById(R.id.categoryRecycler);
        recentlyViewedRecycler = findViewById(R.id.recently_item);
    }

    private void setDiscountedRecycler(List<DiscountedProducts> dataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        discountRecyclerView.setLayoutManager(layoutManager);
        discountedProductAdapter = new DiscountedProductAdapter(this,dataList);
        discountRecyclerView.setAdapter(discountedProductAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this,categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    private void setRecentlyViewedRecycler(List<RecentlyViewed> recentlyViewedDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recentlyViewedRecycler.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new RecentlyViewedAdapter(this,recentlyViewedDataList);
        recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);
    }

    @SuppressLint("NonConstantResourceId")
    private void ActionBarClass() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(HomeScreen.this,drawerLayout,toolbar,
                R.string.openDrawer,R.string.closeDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.nav_home:
                    Intent home = new Intent(HomeScreen.this, HomeScreen.class);
                    startActivity(home);
                    break;
                case R.id.nav_profile:
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_offers:
                    Toast.makeText(this, "No Offers Find", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.nav_Product:
                case R.id.nav_my_order:
                case R.id.nav_my_cart:
                    Toast.makeText(this, "Under Processing", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.logout:
                    Intent sig = new Intent(HomeScreen.this, LoginActivity.class);
                    startActivity(sig);
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}