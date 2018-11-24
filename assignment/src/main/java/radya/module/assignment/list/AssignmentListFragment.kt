package radya.module.assignment.list


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import com.google.gson.JsonObject
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.assignment_list_fragment.*
import radya.app.core.util.*
import radya.module.assignment.AssignmentViewModel
import radya.module.assignment.R


/**
 * Created by aderifaldi on 2018-02-06.
 */

class AssignmentListFragment : Fragment() {

    companion object {
        fun newInstance(): AssignmentListFragment {
            return AssignmentListFragment()
        }
    }

    private var viewModel: AssignmentViewModel? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var adapter: AssignmentListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.assignment_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(AssignmentViewModel::class.java)
    }

    private fun initView() {

        initLoading(swipeRefreshLayout)

        linearLayoutManager = activity?.let { LinearLayoutManager(it) }
        adapter = AssignmentListAdapter()

        listAssignment.adapter = adapter
        listAssignment.layoutManager = linearLayoutManager

        adapter?.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val item = adapter?.data?.get(i)
            activity?.let { showToast(it, item!!.assignmentName) }
        })

        txtEmptyInfo.setOnClickListener { activity?.let { showDatePicker(it, object : dateSetted {
            override fun onDateSetted(date: String) {
                loadContactList(date, swipeRefreshLayout, txtEmptyInfo)
            }

        }, false) } }

        loadContactList(currentTime, swipeRefreshLayout, txtEmptyInfo)
    }

    private fun initLoading(swipeRefreshLayout: SwipeRefreshLayout) {
        activity?.let { setupLoading(it, swipeRefreshLayout) }
    }

    private fun loadContactList(currentTime: String, swipeRefreshLayout: SwipeRefreshLayout, txtEmptyInfo: TextView) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("keyword", "")

        showLoading(swipeRefreshLayout)

        activity?.let { viewModel?.getListAssignment(this, it, currentTime, currentTime, 0) }
        viewModel?.getResponse()?.observe(this, Observer { apiResponse ->
            dismissLoading(swipeRefreshLayout)

            if (apiResponse != null){

                if (apiResponse.data.isNotEmpty()){
                    txtEmptyInfo.visibility = View.GONE

                    for (i in 0 until apiResponse.data.size) {
                        val item = apiResponse.data[i]
                        adapter?.data?.add(item)
                        adapter?.notifyItemInserted(adapter?.data?.size!! - 1)
                    }
                    adapter?.notifyDataSetChanged()
                }else{
                    txtEmptyInfo.visibility = View.VISIBLE
                }

            }

        })

    }

}
