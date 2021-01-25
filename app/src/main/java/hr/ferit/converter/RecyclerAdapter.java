package hr.ferit.converter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final ArrayList<String> historyData = new ArrayList<>();
    private final ButtonClickListener clickListener;

    public RecyclerAdapter(ButtonClickListener clickListener){
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View historyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(historyView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //TODO: set data in holder here
        holder.displayData(historyData.get(position));
    }

    @Override
    public int getItemCount() {
        return historyData.size();
    }

    public void clearData(){
        clickListener.onClearClick();
        notifyDataSetChanged();
    }

    public void addToHistory(String input) {
        historyData.add(getItemCount(), input);
        notifyDataSetChanged();
    }
}
