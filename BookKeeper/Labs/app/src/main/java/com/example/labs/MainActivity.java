package com.example.labs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labs.provider.BookViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {

    EditText titleEt;
    EditText priceEt;
    EditText bookIdEt;
    EditText isbnEt;
    EditText authorEt;
    EditText descriptionEt;
    FloatingActionButton floatingActionButton;

    public static final String[] attribute_keys
            = {"book_id_key","title_key","isbn_key","author_key","description_key","price_key"};
    public static final int[] attribute_id
            = {R.id.bookIdInput_id,R.id.titleInput_id,R.id.isbnInput_id,R.id.authorInput_id,R.id.descriptionInput_id,R.id.priceInput_id};
    EditText[] attribute_objects = new EditText[6];
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private ArrayList<Book> library = new ArrayList<>();

    private BookViewModel mBookViewModel;

    DatabaseReference cloudDB;

    DatabaseReference bookTable; //branch tree (no relational database)
    View touchView;

    GestureDetector detector;
    ScaleGestureDetector scaleDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout_main);
        setAttributes();
        setToolbar();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_id,new ListBooksFragment()).commit();

        cloudDB = FirebaseDatabase.getInstance().getReference();
        bookTable = cloudDB.child("labs/books");


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id = item.getItemId();
               switch (id){
                   case(R.id.addBook):
                       addBook();
                       break;

                   case(R.id.removeBook):
                       Toast.makeText(MainActivity.this, "removing book", Toast.LENGTH_SHORT).show();
                       mBookViewModel.deleteLastBook();
                       break;
                   case(R.id.clearLibrary):
                       mBookViewModel.deleteAllBooks();
                       bookTable.removeValue();
                       Snackbar.make(navigationView,"library cleared", Snackbar.LENGTH_LONG)
                               .setAction("Action", null).show();

                       break;
                   case(R.id.listAll):
                       changeToListBookActivity();
                       break;

                   default:
                       break;
               }
               drawerLayout.closeDrawers();
               return true;
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBook();
            }
        });

        if (savedInstanceState == null || savedInstanceState.isEmpty()) {load();}
        else {onRestoreInstanceState(savedInstanceState);}

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS, android.Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);
        MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();
        registerReceiver(myBroadCastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER));
        setTouchView();


        getTopRatedBooks();
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //String titleStr = titleEt.getText().toString();
        //String isbnStr = isbnEt.getText().toString();
        //outState.putString(attribute_keys[1],titleStr);
        //outState.putString(attribute_keys[2],isbnStr);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //String titleStr = savedInstanceState.getString(attribute_keys[1]);
        //String isbnStr = savedInstanceState.getString(attribute_keys[2]);
        //titleEt.setText(titleStr);
        //isbnEt.setText(isbnStr);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case(R.id.clearFields):
                clear();
                Toast.makeText(MainActivity.this, "Fields Cleared", Toast.LENGTH_SHORT).show();
                break;
            case(R.id.loadFields):
                load();
                Toast.makeText(MainActivity.this, "Fields Loaded", Toast.LENGTH_SHORT).show();
                break;
            default:
                return true;
        }
        return true;
    }
    public void addBook(){
        String msg;
        Double price;

        String bookIdStr = bookIdEt.getText().toString();
        String titleStr = titleEt.getText().toString();
        String isbnStr = isbnEt.getText().toString();
        String authorStr = authorEt.getText().toString();
        String descriptionStr = descriptionEt.getText().toString();
        String priceStr = priceEt.getText().toString();

        if (titleStr.equals("")){
            msg = "No Title given";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();}
        else {
            if(priceStr.equals("")){price = 0.00;}
            else{price = Double.parseDouble(priceStr); }
            msg = String.format(titleStr + " | (%.2f)",price);
            Book book = new Book(bookIdStr,titleStr,isbnStr,authorStr,descriptionStr,priceStr);
            mBookViewModel.insertBook(book);
            bookTable.push().setValue(book);

            Toast.makeText(this, String.format("adding "+titleStr+" with price: (%.2f)",price), Toast.LENGTH_SHORT).show();
        }
        save();
    }
    public void clear(){
        for (EditText attribute:attribute_objects) {
            attribute.setText("");
        }
        /*
        titleEt.setText("");
        bookIdEt.setText("");
        isbnEt.setText("");
        authorEt.setText("");
        descriptionEt.setText("");
        priceEt.setText("");
         */
    }

    public void load(){

        SharedPreferences sP = getSharedPreferences("file1",MODE_PRIVATE);
        for (int i = 0; i < attribute_objects.length; i++) {
            String loadString = sP.getString(attribute_keys[i],"");
            attribute_objects[i].setText(loadString);
        }


        /*
        bookIdEt.setText(sP.getString(book_id_key,""));
        titleEt.setText(sP.getString(title_key,""));
        isbnEt.setText(sP.getString(isbn_key,""));
        authorEt.setText(sP.getString(author_key,""));
        descriptionEt.setText(sP.getString(description_key,""));
        priceEt.setText(sP.getString(price_key,""));
         */


    }
    public void save(){
        SharedPreferences sP = getSharedPreferences("file1", MODE_PRIVATE);
        SharedPreferences.Editor editor = sP.edit();
        for (int i = 0; i < attribute_objects.length; i++) {
            String saveString = attribute_objects[i].getText().toString();
            editor.putString(attribute_keys[i],saveString);

        }
        editor.apply();

        /*
        editor.putString(book_id_key, bookIdEt.getText().toString());
        editor.putString(title_key, titleEt.getText().toString());
        editor.putString(isbn_key, isbnEt.getText().toString());
        editor.putString(author_key, authorEt.getText().toString());
        editor.putString(description_key, descriptionEt.getText().toString());
        editor.putString(price_key, priceEt.getText().toString());
         */

    }
    class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String msg = intent.getStringExtra(SMSReceiver.SMS_MSG_KEY);

            StringTokenizer sT = new StringTokenizer(msg, "|");

            for (int i = 0; i < attribute_objects.length; i++) {
                String receivedString = sT.nextToken();
                attribute_objects[i].setText(receivedString);
            }





            /*
            String bookIDStr = sT.nextToken();
            String tittleStr = sT.nextToken();
            String isbnStr = sT.nextToken();
            String authorStr = sT.nextToken();
            String descriptionStr = sT.nextToken();
            String priceStr = sT.nextToken();

            bookIdEt.setText(bookIDStr);
            titleEt.setText(tittleStr);
            isbnEt.setText(isbnStr);
            authorEt.setText(authorStr);
            descriptionEt.setText(descriptionStr);
            priceEt.setText(priceStr);

             */
        }
    }
    private void setAttributes() {
        touchView = findViewById(R.id.touch_view_id);

        //recyclerView = findViewById(R.id.recycle_view_id);
        //layoutManager = new LinearLayoutManager(this);
        //adapter = new BookRecycleViewAdapter();
        mBookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_main);
        bookIdEt = findViewById(R.id.bookIdInput_id);
        titleEt = findViewById(R.id.titleInput_id);
        isbnEt = findViewById(R.id.isbnInput_id);
        authorEt = findViewById(R.id.authorInput_id);
        descriptionEt = findViewById(R.id.descriptionInput_id);
        priceEt = findViewById(R.id.priceInput_id);

        attribute_objects[0] = bookIdEt;
        attribute_objects[1] = titleEt;
        attribute_objects[2] = isbnEt;
        attribute_objects[3] = authorEt;
        attribute_objects[4] = descriptionEt;
        attribute_objects[5] = priceEt;

    }
    private void setToolbar(){
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout,toolbar,R.string.OpenNavDrawer,R.string.CloseNavDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }
    public void changeToListBookActivity() {
        Intent intent = new Intent(MainActivity.this, ListBookActivity.class);
        startActivity(intent);
    }

    public List<Book> getTopRatedBooks(){
        final List<Book> result = new ArrayList<Book>();
        mBookViewModel.selectTop10Books().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                result.addAll(books);
                mBookViewModel.selectTop10Books().removeObservers(MainActivity.this);
            }
        });
        return result;
    }

    /*
    int x,y,xm,ym =0;
    */
    public void setTouchView(){
        detector = new GestureDetector(MainActivity.this,new MyGestureDetector());
        scaleDetector = new ScaleGestureDetector(MainActivity.this,new MyScaleDetector());
        touchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                scaleDetector.onTouchEvent(event);


                /*
                int eventType = event.getActionMasked();
                if (eventType == MotionEvent.ACTION_DOWN){
                    x = (int) event.getX();
                    y = (int) event.getY();
                }
                else if (eventType == MotionEvent.ACTION_MOVE){
                    int x2 = (int) event.getX();
                    int y2 = (int) event.getY();
                    int xDif = Math.abs(x2 - xm);
                    int yDif = Math.abs(y2 - ym);

                    if (x2 > xm && (xDif > yDif)){
                        //swiping right incremental
                        int curPrice = Integer.parseInt(priceEt.getText().toString());
                        curPrice++;
                        priceEt.setText(String.valueOf(curPrice));
                    }
                    //if (x2 < xm && (xDif > yDif)){
                        //swiping left incremental
                    //}
                    xm = x2;
                    ym = y2;
                }
                else if (eventType == MotionEvent.ACTION_UP) {
                    int x2 = (int) event.getX();
                    int y2 = (int) event.getY();
                    int xDif = Math.abs(x2 - x);
                    int yDif = Math.abs(y2 - y);
                    if ( (xDif > 75) && (yDif >75)){
                        mBookViewModel.deleteAllBooks();
                        bookTable.removeValue();
                        Snackbar.make(navigationView,"library cleared", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    else if (x2 > x && (xDif > yDif)) {
                        //("Swipe Right");
                    } else if (x2 < x && (xDif > yDif)) {
                        //("Swipe Left");
                        addBook();
                    } else if (y2 > y && (yDif > xDif)) {
                        //("Swipe Down");;
                    } else if (y2 < y && (yDif > xDif)) {
                        //("Swipe Up");;
                        clear();
                    }
                }

                 */
                return true;
            }
        });
    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
            isbnEt.setText(getRandomISBN());
            return true;
        }

        @Override
        public boolean onDoubleTap(@NonNull MotionEvent e) {
            clear();
            return true;
        }

        @Override
        public boolean onScroll(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
            if((int) Math.abs(distanceY) < 4) {
                int price = Integer.parseInt(priceEt.getText().toString());
                price += distanceX;
                priceEt.setText(String.valueOf(price));
            }
            return true;
        }

        @Override
        public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            int asq = (int) Math.pow(velocityX, 2);
            int bsq = (int) Math.pow(velocityY, 2);
            int c = (int) Math.sqrt(asq + bsq);
            if(c > 1000){
                moveTaskToBack(true);
            }
            return true;
        }

        @Override
        public void onLongPress(@NonNull MotionEvent e) {
            load();
        }

    }
    public int scale;
    class MyScaleDetector extends  ScaleGestureDetector.SimpleOnScaleGestureListener{

        @Override
        public boolean onScaleBegin(@NonNull ScaleGestureDetector detector) {
            scale = 0;
            return true;
        }

        @Override
        public boolean onScale(@NonNull ScaleGestureDetector detector) {
            scale ++;
            if(scale > 5){
                mBookViewModel.deleteAllBooks();
                bookTable.removeValue();                ;
            }
            return true;
        }

        @Override
        public void onScaleEnd(@NonNull ScaleGestureDetector detector) {

        }
    }
    public String getRandomISBN(){
        String digits = "0123456789";
        int length = 6;
        Random random = new Random();
        char[] buf = new char[length];
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = digits.charAt(random.nextInt(digits.length()));
        return new String(buf);
    }

}