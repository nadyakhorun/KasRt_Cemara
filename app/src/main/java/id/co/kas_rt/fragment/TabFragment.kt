package id.co.kas_rt.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import id.co.kas_rt.R

class TabFragment : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

        tabLayout = findViewById(R.id.tab)
        viewPager = findViewById(R.id.viewpager)

        tabLayout.setupWithViewPager(viewPager)

        val vpAdapter = VPAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        vpAdapter.addFragment(TabFragment1(), "Pemasukan")
        vpAdapter.addFragment(TabFragment2(), "Pengeluaran")
        viewPager.adapter = vpAdapter
    }
}
