package com.innova.phonetics.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.innova.phonetics.R;


//An adapter class to display the mainActivity icons in a recyclerView
public class HomeIconAdapter extends RecyclerView.Adapter<HomeIconAdapter.HomeIconViewHolder> {

    private Context context;
            String [] home_items;
            Integer [] items_bg;

    // Define listener member variable
    private static OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public HomeIconAdapter(Context context, String [] home_items, Integer [] items_bg){
        this.context = context;
        this.home_items = home_items;
        this.items_bg = items_bg;
    }

    @NonNull
    @Override
    public HomeIconViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_item, null);
        return new HomeIconViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeIconViewHolder homeIconViewHolder, int i) {
        String item = home_items[i];
        Integer item_bg = items_bg[i];
        homeIconViewHolder.item_text.setText(item);
        homeIconViewHolder.item_btn.setImageResource(item_bg);
    }

    // Get the number of items pased to the adapter
    @Override
    public int getItemCount() {
        return home_items.length;
    }


class HomeIconViewHolder extends RecyclerView.ViewHolder{
    ImageView item_btn;
    TextView item_text;
    CardView cardView;

    public HomeIconViewHolder(@NonNull View itemView) {
        super(itemView);

        item_btn = itemView.findViewById(R.id.item_btn);
        item_text = itemView.findViewById(R.id.item_text);
        cardView = itemView.findViewById(R.id.cardView);

        // Set the item clicked to the view
        cardView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Triggers clicked upwards to the adapter on click
                if (listener != null)
                    listener.onItemClick(cardView, getLayoutPosition());
            }
        });
    }
}
}
