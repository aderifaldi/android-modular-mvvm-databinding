package radya.module.assignment

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Assignment (
    @SerializedName("assignment_id")
    val assignmentId: String,
    @SerializedName("assignment_code")
    val assignmentCode: String,
    @SerializedName("assignment_name")
    val assignmentName: String,
    @SerializedName("assignment_date")
    val assignmentDate: String,
    @SerializedName("assignment_address")
    val assignmentAddress: String
): Serializable