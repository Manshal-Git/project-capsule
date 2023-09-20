package com.example.assignmentproject.video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import com.example.assignmentproject.CapsuleActivity
import com.example.assignmentproject.NextButtonFragment
import com.example.assignmentproject.R
import com.example.assignmentproject.databinding.FragmentVideoBinding
import com.example.assignmentproject.utils.extension.changeFragment

/**
 * A simple [Fragment] subclass.
 * Use the [VideoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VideoFragment : Fragment() {

    private lateinit var binding: FragmentVideoBinding
    private lateinit var parentActivity : CapsuleActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentActivity = requireActivity() as CapsuleActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setUpVideoView("https://youtu.be/oP2sbq9G5PI?si=MLQKLLuEs2upW2_O")
        val videoPath = "android.resource://" + requireActivity().packageName + "/" + R.raw.splash_video
        setUpVideoView(videoPath)
        setUpNextButtonLayout()
    }

    private fun setUpNextButtonLayout() {
        changeFragment(
            NextButtonFragment.newInstance(
                "Topic Notes",
                "Learn more from summarized notes",
                0,
                parentActivity::changeTab
            )
        )
    }


    private fun setUpVideoView(videoPath : String) {
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(binding.videoView)

        binding.videoView.apply {
            setMediaController(mediaController)
            setVideoPath(videoPath)
            start()
            setOnCompletionListener { mediaController.show() }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment VideoFragment.
         */

        @JvmStatic
        fun newInstance() =
            VideoFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}