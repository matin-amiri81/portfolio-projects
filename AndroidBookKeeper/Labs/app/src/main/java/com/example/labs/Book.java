package com.example.labs;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book_table")
public class Book{
    public static final String TABLE_NAME = "book_table";

    public static final String COLUMN_BOOK_ID = "book_id";
    public static final String COLUMN_BOOK_TITLE = "book_title";
    public static final String COLUMN_BOOK_ISBN = "book_isbn";
    public static final String COLUMN_BOOK_AUTHOR = "book_author";
    public static final String COLUMN_BOOK_DESCRIPTION = "book_description";
    public static final String COLUMN_BOOK_PRICE = "book_price";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "book_id")
    private String bookId;
    @ColumnInfo(name = "book_title")
    private String title;
    @ColumnInfo(name = "book_isbn")
    private String isbn;
    @ColumnInfo(name = "book_author")
    private String author;
    @ColumnInfo(name = "book_description")
    private String description;
    @ColumnInfo(name = "book_price")
    private String price;

    public Book(String bookId, String title, String isbn, String author, String description, String price) {
        this.bookId = bookId;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.description = description;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookId='" + bookId + '\'' +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
