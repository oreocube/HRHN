package com.hrhn.presentation.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun LocalDateTime.formatDateString(): String {
    return format(DateTimeFormatter.ofPattern("MM/dd"))
}

fun LocalDateTime.formatDateWithYearString(): String {
    return format(
        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.getDefault())
    )
}
