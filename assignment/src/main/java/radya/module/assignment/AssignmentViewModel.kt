package radya.module.assignment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.v4.app.Fragment
import radya.app.core.util.getJson
import radya.module.assignment.list.AssignmentList

class AssignmentViewModel : ViewModel() {

    private var response: MediatorLiveData<AssignmentList>? = null

    fun getResponse(): LiveData<AssignmentList>? {
        return response
    }

    fun getListAssignment(owner: Fragment, context: Context, from: String, to: String, status: Int) {
        response = MediatorLiveData()

        var login: AssignmentList?

        AssignmentReposiitory().listAssignment(context, from, to, status)?.observe(owner, Observer { r ->
            login = getJson(r!!, AssignmentList::class.java) as AssignmentList
            response?.value = login
        })

    }

}