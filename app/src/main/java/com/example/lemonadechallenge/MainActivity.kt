package com.example.lemonadechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonadechallenge.ui.theme.LemonadeChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Lemonade(modifier = Modifier)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lemonade(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFfbec5d),
                )
            )
        }
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            var result by remember { mutableIntStateOf(1) }
            var squeezeCount by remember { mutableIntStateOf(0) }

            when (result) {
                1 ->
                    LemonadeTextAndImage(
                        modifier = modifier,
                        onClick = {
                            result = 2
                            squeezeCount = (1..4).random()
                        },
                        image = R.drawable.lemon_tree,
                        text = R.string.lemon_tree_description,
                    )

                2 ->
                    LemonadeTextAndImage(
                        modifier = modifier,
                        onClick = {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                result = 3
                            }
                        },
                        image = R.drawable.lemon_squeeze,
                        text = R.string.lemon_description,
                    )

                3 ->
                    LemonadeTextAndImage(
                        modifier = modifier,
                        onClick = {
                            result = 4
                        },
                        image = R.drawable.lemon_drink,
                        text = R.string.lemonade_description,
                    )

                4 ->
                    LemonadeTextAndImage(
                        modifier = modifier,
                        onClick = {
                            result = 1
                        },
                        image = R.drawable.lemon_restart,
                        text = R.string.glass_description,
                    )
            }
        }
    }
}

@Composable
fun LemonadeTextAndImage(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @DrawableRes image: Int,
    text: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = modifier
                .clip(MaterialTheme.shapes.extraLarge)
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .padding(horizontal = 20.dp, vertical = 5.dp)
                .clickable {
                    onClick()
                },
            painter = painterResource(image),
            contentDescription = "contentDescription",
        )
        Text(
            modifier = modifier.padding(16.dp),
            text = stringResource(text),
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeChallengeTheme {
        Lemonade()
    }
}