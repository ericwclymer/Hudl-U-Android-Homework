package clymer.eric.hudlu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by a guy named eric clymer on 11/26/2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.EricsViewHolder> {

    String[] inputData;
    private OnAdapterInteractionListener mListener;

    public RecyclerViewAdapter(Context context, String[] data) {
        inputData = data;
        mListener = (OnAdapterInteractionListener) context;
    }

    @Override
    public EricsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new EricsViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(EricsViewHolder holder, final int position) {
        holder.myText.setText(inputData[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClicked(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inputData.length;
    }

    public interface OnAdapterInteractionListener {


        void onItemClicked(View view, int position);
    }

    public static class EricsViewHolder extends RecyclerView.ViewHolder {
        TextView myText;
        public EricsViewHolder(View itemView) {
            super(itemView);
            myText = (TextView) itemView.findViewById(R.id.item_my_text);
        }
    }
}
