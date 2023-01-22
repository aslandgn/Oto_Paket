package com.example.otopaket;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.otopaket.clients.RetrofitClient;
import com.example.otopaket.databinding.FragmentHomepageBinding;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;



public class HomePageFragment extends Fragment {

    private FragmentHomepageBinding binding;
    private ProgressBar spinner;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentHomepageBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomAppBar bottomAppBar = getActivity().findViewById(R.id.bar);
        ExtendedFloatingActionButton button = getActivity().findViewById(R.id.markalariGetir);
        button.show();
        bottomAppBar.performShow();
    }
}


