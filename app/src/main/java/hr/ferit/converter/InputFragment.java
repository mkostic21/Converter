package hr.ferit.converter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

    //TODO add mass and temp. ratios

    private final static float[] ratio = {0.393701f, 0.0328084f, 0.0109361f, 6.21371e-6f, 10.0f, 1.0f, 0.01f, 1.0e-5f};

    public static InputFragment newInstance() {
        return new InputFragment();
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
    }

    private void setupListeners() {
        //TODO implement switching to different conversion rates <- buttons from HOME
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = edInput.getText().toString();

                if(input.trim().length()!=0){
                    int index1 = spinFrom.getSelectedItemPosition();
                    int index2 = spinInto.getSelectedItemPosition();
                    float value = Float.parseFloat(input);
                    float result = value / ratio[index1] * ratio[index2];
                    tvResult.setText(String.valueOf(result));
                }
            }
        });
    }

    private void initViews(View view) {
        edInput = view.findViewById(R.id.edInput);
        spinFrom = view.findViewById(R.id.spinFrom);
        spinInto = view.findViewById(R.id.spinInto);
        btnConvert = view.findViewById(R.id.btnConvert);
        tvResult = view.findViewById(R.id.tvResult);
        tvCurrent = view.findViewById(R.id.tvCurrent);

        //TODO reformat to separate func (array source <- onClick from HOME)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.length_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinFrom.setAdapter(adapter);
        spinInto.setAdapter(adapter);

    }
}