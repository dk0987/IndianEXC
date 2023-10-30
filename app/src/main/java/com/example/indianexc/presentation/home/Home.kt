package com.example.indianexc.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(true){
        viewModel.observeInternet(context)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFfefefe))
            .padding(horizontal = 10.dp),
    ){
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.Start ,
                verticalAlignment = Alignment.Bottom
            ){
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(text = buildAnnotatedString {
                        withStyle(SpanStyle(
                            fontSize = 45.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF391285)
                        ) ){
                            append("F")
                        }
                        withStyle(SpanStyle(
                            fontSize = 30.sp,
                            color = Color.Black
                        ) ){
                            append("orex")
                        }
                    },
                        letterSpacing = 2.sp
                    )

                }

            }
        }

        item {
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "Today's Exchange Rate",
                color = Color.Black,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
        }

        item{

            state.value.latestExchangeRate?.let {
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp) ,
                    horizontalArrangement = Arrangement.SpaceBetween ,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = it.baseCountry.flag),
                            contentDescription = "Flag" ,
                            modifier = Modifier
                                .width(40.dp)
                                .height(30.dp),
                            contentScale = ContentScale.FillWidth
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = it.baseCountry.name,
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "(Base Currency)",
                            color = Color.Black,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = (1.00).toString(),
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = it.baseCountry.currencyName,
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }


                }
            }

        }

        state.value.latestExchangeRate?.let { currency  ->
            items(currency.latestCurrencies){latestCurency ->
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = if (state.value.latestExchangeRate!!.latestCurrencies.indexOf(latestCurency) == state.value.selectedIndex) 10.dp else 40.dp,
                            end = 10.dp
                        )
                        .clickable {
                            viewModel.onEvent(HomeEvent.SelectedIndex(state.value.latestExchangeRate!!.latestCurrencies.indexOf(latestCurency)))
                        },
                    horizontalArrangement = Arrangement.SpaceBetween ,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Image(
                            painter = painterResource(id = latestCurency.country.flag),
                            contentDescription = "Flag" ,
                            modifier = Modifier
                                .width(40.dp)
                                .height(25.dp),
                            contentScale = ContentScale.FillWidth
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = latestCurency.country.name,
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        )

                    }

                    Row(verticalAlignment = Alignment.CenterVertically){

                        Text(
                            text = (latestCurency.rate).toString(),
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = latestCurency.country.currencyName,
                            color = Color.Black,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            }

            item {
                Spacer(modifier = Modifier.height(18.dp))
                Row {
                    Text(
                        text = "Weekly Representation ",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )

                    state.value.latestExchangeRate?.let {
                        Image(
                            painter = painterResource(id = it.latestCurrencies[state.value.selectedIndex].country.flag),
                            contentDescription = "Flag" ,
                            modifier = Modifier
                                .width(40.dp)
                                .height(25.dp),
                            contentScale = ContentScale.FillWidth
                        )
                    }

                }

            }

            item{

                Spacer(modifier = Modifier.height(50.dp))

                ExchangeRateChart(
                    data = state.value.exchangeRates,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(start = 10.dp),
                    selectedIndex = state.value.selectedIndex
                )

            }


        }
    }
}