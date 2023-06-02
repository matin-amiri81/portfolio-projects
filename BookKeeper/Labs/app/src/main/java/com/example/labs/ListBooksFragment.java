package com.example.labs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.labs.provider.BookViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListBooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListBooksFragment extends Fragment {

    List<Book> library;
    RecyclerView recyclerView;
    BookRecycleViewAdapter adapter;
    BookViewModel bookViewModel;

    public ListBooksFragment() {
        // Required empty public constructor
    }

    public static ListBooksFragment newInstance() {
        ListBooksFragment fragment = new ListBooksFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new BookRecycleViewAdapter();
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        bookViewModel.getAllBooks().observe(this, books -> {
            library = books;
            adapter.setLibrary(library);
            adapter.notifyDataSetChanged();
            Toast.makeText(getContext(),"#Books: "+books.size(),Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_books,container,false);
        recyclerView = v.findViewById(R.id.recycle_view_id);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }
}