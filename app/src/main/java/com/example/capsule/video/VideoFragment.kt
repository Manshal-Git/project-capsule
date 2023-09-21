package com.example.capsule.video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import com.example.capsule.CapsuleActivity
import com.example.capsule.NextButtonFragment
import com.example.capsule.R
import com.example.capsule.databinding.FragmentVideoBinding
import com.example.capsule.utils.extension.changeFragment

/**
 * A simple [Fragment] subclass.
 * Use the [VideoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VideoFragment : Fragment() {

    private lateinit var binding: FragmentVideoBinding
    private lateinit var parentActivity: CapsuleActivity

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
        val videoPath =
            "android.resource://" + requireActivity().packageName + "/" + R.raw.imagine_dragons_green_screen
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
            ),
            containerId = R.id.gotoNotesFrame
        )
    }


    private fun setUpVideoView(videoPath: String) {
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
        @JvmStatic
        fun newInstance() =
            VideoFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}