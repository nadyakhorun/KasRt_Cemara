package id.co.kas_rt.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import id.co.kas_rt.R
import id.co.kas_rt.model.DataItem
import id.co.kas_rt.model.ResponseUser
import id.co.kas_rt.network.ApiConfig
import id.co.kas_rt.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.Locale

class TabFragment1 : Fragment() {

    private lateinit var tvTotalPemasukan: TextView
    private lateinit var tvTotalKeseluruhan: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pemasukan, container, false)

        // Initialize TextViews
        tvTotalPemasukan = view.findViewById(R.id.tvTotalPemasukan)
        tvTotalKeseluruhan = view.findViewById(R.id.tvTotalKeseluruhan)

        // Fetch data from API
        fetchDataFromApi()

        return view
    }

    private fun fetchDataFromApi() {
        val apiService = ApiConfig.getApiService()
        val call = apiService.getUsers()
        call.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful && response.body() != null) {
                    val dataItems = response.body()?.data
                    if (dataItems != null) {
                        calculateAndDisplayTotals(dataItems)
                    } else {
                        showError("Data tidak ditemukan")
                    }
                } else {
                    showError("Gagal mengambil data")
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                showError("Koneksi API gagal: ${t.message}")
            }
        })
    }

    private fun calculateAndDisplayTotals(dataItems: List<DataItem>) {
        var totalPemasukan = 0
        var totalPengeluaran = 0
        for (dataItem in dataItems) {
            totalPemasukan += dataItem.total_iuran_individu
            totalPengeluaran += dataItem.pengeluaran_iuran_warga
        }
        val totalKeseluruhan = totalPemasukan - totalPengeluaran
        val numberFormat = NumberFormat.getNumberInstance(Locale.US)
        // Update UI
        tvTotalPemasukan.text = "Rp ${numberFormat.format(totalPemasukan)}"
        tvTotalKeseluruhan.text = "Rp ${numberFormat.format(totalKeseluruhan)}"
    }

    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
