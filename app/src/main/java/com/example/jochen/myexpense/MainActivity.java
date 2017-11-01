package com.example.jochen.myexpense;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jochen.myexpense.adapter.listview.ExpenseOverviewAdapter;
import com.example.jochen.myexpense.db.MyExpenseOpenHandler;
import com.example.jochen.myexpense.model.Expense;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {

    // private static final String LOG_TAG = MyExpenseOpenHandler.class.getSimpleName();

    private ListView resultList;
    private List<Expense> dataSource;
    private ExpenseOverviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Neuer Ansatz:
        this.resultList = (ListView) findViewById(R.id.resultList) ;

        Button create = (Button) findViewById(R.id.create);
        Button updateFirst = (Button) findViewById(R.id.updateFirst);
        Button clearFirst = (Button) findViewById(R.id.clearFirst);
        Button clearAll = (Button) findViewById(R.id.clearAll);

        this.dataSource = MyExpenseOpenHandler.getInstance(this).readAllExpenses();

        this.adapter = new ExpenseOverviewAdapter(this, dataSource);

        // Kontext der Activity = this, layout wie jeder einzelne Eintrag angezeigt werden soll, Datenquelle
        // wird gelöscht: android.R.layout.simple_list_item_1,
        this.resultList.setAdapter(adapter);
        // Was passiert bei OnClick?
        this.resultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, final long l) {
                Object element = adapterView.getAdapter().getItem(i);

                Log.e("Click on List: ", element.toString());

            }
        });

        if(updateFirst != null) {
            updateFirst.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    if(dataSource.size() > 0) {
                        MyExpenseOpenHandler database = MyExpenseOpenHandler.getInstance(MainActivity.this);
                        Random r = new Random();
                        dataSource.get(0).setAmount(String.valueOf(r.nextInt()));
                        database.updateExpense(dataSource.get(0));
                        refreshListView();
                    }

                }
        });
        }

        if(clearFirst != null) {
            clearFirst.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    if(dataSource.size() > 0) {
                        MyExpenseOpenHandler database = MyExpenseOpenHandler.getInstance(MainActivity.this);
                        database.deleteExpense(dataSource.get(0));
                        refreshListView();
                    }

                }
            });
        }

        if(create != null) {
            create.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    MyExpenseOpenHandler database = MyExpenseOpenHandler.getInstance(MainActivity.this);
                    database.createExpense(new Expense("123", "Auto"));
                    database.createExpense(new Expense("234", "Essen", Calendar.getInstance()));

                    //database.createExpense(new Expense("234", "Essen", "2016"));
                    refreshListView();
                }
            });
        }

        if(clearAll != null) {
            clearAll.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    MyExpenseOpenHandler database = MyExpenseOpenHandler.getInstance(MainActivity.this);
                    database.deleteAllExpenses();
                    refreshListView();

                }
            });
        }
    }

    private void refreshListView() {
        dataSource.clear();
        dataSource.addAll(MyExpenseOpenHandler.getInstance(this).readAllExpenses());
        adapter.notifyDataSetChanged();
    }
}
