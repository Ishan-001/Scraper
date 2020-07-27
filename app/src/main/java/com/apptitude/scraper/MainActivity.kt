package com.apptitude.scraper

import android.os.AsyncTask
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AsyncTaskExample(this).execute()
    }

    class AsyncTaskExample(private var activity: MainActivity?) : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg p0: String?): String {
            val doc=Jsoup.connect("https://www.google.com/").get()
            val e = doc.head().select("link[href~=.*\\.ico]").first()
            return doc.head().select("meta[content]").first().toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            activity?.updateUI(result)
        }
    }

    fun updateUI(text:String?){
        textView.text=text
        textView.movementMethod = ScrollingMovementMethod()
    }
}
