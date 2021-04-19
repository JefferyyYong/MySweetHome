package com.example.mysweethome

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class InspectionFloorAdapter (val mCtx: Context, val layoutResId: Int, val ifList: List<InspectionFloor>)
    : ArrayAdapter<InspectionFloor>(mCtx, layoutResId, ifList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //return super.getView(position, convertView, parent)

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx);
        val view: View = layoutInflater.inflate(layoutResId, null)

        val textViewFloor = view.findViewById<TextView>(R.id.etFloor)
        val textViewItem = view.findViewById<TextView>(R.id.etItem)
        val textViewStatus = view.findViewById<TextView>(R.id.etStatus)
        val textViewRemarks = view.findViewById<TextView>(R.id.etRemarks)

        val ifloor = ifList[position]

        textViewFloor.text = ifloor.floor
        textViewItem.text = ifloor.item
        textViewStatus.text = ifloor.status
        textViewRemarks.text = ifloor.remarks

        return view;
    }
}