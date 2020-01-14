package com.samtou.ipnet_gl3_2019.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class CircleLayout(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {

    var paint: Paint = Paint()
    var graduationPaint: Paint = Paint()
    var graduationPaint2: Paint = Paint()

    init {
        paint.color = Color.BLACK
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = 2F
        paint.style = Paint.Style.STROKE
        paint.textSize = 7.0F

        graduationPaint.color = Color.BLUE
        graduationPaint.strokeJoin = Paint.Join.ROUND
        graduationPaint.strokeWidth = 10F
        graduationPaint.style = Paint.Style.STROKE
        graduationPaint.textSize = 30.0F

        graduationPaint2.color = Color.BLACK
        graduationPaint2.strokeJoin = Paint.Join.ROUND
        graduationPaint2.strokeWidth = 2F
        graduationPaint2.style = Paint.Style.STROKE
        graduationPaint2.textSize = 15.0F
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val xCentre = width.div(2)
        val yCentre = height.div(2)
        val rayon = xCentre - 10
        val nbInter = 5
        val interval = rayon / nbInter
        canvas?.drawCircle(xCentre.toFloat(), yCentre.toFloat(), rayon.toFloat(), paint)

        val xDebutAbscisse = xCentre.toFloat()
        val yDebutAbscisse = (yCentre-rayon).toFloat()

        val xFinAbscisse = xCentre.toFloat()
        val yFinAbscisse = (yCentre+rayon).toFloat()
        canvas?.drawLine(xDebutAbscisse, yDebutAbscisse, xFinAbscisse, yFinAbscisse, paint)


        val xDebutOrdonnee = 10F
        val yDebutOrdonnee = yCentre.toFloat()

        val xFinOrdonnee = (xCentre+rayon).toFloat()
        val yFinOrdonnee = yCentre.toFloat()
        canvas?.drawLine(xDebutOrdonnee, yDebutOrdonnee, xFinOrdonnee, yFinOrdonnee, paint)

//        canvas?.drawPoint(10+(interval).toFloat(), yCentre.toFloat(), graduationPaint)
        for (i in 0..nbInter-1) {
            canvas?.drawPoint(10+(interval*i).toFloat(), yCentre.toFloat(), graduationPaint)
            canvas?.drawText("${-nbInter+i}", 10+(interval*i).toFloat(), yCentre.toFloat()+15, graduationPaint2)

            canvas?.drawPoint((width-10)-(interval*i).toFloat(), yCentre.toFloat(), graduationPaint)
            canvas?.drawText("${nbInter-i}", (width-10)-(interval*i).toFloat(), yCentre.toFloat()+15, graduationPaint2)
        }

        for (i in 0..nbInter-1) {
            canvas?.drawPoint(xCentre.toFloat(), (yCentre-rayon+(interval*i)).toFloat(), graduationPaint)
            canvas?.drawPoint(xCentre.toFloat(), (yCentre+rayon-(interval*i)).toFloat(), graduationPaint)

            canvas?.drawText("${nbInter-i}", xCentre.toFloat()+15, (yCentre-rayon+(interval*i)).toFloat(), graduationPaint2)
            canvas?.drawText("${-nbInter+i}", xCentre.toFloat()+15, (yCentre+rayon-(interval*i)).toFloat(), graduationPaint2)
        }

        canvas?.drawText("0", xCentre.toFloat(), yCentre.toFloat(), graduationPaint2)
    }
}