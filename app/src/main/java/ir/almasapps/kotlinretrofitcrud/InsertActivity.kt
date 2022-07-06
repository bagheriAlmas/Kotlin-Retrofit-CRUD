package ir.almasapps.kotlinretrofitcrud

import android.app.ProgressDialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.almasapps.kotlinretrofitcrud.API.Client
import ir.almasapps.kotlinretrofitcrud.API.Service
import ir.almasapps.kotlinretrofitcrud.Model.ResponseResult
import kotlinx.android.synthetic.main.activity_insert.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertActivity : AppCompatActivity() {
    var strColor = "#FFEBEE"
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        progressDialog = ProgressDialog(this)

        progressDialog = ProgressDialog(this)

        insert_btnInsert.setOnClickListener {

            insertUser(insert_txtTitle.text.toString(), insert_txtNote.text.toString(), strColor)
            progressDialog.setMessage("Inserting ... \n Please wait ...")
            progressDialog.show()
        }


    }
    private fun insertUser(username: String, password: String, email: String) {
        val apiInterface: Service = Client().getApiClient()!!
        val call: Call<ResponseResult> = apiInterface.insertUser(username, password, email)
        call.enqueue(object : Callback<ResponseResult?> {
            override fun onResponse(
                call: Call<ResponseResult?>,
                response: Response<ResponseResult?>
            ) {
                progressDialog.dismiss()
                finish()
            }

            override fun onFailure(call: Call<ResponseResult?>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getColor(view: View) {
        val viewColor = view.background as ColorDrawable
        val colorId = viewColor.color
        strColor = String.format("#%06X", 0xFFFFFF and colorId)
        insert_txtTitle.setBackgroundColor(colorId)
        insert_txtNote.setBackgroundColor(colorId)
    }
}