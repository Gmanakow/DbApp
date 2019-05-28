package manakov.sample.dbapp.Language;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import manakov.sample.dbapp.Language.Language;
import manakov.sample.dbapp.R;

public class SpinnerLanguageAdapter extends ArrayAdapter<Language> {

    public SpinnerLanguageAdapter(@NonNull Context context, ArrayList list) {
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
        Language language = getItem(position);

        textView.setText(language.getLanguage());
        return convertView;
    }
}
