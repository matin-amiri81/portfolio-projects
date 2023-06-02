package com.example.labs;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class BookRecycleViewAdapter extends RecyclerView.Adapter<BookRecycleViewAdapter.ViewHolder> {

    List<Book> library = new ArrayList<Book>();

    public void setLibrary(List<Book> library) {
        this.library = library;
    }

    @NonNull
    @Override
    public BookRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookRecycleViewAdapter.ViewHolder holder, int position) {

        holder.titleTv.setText(library.get(position).getTitle());
        holder.priceTv.setText("$"+library.get(position).getPrice());
        holder.bookIdTv.setText("Id: "+library.get(position).getBookId());
        holder.isbnTv.setText("ISBN: "+library.get(position).getIsbn());
        holder.authorTv.setText(library.get(position).getAuthor());
        holder.descriptionTv.setText(library.get(position).getDescription());

        holder.positionTV.setText(String.valueOf(position+1));

    }

    @Override
    public int getItemCount() {
        return library.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTv;
        public TextView priceTv;
        public TextView bookIdTv;
        public TextView isbnTv;
        public TextView authorTv;
        public TextView descriptionTv;
        public TextView positionTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv=itemView.findViewById(R.id.tittle_book_id);
            priceTv=itemView.findViewById(R.id.price_book_id);
            bookIdTv=itemView.findViewById(R.id.bookid_book_id);
            isbnTv=itemView.findViewById(R.id.isbn_book_id);
            authorTv=itemView.findViewById(R.id.author_book_id);
            descriptionTv=itemView.findViewById(R.id.description_book_id);

            positionTV = itemView.findViewById(R.id.position_id);
        }
    }
}
