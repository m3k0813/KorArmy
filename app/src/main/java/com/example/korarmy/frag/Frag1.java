package com.example.korarmy.frag;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.korarmy.Adapter;
import com.example.korarmy.R;
import com.example.korarmy.RecyAdapter;
import com.example.korarmy.RecyclerData;

import java.util.ArrayList;


public class Frag1 extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private RecyAdapter recyAdapter;
    private ArrayList<RecyclerData> arrayList= new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag1, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyAdapter = new RecyAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(recyAdapter);
        RecyclerData recyclerData = new RecyclerData("자유게시판");
        RecyclerData recyclerData2 = new RecyclerData("비밀게시판");
        if (arrayList.isEmpty()) {
            arrayList.add(recyclerData);
            arrayList.add(recyclerData2);
            recyAdapter.notifyDataSetChanged();
        }

        ViewPager2 viewPager2 = view.findViewById(R.id.viewpager2);
        FragmentPagerAdapter fragmentPagerAdapter = new Adapter(getChildFragmentManager());


        return view;
    }
}
