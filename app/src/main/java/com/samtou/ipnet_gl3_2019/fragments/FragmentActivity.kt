package com.samtou.ipnet_gl3_2019.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.tabs.TabLayout
import com.samtou.ipnet_gl3_2019.R
import com.samtou.ipnet_gl3_2019.intents.User

class FragmentActivity : AppCompatActivity() {
    @BindView(R.id.tablayout) lateinit var tabLayout: TabLayout
    @BindView(R.id.viewpager) lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var fragments: ArrayList<Fragment>
    lateinit var titles: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        ButterKnife.bind(this);

        tabLayout = findViewById(R.id.tablayout)
        viewPager = findViewById(R.id.viewpager)

        fragments = ArrayList()
        titles = ArrayList()

        fragments.add(FirstFragment())
        fragments.add(SecondFragment())
        fragments.add(ThirdFragment())

        titles.add("Animaux")
        titles.add("Musique")
        titles.add("Third#")

        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments, titles)
        viewPager.adapter = viewPagerAdapter

        tabLayout.setupWithViewPager(viewPager)

        if(intent.extras != null) {
            val usern = intent.getStringExtra("username")
            val pass = intent.getStringExtra("password")
            val bool = intent.getBooleanExtra("boolean", false)
            val decim = intent.getFloatExtra("decimal", 12.3F)
            val user = intent.getSerializableExtra("user") as User

            Toast.makeText(this, usern + " " + pass + " " + bool + " " + decim, Toast.LENGTH_LONG).show()
        }
    }
}
