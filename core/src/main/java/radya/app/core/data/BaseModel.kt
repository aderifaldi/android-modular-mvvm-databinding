package radya.app.core.data

import com.google.gson.annotations.SerializedName

import java.io.Serializable

/**
 * Created by RadyaLabs PC on 28/09/2017.
 */
data class BaseModel (
    @SerializedName("error")
    val isError : Boolean,
    @SerializedName("alert")
    val alert: Alert
) : Serializable
