package com.example.wallpaperapp
import android.app.WallpaperManager
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    //set ids of images to the array
    var myWallpaperList = arrayOf(R.drawable.first,R.drawable.second,R.drawable.third,
            R.drawable.fourth,R.drawable.fifth)
    private lateinit var changeWallpaper:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeWallpaper=findViewById(R.id.change_wallpaper)
        changeWallpaper.setOnClickListener { setWallpaper() }
        //onclick to call the wallpaper change function
    }
    fun setWallpaper() {
        Toast.makeText(this,"Setting wallpaper please wait...",
                Toast.LENGTH_SHORT).show()
        Handler().postDelayed(
                {
                    for(i in myWallpaperList)
                    {
                        val manager = WallpaperManager.getInstance(baseContext)
                        manager.setResource(i)
                        Thread.sleep(3000)
                    }
                }
        ,3000)
    }

}