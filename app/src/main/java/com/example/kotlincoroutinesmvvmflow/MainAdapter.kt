package com.example.kotlincoroutinesmvvmflow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincoroutinesmvvmflow.model.User
import kotlinx.android.synthetic.main.recyclerview.view.*

class MainAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

        class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(user: User) {
                itemView.apply {
                    textViewUserName.text = user.userName
                    textViewUserEmail.text = user.userEmail
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
            DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview, parent, false))

        override fun getItemCount(): Int = users.size

        override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
            holder.bind(users[position])
        }

        fun addUsers(users: List<User>) {
            this.users.apply {
                clear()
                addAll(users)
            }

        }
    }
