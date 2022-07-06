package ir.almasapps.kotlinretrofitcrud

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ir.almasapps.kotlinretrofitcrud.API.Client
import ir.almasapps.kotlinretrofitcrud.API.Service
import ir.almasapps.kotlinretrofitcrud.Adapter.RecyclerViewAdapter
import ir.almasapps.kotlinretrofitcrud.Model.Note
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var progressDialog :ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressDialog = ProgressDialog(this)

        btnInsert.setOnClickListener {
            startActivity(Intent(baseContext, InsertActivity::class.java))
        }

        fetchInformation()
    }

    private fun fetchInformation() {
        val apiInterface: Service = Client().getApiClient()!!
        val call: Call<List<Note>> = apiInterface.getNote()
        call.enqueue(object : Callback<List<Note>> {

            override fun onResponse(call: Call<List<Note>>, response: Response<List<Note>>) {
                progressDialog.dismiss()
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                var noteList = response.body()
                recyclerView.adapter = noteList?.let { RecyclerViewAdapter(this@MainActivity, it) }
            }

            override fun onFailure(call: Call<List<Note>>, t: Throwable) {
                progressDialog.dismiss()
                Log.e("zzzzzzzzzz", t.toString())
                Toast.makeText(baseContext, t.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_refresh) {
            progressDialog.setMessage("Fetching Data ... \n Please wait ...")
            progressDialog.show()
            fetchInformation()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        progressDialog.setMessage("Fetching Data ... \n Please wait ...")
        progressDialog.show()
        fetchInformation()
    }
}

