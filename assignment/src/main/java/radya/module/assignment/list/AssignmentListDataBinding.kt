package radya.module.assignment.list

import adr.core.data.constant.DateFormat
import android.databinding.BaseObservable
import radya.app.core.util.convertStringDate
import radya.module.assignment.Assignment

class AssignmentListDataBinding(private val data: Assignment) : BaseObservable() {

    val taskName: String
        get() = data.assignmentName

    val taskCode: String
        get() = "#" + data.assignmentCode

    val taskDate: String
        get() = convertStringDate(data.assignmentDate, DateFormat.MEDIUM)

    val taskAddress: String
        get() = data.assignmentAddress

}
