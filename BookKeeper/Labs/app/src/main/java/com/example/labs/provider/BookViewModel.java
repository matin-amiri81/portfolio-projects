package com.example.labs.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.labs.Book;

import java.util.List;

public class BookViewModel extends AndroidViewModel {
    private BookRepository mRepository;
    private LiveData<List<Book>> mAllBooks;

    public BookViewModel(@NonNull Application application) {
        super(application);
        mRepository = new BookRepository(application);
        mAllBooks = mRepository.getAllBooks();
    }

    public LiveData<List<Book>> getAllBooks() {
        return mAllBooks;
    }
    public void insertBook(Book book){
        mRepository.insert(book);
    }
    public void deleteAllBooks(){
        mRepository.deleteAll();
    }
    public void deleteLastBook(){
        mRepository.deleteLastBook();
    }
    public LiveData<List<Book>> selectTop10Books(){
        return mRepository.selectTop10Books();
    }
    public LiveData<List<Book>> selectAuthorBooks(String author){
        return mRepository.selectAuthorBooks(author);
    }
}
