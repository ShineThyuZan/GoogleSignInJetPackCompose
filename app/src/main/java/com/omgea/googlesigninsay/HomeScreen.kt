package com.omgea.googlesigninsay

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.omgea.googlesigninsay.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(user: User) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(all = 1.dp)
                    .clip(CircleShape),
                model = user.profileUrl,
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = user.email)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = user.displayName,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 24.sp
            )
        }
    }
}