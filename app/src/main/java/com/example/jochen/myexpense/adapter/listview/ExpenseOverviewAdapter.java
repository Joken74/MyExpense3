package com.example.jochen.myexpense.adapter.listview;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.jochen.myexpense.R;
import com.example.jochen.myexpense.model.Expense;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Jochen on 28.10.2017.
 */

// Folge 7 macht aus ArrayAdapter ein CursorAdapter "extends ArrayAdapter<Expense>"

public class ExpenseOverviewAdapter extends ArrayAdapter<Expense> {

    /*public ExpenseOverviewAdapter(final Context context, final Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return null;
    }*/



    // wir brauchen diesen Super-konstruktor...
   public ExpenseOverviewAdapter(final Context context, final List<Expense> objects) {
        // 0 = wir kriegen kein Layout, hab ich oben gelöscht zwischen context und List
        super(context, 0, objects);
    }

    // View Methode muss überschrieben werden
    // View wird pro Element aufgerufen, also pro Expense
    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {
        Expense currentExpense = getItem(position);

        View view = convertView;

        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.expense_overview_listitem, parent, false);
        }
        TextView amount = (TextView) view.findViewById(R.id.amount);
        TextView category = (TextView) view.findViewById(R.id.category);
        TextView dueDate = (TextView) view.findViewById(R.id.date);

        amount.setText(String.valueOf(currentExpense.getAmount()));
        category.setText(String.valueOf(currentExpense.getCategory()));

        if (currentExpense.getDate() == null) {
            dueDate.setVisibility(View.GONE);
        } else {
            dueDate.setVisibility(View.VISIBLE);
            dueDate.setText(String.valueOf(currentExpense.getDate().get(Calendar.YEAR)));
        }

        return view;
    }
}
