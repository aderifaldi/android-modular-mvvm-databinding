package radya.app.core.helper

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView

/**
 * Created by aderifaldi on 2018-01-04.
 */

class GenericViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    private var onItemClickListener: AdapterView.OnItemClickListener? = null
    private var pos: Int = 0

    init {
        val itemView = binding.root
        itemView.setOnClickListener(this)
    }

    fun bindModel(modelType: Int, obj: Any, i: Int, onItemClick: AdapterView.OnItemClickListener) {
        onItemClickListener = onItemClick
        pos = i

        binding.setVariable(modelType, obj)
        binding.executePendingBindings()
    }

    override fun onClick(view: View) {
        onItemClickListener!!.onItemClick(null, view, pos, 0)
    }
}