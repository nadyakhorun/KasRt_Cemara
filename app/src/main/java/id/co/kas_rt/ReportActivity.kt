package id.co.kas_rt

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.util.Calendar

class ReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        // Inisialisasi komponen yang ada di dalam layout
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val inputTanggal = findViewById<EditText>(R.id.inputTanggal)
        val fabSend = findViewById<ExtendedFloatingActionButton>(R.id.fabSend)

        // Lakukan pengaturan atau manipulasi terhadap komponen yang telah diinisialisasi
        tvTitle.text = "Form Laporan"

        // Atur aksi yang dijalankan saat EditText inputTanggal diklik
        inputTanggal.setOnClickListener { // Mendapatkan tanggal saat ini
            val calendar = Calendar.getInstance()
            val year = calendar[Calendar.YEAR]
            val month = calendar[Calendar.MONTH]
            val day = calendar[Calendar.DAY_OF_MONTH]

            // Membuat dan menampilkan DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                this@ReportActivity,
                { view, year, month, dayOfMonth -> // Memformat tanggal dan mengatur ke EditText
                    inputTanggal.setText(dayOfMonth.toString() + "/" + (month + 1) + "/" + year)
                }, year, month, day
            )
            datePickerDialog.show()
        }

        // Atur aksi yang dijalankan saat tombol fabSend ditekan
        fabSend.setOnClickListener {
            // Tambahkan kode untuk mengirim laporan ke spreadsheet di sini
        }
    }
}