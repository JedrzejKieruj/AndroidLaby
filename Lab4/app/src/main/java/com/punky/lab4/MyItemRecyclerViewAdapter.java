package com.punky.lab4;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.punky.lab4.TaskFragment.OnListFragmentInteractionListener;
import com.punky.lab4.tasks.TaskListContent;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link TaskListContent.Task} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<TaskListContent.Task> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<TaskListContent.Task> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        TaskListContent.Task task = mValues.get(position);
        holder.mItem = task;
        //holder.mItem = mValues.get(position);

        holder.mContentView.setText(task.contactName);
        final String picPath = task.picPath;
        Context context = holder.mView.getContext();
        if(picPath != null && !picPath.isEmpty())  {
            if(picPath.contains("drawable")){
                Drawable taskDrawable;
                switch (picPath)  {
                    case "drawable1":
                        taskDrawable = context.getResources().getDrawable(R.drawable.drawable1);
                        break;
                    case "drawable2":
                        taskDrawable = context.getResources().getDrawable(R.drawable.drawable2);
                        break;
                    case "drawable3":
                        taskDrawable = context.getResources().getDrawable(R.drawable.drawable3);
                        break;
                    case "drawable4":
                        taskDrawable = context.getResources().getDrawable(R.drawable.drawable4);
                        break;
                    case "drawable5":
                        taskDrawable = context.getResources().getDrawable(R.drawable.drawable5);
                        break;
                    default:
                        taskDrawable = context.getResources().getDrawable(R.drawable.drawable1);
                }
                holder.mItemImageView.setImageDrawable(taskDrawable);
            }
        }else{
            holder.mItemImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.drawable1));
        }


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentClickInteraction(holder.mItem, position);
                }
            }
        });
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mListener.onListFragmentLongClickInteraction(holder.mItem,position);
                return false;
            }
        });

        holder.delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListDeleteButton(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final ImageView mItemImageView;
        public TaskListContent.Task mItem;
        public final Button delButton;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mItemImageView = view.findViewById(R.id.item_image);
            mContentView = view.findViewById(R.id.content);
            delButton = view.findViewById(R.id.deleteContact);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
