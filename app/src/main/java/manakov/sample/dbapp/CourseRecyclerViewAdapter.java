package manakov.sample.dbapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import manakov.sample.dbapp.Course.Course;

public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<CourseRecyclerViewAdapter.ViewHolder> {
    public ArrayList<Course> courses;
    public View.OnClickListener onClickListener;

    public CourseRecyclerViewAdapter(ArrayList<Course> courses){
        this.courses = courses;
    }

    @Override
    public CourseRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view =
                LayoutInflater.from(
                        parent.getContext()
                ).inflate(
                        R.layout.course_view_item_layout,
                        parent,
                        false
                );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseRecyclerViewAdapter.ViewHolder viewHolder, int position){
        viewHolder.courseNameTextView.setText(
                courses.get(position).getCourseName()
        );
    }

    @Override
    public int getItemCount(){
        return courses.size();
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

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
}
