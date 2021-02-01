package se.magictechnology.pia9v5firebase

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter() : RecyclerView.Adapter<TodoViewHolder>() {

    lateinit var mainact : MainActivity

    private var todostuff : List<Todo>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val vh = TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false))
        return vh
    }

    override fun getItemCount(): Int {

        todostuff?.let {
            return it.size
        }

        return 0
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

        holder.todotextview.text = todostuff!![position].todoname


        if(todostuff!![position].tododone == true)
        {
            holder.itemView.setBackgroundColor(Color.GREEN)
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }


        holder.itemView.setOnClickListener {
            mainact.clickRow(todostuff!![position])
        }


    }

    fun todoListUpdate(tododata : List<Todo>)
    {
        todostuff = tododata
        notifyDataSetChanged()
    }

}

class TodoViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    var todotextview = view.findViewById<TextView>(R.id.todoTextView)

}