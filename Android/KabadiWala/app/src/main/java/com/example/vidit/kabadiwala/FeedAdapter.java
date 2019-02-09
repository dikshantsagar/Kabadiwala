package com.example.vidit.kabadiwala;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * @author Alhaytham Alfeel on 10/10/2016.
 */

public class FeedAdapter extends ArrayAdapter<Card>
{
    ArrayList<Card> feedArrayList;
    Context context;
    LayoutInflater inflater;
    public FeedAdapter(Context context,ArrayList<Card> feedArrayList) {
        super(context, R.layout.card_layout);
        this.context=context;
        this.feedArrayList=feedArrayList;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.card_layout, parent, false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Card card = getItem(position);

        holder.imageView.setImageResource(R.drawable.scrap);
        holder.title.setText(card.title);
        holder.price.setText("₹"+card.price);
        holder.quantity.setText(card.quantity+"kg");
        holder.currentBid.setText("Current bid: ₹"+card.bid);

        return convertView;
    }

    @Override
    public int getCount()
    {
        return feedArrayList.size();
    }

    static class ViewHolder {
        ImageView imageView;
        TextView title;
        TextView quantity;
        TextView price;
        TextView currentBid;

        ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.productImageView);
            title = (TextView) view.findViewById(R.id.titleTextView);
            price = (TextView) view.findViewById(R.id.priceTextView);
            quantity=(TextView) view.findViewById(R.id.quantityTextView);
            currentBid=view.findViewById(R.id.currentBidTextView);

        }
    }
}
