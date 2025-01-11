package com.chinky.localandroidapplication.activity.family.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chinky.localandroidapplication.R
import com.chinky.localandroidapplication.activity.family.model.FamilyMember

class FamilyAdapter(private val familyList: List<FamilyMember>, private val context: Context) :
    RecyclerView.Adapter<FamilyAdapter.FamilyViewHolder>() {

    private var isSelected: Boolean = false

    constructor(
        familyList: List<FamilyMember>,
        context: Context,
        isSelected: Boolean
    ) : this(familyList, context) {
        this.isSelected = isSelected;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.family_adapter_row_item, parent, false)
        return FamilyViewHolder(view)
    }

    override fun onBindViewHolder(holder: FamilyViewHolder, position: Int) {
        val familyMember = familyList[position]
        holder.txtName.text = familyMember.name
        holder.txtAddress.text = familyMember.address
        holder.txtAge.setText("${familyMember.age} years old")
    }

    override fun getItemCount(): Int {
        return if (familyList.isEmpty()) 0 else familyList.size
    }

    private var familyMembers: List<FamilyMember> = emptyList()

    class FamilyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtAddress: TextView = itemView.findViewById(R.id.txtAddress)
        val txtAge: TextView = itemView.findViewById(R.id.txtAge)
    }

}