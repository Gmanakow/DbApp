package manakov.sample.dbapp.Student;

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

public class StudentRecyclerViewAdapter extends RecyclerView.Adapter<StudentRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Student> list;
    private View.OnClickListener onClickListener;
    private Context context;

    public StudentRecyclerViewAdapter(ArrayList<Student> list){
        this.list = list;
    }

    @Override
    public StudentRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_view_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final StudentRecyclerViewAdapter.ViewHolder viewHolder,int position){
        viewHolder.studentFirstNameTextView.setText(list.get(position).getFirstName());
        viewHolder.studentLastNameTextView .setText(list.get(position).getLastName() );
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView studentFirstNameTextView;
        public TextView studentLastNameTextView;

        public ViewHolder(View view){
            super(view);

            studentFirstNameTextView = view.findViewById(R.id.studentFirstNameTextView);
            studentLastNameTextView  = view.findViewById(R.id.studentLastNameTextView);

            view.setTag(this);
            view.setOnClickListener(onClickListener);
        }

    }

    public void setOnItemClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

}
