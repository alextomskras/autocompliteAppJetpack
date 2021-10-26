package com.dreamer.autocompliteappjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dreamer.autocompliteappjetpack.response.SampleData
import com.dreamer.autocompliteappjetpack.ui.theme.AutocompliteAppJetpackTheme
import com.dreamer.autocompliteappjetpack.view.SampleDataDetails
import com.dreamer.autocompliteappjetpack.view.SampleList
import com.google.gson.Gson


class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutocompliteAppJetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NavigatePage()
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun NavigatePage() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = "sample_data"
    ) {
        composable("sample_data") {
            SampleList(navController = navHostController)
        }
        composable("sample_detail/{item}",
            arguments = listOf(
                navArgument("item") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("item")?.let { json ->
                val item = Gson().fromJson(json, SampleData::class.java)
                SampleDataDetails(data = item)
            }
        }
    }
}