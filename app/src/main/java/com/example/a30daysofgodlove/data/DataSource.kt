package com.example.a30daysofgodlove.data

import com.example.a30daysofgodlove.R
import com.example.a30daysofgodlove.model.Versus

class DataSource() {

    fun loadVersus(): List<Versus>{
        return listOf<Versus>(
            Versus( R.string.title1, R.string.job_6, R.drawable.job_6),
            Versus( R.string.title2, R.string.psalm ,R.drawable.psalm),
            Versus( R.string.title3, R.string.proverbs, R.drawable.proverbs),
            Versus( R.string.title4, R.string.isaiah,R.drawable.isaiah),
            Versus( R.string.title5,R.string.micah ,R.drawable.micah),
            Versus( R.string.title6, R.string.matthew, R.drawable.matthew)
        )
    }

}