package com.example.dinu.catzz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ListFragment extends Fragment {
    RelativeLayout container2;
    ListView listView;
    Button backToFactsBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        listView = view.findViewById(R.id.listView);
        backToFactsBtn = view.findViewById(R.id.backToFactsBtn);
        container2 = view.findViewById(R.id.container2);

        listView.setAdapter(new UserAdapter(((MainActivity) getActivity()).getCatModelList(), getActivity()));

        backToFactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container2.setVisibility(View.GONE);
                ((MainActivity) getActivity()).getMain_layout().setVisibility(View.VISIBLE);
            }
        });
        return view;
    }
}
