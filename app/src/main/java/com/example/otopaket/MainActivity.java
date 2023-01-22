package com.example.otopaket;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.otopaket.clients.RetrofitClient;
import com.example.otopaket.dtos.BrandDto;
import com.example.otopaket.dtos.ServiceResponse;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.otopaket.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.markalariGetir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner = findViewById(R.id.progress_bar);
                spinner.setVisibility(View.VISIBLE);

                RetrofitClient.getInstance().getBrandService().getBrands().enqueue(new Callback<ServiceResponse<List<BrandDto>>>() {
                    @Override
                    public void onResponse(Call<ServiceResponse<List<BrandDto>>> call, Response<ServiceResponse<List<BrandDto>>> response) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("brandDtos", (ArrayList<? extends Parcelable>) response.body().Data);
                        BrandsFragment fragment2 = new BrandsFragment();
                        fragment2.setArguments(bundle);
                        spinner.setVisibility(View.GONE);
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_content_main).navigate(R.id.BrandsFragment, bundle);
                    }

                    @Override
                    public void onFailure(Call<ServiceResponse<List<BrandDto>>> call, Throwable t) {
                        spinner.setVisibility(View.GONE);
//                        getView().setClickable(true);
                        Toast.makeText(MainActivity.this, R.string.error_message, Toast.LENGTH_SHORT).show();
                    }
                });


            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void ChangeVisibilityBottomNavigationItems(boolean show) {
        BottomAppBar bottomAppBar = findViewById(R.id.bar);
        ExtendedFloatingActionButton button = findViewById(R.id.markalariGetir);
        if (bottomAppBar != null && button != null) {
            if (show) {
                bottomAppBar.performShow();
                button.show();
            } else {
                bottomAppBar.performHide();
                button.hide();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}