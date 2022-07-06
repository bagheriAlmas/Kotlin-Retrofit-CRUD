package ir.almasapps.kotlinretrofitcrud

import android.app.ProgressDialog
import android.app.Service
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.almasapps.kotlinretrofitcrud.API.Client
import ir.almasapps.kotlinretrofitcrud.Model.Note
import ir.almasapps.kotlinretrofitcrud.Model.ResponseResult
import kotlinx.android.synthetic.main.activity_update.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog
    private lateinit var strId: String
    private lateinit var strColor:String

    lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        progressDialog = ProgressDialog(this)
        progressDialog = ProgressDialog(this)


        var note = Note(
            intent.getStringExtra("itemId").toString(),
            intent.getStringExtra("itemTitle").toString(),
            intent.getStringExtra("itemNote").toString(),
            intent.getStringExtra("itemColor").toString(),
            "")


        strColor = intent.getStringExtra("itemColor").toString()

        strId = note.getId()
        update_txtTitle.setText(note.getTitle())
        update_txtNote.setText(note.getNote())
        update_txtTitle.setBackgroundColor(Color.parseColor(strColor))
        update_txtNote.setBackgroundColor(Color.parseColor(strColor))


        update_btnUpdate.setOnClickListener {
            updateUser(strId, update_txtTitle.text.toString(), update_txtNote.text.toString(), strColor)
            progressDialog.setMessage("Updating ... \n Please wait ...")
            progressDialog.show()
        }

        update_btnDelete.setOnClickListener {
            deleteUser(note.getId())
            progressDialog.setMessage("Deleting ... \n Please wait ...")
            progressDialog.show()
        }

    }


    private fun updateUser(id: String, title: String, note: String, color: String) {
        var apiInterface = Client().getApiClient()!!
        val call: Call<ResponseResult> = apiInterface.updateUser(id, title, note, color)
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
                Log.e("zzzzzzzzzz", t.toString())
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun deleteUser(id: String) {
        var apiInterface = Client().getApiClient()!!
        val call: Call<ResponseResult> = apiInterface.deletetUser(id)
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
                Log.e("zzzzzzzzzz", t.toString())
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getColor(view: View) {
        val viewColor = view.background as ColorDrawable
        val colorId = viewColor.color
        strColor = String.format("#%06X", 0xFFFFFF and colorId)
        update_txtTitle.setBackgroundColor(colorId)
        update_txtNote.setBackgroundColor(colorId)
    }
}