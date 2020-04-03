package com.example.copy_note_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.copy_note_app.R;
import com.example.copy_note_app.RoomDB.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter {

    Context context;
    int layout;
    List<Note> NoteList;

    private static onItemClickListener mListener;


    public NoteAdapter(Context context, int layout, List<Note> NoteList) {
        this.context = context;
        this.layout = layout;
        this.NoteList = NoteList;
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        TextView date_note, content_note,title_note;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);

            date_note = (itemView).findViewById(R.id.txt_date);
            content_note = (itemView).findViewById(R.id.txt_note);
            title_note=(itemView).findViewById(R.id.txt_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(v,getLayoutPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        RecyclerView.ViewHolder viewHolder = new ViewHoler(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Note Note = NoteList.get(position);

        ((ViewHoler) holder).date_note.setText(Note.getDate());
        ((ViewHoler) holder).title_note.setText(Note.getTitle());
        ((ViewHoler) holder).content_note.setText(Note.getContent());
    }

    public Note getNoteAt(int position) {
        return NoteList.get(position);
    }

    @Override
    public int getItemCount() {
        return NoteList.size();
    }

    //xu ly Click
    public interface onItemClickListener {
        void onClick(View v,int position);
    }

    public void setOnClickListener(onItemClickListener listener) {
        this.mListener = listener;

    }
}
