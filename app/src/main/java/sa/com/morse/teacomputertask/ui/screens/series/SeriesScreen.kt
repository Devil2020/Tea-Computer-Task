package sa.com.morse.teacomputertask.ui.screens.series

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import sa.com.morse.teacomputertask.R
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.ui.screens.home.MovieOrSeriesItem
import sa.com.morse.teacomputertask.ui.screens.movies.MoviesViewModel
import sa.com.morse.teacomputertask.ui.theme.AppColors
import sa.com.morse.teacomputertask.ui.theme.FontSize
import sa.com.morse.teacomputertask.utils.ErrorView
import sa.com.morse.teacomputertask.utils.ExceptionType
import sa.com.morse.teacomputertask.utils.LoadingView
import sa.com.morse.teacomputertask.utils.MediaImage
import sa.com.morse.teacomputertask.utils.getRandomColor
import sa.com.morse.teacomputertask.utils.onFail
import sa.com.morse.teacomputertask.utils.onLoading
import sa.com.morse.teacomputertask.utils.onSuccess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeriesScreen(
    modifier: Modifier = Modifier,
    vm: SeriesViewModel ?,
    openDetails: (Int, Boolean) -> Unit,
    finish: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Black1A1A1D)
            .then(modifier)
    ) {

        val state = vm?.series?.observeAsState()
        val scroll = rememberLazyGridState()
        val (actionbar, list) = createRefs()
        val startGuideline = createGuidelineFromStart(0.05F)
        val endGuideline = createGuidelineFromStart(0.95F)
        val topGuideline = createGuidelineFromTop(0.01F)
        val contentModifier = Modifier.constrainAs(list) {
            linkTo(startGuideline, endGuideline)
            linkTo(actionbar.bottom, parent.bottom, topMargin = 10.dp)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }
        TopAppBar(modifier = Modifier
            .constrainAs(actionbar) {
                top.linkTo(topGuideline)
                linkTo(parent.start, parent.end)
                width = Dimension.fillToConstraints
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = AppColors.Black1A1A1D),
            title = {
                Text(
                    text = stringResource(id = R.string.series),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = FontSize._14SP,
                    color = AppColors.WhiteFFFFFF,
                    modifier = Modifier.padding(start = 10.dp)
                )
            },
            navigationIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back Button",
                    tint = AppColors.WhiteFFFFFF,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clickable { finish.invoke() }
                )
            })

        state?.value
            ?.onLoading { LoadingView(modifier = contentModifier) }
            ?.onFail {
                ErrorView(modifier = contentModifier, it.getErrorMessage()) {
                    vm.loadSeries()
                }
            }
            ?.onSuccess {
                LazyColumn(
                    modifier = contentModifier,
                ) {
                    items(it) {
                        SeriesItem(item = it) { id ->
                            openDetails.invoke(id, false)
                        }
                    }
                }
            }
    }

}

@Composable
fun SeriesItem(modifier: Modifier = Modifier, item: MovieOrSeriesItem, onClick: (Int) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(top = 20.dp)
            .clickable { onClick.invoke(item.id) }
            .then(modifier)
    ) {
        val (background, image, name, extraInfo) = createRefs()
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .height(120.dp)
                .constrainAs(background) {
                    bottom.linkTo(parent.bottom)
                    linkTo(parent.start, parent.end, 10.dp, 10.dp)
                    width = Dimension.fillToConstraints
                },
            colors = CardDefaults.cardColors(containerColor = getRandomColor())
        ) {}

        MediaImage(
            url = item.image,
            modifier = Modifier
                .height(170.dp)
                .width(120.dp)
                .constrainAs(image) {
                    start.linkTo(background.start, 10.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                }
        )

        Text(
            text = item.name, modifier = Modifier.constrainAs(name) {
                linkTo(image.end, background.end, 10.dp, 10.dp)
                width = Dimension.fillToConstraints
                top.linkTo(background.top, 20.dp)
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = AppColors.GrayE8E8E8,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize._18SP
        )

        Text(
            text = item.date, modifier = Modifier.constrainAs(extraInfo) {
                start.linkTo(name.start)
                top.linkTo(name.bottom, 5.dp)
            },
            color = AppColors.GrayE8E8E8,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize._14SP
        )

    }
}