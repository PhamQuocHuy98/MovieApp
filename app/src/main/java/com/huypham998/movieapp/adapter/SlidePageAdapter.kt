package com.huypham998.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.huypham998.movieapp.R
import com.huypham998.movieapp.model.Slide
import kotlinx.android.synthetic.main.slide_item.view.*

data class SlidePageAdapter(private val context:Context ,var data: MutableList<Slide>): PagerAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layout =inflater.inflate(R.layout.slide_item,container,false)

        layout.imageSlideItem.setImageResource(data[position].image)
        layout.txtTitleMovie.text=data[position].title
        container.addView(layout,0)


        return layout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view ==`object`
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        return container.removeView(`object` as View)
    }

}

