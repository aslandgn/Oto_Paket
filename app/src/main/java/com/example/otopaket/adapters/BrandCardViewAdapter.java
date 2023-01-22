package com.example.otopaket.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.otopaket.R;
import com.example.otopaket.dtos.BrandDto;

import java.util.List;
import java.util.Locale;


public class BrandCardViewAdapter extends BaseAdapter {
    private List<BrandDto> _brandDtos;
    private Context _context;

    public  BrandCardViewAdapter(List<BrandDto> brandDtos, FragmentActivity activity) {
        _brandDtos = brandDtos;
        _context = activity;
    }


    @Override
    public int getCount() {
        return  _brandDtos.size();
    }

    @Override
    public Object getItem(int i) {
        return  _brandDtos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return _brandDtos.get(i).Id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(_context).
                    inflate(R.layout.brand_view, viewGroup, false);
        }
        BrandDto current = (BrandDto) getItem(i);
        TextView brandName = view.findViewById(R.id.brandText);
        ImageView brandImage = view.findViewById(R.id.brandImage);
        brandName.setText(current.getName());
        view.setId(current.Id);
        try {
            String imageName = "carlogo_" + current.Name.replaceAll(" ","").toLowerCase(Locale.ROOT);
            Drawable drawable = view.getResources().getDrawable(view.getResources()
                    .getIdentifier(imageName, "drawable", view.getContext().getPackageName()));
            brandImage.setImageDrawable(drawable);
        }
        catch (Exception e) {

        }

        return view;
    }


}
