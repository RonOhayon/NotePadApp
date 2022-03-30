package com.ronoh.moveohometask;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    LayoutInflater inflater;
    List<Note> notes;
    Adapter(Context context,List<Note> notes){
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_view,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
    String title = notes.get(position).getTitle();
    String date = notes.get(position).getDate();
    String id = ""+position;
    holder.ls_LBL_title.setText(title);
    holder.ls_LBL_date.setText(date);
    holder.ls_LBL_id.setText(id);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ls_LBL_title,ls_LBL_date,ls_LBL_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ls_LBL_title = itemView.findViewById(R.id.ls_LBL_title);
            ls_LBL_date = itemView.findViewById(R.id.ls_LBL_date);
            ls_LBL_id = itemView.findViewById(R.id.ls_LBL_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),NoteDetails.class );
                    i.putExtra("ID",notes.get(getAdapterPosition()).getID());
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}







