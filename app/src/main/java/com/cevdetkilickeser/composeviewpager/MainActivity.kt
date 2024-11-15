package com.cevdetkilickeser.composeviewpager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cevdetkilickeser.composeviewpager.Data.getirAlisverisImg
import com.cevdetkilickeser.composeviewpager.Data.getirBiTaksiImg
import com.cevdetkilickeser.composeviewpager.Data.getirBuyukImg
import com.cevdetkilickeser.composeviewpager.Data.getirCarsiImg
import com.cevdetkilickeser.composeviewpager.Data.getirFinansImg
import com.cevdetkilickeser.composeviewpager.Data.getirImg
import com.cevdetkilickeser.composeviewpager.Data.getirIsImg
import com.cevdetkilickeser.composeviewpager.Data.getirSuImg
import com.cevdetkilickeser.composeviewpager.Data.getirYemekImg
import com.cevdetkilickeser.composeviewpager.ui.theme.ComposeViewPagerTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeViewPagerTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(
                    color = colorResource(id = R.color.status_bar)
                )
                HomePage(pages = Data.pages)
            }
        }
    }
}

@Composable
fun HomePage(pages:List<Int>) {
    Column {
        AddressRow()
        HorizontalPagerDemo(pages = pages)
        AppSelection()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerDemo(pages: List<Int>) {
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState.currentPage) {
        delay(3000)
        val nextPage = (pagerState.currentPage + 1) % pages.size
        pagerState.animateScrollToPage(
            page = nextPage
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.gray_background)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
        ) { page ->
            Image(
                painter = painterResource(id = pages[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = colorResource(id = R.color.getir_purple),
            inactiveColor = colorResource(id = R.color.light_gray),
            indicatorWidth = 6.dp,
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun AppSelection() {
    Column (
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.gray_background)
            ),
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            GetirCard(
                text = "getir",
                imageRes = getirImg,
                aspectRatio = 1.15f
            )
            Spacer(modifier = Modifier.width(8.dp))
            GetirCard(
                text = "getirfinans",
                imageRes = getirFinansImg,
                aspectRatio = 1.15f
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            GetirCard(
                text = "getirbüyük",
                imageRes = getirBuyukImg,
                aspectRatio = 2.5f,
                fontSize = 18
            )
            Spacer(modifier = Modifier.width(8.dp))
            GetirCard(
                text = "getiryemek",
                imageRes = getirYemekImg,
                aspectRatio = 2.5f,
                fontSize = 18
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            GetirCard(
                text = "getircarsi",
                imageRes = getirCarsiImg,
                aspectRatio = 2.5f,
                fontSize = 18
            )
            Spacer(modifier = Modifier.width(8.dp))
            GetirCard(
                text = "getiralışveriş",
                imageRes = getirAlisverisImg,
                aspectRatio = 2.5f,
                fontSize = 18
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            GetirCard(
                text = "getirsu",
                imageRes = getirSuImg,
                aspectRatio = 2.5f,
                fontSize = 18
            )
            Spacer(modifier = Modifier.width(8.dp))
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .width(170.dp)
                    .aspectRatio(2.5f)
            ){
                GetirCard(
                    text = "getiriş",
                    imageRes = getirIsImg,
                    width = 81.dp,
                    aspectRatio = 1.19f,
                    fontSize = 10,
                    textPaddingTop = 4.dp
                )
                GetirCard(
                    text = "getirbitaksi",
                    imageRes = getirBiTaksiImg,
                    width = 81.dp,
                    aspectRatio = 1.19f,
                    fontSize = 10,
                    textPaddingTop = 4.dp
                )
            }
        }
    }
}

@Composable
fun AddressRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.white_background)
            )
            .clickable { println("Row clicked") },
    ) {
        Text(
            fontSize = 14.sp,
            text = "Teslimat Adresi Belirle",
            modifier = Modifier
                .padding(16.dp)
        )
        Icon(
            modifier = Modifier.padding(end = 10.dp),
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            tint = colorResource(
                id = R.color.getir_purple
            )
        )
    }
}

@Composable
fun GetirCard(
    text: String,
    imageRes: Int,
    modifier: Modifier = Modifier,
    aspectRatio: Float = 1f,
    width: Dp = 170.dp,
    fontSize: Int = 20,
    textPaddingTop: Dp = 8.dp
) {
    Card(
        modifier = modifier
            .border(0.5.dp, Color.LightGray, RoundedCornerShape(15.dp))
            .width(width)
            .aspectRatio(aspectRatio),
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.white))
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxSize()
            )
            Text(
                text = text,
                color = colorResource(id = R.color.getir_purple),
                fontSize = fontSize.sp,
                fontFamily = FontFamily(Font(R.font.getir)),
                modifier = Modifier.padding(start = 8.dp, top = textPaddingTop)
            )
        }
    }
}

object Data{
    val pages = listOf(
        R.drawable.g1,
        R.drawable.g2,
        R.drawable.g3,
        R.drawable.g4,
        R.drawable.g5,
        R.drawable.g6,
        R.drawable.g7,
        R.drawable.g8
    )

    val getirImg = R.drawable.getir
    val getirFinansImg = R.drawable.getirfinans
    val getirBuyukImg = R.drawable.getirbuyuk
    val getirYemekImg = R.drawable.getiryemek
    val getirCarsiImg = R.drawable.getircarsi
    val getirAlisverisImg = R.drawable.getiralisveris
    val getirSuImg = R.drawable.getirsu
    val getirIsImg = R.drawable.getiris
    val getirBiTaksiImg = R.drawable.getirbitaksi
}