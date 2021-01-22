package hr.ferit.converter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class InputFragment extends Fragment {

    private EditText edInput;
    private EditText tvResult;
    private Spinner spinFrom;
    private Spinner spinInto;
    private Button btnConvert;

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
        //TODO add listeners (+change interface)
    }

    private void initViews(View view) {
        edInput = view.findViewById(R.id.edInput);
        spinFrom = view.findViewById(R.id.spinFrom);
        spinInto = view.findViewById(R.id.spinInto);
        btnConvert = view.findViewById(R.id.btnConvert);
        tvResult = view.findViewById(R.id.tvResult);
    }
}