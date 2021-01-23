package hr.ferit.converter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class InputFragment extends Fragment {

    private EditText edInput;
    private TextView tvResult;
    private TextView tvCurrent;
    private Spinner spinFrom;
    private Spinner spinInto;
    private Button btnConvert;

    private static final String BUNDLE_TYPE = "type";
    private static final String BUNDLE_HISTORY = "history";

    private String conversionType = "";

    //TODO add mass and temp. ratios
    private final static float[] LENGTH_RATIOS = {0.393701f, 0.0328084f, 0.0109361f, 6.21371e-6f, 10.0f, 1.0f, 0.01f, 1.0e-5f};
    private final static float[] MASS_RATIOS = {0.000001f, 0.001f, 1.0f, 1000f, 1000000f, 0.000157473f, 0.002204623f, 0.03527396f};

    public static InputFragment newInstance(String conversionType, String historyData) {
        Bundle args = new Bundle();
        InputFragment fragment = new InputFragment();
        args.putString(BUNDLE_TYPE, conversionType);
        args.putString(BUNDLE_HISTORY, historyData);
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
        setupListeners();
        setupSpinners();

    }

    private void setupSpinners() {
        if (getArguments() != null) {
            conversionType = getArguments().getString(BUNDLE_TYPE);
            String historyData = getArguments().getString(BUNDLE_HISTORY);

            if (conversionType != null && !conversionType.isEmpty()) {
                tvCurrent.setText(conversionType);

                //TODO: method to get which ratios to use
                switch (conversionType){
                    //TODO: REFORMAT!!
                    case "length":
                        ArrayAdapter<CharSequence> lengthAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.length_array, android.R.layout.simple_spinner_item);
                        lengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinFrom.setAdapter(lengthAdapter);
                        spinInto.setAdapter(lengthAdapter);
                    case "mass":
                        ArrayAdapter<CharSequence> massAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.mass_array, android.R.layout.simple_spinner_item);
                        massAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinFrom.setAdapter(massAdapter);
                        spinInto.setAdapter(massAdapter);
                    case "temperature":
                        ArrayAdapter<CharSequence> tempAdapter = ArrayAdapter.createFromResource(this.getContext(), R.array.temperature_array, android.R.layout.simple_spinner_item);
                        tempAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinFrom.setAdapter(tempAdapter);
                        spinInto.setAdapter(tempAdapter);
                }
            }

            //TODO: history data handling here:
        }
    }

    private void setupListeners() {
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = edInput.getText().toString();

                //TODO: REFORMAT!
                //todo: add every input to history
                switch (conversionType) {
                    case "length":
                        if (input.trim().length() != 0) {
                            int index1 = spinFrom.getSelectedItemPosition();
                            int index2 = spinInto.getSelectedItemPosition();
                            float value = Float.parseFloat(input);
                            float result = value / LENGTH_RATIOS[index1] * LENGTH_RATIOS[index2];
                            tvResult.setText(String.valueOf(result));
                        }

                    case "mass":
                        if(input.trim().length()!=0){
                            int index1 = spinFrom.getSelectedItemPosition();
                            int index2 = spinInto.getSelectedItemPosition();
                            float value = Float.parseFloat(input);
                            float result = value / MASS_RATIOS[index1] * MASS_RATIOS[index2];
                            tvResult.setText(String.valueOf(result));
                        }

                    case "temperature":
                        if(input.trim().length()!=0){
                            String spinnerFrom = spinFrom.getSelectedItem().toString();
                            String spinnerInto = spinInto.getSelectedItem().toString();
                            double value = Double.parseDouble(input);
                            double result;

                            if(spinnerFrom.equals("Celsius") && spinnerInto.equals("Celsius")){
                                result = value;
                                tvResult.setText(String.valueOf(result));
                            }
                            if(spinnerFrom.equals("Fahrenheit") && spinnerInto.equals("Fahrenheit")){
                                result = value;
                                tvResult.setText(String.valueOf(result));
                            }
                            if(spinnerFrom.equals("Celsius") && spinnerInto.equals("Fahrenheit")){
                                result = celsius2Fahrenheit(value);
                                tvResult.setText(String.valueOf(result));
                            }
                            if(spinnerFrom.equals("Fahrenheit") && spinnerInto.equals("Celsius")){
                                result = fahrenheit2Celsius(value);
                                tvResult.setText(String.valueOf(result));
                            }
                        }
                }

                //todo: add results to a string for history [e.g. input,unit,result,unit]
            }
        });
    }

    public static double celsius2Fahrenheit(double c){
        return 32+c*9/5;
    }
    public static double fahrenheit2Celsius(double f){
        return (f-32)*5/9;
    }

    private void initViews(View view) {
        edInput = view.findViewById(R.id.edInput);
        spinFrom = view.findViewById(R.id.spinFrom);
        spinInto = view.findViewById(R.id.spinInto);
        btnConvert = view.findViewById(R.id.btnConvert);
        tvResult = view.findViewById(R.id.tvResult);
        tvCurrent = view.findViewById(R.id.tvCurrent);
    }
}