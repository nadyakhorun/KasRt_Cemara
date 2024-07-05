package id.co.kas_rt.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.co.kas_rt.PemanfaatanAdpater
import id.co.kas_rt.R
import id.co.kas_rt.model.DataItem
import id.co.kas_rt.model.ResponseUser
import id.co.kas_rt.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.Locale

class TabFragment2 : Fragment() {
    private lateinit var adapter: PemanfaatanAdpater
    private lateinit var cvTotal: View
    private lateinit var tvTotalPengeluaran: TextView
    private lateinit var rvListData: RecyclerView
    private lateinit var tvNotFound: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pengeluaran, container, false)

        // Initialize views
        cvTotal = view.findViewById(R.id.cvTotal)
        tvTotalPengeluaran = view.findViewById(R.id.tvTotalPengeluaran)
        rvListData = view.findViewById(R.id.rvListData)
        tvNotFound = view.findViewById(R.id.tvNotFound)

        adapter = PemanfaatanAdpater(mutableListOf())

        fetchDataFromApi()

        return view
    }

    private fun fetchDataFromApi() {
        val apiService = ApiConfig.getApiService()
        val call = apiService.getPemanfaatan()
        call.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful) {
                    val dataArray = response.body()?.data
                    if (dataArray != null && dataArray.isNotEmpty()) {
                        // Data ditemukan, tampilkan RecyclerView
                        showRecyclerView(dataArray)
                        // Hitung dan tampilkan total pengeluaran
                        calculateAndDisplayTotalPengeluaran(dataArray)
                    } else {
                        // Data tidak ditemukan, tampilkan pesan "Ups, belum ada pengeluaran"
                        showNotFoundMessage()
                    }
                } else {
                    // Gagal mengambil data, tampilkan pesan error
                    showError("Gagal mengambil data")
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                // Gagal koneksi ke API, tampilkan pesan error
                showError("Koneksi API gagal: ${t.message}")
            }
        })
    }

    private fun calculateAndDisplayTotalPengeluaran(dataArray: List<DataItem>) {
        var totalPengeluaran = 0
        for (dataItem in dataArray) {
            totalPengeluaran += dataItem.pengeluaran_iuran_warga
        }
        val numberFormat = NumberFormat.getNumberInstance(Locale.US)
        tvTotalPengeluaran.text = "Rp ${numberFormat.format(totalPengeluaran)}"
        cvTotal.visibility = View.VISIBLE
    }

    private fun showRecyclerView(dataArray: List<DataItem>) {
        rvListData.visibility = View.VISIBLE
        tvNotFound.visibility = View.GONE

        // Setup RecyclerView
        val layoutManager = LinearLayoutManager(context)
        rvListData.layoutManager = layoutManager
        adapter = PemanfaatanAdpater(dataArray.toMutableList()) // Ubah menjadi MutableList<DataItem>
        rvListData.adapter = adapter
    }


    private fun showNotFoundMessage() {
        rvListData.visibility = View.GONE
        cvTotal.visibility = View.GONE
        tvNotFound.visibility = View.VISIBLE
    }

    private fun showError(message: String) {
        tvNotFound.text = message
        tvNotFound.visibility = View.VISIBLE
    }
}
