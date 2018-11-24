package radya.module.auth.login

import com.google.gson.annotations.SerializedName
import radya.app.core.data.Alert
import radya.app.core.data.BaseModel
import java.io.Serializable

data class Login(
    @SerializedName("error")
    val isError: Boolean,
    @SerializedName("alert")
    val alert: Alert,
    @SerializedName("data")
    val data: Data
) : Serializable {
    data class Data(
        @SerializedName("token")
        val login_token: String,
        @SerializedName("profile")
        val profile: Profile
    ) : Serializable {
        data class Profile(
            @SerializedName("user_name")
            val user_name: String,
            @SerializedName("user_code")
            val user_code: String,
            @SerializedName("role_code")
            val role_code: String,
            @SerializedName("role_name")
            val role_name: String
        ) : Serializable
    }
}