package rtc.amornrat.sirinrat.solendar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowToDoList extends AppCompatActivity {

    //Explicit
    private TextView showDateTextView;
    private ListView toDoListView;
    private String showDateString;
    private String[] strTitle, strID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_to_do_list);

        //Bind Widget
        bindWidget();

        //Show Date
        showDate();

        //Create ListView
        createListView();

    }   // Main Method

    public void clickAddToDo(View view) {

        Intent objIntent = new Intent(ShowToDoList.this, AddToDoList.class);
        objIntent.putExtra("Date", showDateString);
        startActivity(objIntent);
        finish();

    }   // clickAddToDo


    private void createListView() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);

        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM todoTABLE WHERE Date = " + "'" + showDateString + "'", null);
        objCursor.moveToFirst();
        strTitle = new String[objCursor.getCount()];
        strID = new String[objCursor.getCount()];

        for (int i = 0; i < objCursor.getCount(); i++) {

            strTitle[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.DATABASE_ToDo));
            strID[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.DATABASE_id));
            objCursor.moveToNext();

        }   // for
        objCursor.close();

        MyAdapter objMyAdapter = new MyAdapter(ShowToDoList.this, strTitle);
        toDoListView.setAdapter(objMyAdapter);

        //Active When Click ListView for Delete
        toDoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("Solendar1", "Title ==> " + strTitle[i]);

                confirmDelete(strID[i]);

            } // event
        });

    }   // createListView

    private void confirmDelete(String strID) {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        objSqLiteDatabase.delete(ManageTABLE.TABLE_TODO, ManageTABLE.DATABASE_id + "=" + Integer.parseInt(strID), null);
        Toast.makeText(ShowToDoList.this, "Delete OK", Toast.LENGTH_SHORT).show();
        createListView();
    }

    private void bindWidget() {
        showDateTextView = (TextView) findViewById(R.id.txtShowDate);
        toDoListView = (ListView) findViewById(R.id.listView);
    }

    private void showDate() {
        showDateString = getIntent().getStringExtra("Date");
        showDateTextView.setText(showDateString);
    }

}   // Main Class
