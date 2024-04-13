package com.gerhard.cs50x.core.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import java.text.SimpleDateFormat
import java.util.*

fun String.toFormattedDate(): String {
    val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH)
    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)
}

fun Fragment.findNavControllerSafely(id: Int): NavController? {
    return if (findNavController().currentDestination?.id == id) {
        findNavController()
    } else {
        null
    }
}
