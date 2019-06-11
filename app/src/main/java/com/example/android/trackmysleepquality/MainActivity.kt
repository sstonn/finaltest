

package com.example.android.trackmysleepquality

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.ui.NavigationUI
import com.example.android.trackmysleepquality.adapter.NaturalDisaterAdapter
import com.example.android.trackmysleepquality.adapter.NaturalDisaterListener
import com.example.android.trackmysleepquality.databinding.ActivityMainBinding
import com.example.android.trackmysleepquality.model.NaturalDisaterViewModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        val disaterViewModel = ViewModelProviders.of(this,NaturalDisaterViewModel.Factory(this.application,this)).get(NaturalDisaterViewModel::class.java)

        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(this,drawer,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        binding.naturalDisaterViewModel = disaterViewModel
        binding.setLifecycleOwner(this)

        val adapter = NaturalDisaterAdapter(this,this,NaturalDisaterListener {
            intent = Intent(this@MainActivity,InfoActivity::class.java)

            val bundle = Bundle()
            bundle.putString("naturalDisaterID",it)
            intent.putExtras(bundle)

            startActivity(intent)
        })

        binding.disaterList.adapter = adapter

        disaterViewModel.disaters.observe(this, Observer {
            adapter.submitList(it)
        })

        //adapter.submitList(disaterViewModel.disaters)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val disaterViewModel = ViewModelProviders.of(this).get(NaturalDisaterViewModel::class.java)

        when(item?.itemId){
//            R.id.flood -> disaterViewModel.initData("flood")
//            R.id.earthquake -> disaterViewModel.initData("earthquake")
//            R.id.hurricane -> disaterViewModel.initData("hurricane")
//            R.id.volcano -> disaterViewModel.initData("volcano")
//            R.id.thunderstorm -> disaterViewModel.initData("thunderstorm")
            R.id.flood -> disaterViewModel.refreshDisaterData("qthang.97khtn@gmail.com","flood","latest")
            R.id.earthquake -> disaterViewModel.refreshDisaterData("qthang.97khtn@gmail.com","earthquake","latest")
            R.id.hurricane -> disaterViewModel.refreshDisaterData("qthang.97khtn@gmail.com","hurricane","latest")
            R.id.volcano -> disaterViewModel.refreshDisaterData("qthang.97khtn@gmail.com","volcano","latest")
            R.id.thunderstorm -> disaterViewModel.refreshDisaterData("qthang.97khtn@gmail.com","thunderstorm","latest")
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var intent:Intent
        when(item.itemId){
            R.id.aboutActivity -> {
                intent = Intent(this@MainActivity,AboutActivity::class.java)
                startActivity(intent)
            }
            R.id.manualActivity -> {
                intent = Intent(this@MainActivity,ManualActivity::class.java)
                startActivity(intent)
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
}
