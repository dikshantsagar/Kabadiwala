package com.example.vidit.kabadiwala;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConsignmentAdapter extends ArrayAdapter<Consignment>
{
    ArrayList<Consignment> consignmentArrayList;
    Context context;
    LayoutInflater inflater;
    public ConsignmentAdapter(Context context, ArrayList<Consignment> consignmentArrayList)
    {
        super(context, R.layout.consignment_card_layout,consignmentArrayList);
        this.context=context;
        this.consignmentArrayList=consignmentArrayList;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.consignment_card_layout, parent, false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Consignment consignment = getItem(position);

        holder.imageView.setImageResource(consignment.imageId);
        holder.title.setText(consignment.title);
        holder.basePrice.setText(consignment.basePrice);
        holder.quantity.setText(consignment.quantity);
        holder.currentBid.setText(consignment.currentBid);
        return convertView;
    }

    @Override
    public int getCount()
    {
        return consignmentArrayList.size();
    }

    static class ViewHolder {
        ImageView imageView;
        TextView title;
        TextView quantity;
        TextView basePrice;
        TextView currentBid;

        ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.productImageView);
            title = (TextView) view.findViewById(R.id.titleTextView);
            basePrice = (TextView) view.findViewById(R.id.basePriceTextView);
            quantity=(TextView) view.findViewById(R.id.quantityTextView);
            currentBid=view.findViewById(R.id.currentBidTextView);
        }
    }
}

