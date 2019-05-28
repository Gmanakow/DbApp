package manakov.sample.dbapp.Teacher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.R;

class TeacherRecyclerViewAdapter extends RecyclerView.Adapter<TeacherRecyclerViewAdapter.ViewHolder>{
    private ArrayList<Teacher> list;
    private View.OnClickListener onClickListener;
    private Context context;

    public TeacherRecyclerViewAdapter(ArrayList<Teacher> list) {
        this.list = list;
    }

    @Override
    public TeacherRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_view_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeacherRecyclerViewAdapter.ViewHolder viewHolder, final int position){
        viewHolder.teacherFirstNameTextView.setText(list.get(position).getFirstName());
        viewHolder.teacherLastNameTextView .setText(list.get(position).getLastName() );
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView teacherFirstNameTextView;
        public TextView teacherLastNameTextView;

        public ViewHolder(View view){
            super(view);

            teacherFirstNameTextView = view.findViewById(R.id.teacherFirstNameTextView);
            teacherLastNameTextView = view.findViewById(R.id.teacherLastNameTextView);

            view.setTag(this);
            view.setOnClickListener(onClickListener);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
