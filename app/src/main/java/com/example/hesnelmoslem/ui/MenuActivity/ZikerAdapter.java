package com.example.hesnelmoslem.ui.MenuActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hesnelmoslem.R;
import com.example.hesnelmoslem.data.MenuActivity.ZikerConten;
import com.example.hesnelmoslem.data.ZikerItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ZikerAdapter extends RecyclerView.Adapter<ZikerAdapter.ZikerHolder> {

    private List<ZikerConten> zikerContenList;
    private OnItemClickListener listener;

    public ZikerAdapter() {
        zikerContenList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ZikerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new ZikerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZikerHolder holder, int position) {
        holder.textViewZikerContent.setText(zikerContenList.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return this.zikerContenList.size();
    }


    public void setZikerContenList(List<ZikerConten> zikerContenList) {
        this.zikerContenList = zikerContenList;
        notifyDataSetChanged();
    }

    class ZikerHolder extends RecyclerView.ViewHolder {
        public TextView textViewZikerContent;

        public ZikerHolder(@NonNull View itemView) {
            super(itemView);
            textViewZikerContent = itemView.findViewById(R.id.menu_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null&&getAdapterPosition()!=RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(getZikerConten(getAdapterPosition()));
                    }
                }
            });
        }
    }
    private ZikerConten getZikerConten(int position)
    {
        return this.zikerContenList.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(ZikerConten zikerItem);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener=listener;
    }

}
