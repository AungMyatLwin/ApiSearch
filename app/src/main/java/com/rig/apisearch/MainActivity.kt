package com.rig.apisearch

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.rig.apisearch.databinding.ActivityMainBinding
import com.rig.apisearch.responseData.Pagination
import com.rig.apisearch.responseData.data
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val viewModel:MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel.response.observe(this, Observer {
            if (it!=null){
                binding.composefull.setContent {
                   MyApp(it)
                }
                binding.numberofPage.setContent {
                    Pages(it.pagination)
                }
            }
        })

        viewModel.searchQuery("cat")
        binding.click.setOnClickListener {
            viewModel.searchQuery(binding.editQuery.text.toString())
        }


        setContentView(binding.root)
    }

    @Composable
    fun Pages(pagination: Pagination) {
        LazyRow{
            items(pagination.total){
                Button(onClick = {
                    viewModel.searchPagination(binding.editQuery.text.toString(), it, it+3)
                }, modifier = Modifier.padding(5.dp)) {
                    Text(text = it.toString())
                }
            }
        }
    }



    @Composable
    fun MyApp(it: data?) {
        Surface(modifier = Modifier.padding(4.dp)) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start){
                LazyColumn {
                    if (it != null) {
                        items(it.data){
                            Card(modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                                elevation = CardDefaults.elevatedCardElevation(2.dp)) {
                                Row {
                                    DecodeImage( it.thumbnail.lqip.substringAfter(","), it.title)
                                    Text(it.title, style = MaterialTheme.typography.titleLarge,
                                        modifier = Modifier.padding(5.dp))
                                }
                                Text(it.api_link,
                                    modifier = Modifier.padding(5.dp))
                                Text(it.timestamp,
                                    modifier = Modifier.padding(5.dp))

                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun DecodeImage(substringAfter: String, title: String) {
        val base64 = android.util.Base64.decode(substringAfter, android.util.Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(base64,0,base64.size)
        Image(bitmap = bitmap.asImageBitmap(), contentDescription = title, modifier = Modifier.size(50.dp).padding(10.dp))
    }
}



