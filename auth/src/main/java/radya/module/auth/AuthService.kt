package radya.module.auth

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by aderifaldi on 08/08/2016.
 */
interface AuthService {

    //AUTH
    @POST("auth")
    fun Login(
        @Body jsonPost: JsonObject
    ): Call<String>

}
