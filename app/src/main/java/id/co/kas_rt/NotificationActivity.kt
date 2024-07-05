package id.co.kas_rt

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        // Mendapatkan data dari intent
        val title = "Kegiatan Arisan RT"
        val content = "Kegiatan Arisan RT ini akan dilaksanakan pada tanggal 28 Juni 2024" +
                " .Untuk lokasi kumpul adalah dirumah ibu Dhea."

        // Mengatur teks untuk TextView di layout notifikasi
        findViewById<TextView>(R.id.textViewTitle).text = title
        findViewById<TextView>(R.id.textViewContent).text = content
    }
}
