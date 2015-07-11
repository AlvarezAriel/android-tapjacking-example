package ar.edu.unq.seguridad.tapjacking

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import kotlin.properties.Delegates

public class OverlayService : Service() {

    val overlay by Delegates.lazy { LinearLayout(this) }

    override fun onCreate() {
        super.onCreate()

        overlay.setBackgroundColor(TRANSLUCENT_RED)

        val params = WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT)

        (getSystemService(Context.WINDOW_SERVICE) as WindowManager).addView(overlay, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        (getSystemService(Context.WINDOW_SERVICE) as WindowManager).removeView(overlay)
    }


    val TRANSLUCENT_RED = -1996554240
    override fun onBind(i: Intent): IBinder? {return null}

}
