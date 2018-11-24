package radya.app.core.util

import adr.core.data.constant.DateFormat
import android.app.Activity
import android.app.DatePickerDialog
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by aderifaldi on 2018-02-10.
 */
fun convertStringDate(date: String, format: String): String {

    val sdf: SimpleDateFormat
    val dateFormat: SimpleDateFormat
    val convertedDate: Date

    sdf = SimpleDateFormat(format)
    dateFormat = SimpleDateFormat(DateFormat.SERVER)

    try {
        convertedDate = dateFormat.parse(date)
        return sdf.format(convertedDate)
    } catch (e: ParseException) {
        // TODO Auto-generated catch block
        e.printStackTrace()
        return ""
    }

}

val currentTime: String
    get() {
        try {
            val calendar = Calendar.getInstance()
            val currentDate = calendar.time
            return convertDateToString(currentDate, DateFormat.SERVER)
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }

    }

fun convertDateToString(date: Date, format: String): String {
    try {
        val sdf: SimpleDateFormat
        sdf = SimpleDateFormat(format)
        return sdf.format(date)
    } catch (e: Exception) {
        return e.message!!
    }

}

interface dateSetted {
    fun onDateSetted(date: String)
}

fun showDatePicker(activity: Activity, dateSetted: dateSetted, isMinToday: Boolean) {
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val c = Calendar.getInstance()
    mYear = c.get(Calendar.YEAR)
    mMonth = c.get(Calendar.MONTH)
    mDay = c.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        activity,
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, monthOfYear, dayOfMonth)

            dateSetted.onDateSetted(convertDateToString(calendar.time, DateFormat.SERVER))
        }, mYear, mMonth, mDay
    )

    if (isMinToday) {
        datePickerDialog.datePicker.minDate = c.timeInMillis
    } else {
        datePickerDialog.datePicker.maxDate = c.timeInMillis
    }

    datePickerDialog.show()
}
