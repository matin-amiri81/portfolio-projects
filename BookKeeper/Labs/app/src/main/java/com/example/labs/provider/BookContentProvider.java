package com.example.labs.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import com.example.labs.Book;

public class BookContentProvider extends ContentProvider {

    BookDatabase db;
    //UriMatcher uriMatcher = createUriMatcher();
    private static final String AUTHORITY = "fit2081.app.matin";

    //not 100% sure about what this does with insert
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    private static final int BOOK_SINGLE = 1;

    //uri matcher not used just vibing
    UriMatcher uriMatcher = createUriMatcher();

    public BookContentProvider(){}

    private static UriMatcher createUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // "fit2081.app.matin/book_table"
        uriMatcher.addURI(AUTHORITY, Book.TABLE_NAME, BOOK_SINGLE);
        return uriMatcher;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long rowId = db
                .getOpenHelper()
                .getWritableDatabase()
                .insert(Book.TABLE_NAME, 0, values);

        return ContentUris.withAppendedId(CONTENT_URI, rowId);
    }

    @Override
    public boolean onCreate() {
        db = BookDatabase.getDatabase(getContext());
        return true;
    }

    //old:       columns        table           rows
    // SELECT maker,model FROM cars WHERE price > 1500 and year = 2023

    //new:
    // projection: ["maker","model"]
    // selection: "price > ? and year = ?"
    // selectionArgs: ["1500","2023"]
    // sortOrder: ascending or descending

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {


        //int pattern = uriMatcher.match(uri);
        //switch (pattern){}

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(Book.TABLE_NAME);
        String query = builder.buildQuery(projection, selection,selectionArgs,null,null,sortOrder,null);
        //String query = builder.buildQuery(projection, selection,null,null,sortOrder,null);

        Cursor cursor = db              //cursor allows us to save multiple rows/ portion of the table with a cursor
                .getOpenHelper()        //low level of database raw database
                .getReadableDatabase()  //readable database / because we just want to retrieve
                .query(query);          // our query

        return cursor;                  // curse is also a "cursor" allows to move through the table


    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}