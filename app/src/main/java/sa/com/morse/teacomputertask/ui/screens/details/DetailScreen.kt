package sa.com.morse.teacomputertask.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import sa.com.morse.teacomputertask.R
import sa.com.morse.teacomputertask.data.models.ActorsResponse
import sa.com.morse.teacomputertask.domain.models.Detail
import sa.com.morse.teacomputertask.ui.theme.AppColors
import sa.com.morse.teacomputertask.ui.theme.FontSize
import sa.com.morse.teacomputertask.utils.ErrorView
import sa.com.morse.teacomputertask.utils.ExceptionType
import sa.com.morse.teacomputertask.utils.LoadingView
import sa.com.morse.teacomputertask.utils.MediaImage
import sa.com.morse.teacomputertask.utils.ShadowButton
import sa.com.morse.teacomputertask.utils.onFail
import sa.com.morse.teacomputertask.utils.onLoading
import sa.com.morse.teacomputertask.utils.onSuccess

@Composable
fun DetailScreen(modifier: Modifier = Modifier, vm: DetailViewModel?) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Black1A1A1D)
            .then(modifier)
    ) {
        val (loading, error) = createRefs()

        val state = vm?.details?.observeAsState()

        state?.value
            ?.onLoading {
                LoadingView(modifier = Modifier.constrainAs(loading) {
                    linkTo(parent.top, parent.bottom)
                    linkTo(parent.start, parent.end)
                })
            }
            ?.onFail {
                ErrorView(modifier = Modifier.constrainAs(error) {
                    linkTo(parent.top, parent.bottom)
                    linkTo(parent.start, parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                } , it.getErrorMessage()){
                    vm.loadDetails()
                }
            }
            ?.onSuccess {
                RenderDetails(details = it)
            }


    }
}

@Composable
fun ActorItem(user: ActorsResponse.User) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp)
    ) {
        MediaImage(
            url = user.getPosterPath(),
            modifier = Modifier.size(60.dp),
            shape = CircleShape
        )

        Text(
            text = user.name, modifier = Modifier
                    .padding(top = 6.dp).requiredWidthIn(max = 70.dp),
            color = AppColors.GrayE8E8E8,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            fontSize = FontSize._14SP,
            textAlign = TextAlign.Center ,
        )
    }
}

@Composable
fun ConstraintLayoutScope.RenderDetails(details: Detail) {
    val startGuideline = createGuidelineFromStart(0.05F)
    val endGuideline = createGuidelineFromStart(0.95F)
    val posterGuideline = createGuidelineFromTop(0.35F)
    val (poster, posterBlur, name, year, studio, rateStars, rateText, description, actors, watch) = createRefs()
    val context =
        LocalContext.current

    Text(
        text = details.date,
        modifier = Modifier.constrainAs(year) {
            linkTo(name.top, name.bottom)
            end.linkTo(endGuideline, 10.dp)
        },
        maxLines = 1,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Medium,
        fontSize = FontSize._14SP,
        color = AppColors.Gray8F8F8F,
    )

    MediaImage(
        url = details.image,
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier.constrainAs(poster) {
            linkTo(parent.start, parent.end)
            width = Dimension.fillToConstraints
            linkTo(parent.top, name.top)
            height = Dimension.fillToConstraints
        }
    )

    Image(
        rememberImagePainter( ContextCompat.getDrawable(context,R.drawable.linear_transparent_foreground)),
        contentScale = ContentScale.FillBounds,
        contentDescription = "BlurImage",
        modifier = Modifier.constrainAs(posterBlur) {
            linkTo(poster.start, poster.end)
            width = Dimension.fillToConstraints
            linkTo(poster.top, poster.bottom)
            height = Dimension.fillToConstraints
        }
    )

    Text(
        text = details.name,
        modifier = Modifier.constrainAs(name) {
            bottom.linkTo(studio.top, 10.dp)
            linkTo(startGuideline , year.start , endMargin = 20.dp)
            width = Dimension.fillToConstraints
        },
        maxLines = 3,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSize._24SP,
        color = AppColors.WhiteFFFFFF,
    )

    Text(
        text = details.status,
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
        text = details.overview,
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
        items(details.actors.items) { user ->
            ActorItem(user)
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