package sa.com.morse.teacomputertask.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import sa.com.morse.teacomputertask.R
import sa.com.morse.teacomputertask.ui.theme.AppColors
import sa.com.morse.teacomputertask.ui.theme.FontSize
import sa.com.morse.teacomputertask.utils.base.ShadowButton

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DetailScreen(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Black1A1A1D)
            .then(modifier)
    ) {
        val scroll = rememberLazyGridState()
        val startGuideline = createGuidelineFromStart(0.05F)
        val endGuideline = createGuidelineFromStart(0.95F)
        val posterGuideline = createGuidelineFromTop(0.35F)
        val (poster, name, year, studio, rateStars, rateText, description, actors, watch) = createRefs()

        Text(
            text = "2022",
            modifier = Modifier.constrainAs(year) {
                linkTo(name.top, name.bottom)
                start.linkTo(name.end, 10.dp)
                width = Dimension.fillToConstraints
            },
            maxLines = 1,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            fontSize = FontSize._14SP,
            color = AppColors.Gray8F8F8F,
        )

        Image(
            painter = painterResource(id = R.drawable.movie_poster_test),
            contentDescription = "Poster",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.constrainAs(poster){
                linkTo(parent.start , parent.end)
                width = Dimension.fillToConstraints
                linkTo(parent.top , name.top)
                height = Dimension.fillToConstraints
            }
        )

        Text(
            text = "Morbius",
            modifier = Modifier.constrainAs(name) {
                bottom.linkTo(studio.top, 10.dp)
                start.linkTo(startGuideline)
                width = Dimension.wrapContent
            },
            maxLines = 1,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            fontSize = FontSize._24SP,
            color = AppColors.WhiteFFFFFF,
        )

        Text(
            text = "Marvel Studios",
            modifier = Modifier.constrainAs(studio) {
                bottom.linkTo(description.top, 20.dp)
                linkTo(startGuideline, endGuideline)
                width = Dimension.fillToConstraints
            },
            maxLines = 1,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            fontSize = FontSize._14SP,
            color = AppColors.Gray8F8F8F,
        )

        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit,  sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex......",
            modifier = Modifier.constrainAs(description) {
                bottom.linkTo(actors.top, 10.dp)
                linkTo(startGuideline, endGuideline)
                width = Dimension.fillToConstraints
            },
            maxLines = 5,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            fontSize = FontSize._14SP,
            color = AppColors.Gray8F8F8F,
        )

        LazyRow(modifier = Modifier.constrainAs(actors) {
            bottom.linkTo(watch.top)
            linkTo(startGuideline, endGuideline)
            width = Dimension.fillToConstraints
        }) {
            items(100) {
                ActorItem()
            }
        }

        ShadowButton(name = stringResource(id = R.string.watch_now), modifier = Modifier
            .clickable { }
            .padding(50.dp)
            .padding(horizontal = 30.dp)
            .constrainAs(watch) {
                bottom.linkTo(parent.bottom)
                linkTo(parent.start, parent.end)
            })
    }
}

@Composable
fun ActorItem() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.actor_test_image),
            contentDescription = "movie poster image",
            modifier = Modifier.size(60.dp),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = "Secret Wars", modifier = Modifier
                .padding(top = 6.dp),
            color = AppColors.GrayE8E8E8,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize._14SP,
            textAlign = TextAlign.Center
        )

        Text(
            text = "2022", modifier = Modifier
                .padding(top = 6.dp),
            color = AppColors.Gray828282,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize._14SP
        )
    }
}