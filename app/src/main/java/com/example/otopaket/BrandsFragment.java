package com.example.otopaket;

import android.os.Bundle;
import android.os.Parcelable;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.otopaket.adapters.BrandCardViewAdapter;
import com.example.otopaket.clients.RetrofitClient;
import com.example.otopaket.databinding.FragmentBrandsBinding;
import com.example.otopaket.dtos.BrandDto;
import com.example.otopaket.dtos.ModelDto;
import com.example.otopaket.dtos.ServiceResponse;
import com.google.android.material.bottomappbar.BottomAppBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandsFragment extends Fragment {

    private ProgressBar spinner;
    private FragmentBrandsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentBrandsBinding.inflate(inflater, container, false);
        spinner = getActivity().findViewById(R.id.progress_bar);

        ((MainActivity)getActivity()).ChangeVisibilityBottomNavigationItems(false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            List<BrandDto> brandDtos = bundle.getParcelableArrayList("brandDtos");
            GridView gridView = binding.brandGridView;
            BrandCardViewAdapter brandCardViewAdapter = new BrandCardViewAdapter(brandDtos, getActivity());
            gridView.setAdapter(brandCardViewAdapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    getView().setEnabled(false);
                    getView().setClickable(false);
                    spinner.setVisibility(View.VISIBLE);

                    RetrofitClient.getInstance().getModelService().getModelsOfBrand(Integer.toString(view.getId())).enqueue(new Callback<ServiceResponse<List<ModelDto>>>() {
                        @Override
                        public void onResponse(Call<ServiceResponse<List<ModelDto>>> call, Response<ServiceResponse<List<ModelDto>>> response) {
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("modelDtos", (ArrayList<? extends Parcelable>) response.body().Data);
                            spinner.setVisibility(View.GONE);
                            getView().setEnabled(true);
                            getView().setClickable(true);
                            NavHostFragment.findNavController(BrandsFragment.this)
                                    .navigate(R.id.action_BrandsFragment_to_ModelsFragment, bundle);
                        }

                        @Override
                        public void onFailure(Call<ServiceResponse<List<ModelDto>>> call, Throwable t) {
                            spinner.setVisibility(View.GONE);
                            getView().setEnabled(true);
                            getView().setClickable(true);
                            Toast.makeText(getActivity(), R.string.error_message, Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            });
        }
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}