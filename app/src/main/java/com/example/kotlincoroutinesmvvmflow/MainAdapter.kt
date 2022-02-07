package com.example.kotlincoroutinesmvvmflow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincoroutinesmvvmflow.model.FakeResponse
import kotlinx.android.synthetic.main.recyclerview.view.downloadImage
import kotlinx.android.synthetic.main.recyclerview.view.textViewUserName
import kotlinx.android.synthetic.main.recyclerview.view.textViewUserEmail

class MainAdapter(
    private val users: ArrayList<FakeResponse>,
    private val listener: CustomViewHolderListener
) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(fakeResponse: FakeResponse) {
            itemView.apply {
                textViewUserName.text = fakeResponse.name
                textViewUserEmail.text = fakeResponse.type
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview, parent, false)
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
        holder.itemView.downloadImage.setOnClickListener{
            listener.onCustomItemClicked(users[position].url)
        }
    }

    fun addUsers(users: List<FakeResponse>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }

    interface CustomViewHolderListener {
        fun onCustomItemClicked(pathString: String)
    }
}
