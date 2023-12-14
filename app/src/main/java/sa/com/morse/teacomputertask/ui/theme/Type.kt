package sa.com.morse.teacomputertask.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import sa.com.morse.teacomputertask.R

// Set of Material typography styles to start with
val axiformaFontFamily = FontFamily(
    Font(R.font.kastelov_axiforma_bold, weight = FontWeight.Bold),
    Font(R.font.kastelov_axiforma_regular, weight = FontWeight.Normal),
    Font(R.font.kastelov_axiforma_medium, weight = FontWeight.Medium),
    Font(R.font.kastelov_axiforma_semibold, weight = FontWeight.SemiBold),
  )

object AppTextStyle {
    val headingsHeading32px = TextStyle(
        fontSize = FontSize._32SP,
        fontWeight = FontWeight.Bold,
        lineHeight = 44.sp,
        fontFamily = axiformaFontFamily
    )
    val headingsHeading24px = TextStyle(
        fontSize = FontSize._24SP,
        fontWeight = FontWeight.Bold,
        lineHeight = 33.sp,
        fontFamily = axiformaFontFamily
    )
    val headingsHeading20px = TextStyle(
        fontSize = FontSize._20SP,
        fontWeight = FontWeight.Bold,
        fontFamily = axiformaFontFamily
    )
    val headingsHeading18px = TextStyle(
        fontSize = FontSize._12SP,
        fontWeight = FontWeight.Bold,
        lineHeight = 25.sp,
        fontFamily = axiformaFontFamily
    )
    val bodyBodyText16pxW400 = TextStyle(
        fontSize = FontSize._16SP,
        fontWeight = FontWeight.W400,
        lineHeight = 22.sp,
        letterSpacing = FontSize._0_15SP,
        fontFamily = axiformaFontFamily
    )
    val bodyBodyText16pxBold = TextStyle(
        fontSize = FontSize._16SP,
        fontWeight = FontWeight.Bold,
        lineHeight = 22.sp,
        letterSpacing = FontSize._0_15SP,
        fontFamily = axiformaFontFamily
    )
    val bodyBodyText14pxW400 = TextStyle(
        fontSize = FontSize._14SP,
        fontWeight = FontWeight.W400,
        lineHeight = 19.sp,
        letterSpacing = FontSize._0_1SP,
        fontFamily = axiformaFontFamily
    )
    val bodyBodyText14pxBold = TextStyle(
        fontSize =FontSize._14SP,
        fontWeight = FontWeight.Bold,
        lineHeight = 19.sp,
        letterSpacing = FontSize._0_1SP,
        fontFamily = axiformaFontFamily
    )
    val bodyBodyText12pxW400 = TextStyle(
        fontSize = FontSize._12SP,
        fontWeight = FontWeight.W400,
        lineHeight = 16.sp,
        letterSpacing = FontSize._0_5SP,
        fontFamily = axiformaFontFamily
    )
    val bodyBodyText12pxBold = TextStyle(
        fontSize =FontSize._12SP,
        fontWeight = FontWeight.Bold,
        lineHeight = 16.sp,
        letterSpacing = FontSize._0_5SP,
        fontFamily = axiformaFontFamily
    )
}


val Typography = Typography(

    /*Headlines are best-suited for short, high-emphasis text on smaller screens.
     These styles can be good for marking primary passages of text or important regions of content.
    Headlines can also make use of expressive typefaces,
    provided that appropriate line height and letter spacing is also integrated to maintain readability.

    ---------------------> Headline style used for short text on a small screen , Dialog using a headline style
    */

    headlineSmall = AppTextStyle.headingsHeading20px,
    headlineMedium = AppTextStyle.headingsHeading24px,
    headlineLarge =  AppTextStyle.headingsHeading32px,

    /*Titles are smaller than headline styles, and should be used for medium-emphasis text that remains relatively short.
    For example, consider using title styles to divide secondary passages of text or secondary regions of content.
    For titles, use caution when using expressive fonts, including display, handwritten, and script styles.

    ------------------> Top app bar using title style ,
    */

    titleSmall = AppTextStyle.bodyBodyText12pxW400,
    titleMedium = AppTextStyle.bodyBodyText14pxW400,
    titleLarge = AppTextStyle.bodyBodyText16pxW400,

    /*
    Body styles are used for longer passages of text in your app.
    Use typefaces intended for body styles, which are readable at smaller sizes and can be comfortably read in longer passages.
    Avoid expressive or decorative fonts for body text because these can be harder to read at small sizes.

    ------------------> Body style used throughout an article about pesto , Example of body style used throughout a setup flow ( Button Layout )
    */

    bodySmall = AppTextStyle.bodyBodyText12pxBold,
    bodyMedium = AppTextStyle.bodyBodyText14pxBold,
    bodyLarge = AppTextStyle.bodyBodyText16pxBold,

    /*Label styles are smaller, utilitarian styles, used for things like the text inside components or
     for very small text in the content body, such as captions. Buttons, for example, use the label large style.

     ----------------------> A navigation bar using label style for the destination text
     */

    labelSmall = AppTextStyle.bodyBodyText12pxW400,
    labelMedium = AppTextStyle.bodyBodyText14pxW400,
    labelLarge = AppTextStyle.bodyBodyText16pxW400,

    /*There are three display styles in the default type scale: Large, medium, and small.
     As the largest text on the screen, display styles are reserved for short, important text or numerals.
     They work best on large screens. For display type, consider choosing a more expressive font,
     such as a handwritten or script style.
     If available, set the appropriate optical size to your usage.

     ------------> A card using a display size , An expressive typeface can entice readers to engage with an eye-catching design
     */
    displaySmall = AppTextStyle.bodyBodyText12pxBold,
    displayMedium = AppTextStyle.bodyBodyText14pxBold,
    displayLarge = AppTextStyle.bodyBodyText16pxBold
)