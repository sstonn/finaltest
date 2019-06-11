package com.example.android.trackmysleepquality

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ShareCompat
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.android.trackmysleepquality.databinding.ActivityInfoBinding
import com.example.android.trackmysleepquality.model.NaturalDisaterDetailViewModel
import com.example.android.trackmysleepquality.model.NaturalDisaterViewModelFactory

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_info)
        val binding = DataBindingUtil.setContentView<ActivityInfoBinding>(this,R.layout.activity_info)

        val disaterUrl:String = intent.getStringExtra("naturalDisaterID")
        Log.i("TestBundleData","disaterUrl: $disaterUrl")

        val viewModelFactory = NaturalDisaterViewModelFactory(disaterUrl)

        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(NaturalDisaterDetailViewModel::class.java)

        viewModel.naturalDisater.observe(this, Observer {
            binding.disaterTitle.text = viewModel.naturalDisater.value?.fields?.title

            val imgUrl = viewModel.naturalDisater.value!!.fields!!.file[0]!!.preview!!.url
            val imgUri =imgUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
            Glide.with(binding.disaterImg.context)
                .load(imgUri)
                .into(binding.disaterImg)

            binding.disaterDescription.text = "Description : " + viewModel.naturalDisater.value!!.fields!!.body

            binding.disaterCountry.text = "Country : "+viewModel.naturalDisater.value!!.fields!!.country!!.name
        })

        viewModel.error.observe(this, Observer {
            if(it){
                binding.disaterTitle.text = ""
                binding.disaterImg.setImageResource(R.drawable.nodata)
                binding.disaterCountry.text = ""
                binding.disaterDescription.text = ""
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share,menu)

        if (getShareIntent().resolveActivity(this.packageManager) == null){
            menu?.findItem(R.id.share)?.setVisible(false)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)

    }

    private fun getShareIntent(): Intent {

        val disaterUrl:String = intent.getStringExtra("naturalDisaterID")
        val viewModelFactory = NaturalDisaterViewModelFactory(disaterUrl)
        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(NaturalDisaterDetailViewModel::class.java)

        val shareIntent = ShareCompat.IntentBuilder.from(this)
            .setText(viewModel.naturalDisater.value?.fields?.title).setType("text/plain").intent
        return shareIntent
    }

    private fun shareSuccess(){
        startActivity(getShareIntent())
    }


}
