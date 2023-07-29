package com.akshay.composecatchflicks.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.akshay.composecatchflicks.R

private val defaultTypography = Typography()
val customTypography = Typography(
    headlineLarge = defaultTypography.headlineLarge.copy(
        fontFamily = FontFamily(Font(R.font.itc_avant_grade_std_bold)),
        fontWeight = FontWeight.Bold
    ),
    headlineMedium = defaultTypography.headlineMedium.copy(
        fontFamily = FontFamily(Font(R.font.itc_avant_grade_std_bold)),
        fontWeight = FontWeight.Bold
    ),
    headlineSmall = defaultTypography.headlineSmall.copy(
        fontFamily = FontFamily(Font(R.font.itc_avant_grade_std_bold)),
        fontWeight = FontWeight.Bold
    ),
    bodyLarge = defaultTypography.bodyLarge.copy(
        fontFamily = FontFamily(Font(R.font.itc_avant_grade_std_bk)),
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = defaultTypography.bodyMedium.copy(
        fontFamily = FontFamily(Font(R.font.itc_avant_grade_std_bk)),
        fontWeight = FontWeight.Normal,
    ),
    bodySmall = defaultTypography.bodySmall.copy(
        fontFamily = FontFamily(Font(R.font.itc_avant_grade_std_bk)),
        fontWeight = FontWeight.Normal
    ),
)
