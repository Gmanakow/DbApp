package manakov.sample.dbapp.Course;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.R;

class CoursesRecyclerViewAdapter extends RecyclerView.Adapter<CoursesRecyclerViewAdapter.ViewHolder>{
    private ArrayList<Course> list;
    private View.OnClickListener onClickListener;
    private Context context;

    public CoursesRecyclerViewAdapter(ArrayList<Course> list) {
        this.list = list;
    }

    @Override
    public CoursesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_view_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CoursesRecyclerViewAdapter.ViewHolder viewHolder,final int position){
        viewHolder.courseNameTextView.setText(list.get(position).getCourseName());

    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView courseNameTextView;

        public ViewHolder(View view){
            super(view);

            courseNameTextView = view.findViewById(R.id.courseNameTextView);

            view.setTag(this);
            view.setOnClickListener(onClickListener);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
