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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.room.Room
import com.example.ktcounter.ui.theme.KTCounterTheme
import kotlinx.coroutines.launch

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

        setContent {
            KTCounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Greeting("Zebra"); Counter(dao = dao, modifier = Modifier)

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
fun Counter(dao: CounterDao, modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }
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
        }) {
            Text(text = "Increment")
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