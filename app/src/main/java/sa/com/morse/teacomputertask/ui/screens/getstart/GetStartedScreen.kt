package sa.com.morse.teacomputertask.ui.screens.getstart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import sa.com.morse.teacomputertask.R
import sa.com.morse.teacomputertask.ui.theme.AppColors
import sa.com.morse.teacomputertask.ui.theme.AppSizes
import sa.com.morse.teacomputertask.utils.ShadowButton

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GetStartedScreen(modifier: Modifier = Modifier , navigateToHome : () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Black18181B)
            .then(modifier)
    ) {

        val (button, body, title, image) = createRefs()

        ShadowButton(name =  stringResource(id = R.string.get_start_now) , modifier = Modifier
            .clickable { navigateToHome.invoke() }
            .padding(70.dp)
            .padding(horizontal = 30.dp )
            .constrainAs(button) {
                bottom.linkTo(parent.bottom)
                linkTo(parent.start, parent.end)
            })

        Text(
            text = stringResource(id = R.string.on_boarding_body),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Normal,
            color = AppColors.WhiteFFFFFF,
            modifier = Modifier.constrainAs(body) {
                bottom.linkTo(button.top, AppSizes._10_DP)
                linkTo(parent.start, parent.end, AppSizes._20_DP, AppSizes._20_DP)
            }
        )

        Text(
            text = stringResource(id = R.string.on_boarding_title),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = AppColors.WhiteFFFFFF,
            modifier = Modifier.constrainAs(title) {
                bottom.linkTo(body.top, AppSizes._30_DP)
                linkTo(parent.start, parent.end, AppSizes._20_DP, AppSizes._20_DP)
            }
        )

        Image(
            painter = painterResource(id = R.drawable.get_started_bg),
            contentDescription = "Get Start Background",
            modifier = Modifier.constrainAs(image) {
                linkTo(parent.top, title.top)
                linkTo(parent.start, parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.preferredValue(350.dp)
            }, contentScale = ContentScale.FillBounds
        )
    }
}