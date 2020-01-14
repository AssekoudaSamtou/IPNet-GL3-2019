package com.samtou.ipnet_gl3_2019.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.View

class MyLayout(context: Context): View(context) {

    var paint: Paint
    var path: Path

    init {
        paint = Paint()
        path = Path()
        paint.color = Color.BLUE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = 5F
        paint.style = Paint.Style.STROKE
        paint.textSize = 25.0F
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPoint(200F, 300F, paint)
        canvas?.drawLine(150F, 100F, 500F, 650F, paint)
        canvas?.drawPath(path, paint)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val xPos: Float? = event?.x
        val yPos: Float? = event?.y

        if (event?.action == MotionEvent.ACTION_DOWN) {
            path.moveTo(xPos!!, yPos!!)
            return true
        }
        else if (event?.action == MotionEvent.ACTION_MOVE) {
            path.lineTo(xPos!!, yPos!!)
        }
        else if (event?.action == MotionEvent.ACTION_UP) {

        }
        else {
            return false
        }

        invalidate()
        return true
    }

}