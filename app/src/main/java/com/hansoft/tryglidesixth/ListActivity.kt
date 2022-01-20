package com.hansoft.tryglidesixth

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import butterknife.BindView
import butterknife.ButterKnife
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class ListActivity : AppCompatActivity() {
    @BindView(R.id.firstRecyclerView)
    var firstRecyclerView: RecyclerView? = null

    private val canadas = ArrayList<Canada>()
    var data: String? = null
    var title: String? = null
    var finishDownload = false
    val GET_DATA_SUCCESS = 1
    val NETWORK_ERROR = 2
    val SERVER_ERROR = 3


    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_new)
        ButterKnife.bind(this)
        bindview()


        loadJsonFile("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json")

    }

    fun bindview()
    {
        swipeRefreshLayout = findViewById(R.id.swipeRefresh)
        swipeRefreshLayout!!.setOnRefreshListener(OnRefreshListener {
            refresh()
            swipeRefreshLayout!!.setRefreshing(false)
        })
        firstRecyclerView = findViewById(R.id.recycler_view)

    }

    fun initList() {

     //   firstTextView!!.setBackgroundColor(Color.LTGRAY)
     //   firstTextView!!.setVisibility(View.GONE)
        firstRecyclerView!!.setHasFixedSize(true)
        firstRecyclerView!!.setLayoutManager(LinearLayoutManager(this))
        firstRecyclerView!!.setAdapter(
                ListAdapter(this,
                        canadas,
                        object : ListAdapter.OnItemClickListener {
                            override fun onItemClick(position: Int) {
                                //   var item = getData()[position];
                                //   firstTextView!!.setText(item)
                                //   firstTextView!!.setVisibility(View.VISIBLE)
                            }
                        })
        )
    }

    private fun getData(): ArrayList<String> {
        val data = ArrayList<String>()
        val temp = "item "
        for (i in 0..19) {
            data.add(temp + i.toString())
        }
        return data
    }

    private val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                GET_DATA_SUCCESS -> {
                    setTitle(title)
                    initList()

                }
                NETWORK_ERROR -> Toast.makeText(
                        this@ListActivity,
                        "Newwork is not available",
                        Toast.LENGTH_SHORT
                ).show()
                SERVER_ERROR -> Toast.makeText(
                        this@ListActivity,
                        "Server does not work",
                        Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun downloadJsonFile(url: String): String? {
        try {
            data = NetworkService.INSTANCE.getString(url)
            finishDownload = true
        } catch (e: Exception) {
            Log.d("aaa", "downloadJsonFile: Exception = " + e.localizedMessage)
        }
        return data
    }

    private fun loadJsonFile(url: String) {
        object : Thread() {
            override fun run() {
                val result = downloadJsonFile(url)
                if (result == null) {
                    handler.sendEmptyMessage(SERVER_ERROR)
                } else {
                    try {
                        canadas.clear()
                        val root = JSONObject(result)
                        Log.d("aaa", "title=" + root.getString("title"))
                        Log.d("aaa", "run: result = $result")
                        // this.setTitle(root.getString("title"));
                        title = root.getString("title")
                        val array = root.getJSONArray("rows")
                        for (i in 0 until array.length()) {
                            val lan = array.getJSONObject(i)
                            val canada = Canada()

                            canada.title = lan.getString("title")
                            canada.description = lan.getString("description")
                            canada.imageHref = lan.getString("imageHref")
                            Log.d("aaa", "run: title = " + lan.getString("title"))
                            canadas.add(canada)
                        }
                        Log.d("aaa", "run: aaabbb")
                        handler.sendEmptyMessage(GET_DATA_SUCCESS)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
        }.start()
    }


    private fun refresh() {
        loadJsonFile("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json")
    }

}