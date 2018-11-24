/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package radya.app.core.util

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.view.inputmethod.InputMethodManager

fun replaceFragment(
    fragmentManager: FragmentManager,
    fragment: Fragment, frameId: Int,
    addToBackStack: Boolean
) {
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(frameId, fragment)
    if (addToBackStack) {
        transaction.addToBackStack(null)
    }
    transaction.commit()
}

fun replaceFragment(
    fragmentManager: FragmentManager,
    fragment: Fragment, frameId: Int,
    data: Bundle,
    addToBackStack: Boolean
) {
    fragment.arguments = data

    val transaction = fragmentManager.beginTransaction()
    transaction.replace(frameId, fragment)
    if (addToBackStack) {
        transaction.addToBackStack(null)
    }
    transaction.commit()
}

fun hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = activity.currentFocus
    //If negativeButton view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }

    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
