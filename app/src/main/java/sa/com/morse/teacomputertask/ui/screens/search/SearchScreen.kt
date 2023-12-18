package sa.com.morse.teacomputertask.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import sa.com.morse.teacomputertask.R
import sa.com.morse.teacomputertask.ui.screens.home.MovieOrSeriesItem
import sa.com.morse.teacomputertask.ui.screens.series.SeriesItem
import sa.com.morse.teacomputertask.ui.screens.series.SeriesViewModel
import sa.com.morse.teacomputertask.ui.theme.AppColors
import sa.com.morse.teacomputertask.ui.theme.FontSize
import sa.com.morse.teacomputertask.utils.EmptyView
import sa.com.morse.teacomputertask.utils.ErrorView
import sa.com.morse.teacomputertask.utils.ExceptionType
import sa.com.morse.teacomputertask.utils.LoadingView
import sa.com.morse.teacomputertask.utils.onEmpty
import sa.com.morse.teacomputertask.utils.onFail
import sa.com.morse.teacomputertask.utils.onLoading
import sa.com.morse.teacomputertask.utils.onNotEmpty
import sa.com.morse.teacomputertask.utils.onSuccess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    vm: SearchViewModel?,
    openDetails: (Int, Boolean) -> Unit ,
    finish: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Black1A1A1D)
            .then(modifier)
    ) {

        val scroll = rememberLazyGridState()
        val (actionbar, searchView, list) = createRefs()
        val startGuideline = createGuidelineFromStart(0.05F)
        val endGuideline = createGuidelineFromStart(0.95F)
        val topGuideline = createGuidelineFromTop(0.01F)
        var search by remember { mutableStateOf("") }
        val state = vm?.items?.observeAsState()
        val contentModifier = Modifier.constrainAs(list) {
            linkTo(startGuideline, endGuideline)
            linkTo(searchView.bottom, parent.bottom, topMargin = 10.dp)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }
        LaunchedEffect(key1 = true){
            vm?.search("")
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
                    text = stringResource(id = R.string.search_for_content),
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

        Box(contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .clickable { }
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.search_bg),
                    contentScale = ContentScale.FillBounds
                )
                .constrainAs(searchView) {
                    top.linkTo(actionbar.bottom)
                    linkTo(parent.start, parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(vertical = 20.dp)

        ) {
            OutlinedTextField(
                value = search,
                onValueChange = {
                    search = it
                    vm?.search(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 20.dp)
                    .background(Color.Transparent, CircleShape),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = AppColors.WhiteFFFFFF,
                    focusedTextColor = AppColors.WhiteFFFFFF,
                    unfocusedTextColor = AppColors.WhiteFFFFFF
                ),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.None
                ),
                shape = CircleShape,
                placeholder = {
                    Text(
                        stringResource(id = R.string.search_for_content),
                        color = AppColors.WhiteFFFFFF,
                        fontWeight = FontWeight.Light,
                        fontSize = FontSize._12SP
                    )
                },
                maxLines = 1,
                singleLine = true
            )
        }

        state?.value
            ?.onLoading { LoadingView(modifier = contentModifier) }
            ?.onFail {
                ErrorView(modifier = contentModifier, it.getErrorMessage()) {
                    vm.search(search)
                }
            }
            ?.onSuccess {
                it.onEmpty {
                    EmptyView(
                        modifier = contentModifier,
                        message = stringResource(id = R.string.not_found_result)
                    )
                }.onNotEmpty {
                    LazyVerticalGrid(
                        modifier = contentModifier,
                        columns = GridCells.Adaptive(minSize = 100.dp),
                        state = scroll
                    ) {
                        items(it) { item ->
                            MovieOrSeriesItem(item = item) { id, isMovie ->
                                search = ""
                                vm?.save(item)
                                openDetails.invoke(id, isMovie)
                            }
                        }
                    }
                }
            }

    }

}