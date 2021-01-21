package hr.ferit.converter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    private Button btnLength;
    private Button btnArea;
    private Button btnMass;
    private Button btnVolume;
    private Button btnTemperature;
    private ButtonClickListener clickListener;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupListeners();
    }

    private void setupListeners() {
        btnArea.setOnClickListener(view -> {
            String conversionType = btnArea.getText().toString();
            clickListener.onButtonClick(conversionType);
        });

        btnLength.setOnClickListener(view -> {
            String conversionType = btnLength.getText().toString();
            clickListener.onButtonClick(conversionType);
        });

        btnMass.setOnClickListener(view -> {
            String conversionType = btnMass.getText().toString();
            clickListener.onButtonClick(conversionType);
        });

        btnVolume.setOnClickListener(view -> {
            String conversionType = btnVolume.getText().toString();
            clickListener.onButtonClick(conversionType);
        });

        btnTemperature.setOnClickListener(view -> {
            String conversionType = btnTemperature.getText().toString();
            clickListener.onButtonClick(conversionType);
        });
    }

    private void initViews(View view) {
        btnArea = view.findViewById(R.id.btnArea);
        btnLength = view.findViewById(R.id.btnLength);
        btnMass = view.findViewById(R.id.btnMass);
        btnVolume = view.findViewById(R.id.btnVolume);
        btnTemperature = view.findViewById(R.id.btnTemperature);
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