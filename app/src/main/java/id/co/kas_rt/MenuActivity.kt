package id.co.kas_rt

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import id.co.kas_rt.fragment.TabFragment


class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)

        // Find the CardView elements by their IDs
        val cvWarga: CardView = findViewById(R.id.cvWarga)
        val cvKas: CardView = findViewById(R.id.cvKas)
        val cvPengumuman: CardView = findViewById(R.id.cvPengumuman)
        val cvLaporan: CardView = findViewById(R.id.cvLaporan)

        // Menghilangkan status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        // Set click listeners for each CardView
        cvWarga.setOnClickListener {
            Toast.makeText(this, "Daftar Warga clicked", Toast.LENGTH_SHORT).show()
            // Add your code here to handle the click event, e.g., start a new Activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        cvKas.setOnClickListener {
            Toast.makeText(this, "Laporan KAS Warga clicked", Toast.LENGTH_SHORT).show()
            // Add your code here to handle the click event, e.g., start a new Activity
            val intent = Intent(this, TabFragment::class.java)
            startActivity(intent)
        }

        cvPengumuman.setOnClickListener {
            Toast.makeText(this, "Pengumuman Warga clicked", Toast.LENGTH_SHORT).show()
            // Add your code here to handle the click event, e.g., start a new Activity
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }

        cvLaporan.setOnClickListener {
            Toast.makeText(this, "Laporan Warga clicked", Toast.LENGTH_SHORT).show()
            // Add your code here to handle the click event, e.g., start a new Activity
            val intent = Intent(this, ReportActivity::class.java)
            startActivity(intent)
        }
    }
}
