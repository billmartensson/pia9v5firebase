package se.magictechnology.pia9v5firebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter() : RecyclerView.Adapter<TodoViewHolder>() {

    var todostuff : List<Todo>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val vh = TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false))
        return vh
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

    }

}

class TodoViewHolder (view: View) : RecyclerView.ViewHolder(view) {



}