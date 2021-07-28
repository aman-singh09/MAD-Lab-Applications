package com.example.texttospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.EditText
import com.google.android.material.button.MaterialButton
import java.util.*

class MainActivity : AppCompatActivity(),TextToSpeech.OnInitListener {

    private var textToSpeech: TextToSpeech?= null
    private lateinit var textToSpeechButton : MaterialButton
    private lateinit var textInput : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textToSpeechButton=findViewById(R.id.speak)
        textInput=findViewById(R.id.usertext)
        textToSpeechButton!!.isEnabled= false
        textToSpeech=TextToSpeech(this,this)
        textToSpeechButton!!.setOnClickListener { convertTOSpeech() }
    }

     override fun onInit(status:Int){
        if(status==TextToSpeech.SUCCESS){
            val result = textToSpeech!!.setLanguage(Locale.US)

            if (result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","Language specified NOT SUPPORTED")
            }
            else{
                textToSpeechButton!!.isEnabled=true
            }

        }
        else{
            Log.e("TTS","Initialisation Failed")
        }
    }

    private fun convertTOSpeech(){
        val text=textInput!!.text.toString()
        textToSpeech!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    public override fun onDestroy() {
        if(textToSpeech !=null){
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()

        }
        super.onDestroy()
    }

}