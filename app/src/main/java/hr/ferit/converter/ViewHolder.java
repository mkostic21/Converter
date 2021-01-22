package hr.ferit.converter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//TODO: implements click listener
public class ViewHolder extends RecyclerView.ViewHolder{

    private final TextView tvHistory;
    private final ImageButton btnHistory;

    //TODO: click listener goes in constructor
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        //TODO: reformat...
        tvHistory = itemView.findViewById(R.id.tvHistory);
        btnHistory = itemView.findViewById(R.id.btnHistory);
    }

    //TODO: methods that can be called on item click go here:
}
