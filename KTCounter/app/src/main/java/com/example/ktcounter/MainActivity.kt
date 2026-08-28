package com.example.ktcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.room.Room
import coil3.compose.AsyncImage
import com.example.ktcounter.ui.theme.KTCounterTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // We build the Database
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "counter-database"
        ).build()

        val dao = db.counterDao()

        // Retrofit Object

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val dogApiService = retrofit.create(DogApiService::class.java)

        setContent {
            KTCounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Greeting("Zebra"); Counter(dao = dao, dogApiService = dogApiService, modifier = Modifier)

                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
/*
@Preview(showBackground = true)
@Composable
fun Counter(modifier: Modifier= Modifier) {
    var count by remember { mutableIntStateOf(0) }
    Column(modifier = modifier) {
        Text(text = "Count: $count")
        Button(onClick = {count++}) {
            Text(text = "Increment")
        }
    }

}
*/
// Counter with DBMS
@Composable
fun Counter(dao: CounterDao, dogApiService: DogApiService, modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }
    var imageUrl by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        count = dao.getCounter()?.count ?: 0
    }

    Column(modifier = modifier) {
        Text(text = "Count: $count")
        Button(onClick = {
            count++
            scope.launch {
                dao.upsertCounter(CounterEntity(id = 1, count = count))
            }
            if (count % 20 == 0) {
                scope.launch {
                    val result = dogApiService.getRandomDogImage()
                    imageUrl = result.message
                }
            }
        }) {
            Text(text = "Increment")
        }

        imageUrl?.let { url ->
            AsyncImage(
                model = url,
                contentDescription = "A random dog"
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KTCounterTheme {
        Greeting("Zebra")
    }
}