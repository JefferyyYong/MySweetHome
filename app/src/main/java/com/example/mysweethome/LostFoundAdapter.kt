package com.example.mysweethome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import org.w3c.dom.Text

class LostFoundAdapter (val mCtx: Context, val layoutResId: Int, val lostFoundList: List<LostFound>)
    :ArrayAdapter<LostFound>(mCtx,layoutResId,lostFoundList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //return super.getView(position, convertView, parent)
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx);
        val view:View = layoutInflater.inflate(layoutResId, null)

        val textViewDate = view.findViewById<TextView>(R.id.tvDate)
        val textViewLocation = view.findViewById<TextView>(R.id.tvLocation)
        val textViewItem = view.findViewById<TextView>(R.id.tvItem)
        val textViewStatus = view.findViewById<TextView>(R.id.tvStatus)

        val lf = lostFoundList[position]

        textViewDate.text = lf.date
        textViewLocation.text = lf.location
        textViewItem.text = lf.item
        textViewStatus.text = lf.status

        return view;
    }
}

