package com.akrwt.innerwork

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import androidx.navigation.ui.NavigationUI.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val navController = Navigation.findNavController(this, R.id.fragment)
        setupWithNavController(navigation_view, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)

        navigation_view.menu.findItem(R.id.share).setOnMenuItemClickListener {
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Innerwork Solutions Pvt. Lmt.")
                putExtra(Intent.EXTRA_TEXT, "Hey! Download this app from google play store.")
                startActivity(Intent.createChooser(this, "Share"))
            }
            return@setOnMenuItemClickListener true

        }
        navigation_view.menu.findItem(R.id.log_out).setOnMenuItemClickListener {
            AlertDialog.Builder(this)
                .setTitle("Log Out")
                .setMessage("Do you really want to logout from Innerwork")
                .setPositiveButton("Yes"){_,_->
                    //TODO
                }
                .setNegativeButton("No"){dialog,_->
                    dialog.dismiss()
                }
                .create().show()
            return@setOnMenuItemClickListener true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return NavigationUI.navigateUp(navController, drawer_layout)
    }
}
