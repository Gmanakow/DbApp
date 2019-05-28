package manakov.sample.dbapp.Language;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.DbApplication;
import manakov.sample.dbapp.R;
import manakov.sample.dbapp.Student.Student;
import manakov.sample.dbapp.Student.StudentLanguageProficiency;
import manakov.sample.dbapp.Teacher.Teacher;
import manakov.sample.dbapp.Teacher.TeacherLanguageProficiency;

public class LanguageProficiencyRecyclerViewAdapter extends RecyclerView.Adapter<LanguageProficiencyRecyclerViewAdapter.ViewHolder> {
    private String subject;
    private ArrayList<StudentLanguageProficiency> studentLanguageProficiencies;
    private ArrayList<TeacherLanguageProficiency> teacherLanguageProficiencies;
    private View.OnClickListener onClickListener;
    private DbApplication application;

    public LanguageProficiencyRecyclerViewAdapter(ArrayList<StudentLanguageProficiency> languageProficiencies, DbApplication application, Student student){
        this.subject = "student";
        this.studentLanguageProficiencies = languageProficiencies;
        this.teacherLanguageProficiencies = null;
        this.application = application;
    }

    public LanguageProficiencyRecyclerViewAdapter(ArrayList<TeacherLanguageProficiency> languageProficiencies, DbApplication application, Teacher teacher){
        this.subject = "teacher";
        this.studentLanguageProficiencies = null;
        this.teacherLanguageProficiencies = languageProficiencies;
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
        if (subject.equals("student")) {
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
        if (subject.equals("teacher")) {
            viewHolder.languageView.setText(
                    application.database.languageDao().getLanguageById(
                            teacherLanguageProficiencies.get(position).getLanguageId()
                    ).getLanguage()
            );
            viewHolder.proficiencyView.setText(
                    application.database.proficiencyDao().getProficiencyById(
                            teacherLanguageProficiencies.get(position).getProficiencyId()
                    ).getProficiency()
            );
        }
    }

    @Override
    public int getItemCount(){
        if (subject.equals("student")){
            return studentLanguageProficiencies.size();
        }
        if (subject.equals("teacher")){
            return teacherLanguageProficiencies.size();
        }
        return 0;
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
