package com.example.englishstudy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class ListAdapter extends ArrayAdapter<QnA> {
    private TextView tv_list_question;
    private TextView tv_list_answer;

    private Context context;
    private List mList;
    private ListView mListView;

    class ListViewHolder
    {
        public TextView question;
        public TextView answer;
        public CheckBox checkBox;
    }

    public ListAdapter(Context context, List<QnA> list, ListView listview)
    {
        super(context, 0, list);

        this.context = context;
        this.mList = list;
        this.mListView = listview;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;
        ListViewHolder viewHolder;
        String status;

        if(rowView == null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            rowView = layoutInflater.inflate(R.layout.listview_item, parent, false);

            viewHolder = new ListViewHolder();
            viewHolder.question = (TextView) rowView.findViewById(R.id.tv_list_question);
            viewHolder.answer = (TextView) rowView.findViewById(R.id.tv_list_answer);
            viewHolder.checkBox = (CheckBox) rowView.findViewById(R.id.cb_list_checkbox);

            rowView.setTag(viewHolder);

            status = "created";
        }
        else
        {
            viewHolder = (ListViewHolder) rowView.getTag();

            status = "reused";
        }

        String Tag = rowView.getTag().toString();
        int idx = Tag.indexOf("@");
        String tag = Tag.substring(idx+1);

        final QnA qna = (QnA)mList.get(position);

        viewHolder.question.setText(qna.getQuestion());
        viewHolder.answer.setText(qna.getAnswer());
        viewHolder.checkBox.setChecked(false);

        return rowView;
    }
}
