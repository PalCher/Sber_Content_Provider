package com.nexp.pavel.notepad_contentprovider;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Note> notes;
    private static final String AUTHORITY = "com.nexp.pavel.ass_hm_notepad_room";
    private static final String NOTES_TABLE = "notes";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + NOTES_TABLE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = read();

        mRecyclerView = findViewById(R.id.note_recycler);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(notes);
        mRecyclerView.setAdapter(mAdapter);




        if (getIntent().hasExtra("changeFlag")){
            Intent intent = getIntent();
            Boolean flag = intent.getBooleanExtra("changeFlag", false);
            if (flag){
                notes = read();
                mAdapter.refreshData(notes);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                startActivity(NewNote.newIntent(this));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static final Intent newIntent(Context context, boolean flag){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("changeFlag", flag);
        return intent;

    }

    private List<Note> read(){
        Cursor cursor = getContentResolver().query(CONTENT_URI, null,null,
                null, null);
        List<Note> notes = Converter.convertCursorToNotes(cursor);
        return notes;
    }


}
