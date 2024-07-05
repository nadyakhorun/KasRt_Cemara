package id.co.kas_rt

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_DELAY: Long = 3000 // Waktu penundaan dalam milidetik (contoh: 3000 ms atau 3 detik)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        // Inisialisasi view
        val decorView: View = window.decorView
        // Hide the status bar
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        // Pindah ke MenuActivity setelah penundaan
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, MenuActivity::class.java)
            startActivity(intent)
            finish() // Optional: sebaiknya ditambahkan untuk menutup SplashActivity setelah membuka MenuActivity
        }, SPLASH_DELAY)
    }
}
