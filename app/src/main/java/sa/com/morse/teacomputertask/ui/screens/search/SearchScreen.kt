package sa.com.morse.teacomputertask.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import sa.com.morse.teacomputertask.R
import sa.com.morse.teacomputertask.ui.screens.home.MovieOrSeriesItem
import sa.com.morse.teacomputertask.ui.theme.AppColors
import sa.com.morse.teacomputertask.ui.theme.FontSize

@Preview(showBackground = true , showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(modifier: Modifier = Modifier , openDetails : (Int)->Unit = {}) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(AppColors.Black1A1A1D)
        .then(modifier)) {
        val scroll = rememberLazyGridState()
        val (actionbar,   searchView, list) = createRefs()
        val startGuideline = createGuidelineFromStart(0.05F)
        val endGuideline = createGuidelineFromStart(0.95F)
        val topGuideline = createGuidelineFromTop(0.01F)
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
                    modifier = Modifier.padding(start = 10.dp)
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
                value = "",
                onValueChange = { /*viewModel.updateQuery(it)*/ },
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

        LazyVerticalGrid(
            modifier = Modifier.constrainAs(list) {
                linkTo(startGuideline, endGuideline)
                linkTo(searchView.bottom, parent.bottom, topMargin = 10.dp)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },
            columns = GridCells.Adaptive(minSize = 100.dp),
            state = scroll
        ) {
            items(20) {
                MovieOrSeriesItem{
                    openDetails.invoke(it)
                }
            }
        }

    }

}