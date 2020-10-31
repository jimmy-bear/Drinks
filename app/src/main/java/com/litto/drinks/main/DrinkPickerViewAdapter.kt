package com.litto.drinks.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.litto.drinks.R
import com.litto.drinks.list.DrinkListActivity
import kotlinx.android.synthetic.main.page_item.view.*

class DrinkPickerViewAdapter : RecyclerView.Adapter<DrinkPickerViewAdapter.DesiredViewHolder>() {

    private val viewData = LinkedHashMap<PageType, List<String>>()
    private var selectedCategory = ""
    private var selectedGlass = ""

    fun submitData(key: PageType, list: List<String>) {
        viewData[key] = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesiredViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.page_item, parent, false)
        return DesiredViewHolder(view)
    }

    override fun onBindViewHolder(holder: DesiredViewHolder, position: Int) {
        val key = getIndexKey(position) ?: return
        val pageData = viewData[key] ?: return
        val context = holder.itemView.context
        val group = RadioGroup(context)
        for (data in pageData) {
            RadioButton(context).apply {
                text = data
                setOnClickListener {
                    val radio = it as RadioButton
                    when(key) {
                        PageType.CATEGORIES -> {
                            selectedCategory = radio.text.toString()
                            selectedGlass = ""
                        }
                        PageType.GLASSES -> {
                            selectedGlass = radio.text.toString()
                            selectedCategory = ""
                        }
                    }
                }
            }.also {
                group.addView(it)
            }
        }
        holder.itemView.container.addView(group)
        holder.results.setOnClickListener {
            Intent(context, DrinkListActivity::class.java).apply {
                putExtra("selectedCategory", selectedCategory)
                putExtra("selectedGlass", selectedGlass)
                context.startActivity(this)
            }
        }
    }

    override fun getItemCount(): Int {
        return viewData.size
    }

    private fun getIndexKey(position: Int): PageType? {
        return viewData.keys.toTypedArray().getOrNull(position)
    }

    enum class PageType {
        CATEGORIES, GLASSES
    }

    inner class DesiredViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val container = itemView.container
        val results = itemView.btnResults
    }
}
