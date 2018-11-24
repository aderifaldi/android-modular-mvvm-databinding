package radya.module.assignment.list

import com.google.gson.annotations.SerializedName
import radya.app.core.data.Alert
import radya.module.assignment.Assignment
import java.io.Serializable

data class AssignmentList (
    @SerializedName("error")
    val isError: Boolean,
    @SerializedName("alert")
    val alert: Alert,
    @SerializedName("data")
    val data: List<Assignment>
) : Serializable