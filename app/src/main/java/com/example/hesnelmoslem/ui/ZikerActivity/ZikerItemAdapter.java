package com.example.hesnelmoslem.ui.ZikerActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hesnelmoslem.R;
import com.example.hesnelmoslem.R.id;
import com.example.hesnelmoslem.data.ZikerItem;


import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.R;

public class ZikerItemAdapter extends RecyclerView.Adapter<ZikerItemAdapter.ZikerItemHolder> {

    private List<ZikerItem> zikerItems = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ZikerItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.example.hesnelmoslem.R.layout.ziker_item, parent, false);
        return new ZikerItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZikerItemHolder holder, int position) {
        holder.textView_REPEAT.setText("Repeat : "+String.valueOf(this.zikerItems.get(position).getRepeat()));
       holder.textView_TRANSLATED_TEXT.setText(this.zikerItems.get(position).getTranslatedText());
        holder.textView_LANGUAGE_ARABIC_TRANSLATED_TEXT.setText(this.zikerItems.get(position).getLanguageArabicTranslatedText());
        holder.textView_ARABIC_TEXT.setText(this.zikerItems.get(position).getArabic_text());
    }

    @Override
    public int getItemCount() {
        return this.zikerItems.size();
    }

    public void setZikerItems(List<ZikerItem> zikerItems) {
        this.zikerItems = zikerItems;
        for(ZikerItem zikerItem:zikerItems)
        {
            Log.d("TAG", "onResponse: "+zikerItem.getId());
        }
        notifyDataSetChanged();
    }
    private ZikerItem getZiker(int position)
    {
        return this.zikerItems.get(position);
    }

    class ZikerItemHolder extends RecyclerView.ViewHolder {

        public TextView textView_LANGUAGE_ARABIC_TRANSLATED_TEXT,
                textView_TRANSLATED_TEXT, textView_REPEAT, textView_ARABIC_TEXT;

        public ZikerItemHolder(@NonNull View itemView) {
            super(itemView);

            textView_ARABIC_TEXT = itemView.findViewById(id.textView_ARABIC_TEXT);
            textView_LANGUAGE_ARABIC_TRANSLATED_TEXT = itemView.findViewById(id.textView_LANGUAGE_ARABIC_TRANSLATED_TEXT);
            textView_TRANSLATED_TEXT = itemView.findViewById(id.textView_TRANSLATED_TEXT);
            textView_REPEAT = itemView.findViewById(id.textView_REPEAT);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null&&getAdapterPosition()!=RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(getZiker(getAdapterPosition()));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(ZikerItem zikerItem);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener=listener;
    }
}
