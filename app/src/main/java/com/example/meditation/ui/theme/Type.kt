package com.example.meditation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.meditation.R // Make sure this import matches your R file's actual package

// --- Define your Custom Font Families ---
// If you have multiple weights, define them here.
// For example, if you have 'font_1_regular.ttf' and 'font_1_bold.ttf'
val customAppFontFamily = FontFamily(
    Font(R.font.font_1, FontWeight.Normal),
    // Assuming you have a bold version
    // Add more Font entries for different weights/styles if available
    // Font(R.font.font_1_italic, FontWeight.Normal, FontStyle.Italic)
)

// --- Set of Material typography styles to start with ---
val Typography = Typography(
    // Example: Applying customAppFontFamily to bodyLarge
    bodyLarge = TextStyle(
        fontFamily = customAppFontFamily, // <--- Use your custom font here
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    // You'll likely want to define more styles
    // For Material 3, common styles include display, headline, title, body, and label variants.

    // Example: Headline Large
    headlineLarge = TextStyle(
        fontFamily = customAppFontFamily,
        fontWeight = FontWeight.Bold, // Use a heavier weight for headlines if available
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    // Example: Title Medium
    titleMedium = TextStyle(
        fontFamily = customAppFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    // Example: Body Small
    bodySmall = TextStyle(
        fontFamily = customAppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    // You can continue to define all the required Typography styles
    // according to your design system and Material 3 guidelines.
    // labelSmall = TextStyle(...)
    // displaySmall = TextStyle(...)
    // etc.
)