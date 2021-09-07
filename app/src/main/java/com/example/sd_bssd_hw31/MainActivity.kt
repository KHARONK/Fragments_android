package com.example.sd_bssd_hw31

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.commit





private var fid = 0
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val addButton = Button( this).apply{
            text = "+"
            id = View.generateViewId()
            setOnClickListener{
                supportFragmentManager.commit {
                    //setReorderingAllowed(true)
                    add(fid,NoteFragment.newInstance(), null)
                }
            }
        }
        val fragmentLinearLayout = LinearLayoutCompat(this).apply{
            id = View.generateViewId()
            fid = id
            setBackgroundColor(Color.BLUE)
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT)
            (layoutParams as RelativeLayout.LayoutParams).addRule(
                RelativeLayout.BELOW, addButton.id)
        }
        val relativeLayout = RelativeLayout( this).apply{
            setBackgroundColor(Color.LTGRAY)
            addView(addButton)
            addView(fragmentLinearLayout)
        }
        setContentView(relativeLayout)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(fragmentLinearLayout.id, NoteFragment.newInstance(), null)
        }

    }
}