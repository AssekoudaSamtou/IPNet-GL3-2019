package com.samtou.ipnet_gl3_2019.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.samtou.ipnet_gl3_2019.R
import com.samtou.ipnet_gl3_2019.intents.User
import java.util.ArrayList

class UserAdapter(var users : ArrayList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val context : Context = parent.context
        val inflater : LayoutInflater = LayoutInflater.from(context)
        var view : View = inflater.inflate(R.layout.recycler_item, parent, false)

        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.updateWithUser(this.users.get(position))
    }


    class UserViewHolder(var userItemView: View) : RecyclerView.ViewHolder(userItemView) {

        @BindView(R.id.user_name) lateinit var textView: TextView

        fun updateWithUser(user : User) {
            ButterKnife.bind(this, userItemView)
            textView = userItemView.findViewById(R.id.user_description)

            this.textView.text = user.username
        }
    }

}