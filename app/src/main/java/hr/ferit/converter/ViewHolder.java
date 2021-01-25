package hr.ferit.converter;

import android.content.ClipData;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//TODO: implements click listener
public class ViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvHistory;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);


        tvHistory = itemView.findViewById(R.id.tvHistory);
    }

    public void displayData(String data){
        tvHistory.setText(data);
    }
}
