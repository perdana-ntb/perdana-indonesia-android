package app.perdana.indonesia.ui.intro.welcome

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import app.perdana.indonesia.R
import app.perdana.indonesia.core.utils.Constants
import app.perdana.indonesia.core.utils.LocalStorage
import app.perdana.indonesia.ui.intro.auth.AuthIntroActivity
import kotlinx.android.synthetic.main.textview_primary_heading.*
import kotlinx.android.synthetic.main.welcome_intro_activity.*

/**
 * Created by ebysofyan on 12/2/19.
 */
class WelcomeIntroActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var imagesAdapter: WelcomeIntroImagesViewPagerAdapter
    private val introImages = mutableListOf<String>(
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSu7MJdDUdk-MsXIrLHFN_EgQNTgewJ0BuFUcAARqsgNTfbPNyt&s",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSu7MJdDUdk-MsXIrLHFN_EgQNTgewJ0BuFUcAARqsgNTfbPNyt&s",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSu7MJdDUdk-MsXIrLHFN_EgQNTgewJ0BuFUcAARqsgNTfbPNyt&s"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_intro_activity)

        initializeUi()
    }

    private fun initializeUi() {
        primary_heading_text.apply {
            text = getString(R.string.app_name)
//            gravity = Gravity.CENTER
        }

        intro_text_description_body.text = getString(R.string.lorem)

        initViewPager()
        initActionListener()
    }

    private fun initViewPager() {
        imagesAdapter =
            WelcomeIntroImagesViewPagerAdapter()
        imagesAdapter.setValues(introImages)
        intro_view_pager.adapter = imagesAdapter
        intro_dots_view.setViewPager(intro_view_pager)
    }

    private fun initActionListener() {

    }

    private fun onViewPagerNext() {
        if (introImages.size != (intro_view_pager.currentItem + 1)) {
            intro_view_pager.setCurrentItem(intro_view_pager.currentItem + 1, true)
        } else {
            onViewPagerSkip()
        }
    }

    private fun onViewPagerSkip() {
        LocalStorage.put(this, Constants.IS_INTRO_DISPLAYED, "1")
        finish()

        startActivity(Intent(this, AuthIntroActivity::class.java))
    }

    override fun onClick(v: View?) {

    }
}