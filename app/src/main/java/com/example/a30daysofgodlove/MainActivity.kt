package com.example.a30daysofgodlove

import android.os.Bundle
import androidx.compose.ui.res.painterResource
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.a30daysofgodlove.data.DataSource
import com.example.a30daysofgodlove.model.Versus
import com.example.a30daysofgodlove.ui.theme._30DaysOfGodLoveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30DaysOfGodLoveTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   DaysOfGodLoveApp()
                }
            }
        }
    }
}

/**
 * Composable that displays an app bar and a list of versus.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaysOfGodLoveApp(){
    Scaffold(
        topBar = {
            VersusTopAppBar()
        }
    ) { it ->
    LazyColumn(contentPadding = it){
        items(DataSource().loadVersus()){
            VersusItem(
                versus = it,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
    }

//    VersusList(versusList = DataSource().loadVersus())
}


/**
 * Composable that displays a Top Bar with an icon and text.
 *
 * @param modifier modifiers to set to this composable
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  VersusTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(title = {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.image_size))
                    .padding(dimensionResource(R.dimen.padding_small)),
                painter = painterResource(R.drawable.cow),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.labelSmall
            )
        }
    },
        modifier = modifier
        )
}


/**
 * Composable that displays a photo of a script.
 *
 * @param dogIcon is the resource ID for the image of the dog
 * @param modifier modifiers to set to this composable
 */

@Composable
fun VersusIcon(
    @DrawableRes dayIcon: Int,
    modifier: Modifier = Modifier
){
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(dayIcon),
        contentDescription = null
    )
}

/**
 * Composable that displays a dog's name and age.
 *
 * @param dogName is the resource ID for the string of the dog's name
 * @param dogAge is the Int that represents the dog's age
 * @param modifier modifiers to set to this composable
 */
@Composable
fun VersusSummary(
    @StringRes versusDesc: Int,
    @DrawableRes img: Int,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier) {
        Image(
           modifier = modifier
               .padding(dimensionResource(id = R.dimen.padding_small))
               .size(dimensionResource(id = R.dimen.image_size))
               .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            painter = painterResource(img),
            contentDescription = null,

        )
        Text(
            text = stringResource(versusDesc),
            style = MaterialTheme.typography.bodyLarge
        )

    }
}

@Composable
fun VersusDetail(
    versusdetail: Versus,
    modifier: Modifier = Modifier
){
    Column( modifier = modifier){
        Image(
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .size(dimensionResource(id = R.dimen.image_medium))
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            painter = painterResource(versusdetail.img),
            contentDescription = null,
        )
        Text(
            text = stringResource(versusdetail.description),
            style = MaterialTheme.typography.bodyLarge
        )

    }
}
@Composable
fun VersusItem(
    versus: Versus,
    modifier: Modifier = Modifier
){
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
    ){
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        ){
            Row(
                modifier
                = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
//                VersusIcon(day.img)
                VersusSummary(versus.title, versus.img)
                Spacer(Modifier.weight(1f))
                VersusItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
            }
            if(!expanded){
                VersusDetail(versus,modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    top = dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium))
                )
            }

        }
    }

}
/**
 * Composable that displays a button that is clickable and displays an expand more or an expand less
 * icon.
 *
 * @param expanded represents whether the expand more or expand less icon is visible
 * @param onClick is the action that happens when the button is clicked
 * @param modifier modifiers to set to this composable
 */
@Composable
private fun VersusItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector  = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )

    }

}
//
//
//@Composable
//fun VersusList(versusList: List<Versus>, modifier: Modifier = Modifier){
//    LazyColumn(modifier = modifier){
//        items(versusList){ versuss ->
//            VersusCard(
//                versus = versuss,
//                modifier = Modifier.padding(8.dp)
//            )
//
//        }
//    }
//}
@Preview(showBackground = true)
@Composable
fun DaysOfGodL0vePreview() {
    _30DaysOfGodLoveTheme(darkTheme = false) {
//        VersusCard(Versus(R.string.job_6, R.drawable.job_6))
        DaysOfGodLoveApp()
    }
}