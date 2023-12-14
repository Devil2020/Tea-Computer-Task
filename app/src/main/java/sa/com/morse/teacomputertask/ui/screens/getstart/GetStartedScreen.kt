package sa.com.morse.teacomputertask.ui.screens.getstart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.NonDisposableHandle.parent
import sa.com.morse.teacomputertask.R
import sa.com.morse.teacomputertask.ui.theme.AppColors
import sa.com.morse.teacomputertask.ui.theme.AppSizes
import sa.com.morse.teacomputertask.ui.theme.AppTextStyle

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GetStartedScreen(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Black18181B)
            .then(modifier)
    ) {

        val (button, body, title, image) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.enter_now_btn),
            contentDescription = "Enter Now Button" ,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clickable {  }
                .height(AppSizes._50_DP)
                .constrainAs(button){
                bottom.linkTo(parent.bottom , AppSizes._30_DP)
                linkTo(parent.start , parent.end)
            }
        )

    }
}