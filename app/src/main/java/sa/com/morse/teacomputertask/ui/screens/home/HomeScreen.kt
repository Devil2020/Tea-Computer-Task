package sa.com.morse.teacomputertask.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import sa.com.morse.teacomputertask.R
import sa.com.morse.teacomputertask.app.navigation.MoviesDirections
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem
import sa.com.morse.teacomputertask.ui.theme.AppColors
import sa.com.morse.teacomputertask.ui.theme.FontSize
import sa.com.morse.teacomputertask.utils.EmptyView
import sa.com.morse.teacomputertask.utils.ErrorView
import sa.com.morse.teacomputertask.utils.LoadingView
import sa.com.morse.teacomputertask.utils.MediaImage
import sa.com.morse.teacomputertask.utils.onEmpty
import sa.com.morse.teacomputertask.utils.onFail
import sa.com.morse.teacomputertask.utils.onLoading
import sa.com.morse.teacomputertask.utils.onNotEmpty
import sa.com.morse.teacomputertask.utils.onSuccess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    vm: HomeViewModel?,
    openMovies: () -> Unit,
    openSeries: () -> Unit,
    openSearch: () -> Unit,
    openDetails: (Int, Boolean) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Black1A1A1D)
            .then(modifier)
    ) {
        val scroll = rememberLazyGridState()
        val (userImage,
            welcomeMessage,
            userName,
            categoriesTitle,
            moviesView,
            seriesView,
            randomListTitle,
            randomListView,
            search,
            emptyView) = createRefs()

        val startGuideline = createGuidelineFromStart(0.05F)
        val endGuideline = createGuidelineFromStart(0.95F)
        val topGuideline = createGuidelineFromTop(0.01F)
        val centerGuideline = createGuidelineFromStart(0.5f)
        val state = vm?.searchItems?.observeAsState()
        val contentModifier = Modifier.constrainAs(emptyView) {
            linkTo(startGuideline, endGuideline)
            linkTo(randomListTitle.bottom, parent.bottom, topMargin = 10.dp)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }
        Image(
            painter = painterResource(id = R.drawable.me),
            contentDescription = "User Image",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(70.dp)
                .constrainAs(userImage) {
                    top.linkTo(topGuideline)
                    start.linkTo(startGuideline)
                }
        )

        Text(
            text = stringResource(id = R.string.welcome_message),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            fontSize = FontSize._14SP,
            color = AppColors.WhiteFFFFFF,
            modifier = Modifier.constrainAs(welcomeMessage) {
                top.linkTo(userImage.top, 20.dp)
                start.linkTo(userImage.end, 10.dp)
            }
        )
        Text(
            text = stringResource(id = R.string.name),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Black,
            fontSize = FontSize._14SP,
            color = AppColors.WhiteFFFFFF,
            modifier = Modifier.constrainAs(userName) {
                top.linkTo(welcomeMessage.bottom)
                start.linkTo(welcomeMessage.start)
            }
        )

        IconButton(onClick = openSearch, modifier = Modifier.constrainAs(search) {
            linkTo(welcomeMessage.top, userName.bottom)
            end.linkTo(endGuideline)
            width = Dimension.preferredValue(20.dp)
            height = Dimension.wrapContent
        }) {
            Icon(
                painter = painterResource(id = R.drawable.icon_search_),
                contentDescription = "Search Icon", tint = Color.White
            )
        }

        Text(
            text = stringResource(id = R.string.categories),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            fontSize = FontSize._14SP,
            color = AppColors.WhiteFFFFFF,
            modifier = Modifier.constrainAs(categoriesTitle) {
                top.linkTo(userImage.bottom, 20.dp)
                start.linkTo(userImage.start)
            }
        )

        MoviesCategoryItem(modifier = Modifier
            .constrainAs(moviesView) {
                linkTo(parent.start, centerGuideline, endMargin = 0.dp)
                top.linkTo(categoriesTitle.bottom, 5.dp)
                width = Dimension.fillToConstraints
            }
            .clickable(onClick = openMovies))

        SeriesCategoryItem(modifier = Modifier
            .constrainAs(seriesView) {
                linkTo(centerGuideline, parent.end, startMargin = 0.dp)
                width = Dimension.fillToConstraints
                top.linkTo(categoriesTitle.bottom, 5.dp)
            }
            .clickable(onClick = openSeries))

        Text(
            text = stringResource(id = R.string.most_searched_items),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            fontSize = FontSize._14SP,
            color = AppColors.WhiteFFFFFF,
            modifier = Modifier.constrainAs(randomListTitle) {
                top.linkTo(moviesView.bottom)
                start.linkTo(userImage.start)
            }
        )


        state?.value
            ?.onLoading { LoadingView(modifier = contentModifier) }
            ?.onFail {
                ErrorView(
                    modifier = contentModifier,
                    stringResource(id = R.string.can_not_load_search)
                )
            }
            ?.onSuccess {
                it.onEmpty {
                    EmptyView(
                        modifier = contentModifier,
                        message = stringResource(id = R.string.you_must_search)
                    )
                }
                    .onNotEmpty {
                        LazyVerticalGrid(
                            modifier = contentModifier,
                            columns = GridCells.Adaptive(minSize = 100.dp),
                            state = scroll
                        ) {
                            items(it) {
                               MovieOrSeriesItem(item = it) { id, isMovie ->
                                    openDetails.invoke(id, isMovie)
                                }
                            }
                        }
                    }
            }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MoviesCategoryItem(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .then(modifier)
    ) {
        Image(
            painter = painterResource(id = R.drawable.blue_bg),
            contentDescription = "Blue Background",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxWidth()
                .padding(start = 20.dp)
                .height(150.dp),
            contentScale = ContentScale.FillWidth
        )
        Image(
            painter = painterResource(id = R.drawable.spider_man),
            contentDescription = "SpiderMan",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(120.dp)
        )

        Text(
            text = stringResource(id = R.string.movies), modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 30.dp, top = 30.dp),
            color = AppColors.WhiteFFFFFF,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize._14SP
        )


    }
}

@Composable
fun SeriesCategoryItem(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.then(modifier)) {
        Image(
            painter = painterResource(id = R.drawable.orange_bg),
            contentDescription = "Blue Background",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(end = 20.dp)
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.FillWidth
        )
        Image(
            painter = painterResource(id = R.drawable.boku_no_hero_acad),
            contentDescription = "SpiderMan", modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(130.dp)
        )

        Text(
            text = stringResource(id = R.string.series), modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 30.dp, top = 30.dp),
            color = AppColors.WhiteFFFFFF,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize._14SP
        )

    }
}

@Composable
fun MovieOrSeriesItem(
    modifier: Modifier = Modifier,
    item: MovieOrSeriesItem,
    onClick: (Int, Boolean) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                onClick.invoke(item.id, item.isMovie)
            }
            .then(modifier)
    ) {
        MediaImage(
            url = item.image,
            modifier = Modifier.size(100.dp, 130.dp),
        )

        Text(
            text = item.name, modifier = Modifier
                .padding(top = 6.dp)
                .requiredWidthIn(max = 70.dp),
            color = AppColors.GrayE8E8E8,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = FontSize._14SP,
            textAlign = TextAlign.Center
        )

        Text(
            text = item.date, modifier = Modifier
                .padding(top = 6.dp),
            color = AppColors.Gray828282,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize._14SP
        )


    }
}
