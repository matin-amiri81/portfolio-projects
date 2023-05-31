package com.example.labs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labs.R;
import com.example.labs.provider.BookViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListBookActivity extends AppCompatActivity {

    EditText authorET;
    Button Btn;
    BookViewModel bmBookViewModel;
    RecyclerView recyclerView;
    BookRecycleViewAdapter adapter;

    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);


        authorET = findViewById(R.id.author_et_id);
        Btn = findViewById(R.id.author_btn);
        bmBookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        recyclerView = findViewById(R.id.author_recycle_id);

        layoutManager = new LinearLayoutManager(this);
        adapter = new BookRecycleViewAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
    public void onAuthorClick(View v) {
        String author = authorET.getText().toString();
        bmBookViewModel.selectAuthorBooks(author).observe(this, books -> {
            List<Book> library = books;
            adapter.setLibrary(library);
            adapter.notifyDataSetChanged();
        });

    }

}