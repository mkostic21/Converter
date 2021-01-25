package hr.ferit.converter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class InputFragment extends Fragment {

    private EditText edInput;
    private TextView tvCurrent;
    private Spinner spinFrom;
    private Spinner spinInto;
    private Button btnConvert;
    private ButtonClickListener clickListener;

    private static final String BUNDLE_TYPE = "type";

    private String conversionType = ""; //Default

    private final static float[] LENGTH_RATIOS = {0.393701f, 0.0328084f, 0.0109361f, 6.21371e-6f, 10.0f, 1.0f, 0.01f, 1.0e-5f};
    private final static float[] MASS_RATIOS = {0.000001f, 0.001f, 1.0f, 1000f, 1000000f, 0.000157473f, 0.002204623f, 0.03527396f};

    public static InputFragment newInstance(String conversionType) {
        Bundle args = new Bundle();
        InputFragment fragment = new InputFragment();
        args.putString(BUNDLE_TYPE, conversionType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupSpinners();
        setupListeners();

    }

    private void setupSpinners() {
        if (getArguments() != null) {
            conversionType = getArguments().getString(BUNDLE_TYPE);

            if (conversionType != null && !conversionType.isEmpty()) {
                tvCurrent.setText(conversionType);

                switch (conversionType) {
                    case "length":
                        ArrayAdapter<CharSequence> lengthAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.length_array, android.R.layout.simple_spinner_item);
                        lengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinFrom.setAdapter(lengthAdapter);
                        spinInto.setAdapter(lengthAdapter);
                        break;

                    case "mass":
                        ArrayAdapter<CharSequence> massAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.mass_array, android.R.layout.simple_spinner_item);
                        massAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinFrom.setAdapter(massAdapter);
                        spinInto.setAdapter(massAdapter);
                        break;

                    case "temperature":
                        ArrayAdapter<CharSequence> tempAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.temperature_array, android.R.layout.simple_spinner_item);
                        tempAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinFrom.setAdapter(tempAdapter);
                        spinInto.setAdapter(tempAdapter);
                        break;
                }
            }
        }
    }

    private void setupListeners() {
        btnConvert.setOnClickListener(view -> {
            String input = edInput.getText().toString();
            edInput.setText("");

            switch (conversionType) {
                case "length":
                    if (input.trim().length() != 0) {
                        int index1 = spinFrom.getSelectedItemPosition();
                        int index2 = spinInto.getSelectedItemPosition();
                        float value = Float.parseFloat(input);
                        float result = value / LENGTH_RATIOS[index1] * LENGTH_RATIOS[index2];

                        Toast.makeText(this.getContext(), String.valueOf(result), Toast.LENGTH_LONG).show();
                        clickListener.onConvertClick(input + " " + spinFrom.getSelectedItem().toString()+ " = " + result + " " +spinInto.getSelectedItem().toString());
                    }
                    break;

                case "mass":
                    if (input.trim().length() != 0) {
                        int index1 = spinFrom.getSelectedItemPosition();
                        int index2 = spinInto.getSelectedItemPosition();
                        float value = Float.parseFloat(input);
                        float result = value / MASS_RATIOS[index1] * MASS_RATIOS[index2];

                        Toast.makeText(this.getContext(), String.valueOf(result), Toast.LENGTH_LONG).show();
                        clickListener.onConvertClick(input + " " + spinFrom.getSelectedItem().toString()+ " = " + result + " " +spinInto.getSelectedItem().toString());
                    }
                    break;

                case "temperature":
                    if (input.trim().length() != 0) {
                        String spinnerFrom = spinFrom.getSelectedItem().toString();
                        String spinnerInto = spinInto.getSelectedItem().toString();
                        double value = Double.parseDouble(input);
                        double result = 0; //default

                        if (spinnerFrom.equals("Celsius") && spinnerInto.equals("Celsius")) {
                            result = value;
                            Toast.makeText(this.getContext(), String.valueOf(result), Toast.LENGTH_LONG).show();
                        }
                        if (spinnerFrom.equals("Fahrenheit") && spinnerInto.equals("Fahrenheit")) {
                            result = value;
                            Toast.makeText(this.getContext(), String.valueOf(result), Toast.LENGTH_LONG).show();
                        }
                        if (spinnerFrom.equals("Celsius") && spinnerInto.equals("Fahrenheit")) {
                            result = celsius2Fahrenheit(value);
                            Toast.makeText(this.getContext(), String.valueOf(result), Toast.LENGTH_LONG).show();
                        }
                        if (spinnerFrom.equals("Fahrenheit") && spinnerInto.equals("Celsius")) {
                            result = fahrenheit2Celsius(value);
                            Toast.makeText(this.getContext(), String.valueOf(result), Toast.LENGTH_LONG).show();
                        }

                        clickListener.onConvertClick(input + " " + spinFrom.getSelectedItem().toString()+ " = " + result + " " +spinInto.getSelectedItem().toString());
                    }
                    break;
            }
        });
    }

    public static double celsius2Fahrenheit(double c) {
        return 32 + c * 9 / 5;
    }

    public static double fahrenheit2Celsius(double f) {
        return (f - 32) * 5 / 9;
    }

    private void initViews(View view) {
        edInput = view.findViewById(R.id.edInput);
        spinFrom = view.findViewById(R.id.spinFrom);
        spinInto = view.findViewById(R.id.spinInto);
        btnConvert = view.findViewById(R.id.btnConvert);
        tvCurrent = view.findViewById(R.id.tvCurrent);
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