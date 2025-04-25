package com.tempest.lokalassignment.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.tempest.lokalassignment.presentation.viewmodal.JobViewModal
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.tempest.lokalassignment.presentation.DetailScreen
import com.tempest.lokalassignment.ui.theme.BottomBlue

@Composable
fun HomePage(navController: NavController, viewModal: JobViewModal, modifier: Modifier) {

    val isLoading by viewModal.isLoading.collectAsStateWithLifecycle()
    val isLoadingNext by viewModal.isLoadingNext.collectAsStateWithLifecycle()
    val showRetry by viewModal.showRetry.collectAsStateWithLifecycle()
    val jobs by viewModal.jobList.collectAsStateWithLifecycle()

    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(Modifier.size(30.dp))
        }
    } else if (showRetry) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Retry",
                modifier = Modifier.clickable {
                    viewModal.getJobs()
                    viewModal.resetRetry()
                },
                color = BottomBlue
            )
        }
    } else {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            itemsIndexed(jobs) { index, job ->

                if (jobs.isNotEmpty() && index >= jobs.size - 2) {
                    viewModal.getJobsNext()
                }

                if (job.title != null) {
                    ListItem(
                        headlineContent = {
                            Column {
                                Text(text = job.job_role, fontSize = 21.sp)
                                Text(text = "Location: ${job.primary_details.Place} ")

                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp)
                            .clickable {
                                viewModal.selectJob(job)
                                navController.navigate(DetailScreen(job.id))
                            },
                        supportingContent = {
                            Column {
                                Text(
                                    text = "Salary: ${job.primary_details.Salary}",
                                    color = Color.Green
                                )
                                Text(text = "Contact Details: ${job.whatsapp_no}")
                            }
                        },
                        leadingContent = {
                            AsyncImage(
                                model = job.creatives[0].file,
                                contentDescription = null,
                                modifier = Modifier.size(100.dp),
                                contentScale = ContentScale.Crop
                            )
                        },
                        tonalElevation = 10.dp,
                    )
                }
            }
            item {
                AnimatedVisibility(isLoadingNext) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(color = BottomBlue)
                    }
                }
            }
        }
    }

}