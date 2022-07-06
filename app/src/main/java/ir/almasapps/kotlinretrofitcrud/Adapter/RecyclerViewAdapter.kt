package ir.almasapps.kotlinretrofitcrud.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.almasapps.kotlinretrofitcrud.Model.Note
import ir.almasapps.kotlinretrofitcrud.R
import ir.almasapps.kotlinretrofitcrud.UpdateActivity

class RecyclerViewAdapter (var context: Context,var list :List<Note>): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView = itemView.findViewById(R.id.list_item_txtTitle)
        private val txtNote: TextView = itemView.findViewById(R.id.list_item_txtNote)
        private val txtDate: TextView = itemView.findViewById(R.id.list_item_txtDate)
        private val layout: RelativeLayout = itemView.findViewById(R.id.list_item_layout);

        fun bindData(data: Note) {
            txtTitle.text = data.getTitle()
            txtNote.text = data.getNote()
            txtDate.text = data.getDate()
            layout.setBackgroundColor(Color.parseColor(data.getColor()))
            layout.setOnClickListener {
                val intent = Intent(context, UpdateActivity::class.java)
                intent.putExtra("itemId", data.getId())
                intent.putExtra("itemTitle", data.getTitle())
                intent.putExtra("itemNote", data.getNote())
                intent.putExtra("itemColor", data.getColor())
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}