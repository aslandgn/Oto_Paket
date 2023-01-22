package com.example.otopaket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.otopaket.dtos.BaseDto;

import java.util.List;

public class GenericListViewAdapter<T extends BaseDto> extends BaseAdapter  {
    private List<T> _dtos;
    private Context _context;
    private int _resource = android.R.layout.simple_list_item_1;
    public GenericListViewAdapter(List<T> dtos, FragmentActivity fragmentActivity) {
        _dtos = dtos;
        _context = fragmentActivity;
    }

    public GenericListViewAdapter(List<T> dtos, FragmentActivity fragmentActivity, int resource) {
        _dtos = dtos;
        _context = fragmentActivity;
        _resource = resource;
    }
    @Override
    public int getCount() {
        return _dtos.size();
    }

    @Override
    public Object getItem(int i) {
        return _dtos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return _dtos.get(i).Id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(_context).
                    inflate(_resource, viewGroup, false);
        }
        T current = (T) getItem(i);
        TextView modelName = view.findViewById(android.R.id.text1);
        modelName.setText(current.Name);
        view.setId(current.Id);

        return view;
    }
}
