package com.wordpress.bhooshankadu.secondkotlin

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list.*
import java.io.File
import java.util.jar.Manifest

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        var status = ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)

        if(status == PackageManager.PERMISSION_GRANTED){

            readFile()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),123)
        }

    }
    fun readFile(){

        var path = "/storage/sdcard0/"

        var file = File(path)

        if(!file.exists())
        {
            path = "/storage/emulated/0/"
            file = File(path)
        }

        var values = file.list()

        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,values)

        list_lv.adapter = adapter

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            readFile()
        }
        else{
            Toast.makeText(this,"Please select allow ",Toast.LENGTH_LONG).show()
        }

    }
}



