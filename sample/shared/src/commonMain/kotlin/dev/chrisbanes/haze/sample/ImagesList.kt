// Copyright 2023, Christopher Banes and the Haze project contributors
// SPDX-License-Identifier: Apache-2.0

package dev.chrisbanes.haze.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import dev.chrisbanes.haze.rememberHazeState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalHazeMaterialsApi::class)
@Composable
fun ImagesList(navController: NavHostController, blurEnabled: Boolean = HazeDefaults.blurEnabled()) {
  MaterialTheme {
    Scaffold(
      topBar = {
        LargeTopAppBar(
          title = { Text(text = "Images") },
          navigationIcon = {
            IconButton(
              onClick = navController::navigateUp,
              modifier = Modifier.testTag("back"),
            ) {
              Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
            }
          },
          modifier = Modifier.fillMaxWidth(),
        )
      },
      modifier = Modifier.fillMaxSize(),
    ) { contentPadding ->
      LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = contentPadding,
        modifier = Modifier
          .testTag("lazy_column")
          .fillMaxSize(),
      ) {
        items(50) { index ->
          key(index) {
            val hazeState = rememberHazeState(blurEnabled = blurEnabled)

            Box(
              modifier = Modifier
                .fillParentMaxWidth()
                .height(160.dp),
            ) {
              AsyncImage(
                model = rememberRandomSampleImageUrl(index),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                  .hazeSource(state = hazeState)
                  .fillMaxSize(),
              )

              Box(
                modifier = Modifier
                  .fillMaxSize(0.8f)
                  .align(Alignment.Center)
                  .clip(RoundedCornerShape(4.dp))
                  .hazeEffect(state = hazeState, style = HazeMaterials.thin()),
              ) {
                Text(
                  "Image $index",
                  style = MaterialTheme.typography.titleLarge,
                  modifier = Modifier.align(Alignment.Center),
                )
              }
            }
          }
        }
      }
    }
  }
}
