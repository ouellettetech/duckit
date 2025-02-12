package com.ouellettetech.duckit.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ouellettetech.duckit.R
import com.ouellettetech.duckit.data.model.DuckitPost
import com.ouellettetech.duckit.data.model.DuckitPosts
import com.ouellettetech.duckit.presentation.events.postsEvents
import com.ouellettetech.duckit.presentation.uiState.PostsUIState
import com.ouellettetech.duckit.presentation.viewModel.postsViewModel
import com.ouellettetech.duckit.utils.Constants.upVoteFontSize

@Composable
fun PostsScreen(
    uiState: PostsUIState,
    navController: NavController
) {

    val viewModel: postsViewModel = hiltViewModel()
    viewModel.setNavController(navController)
    if (!uiState.loading && uiState.posts == null) {
        viewModel.getPosts() // TODO replace with Lifecycle observer that refreshes periodically.
    }
    if (uiState.networkdialog) {
        ErrorDialog(stringResource(R.string.NetworkErrorMessage)) { viewModel.onEvent(postsEvents.DismissNetworkError) }
    }
    AllPosts(uiState.posts, viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllPosts(listOfPosts: DuckitPosts?, viewModel: postsViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                actions = {
                    Button(
                        onClick = { viewModel.onEvent(postsEvents.SigninButtonPressed) },
                    ) {
                        Text("SignIn")
                    }
                },
            )
        }
    ) { padding ->
        val allPosts: List<DuckitPost>? = listOfPosts?.Posts
        if (allPosts.isNullOrEmpty()) {
            Text("Loading Please Wait...")// Should replace with a fancy Skeleton.
            // Show message that there are no posts.
        } else {
            Surface(
                Modifier
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                LazyColumn {
                    items(allPosts) { duckitPost ->
                        PostCard(duckitPost, viewModel)
                    }
                }
            }
        }
    }
}


@Composable
fun PostCard(thisPost: DuckitPost, viewModel: postsViewModel) {
    Column {
        Text(
            text = thisPost.headline
        )
        AsyncImage(
            model = thisPost.image,
            contentDescription = "Current Image"
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            UpVoteButton("↑", { viewModel.onEvent(postsEvents.UpVote(thisPost.id)) })
            Spacer(modifier = Modifier.padding(10.dp))
            Text("${thisPost.upvotes}", fontSize = upVoteFontSize.sp)
            Spacer(modifier = Modifier.padding(10.dp))
            UpVoteButton("↓", { viewModel.onEvent(postsEvents.DownVote(thisPost.id)) })
        }
    }
}

@Composable
fun UpVoteButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            containerColor = Color.Transparent,
        )
    ) {
        Text(text, fontSize = upVoteFontSize.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ErrorDialog(Message: String, onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = Message,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}
