package com.example.loginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<PersonUtils> personUtils;

    public CustomRecyclerAdapter(Context context, List personUtils) {
        this.context = context;
        this.personUtils = personUtils;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(personUtils.get(position));

        PersonUtils pu = personUtils.get(position);

        holder.pName.setText(pu.getPersonFirstName());
        holder.last.setText(pu.getPersonLastName());
        holder.pJobProfile.setText(pu.getJobProfile());

    }

    @Override
    public int getItemCount() {
        return personUtils.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView pName;
        public TextView pJobProfile,last;

        public ViewHolder(View itemView) {
            super(itemView);

            pName = (TextView) itemView.findViewById(R.id.pNametxt);
            pJobProfile = (TextView) itemView.findViewById(R.id.pJobProfiletxt);
            last = (TextView) itemView.findViewById(R.id.last);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    PersonUtils cpu = (PersonUtils) view.getTag();

                    Toast.makeText(view.getContext(), "name :"+cpu.getPersonFirstName()+"full name"+cpu.getPersonLastName()+"Private :"+ cpu.getJobProfile(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }


}