package radya.app.core.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Alert (
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String
) : Serializable