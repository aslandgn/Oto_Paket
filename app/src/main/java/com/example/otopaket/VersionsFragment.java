package com.example.otopaket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.otopaket.adapters.GenericListViewAdapter;
import com.example.otopaket.adapters.VersionListViewHelper;
import com.example.otopaket.databinding.FragmentModelsBinding;
import com.example.otopaket.databinding.FragmentVersionsBinding;
import com.example.otopaket.dtos.ModelDto;
import com.example.otopaket.dtos.VersionDto;

import java.util.List;


public class VersionsFragment extends Fragment {

    private ProgressBar spinner;
    private FragmentVersionsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentVersionsBinding.inflate(inflater, container, false);
        spinner = getActivity().findViewById(R.id.progress_bar);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            List<VersionDto> versionDtos = bundle.getParcelableArrayList("versionDtos");
            ListView listView = binding.versionListView;
            VersionListViewHelper versionsListViewAdapter = new VersionListViewHelper(versionDtos, getActivity());
            listView.setAdapter(versionsListViewAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    getView().setEnabled(false);
                    getView().setClickable(false);
                    spinner.setVisibility(View.VISIBLE);
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
