package manakov.sample.dbapp.Proficiency;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import manakov.sample.dbapp.Proficiency.Proficiency;
import manakov.sample.dbapp.R;

public class SpinnerProficiencyAdapter extends ArrayAdapter<Proficiency> {

    public SpinnerProficiencyAdapter(@NonNull Context context, ArrayList list) {
        super(context, 0 , list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_row, parent, false
            );
        }

        TextView textView = convertView.findViewById(R.id.spinnerRowTextView);
        Proficiency proficiency = getItem(position);

        textView.setText(proficiency.getProficiency());
        return convertView;
    }
}