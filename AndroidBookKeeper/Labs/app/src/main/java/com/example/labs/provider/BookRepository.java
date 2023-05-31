package com.example.labs.provider;


import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.labs.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private BookDao mBookDao;
    private LiveData<List<Book>> mAllBooks;
    private LiveData<List<Book>> top10Books;


    BookRepository(Application application) {
        BookDatabase db = BookDatabase.getDatabase(application);
        mBookDao = db.bookDao();
        mAllBooks = mBookDao.getAllBooks();
    }
    LiveData<List<Book>> getAllBooks() {
        return mAllBooks;
    }
    void insert(Book book) {
        BookDatabase.databaseWriteExecutor.execute(() -> mBookDao.addBookToDatabase(book));
    }

    void deleteAll(){
        BookDatabase.databaseWriteExecutor.execute(()->{
            mBookDao.deleteAllBooks();
        });
    }
    void deleteLastBook() {
        BookDatabase.databaseWriteExecutor.execute(() -> {
            mBookDao.deleteLastBook();
        });
    }
    LiveData<List<Book>> selectTop10Books(){
        top10Books = mBookDao.getTopTenBooks();
        return top10Books;
    }
    LiveData<List<Book>> selectAuthorBooks(String author){
        return mBookDao.getAuthorBooks(author);
    }
}
