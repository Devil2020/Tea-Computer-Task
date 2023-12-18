package sa.com.morse.teacomputertask.ui.screens.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import sa.com.morse.teacomputertask.R
import sa.com.morse.teacomputertask.ui.screens.home.MovieOrSeriesItem
import sa.com.morse.teacomputertask.ui.theme.AppColors
import sa.com.morse.teacomputertask.ui.theme.FontSize
import sa.com.morse.teacomputertask.utils.ErrorView
import sa.com.morse.teacomputertask.utils.LoadingView
import sa.com.morse.teacomputertask.utils.onFail
import sa.com.morse.teacomputertask.utils.onLoading
import sa.com.morse.teacomputertask.utils.onSuccess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    vm: MoviesViewModel,
    openDetails: (Int, Boolean) -> Unit = { i: Int, b: Boolean -> } ,
    finish : () -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Black1A1A1D)
            .then(modifier)
    ) {

        val state = vm.movies.observeAsState()
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
                    text = stringResource(id = R.string.movies),
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
                    modifier = Modifier.padding(start = 10.dp).clickable {
                        finish.invoke()
                    }
                )
            })

        state.value
            ?.onLoading { LoadingView(modifier = contentModifier) }
            ?.onFail {
                ErrorView(modifier = contentModifier) {
                    vm.loadMovies()
                }
            }
            ?.onSuccess {
                LazyVerticalGrid(
                    modifier = contentModifier,
                    columns = GridCells.Adaptive(minSize = 100.dp),
                    state = scroll
                ) {
                    items(it) {
                        MovieOrSeriesItem(item = it)  { id, isMovie ->
                            openDetails.invoke(id, isMovie)
                        }
                    }
                }
            }
    }

}