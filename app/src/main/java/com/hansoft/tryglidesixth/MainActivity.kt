package com.hansoft.tryglidesixth

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: MyAdapter? = null
    private var addButton: Button? = null
    private var deleteButton: Button? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var resultTextView : TextView? = null
    private var testButton : Button? = null
    private val REQUEST_PERMISSION = 250
    val GET_DATA_SUCCESS = 1
    val NETWORK_ERROR = 2
    val SERVER_ERROR = 3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initView()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PERMISSION)
            return
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED || grantResults[1] == PackageManager.PERMISSION_DENIED || grantResults[2] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "Sorry!!!, you can't use this app without granting permission", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initData() {
        mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mAdapter = MyAdapter()
        mAdapter!!.initData(getData())
    }

    private fun initView() {


        addButton = findViewById(R.id.addButton)
        addButton!!.setOnClickListener(View.OnClickListener {
            mAdapter!!.addNewItem()
            mLayoutManager!!.scrollToPosition(0)
        })

        deleteButton = findViewById(R.id.deleteButton)
        deleteButton!!.setOnClickListener(View.OnClickListener {
            mAdapter!!.deleteItem()
            mLayoutManager!!.scrollToPosition(0)
        })

        resultTextView = findViewById(R.id.resultTextView);
        mRecyclerView = findViewById<View>(R.id.myRecyclerView) as RecyclerView
        mRecyclerView!!.setLayoutManager(mLayoutManager)
        mRecyclerView!!.setAdapter(mAdapter)

        var myDividerItemDecoration = MyDividerItemDecoration()
        myDividerItemDecoration.setDividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        mRecyclerView!!.addItemDecoration(myDividerItemDecoration)

        testButton = findViewById(R.id.testButton);
        testButton!!.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, IndexActivity::class.java))
        })


        val obj = object : MyAdapter.OnItemClickListener {
            override  fun onItemClick(view: View?, position: Int) {
                var item = getData()[position];
                resultTextView!!.setText(item)
                Toast.makeText(this@MainActivity, "click $position item", Toast.LENGTH_SHORT).show()
            }

            override fun onItemLongClick(view: View?, position: Int) {
                var item = getData()[position];
                resultTextView!!.setText(item)
                Toast.makeText(this@MainActivity, "long click $position item", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        mAdapter!!.setOnItemClickListener(obj)
    }

    private fun getData(): ArrayList<String> {
        val data = ArrayList<String>()
        val temp = "item "
        for (i in 0..19) {
            data.add(temp + i.toString())
        }
        return data
    }

}