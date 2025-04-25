package com.tempest.lokalassignment.presentation.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.tempest.lokalassignment.presentation.SimpleTopAppBar
import com.tempest.lokalassignment.presentation.viewmodal.JobViewModal
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tempest.lokalassignment.R
import com.tempest.lokalassignment.presentation.formatDate
import com.tempest.lokalassignment.presentation.toEntity
import com.tempest.lokalassignment.ui.theme.BottomBlue

@Composable
fun JobDetailPage(navController: NavController, viewModal: JobViewModal, jobId: Int) {

    val job by viewModal.selectedJob.collectAsStateWithLifecycle()
    val bookmarkStatus by viewModal.isBookmark.collectAsStateWithLifecycle()

    DisposableEffect(Unit) {
        viewModal.bookMarkStatus(jobId)
        onDispose { viewModal.resetSelectedJob() }
    }

    Scaffold(topBar = { SimpleTopAppBar("Job Details") }) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(10.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        job?.let { Text(text = it.job_role, modifier = Modifier.weight(1f).padding(end = 10.dp), fontSize = 20.sp) }
                        Button(
                            onClick = {
                                if (bookmarkStatus) viewModal.clearBookmark(jobId) else job?.let {
                                    viewModal.addBookmark(toEntity(it))
                                }
                            },
                            modifier = Modifier.animateContentSize(),
                            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = BottomBlue)
                        ) {
                            Icon(painter = painterResource(R.drawable.bookmark), contentDescription = null)
                            Text(
                                text = if (bookmarkStatus) "Remove Bookmark" else "Bookmark Now"
                            )
                        }
                    }
                    job?.company_name?.let { Text(text = "Company Name: $it") }
                    job?.id?.let { Text(text = "Job ID: $it") }
                    job?.job_category?.let { Text(text = "Job Category: $it") }
                    job?.other_details?.let { Text(text = it) }
                    job?.job_hours?.let { Text(text = "Job Hours: $it") }
                    job?.primary_details?.Place?.let { Text(text = "Job Location: $it") }
                    job?.primary_details?.Experience?.let { Text(text = "Experience Required: $it") }
                    job?.primary_details?.Qualification?.let { Text(text = "Qualification Required: $it") }
                    job?.primary_details?.Salary?.let { Text(text = "Salary: $it") }
                    job?.openings_count?.let { Text(text = "Total Opening: $it") }
                    job?.num_applications?.let { Text(text = "Number of Applicants: $it") }
                    job?.contentV3?.V3[1]?.let { Text(text = "${it.field_key} : ${it.field_value}") }
                    job?.contentV3?.V3[2]?.let { Text(text = "${it.field_key} : ${it.field_value}") }
                    job?.contentV3?.V3[3]?.let { Text(text = "${it.field_key} : ${it.field_value}") }
                    job?.created_on?.let { Text(text = "Opening date: ${formatDate(it)}") }
                    job?.expire_on?.let { Text(text = "Last date to apply: ${formatDate(it)}") }
                    job?.whatsapp_no?.let { Text(text = "Contact Info: $it") }
                    job?.contact_preference?.whatsapp_link?.let { Text(text = "Contact Medium: Whatsapp") }
                    job?.contact_preference?.preferred_call_start_time?.let { Text(text = "Contact Time: $it - ${job?.contact_preference?.preferred_call_end_time}") }
                    job?.views?.let { Text(text = "Viewed by $it People") }
                    job?.shares?.let { Text(text = "Shared by $it People") }
                    job?.amount?.let { Text(text = "Application fee: $it ") }
                }

            }
        }
    }

}