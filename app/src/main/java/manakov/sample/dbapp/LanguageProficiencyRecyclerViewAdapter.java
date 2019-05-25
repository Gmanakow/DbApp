package manakov.sample.dbapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.Student.StudentLanguageProficiency;

public class LanguageProficiencyRecyclerViewAdapter extends RecyclerView.Adapter<LanguageProficiencyRecyclerViewAdapter.ViewHolder> {
    private ArrayList<StudentLanguageProficiency> studentLanguageProficiencies;
    private View.OnClickListener onClickListener;
    private DbApplication application;

    public LanguageProficiencyRecyclerViewAdapter(ArrayList<StudentLanguageProficiency> studentLanguageProficiencies, DbApplication application){
        this.studentLanguageProficiencies = studentLanguageProficiencies;
        this.application = application;
    }

    @Override
    public LanguageProficiencyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view =
                LayoutInflater.from(
                        parent.getContext()
                ).inflate(
                    R.layout.language_proficiency_recycler_view_item_layout,
                    parent,
                    false
                );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LanguageProficiencyRecyclerViewAdapter.ViewHolder viewHolder, int position){
        viewHolder.languageView.setText(
                application.database.languageDao().getLanguageById(
                    studentLanguageProficiencies.get(position).getLanguageId()
                ).getLanguage()
        );
        viewHolder.proficiencyView.setText(
                application.database.proficiencyDao().getProficiencyById(
                        studentLanguageProficiencies.get(position).getProficiencyId()
                ).getProficiency()
        );
    }

    @Override
    public int getItemCount(){
        return studentLanguageProficiencies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView languageView;
        public TextView proficiencyView;

        public ViewHolder(View view){
            super(view);

            languageView = view.findViewById(R.id.languageTextView);
            proficiencyView = view.findViewById(R.id.proficiencyTextView);

            view.setTag(this);
            view.setOnClickListener(onClickListener);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
}
