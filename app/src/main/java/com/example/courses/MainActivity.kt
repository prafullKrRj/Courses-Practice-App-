package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val list = DataSource.topics
                    CourseListGrid(courseDetails = list)
                }
            }
        }
    }
}

@Composable
fun CourseListGrid(courseDetails: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(courseDetails) {
            CourseCard(courseDetails = it, modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp))
        }
    }
}
@Composable
fun CourseCard(courseDetails: Topic, modifier: Modifier = Modifier) {
    Card (
        modifier = modifier
    ){
        Row (
            modifier = Modifier.fillMaxWidth()
        ){

            Image(
                modifier = Modifier
                    .size(68.dp)
                    .aspectRatio(1f),
                painter = painterResource(id = courseDetails.imageRes),
                contentDescription = stringResource(
                    id = courseDetails.course
                ), contentScale = ContentScale.Crop
            )

            Column (
                modifier = Modifier.padding(horizontal = 16.dp)
            ){
                Text(text = stringResource(id = courseDetails.course), fontSize = 14.sp, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    Icon(painter = painterResource(id = R.drawable.ic_grain), contentDescription = null)
                    Text(text = courseDetails.number.toString(), style = MaterialTheme.typography.labelMedium, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}
@Preview
@Composable
fun CourseCardPreview() {
    CourseCard(courseDetails = Topic(R.string.architecture, 324, R.drawable.architecture))
}