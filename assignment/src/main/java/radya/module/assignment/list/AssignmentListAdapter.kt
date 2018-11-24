package radya.module.assignment.list

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import radya.app.core.helper.GenericViewHolder
import radya.module.assignment.Assignment
import radya.module.assignment.BR
import radya.module.assignment.R
import java.util.ArrayList

class AssignmentListAdapter : RecyclerView.Adapter<GenericViewHolder>() {
    val data: MutableList<Assignment>
    private var onItemClickListener: AdapterView.OnItemClickListener? = null

    init {
        data = ArrayList()
    }

    fun setOnItemClickListener(onItemClickListener: AdapterView.OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.assignment_list_item, parent, false)
        return GenericViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val itemData = data.get(position)
        val dataBinding = AssignmentListDataBinding(itemData)
        holder.bindModel(BR.workOrderItem, dataBinding, position, onItemClickListener!!)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}