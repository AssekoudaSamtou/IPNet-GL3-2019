package com.samtou.ipnet_gl3_2019.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife

import com.samtou.ipnet_gl3_2019.R

/**
 * A simple [Fragment] subclass.
 */
class ThirdFragment : Fragment() {
    @BindView(R.id.txtTname) lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_third, container, false)
        textView = view.findViewById(R.id.txtTname)

        ButterKnife.bind(this, view)
        textView.text = getString(R.string.hello_blank_fragment, "Third")
        return view
    }


}
