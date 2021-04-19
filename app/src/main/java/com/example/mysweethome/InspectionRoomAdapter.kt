package com.example.mysweethome

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class InspectionRoomAdapter (val mCtx: Context, val layoutResId: Int, val irList: List<InspectionRoom>)
    : ArrayAdapter<InspectionRoom>(mCtx, layoutResId, irList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //return super.getView(position, convertView, parent)

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx);
        val view: View = layoutInflater.inflate(layoutResId, null)

        val textViewRoomNo = view.findViewById<TextView>(R.id.etRoom)
        val textViewStatus = view.findViewById<TextView>(R.id.etStatus)
        val textViewRemarks = view.findViewById<TextView>(R.id.etRemarks)

        val ir = irList[position]

        textViewRoomNo.text = ir.roomNo
        textViewStatus.text = ir.status
        textViewRemarks.text = ir.remarks

        return view;
    }
}