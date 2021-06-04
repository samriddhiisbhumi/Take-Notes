package com.aaxena.takenotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class History extends Fragment {
    private ArrayList<HistoryModal> courseModalArrayList;
    private DBHandler dbHandler;
    private NotesRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;
    LottieAnimationView noHistoryAnim;
    TextView noHistoryText;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v4 =  inflater.inflate(R.layout.history,container,false);
        noHistoryAnim = v4.findViewById(R.id.noHistory);
        noHistoryText = v4.findViewById(R.id.historyAdvisory);
        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(getContext());

        // getting our course array
        // list from db handler class.
        courseModalArrayList = dbHandler.readCourses();

        // on below line passing our array list to our adapter class.
        courseRVAdapter = new NotesRVAdapter(courseModalArrayList, getActivity());
        coursesRV = v4.findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);
        if (courseRVAdapter.getItemCount() <= 0) {
            noHistoryAnim.setVisibility(View.VISIBLE);
            noHistoryAnim.playAnimation();
            noHistoryText.setVisibility(View.VISIBLE);
        }
        return v4;
    }
}