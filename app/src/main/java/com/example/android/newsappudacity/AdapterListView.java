package com.example.android.newsappudacity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Анастасия on 08.07.2018.
 */

public class AdapterListView extends ArrayAdapter<NewsClass> {
    private static final String DATE_SEPARATOR = "T";
    String dateOfPublication;
    public AdapterListView(Activity context, ArrayList<NewsClass> guard) {
        super(context, 0, guard);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.custom_adapter_item, parent, false);
        }

        NewsClass currentNewsClass = getItem(position);

        TextView section = convertView.findViewById(R.id.section);
        String sectionString = currentNewsClass.getSection();

        section.setText(sectionString);

        TextView date = convertView.findViewById(R.id.date);
        String formattedDate = currentNewsClass.getDateOfPublication();
        if(formattedDate.contains(DATE_SEPARATOR)){
            String[] parts= formattedDate.split(DATE_SEPARATOR);
            dateOfPublication = parts[0];
        }
        date.setText(dateOfPublication);

        TextView title = convertView.findViewById(R.id.title);
        String titleString = currentNewsClass.getTitleOfArticle();
        title.setText(titleString);

        TextView author = convertView.findViewById(R.id.author);
        String authorString = currentNewsClass.getAuthor();
        author.setText(authorString);
        return convertView;
    }
}
