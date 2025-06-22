package com.guru.composecookbook.ui.learnwidgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBars() {
  var searchText by remember { mutableStateOf("") }
  var searchActive by remember { mutableStateOf(false) }

  var dockedText by remember { mutableStateOf("") }
  var dockedActive by remember { mutableStateOf(false) }

  Column(
    modifier = Modifier.fillMaxWidth().padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Simple SearchBar with modified layout
    Box(modifier = Modifier.fillMaxWidth()) {
      SearchBar(
        modifier = Modifier.fillMaxWidth(),
        query = searchText,
        onQueryChange = { searchText = it },
        onSearch = {
          searchText = it
          searchActive = false
        },
        active = searchActive,
        onActiveChange = { active ->
          searchActive = active
          if (!active) searchText = ""
        },
        placeholder = { Text("Search") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
      ) {
        LazyColumn(modifier = Modifier.fillMaxWidth().heightIn(max = 300.dp)) {
          items(3) { idx ->
            ListItem(
              headlineContent = { Text("Recent search $idx") },
              leadingContent = { Icon(Icons.Default.History, contentDescription = null) },
              modifier =
                Modifier.clickable {
                  searchText = "Recent search $idx"
                  searchActive = false
                }
            )
          }
        }
      }
    }

    Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.surface) {
      // DockedSearchBar
      DockedSearchBar(
        modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
        query = dockedText,
        onQueryChange = { dockedText = it },
        onSearch = {
          dockedText = it
          dockedActive = false
        },
        active = dockedActive,
        onActiveChange = { active ->
          dockedActive = active
          if (!active) dockedText = ""
        },
        placeholder = { Text("Docked Search") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
      ) {
        Surface(modifier = Modifier.fillMaxWidth().heightIn(max = 350.dp)) {
          LazyColumn {
            items(3) { idx ->
              ListItem(
                headlineContent = { Text("Suggestion $idx") },
                leadingContent = { Icon(Icons.Default.History, contentDescription = null) },
                modifier =
                  Modifier.clickable {
                    dockedText = "Suggestion $idx"
                    dockedActive = false
                  }
              )
            }
          }
        }
      }
    }
  }
}
