package com.example.otopaket.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.otopaket.dtos.VersionDto;

import java.util.List;

public class VersionListViewHelper extends  GenericListViewAdapter<VersionDto> {
    public VersionListViewHelper(List<VersionDto> dtos, FragmentActivity fragmentActivity) {
        super(dtos, fragmentActivity, android.R.layout.simple_list_item_2);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = super.getView(i, view, viewGroup);
        VersionDto current = (VersionDto) getItem(i);
        TextView transmissionType = view.findViewById(android.R.id.text2);
        transmissionType.setText(current.getTransmissionType());
        return view;
    }
}
