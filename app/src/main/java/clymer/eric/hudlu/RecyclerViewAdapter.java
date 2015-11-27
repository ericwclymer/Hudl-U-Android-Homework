package clymer.eric.hudlu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by eric.clymer on 11/26/2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.EricsViewHolder> {

    String[] inputData;

    public RecyclerViewAdapter(Context context, String[] data) {
        inputData = data;
    }

    @Override
    public EricsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new EricsViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(EricsViewHolder holder, int position) {
        holder.myText.setText(inputData[position]);
    }

    @Override
    public int getItemCount() {
        return inputData.length;
    }

    public static class EricsViewHolder extends RecyclerView.ViewHolder {
        TextView myText;
        public EricsViewHolder(View itemView) {
            super(itemView);
            myText = (TextView) itemView.findViewById(R.id.item_my_text);
        }
    }
}
