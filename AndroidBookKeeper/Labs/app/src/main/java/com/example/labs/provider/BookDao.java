package com.example.labs.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.labs.Book;

import java.util.ArrayList;
import java.util.List;
@Dao
public interface BookDao {
    @Query("select * from book_table")
    public LiveData<List<Book>> getAllBooks();

    @Query("delete from book_table where book_id = :id")
    public void deleteBook(int id);

    @Insert
    public void addBookToDatabase(Book book);

    @Query("delete from book_table")
    public void deleteAllBooks();

    @Query("DELETE FROM book_table WHERE id=(SELECT MAX(id) FROM book_table)")
    public void deleteLastBook();

    @Query("select * from book_table order by cast(book_price as int) desc limit 10")
    public LiveData<List<Book>> getTopTenBooks();

    @Query("select * from book_table where book_author = :author")
    public LiveData<List<Book>> getAuthorBooks(String author);
}
