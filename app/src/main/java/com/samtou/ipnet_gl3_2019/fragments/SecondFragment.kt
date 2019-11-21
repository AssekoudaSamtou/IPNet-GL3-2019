package com.samtou.ipnet_gl3_2019.fragments


import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife

import com.samtou.ipnet_gl3_2019.R
import kotlinx.android.synthetic.main.fragment_second.view.*
import java.text.FieldPosition

/**
 * A simple [Fragment] subclass.
 */
class SecondFragment : Fragment(), View.OnClickListener{

    //    @BindView(R.id.txtSname) lateinit var textView: TextView

    lateinit var play : Button
    lateinit var pause : Button
    lateinit var stop : Button

    companion object{
        var mediaPlayer: MediaPlayer? = null
        var pauseCurrentPosition: Int = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        view.btn_pause.setOnClickListener(this)
        view.btn_play.setOnClickListener(this)
        view.btn_stop.setOnClickListener(this)

        play = view.findViewById(R.id.btn_play)
        pause = view.findViewById(R.id.btn_pause)
        stop = view.findViewById(R.id.btn_stop)

        return view
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_play -> {
                if (mediaPlayer == null){
                    mediaPlayer = MediaPlayer.create(context, R.raw.ring)
                    mediaPlayer!!.start()
                }else if (mediaPlayer!!.isPlaying) {
                    mediaPlayer!!.seekTo(pauseCurrentPosition)
                    mediaPlayer!!.start()
                }
            }
            R.id.btn_pause -> {
                mediaPlayer!!.pause()
                pauseCurrentPosition = mediaPlayer!!.currentPosition
            }
            R.id.btn_stop -> {
                mediaPlayer!!.stop()
                mediaPlayer = null
            }
            else -> {

            }
        }
    }
}
