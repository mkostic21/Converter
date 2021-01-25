package hr.ferit.converter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private final static String BUNDLE_HISTORY = "history";
    public RecyclerAdapter adapter;
    private Button btnClear;
    private ArrayList<String> historyData;
    private ButtonClickListener clickListener;


    public static HistoryFragment newInstance(ArrayList<String> historyData) {
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        args.putStringArrayList(BUNDLE_HISTORY, historyData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecycler(view);
        addData();
        setupButton(view);
    }

    private void addData() {
        if(getArguments()!=null){
            historyData = getArguments().getStringArrayList(BUNDLE_HISTORY);
        }
        if(historyData!=null) {
            for (String s : historyData) {
                adapter.addToHistory(s);
            }
        }
    }

    private void setupButton(View view) {
        btnClear = view.findViewById(R.id.btnClear);
        btnClear.setOnClickListener(view1 -> adapter.clearData());
    }

    private void setupRecycler(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new RecyclerAdapter(clickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ButtonClickListener) {
            this.clickListener = (ButtonClickListener) context;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.clickListener = null;
    }

}