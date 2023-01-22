package com.example.otopaket;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.otopaket.adapters.GenericListViewAdapter;
import com.example.otopaket.clients.RetrofitClient;
import com.example.otopaket.databinding.FragmentModelsBinding;
import com.example.otopaket.dtos.ModelDto;
import com.example.otopaket.dtos.ServiceResponse;
import com.example.otopaket.dtos.VersionDto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ModelsFragment extends Fragment {

    private ProgressBar spinner;
    private FragmentModelsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentModelsBinding.inflate(inflater, container, false);
        spinner = getActivity().findViewById(R.id.progress_bar);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            List<ModelDto> modelDtos = bundle.getParcelableArrayList("modelDtos");
            ListView listView = binding.modelListView;
            GenericListViewAdapter<ModelDto> modelsListViewAdapter = new GenericListViewAdapter(modelDtos, getActivity());

            listView.setAdapter(modelsListViewAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    getView().setEnabled(false);
                    getView().setClickable(false);
                    spinner.setVisibility(View.VISIBLE);
                    RetrofitClient.getInstance().getVersionService().GetVersionsOfModel(view.getId()).enqueue(new Callback<ServiceResponse<List<VersionDto>>>() {
                        @Override
                        public void onResponse(Call<ServiceResponse<List<VersionDto>>> call, Response<ServiceResponse<List<VersionDto>>> response) {
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("versionDtos", (ArrayList<? extends Parcelable>) response.body().Data);
                            spinner.setVisibility(View.GONE);
                            getView().setEnabled(true);
                            getView().setClickable(true);
                            NavHostFragment.findNavController(ModelsFragment.this)
                                    .navigate(R.id.action_ModelsFragment_VersionsFragment, bundle);
                        }

                        @Override
                        public void onFailure(Call<ServiceResponse<List<VersionDto>>> call, Throwable t) {
                            spinner.setVisibility(View.GONE);
                            getView().setEnabled(true);
                            getView().setClickable(true);
                            Toast.makeText(getActivity(), R.string.error_message, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
