package com.example.android.myformula.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.myformula.R;
import com.example.android.myformula.model.Formula;

import java.util.ArrayList;

/**
 * Created by manaday.mavani on 11/09/15.
 */
public class FormulaListAdapter extends BaseAdapter {

    private ArrayList<Formula> formulaList = new ArrayList<Formula>();
    private LayoutInflater inflater;
    private Context context;

    public FormulaListAdapter(Context context, ArrayList<Formula> formulaList) {
        this.formulaList = formulaList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return formulaList.size();
    }

    @Override
    public Formula getItem(int position) {
        return formulaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.formula_title_list_item, null);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        Formula formula = getItem(position);

        mViewHolder.title.setText(formula.getTitle());
        mViewHolder.description.setText(formula.getDescription());


        return convertView;
    }

    private class ViewHolder {

        TextView title, description;

        public ViewHolder(View item) {
            title = (TextView) item.findViewById(R.id.title_text);
            description = (TextView) item.findViewById(R.id.brief_description_text);
        }

    }

}
