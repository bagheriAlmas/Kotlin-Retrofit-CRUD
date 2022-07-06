package ir.almasapps.kotlinretrofitcrud.Model

import android.annotation.SuppressLint
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@SuppressLint("NotConstructor")

class Note {

    @SerializedName("id")
    @Expose
    private lateinit var id: String


    @SerializedName("title")
    @Expose
    private lateinit var title: String


    @SerializedName("note")
    @Expose
    private lateinit var note: String


    @SerializedName("color")
    @Expose
    private lateinit var color: String


    @SerializedName("date")
    @Expose
    private lateinit var date: String

    constructor(id: String, title: String, note: String, color: String, date: String) {
        this.id = id
        this.title = title
        this.note = note
        this.color = color
        this.date = date
    }


    fun getId(): String {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getNote(): String {
        return note
    }

    fun setNote(note: String) {
        this.note = note
    }

    fun getColor(): String {
        return color
    }

    fun setColor(color: String) {
        this.color = color
    }

    fun getDate(): String {
        return date
    }

    fun setDate(date: String) {
        this.date = date
    }


    fun Note(id: String, title: String, note: String, color: String, date: String) {
        this.id = id
        this.title = title
        this.note = note
        this.color = color
        this.date = date
    }
}