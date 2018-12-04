package com.example.android.dyfragmentdata;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    private EditText userName;
    private EditText userEmail;
    private EditText userMobile;
    private EditText userPassword;
    private EditText userAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_category.xml layout file
        setContentView(R.layout.signup_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#e53935"));
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        ActionBar actionbar = getSupportActionBar();

        if (actionbar !=null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);

        setNavigationViewListener();

        userName = (EditText) findViewById(R.id.et_enter_name);
        userEmail = (EditText) findViewById(R.id.et_enter_email);
        userMobile = (EditText) findViewById(R.id.et_enter_mobile);
        userPassword = (EditText) findViewById(R.id.et_enter_password);
        userAddress = (EditText) findViewById(R.id.et_enter_address);

        Button signupButton = (Button) findViewById(R.id.button_sign_up);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    final String name = userName.getText().toString().trim();
              //  final String email = userEmail.getText().toString().trim();
              //  final String mobile = userMobile.getText().toString().trim();
             //   final String password = userPassword.getText().toString().trim();
             //   final String address = userAddress.getText().toString().trim();

             //   if (TextUtils.isEmpty(name) && TextUtils.isEmpty(email)  && TextUtils.isEmpty(mobile) && TextUtils.isEmpty(password) && TextUtils.isEmpty(address) || TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(mobile) || TextUtils.isEmpty(password) || TextUtils.isEmpty(address)) {

             //       Toast.makeText(SignupActivity.this, "Please enter the required details", Toast.LENGTH_SHORT).show();
            //        return;
            //    } else {
                    registerNetworkRequest();
             //   }
                }
        });

        Button alreadyAccountSignin = (Button) findViewById(R.id.already_account_sign_in);
        alreadyAccountSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intentLogin);
            }
        });

        }
    // NavigationView click events
    private void setNavigationViewListener() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
    /*        case_tab R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                return true; */
            case R.id.action_drawer_signin:
                Intent intent = new Intent(this, SignupActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_drawer_cart:
                Intent intentCart = new Intent(this, CartActivity.class);
                startActivity(intentCart);
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        // set item as selected to persist highlight
        item.setChecked(true);

        // close drawer when item is tapped
        mDrawerLayout.closeDrawers();

        switch(id) {

            case R.id.nav_home:
                Intent intent = new Intent(this, HomepageActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_category:
                Intent intentCategory = new Intent(this, MainActivity.class);
                startActivity(intentCategory);
                break;
            case R.id.nav_product:
                Intent intentProduct = new Intent(this, DetailsActivity.class);
                startActivity(intentProduct);
                break;
            case R.id.nav_login:
                Intent intentLogin = new Intent(this, LoginActivity.class);
                startActivity(intentLogin);

                break;
            case R.id.nav_register:
                Intent intentRegister = new Intent(this, SignupActivity.class);
                startActivity(intentRegister);

                break;
            case R.id.nav_profile:
                Intent intentProfile = new Intent(this, ProfileActivity.class);
                startActivity(intentProfile);
                break;
            case R.id.nav_forgot_password:
                Intent intentForgotPassword = new Intent(this, ForgotPasswordActivity.class);
                startActivity(intentForgotPassword);
                break;
            case R.id.nav_change_password:
                Intent intentChangePassword = new Intent(this, ChangePasswordActivity.class);
                startActivity(intentChangePassword);
                break;
            case R.id.nav_wishlist:
                Intent intentWishlist = new Intent(this, WishlistActivity.class);
                startActivity(intentWishlist);
                break;

            case R.id.nav_about_industry:
                Toast.makeText(this, "NavigationClick", Toast.LENGTH_SHORT).show();

                break;
            case R.id.nav_checkout:
                Intent intentCheckout = new Intent(this, CheckoutActivity.class);
                startActivity(intentCheckout);

                break;
            case R.id.sign_out_menu:
                Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    private void registerNetworkRequest() {

        final String name = userName.getText().toString().trim();
        final String email = userEmail.getText().toString().trim();
        final String mobile = userMobile.getText().toString().trim();
        final String password = userPassword.getText().toString().trim();
        final String address = userAddress.getText().toString().trim();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.godprice.com/api/register.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error Occurred" + error, Toast.LENGTH_SHORT).show();

            }

        }) { @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", name);
            params.put("email", email);
            params.put("mobile", mobile);
            params.put("password", password);
            params.put("address", address);
            return params;
        }

        };

        queue.add(stringRequest);
    }
}