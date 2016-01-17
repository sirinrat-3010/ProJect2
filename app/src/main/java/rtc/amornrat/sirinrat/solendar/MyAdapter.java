package rtc.amornrat.sirinrat.solendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class MyAdapter extends BaseAdapter{

    //Explicit
    private Context objContext;
    private String[] titleStrings;

    public MyAdapter(Context objContext, String[] titleStrings) {
        this.objContext = objContext;
        this.titleStrings = titleStrings;
    }   // Constructor

    @Override
    public int getCount() {
        return titleStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View objView1 = objLayoutInflater.inflate(R.layout.todo_listview, viewGroup, false);

        TextView titleTextView = (TextView) objView1.findViewById(R.id.txtToDoShowLIst);
        titleTextView.setText(titleStrings[i]);

        return objView1;
    }
} // Main Class
