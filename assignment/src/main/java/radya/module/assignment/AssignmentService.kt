package radya.module.assignment

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by aderifaldi on 08/08/2016.
 */
interface AssignmentService {

    //ASSIGNMENT
    @GET("Assignment/all")
    fun GetAssignmentAll(
        @Query("from") form: String,
        @Query("to") to: String,
        @Query("status") status: Int
    ): Call<String>

}
