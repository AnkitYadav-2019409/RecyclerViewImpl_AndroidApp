package com.example.recyclerviewcontactapp;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class CustomRecylerAdapter extends RecyclerView.Adapter<CustomRecylerAdapter.ViewHolder> {

    private ArrayList<Contact> localDataSet;
    private Random random;
    private Context context;

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomRecylerAdapter(ArrayList<Contact> dataSet) {
        this.localDataSet = dataSet;
        this.random = new Random();
    }

    public int generateRandom() {
        return random.nextInt(3);
    }


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView[] textViews;
        private View view;



        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textViews = new TextView[]{(TextView) view.findViewById(R.id.textViewName), (TextView) view.findViewById(R.id.textViewEmail), (TextView) view.findViewById(R.id.textViewPhone)};
            this.view = view;
            //textViews = (TextView) view.findViewById(R.id.textView);
        }

        public View getView() {
            return view;
        }

        public TextView[] getTextView() {
            return textViews;
        }
    }



    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.mast_custom_layout, viewGroup, false);

        ImageView imageView = view.findViewById(R.id.imageViewPhoto);

        int temp = generateRandom();
        if(temp == 0) {
            imageView.setImageResource(R.drawable.icon1);
        }
        else if(temp == 1) {
            imageView.setImageResource(R.drawable.icon2);
        }
        else {
            imageView.setImageResource(R.drawable.icon3);
        }

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        Contact contact = localDataSet.get(position);

        viewHolder.getTextView()[0].setText(contact.getName());
        viewHolder.getTextView()[1].setText(contact.getEmail());
        viewHolder.getTextView()[2].setText(contact.getPhone());


        String PhoneNo = contact.getPhone();
        ImageView imageViewCall = viewHolder.getView().findViewById(R.id.imageViewPhone);
        imageViewCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewHolder.getView().getContext(), PhoneNo, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + PhoneNo));
                viewHolder.getView().getContext().startActivity(intent);
            }
        });

        String emailID = contact.getEmail();
        String[] addresses = {emailID};
        ImageView imageViewMail = viewHolder.getView().findViewById(R.id.imageViewMessage);
        imageViewMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                viewHolder.getView().getContext().startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public void dialPhoneNumber(String phoneNumber) {

    }

}
