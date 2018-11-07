package com.example.dinu.catzz;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class UserAdapter extends BaseAdapter {
    List<CatModel> catModelList = new ArrayList<>();
    Context context;


    public UserAdapter(List<CatModel> catModelList, Context context) {
        this.catModelList = catModelList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return catModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.cell_fragment, parent, false);
        final TextView date = rowView.findViewById(R.id.date);
        final TextView text = rowView.findViewById(R.id.text);

        final CatModel item = catModelList.get(position);
        date.setText(item.getDate());
        text.setText(item.getFactText());
        return rowView;
    }
}
