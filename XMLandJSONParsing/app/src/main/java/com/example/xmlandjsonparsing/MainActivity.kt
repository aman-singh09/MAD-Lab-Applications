package com.example.xmlandjsonparsing

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.json.JSONObject
import org.w3c.dom.Text
import java.io.IOException
import java.nio.charset.Charset
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {
    private lateinit var xmlbtn: Button
    private lateinit var jsonbtn: Button

    private lateinit var datatype: TextView
    private lateinit var cityname: TextView
    private lateinit var latitude: TextView
    private lateinit var longitude: TextView
    private lateinit var temperature: TextView
    private lateinit var humidity: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        xmlbtn= findViewById(R.id.parse_xml)
        jsonbtn=findViewById(R.id.parse_json)

        datatype= findViewById(R.id.datatype)
        cityname= findViewById(R.id.cityname)
        latitude= findViewById(R.id.latitude)
        longitude= findViewById(R.id.longitude)
        temperature= findViewById(R.id.temperature)
        humidity= findViewById(R.id.humidity)

        xmlbtn.setOnClickListener { parseXML() }
        jsonbtn.setOnClickListener { parseJSON() }

    }

    @SuppressLint("SetTextI18n")
    fun parseXML(){
        datatype.text ="Type of Data : XML Data"
        try {
            val ipstream = assets.open("myxml.xml")
            val builderfactory = DocumentBuilderFactory.newInstance().newDocumentBuilder()
            val doc = builderfactory.parse(ipstream)
            cityname.text =
                "City Name:" + doc.getElementsByTagName("City_name").item(0).firstChild.nodeValue
            latitude.text =
                "Latitude :" + doc.getElementsByTagName("Latitude").item(0).firstChild.nodeValue
            longitude.text =
                "Longitude :" + doc.getElementsByTagName("Longitude").item(0).firstChild.nodeValue
            temperature.text = "Temperature :" + doc.getElementsByTagName("Temperature")
                .item(0).firstChild.nodeValue
            humidity.text =
                "Humidity :" + doc.getElementsByTagName("Humidity").item(0).firstChild.nodeValue
        }
        catch (ex: IOException){

        }
    }

    @SuppressLint("SetTextI18n")
    fun parseJSON(){
        datatype.text= "Type Of Data : JSON Data"
        val obj = JSONObject(loadJSON())
        cityname.text= "City Name: " + obj.getString("City Name")
        latitude.text= "Latitude: " + obj.getString("Latitude")
        longitude.text= "Longitude: " + obj.getString("Longitude")
        temperature.text= "Temperature: "+ obj.getString("Temperature")
        humidity.text= "Humidity: "+ obj.getString("Humidity")
    }

    fun loadJSON(): String{
        val content : String?
        try{
            val inputStream= assets.open("myjson.json")
            val size= inputStream.available()
            val buffer= ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            content= String(buffer,charset)
        }
        catch(ex: IOException){
            return ""
        }
        return content
    }
}