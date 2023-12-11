package com.example.test.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.test.R


// FragmentSetting.kt
class FragmentSetting : Fragment(), SettingContract.View {

    private lateinit var toolbar: Toolbar
    private lateinit var presenter: SettingContract.Presenter
    private lateinit var backButton: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        backButton = view.findViewById(R.id.backButton)

        // Initialize the presenter
        presenter = SettingPresenter(this)

        // Check if the activity has an ActionBar
        if ((activity as AppCompatActivity).supportActionBar == null) {
            // Set the toolbar as the action bar for this fragment only if the activity does not have an ActionBar
            (activity as AppCompatActivity).setSupportActionBar(toolbar)

            // Set the title for the toolbar
            (activity as AppCompatActivity).supportActionBar?.title = "Settings"
        }

        // Handle the back button click
        backButton.setOnClickListener {
            // Notify presenter when the back button is clicked
            presenter.onBackButtonClicked()
        }

        return view
    }

    override fun switchFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.frame_container, fragment)
            ?.commit()
    }
}
