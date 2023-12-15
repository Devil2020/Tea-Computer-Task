package sa.com.morse.teacomputertask.utils.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.com.morse.teacomputertask.R
import sa.com.morse.teacomputertask.ui.theme.AppColors
import sa.com.morse.teacomputertask.ui.theme.FontSize

@Composable
fun ShadowButton(modifier: Modifier = Modifier, name: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .paint(
                painterResource(id = R.drawable.button_bg),
                contentScale = ContentScale.FillBounds
            )
            .then(modifier)

    ) {
        Text(
            text = name ,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = AppColors.WhiteFFFFFF,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmptyView(modifier: Modifier = Modifier, message: String = "This may take a few moments") {
    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .then(modifier),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.empty_icon),
            contentDescription = "empty icon"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            fontSize = FontSize._14SP,
            color = AppColors.WhiteFFFFFF,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxSize()
            .then(modifier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = modifier, strokeWidth = 3.dp, color = AppColors.GrayE8E8E8
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ErrorView(modifier: Modifier = Modifier, errorMessage: String = "", onRetry: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxSize()
            .then(modifier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(containerColor = AppColors.BlueB7DFFF),
            modifier = Modifier.size(150.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.error_item),
                contentDescription = "empty icon",
                modifier = Modifier.size(150.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            fontSize = FontSize._14SP,
            textAlign = TextAlign.Center,
            color = AppColors.WhiteFFFFFF,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            onClick = { onRetry.invoke() },
            border = BorderStroke(3.dp, AppColors.BlueB7DFFF),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent
            )
        ) {
            Text(
                text = stringResource(id = R.string.retry), modifier = Modifier
                    .padding(horizontal = 30.dp),
                color = AppColors.BlueB7DFFF,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                fontSize = FontSize._14SP
            )
        }
    }
}