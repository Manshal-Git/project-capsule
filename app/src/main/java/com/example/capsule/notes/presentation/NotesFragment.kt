package com.example.capsule.notes.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.capsule.CapsuleActivity
import com.example.capsule.NextButtonFragment
import com.example.capsule.R
import com.example.capsule.data.Response
import com.example.capsule.databinding.FragmentNotesBinding
import com.example.capsule.notes.domain.Note
import com.example.capsule.utils.extension.changeFragment
import com.example.capsule.utils.extension.hide
import com.example.capsule.utils.extension.show
import org.koin.android.ext.android.get

/**
 * A simple [Fragment] subclass.
 * Use the [NotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotesFragment : Fragment() {

    lateinit var binding: FragmentNotesBinding
    private lateinit var parentActivity : CapsuleActivity

    private val viewModel : NotesViewModel = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentActivity = requireActivity() as CapsuleActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.notes.observe(viewLifecycleOwner){
            when(it){
                is Response.Error -> {
                    logAndShowError(it)
                }
                is Response.Loading -> {
                    binding.loadingLayout.show()
                }
                is Response.Success -> {
                    setUpNotesContent(it.data!!)
                    binding.loadingLayout.hide()
                }
            }
        }
        viewModel.getNote(R.string.lorem_ipsum)
        setUpNextButtonLayout()
    }

    private fun setUpNotesContent(data: List<Note>) {
        val note = data.firstOrNull()
        note?.let {
            binding.tvNoteTitle.text = it.title
            binding.tvNoteContent.text = it.content
        }
    }

    private fun setUpNextButtonLayout() {
        changeFragment(
            NextButtonFragment.newInstance(
                "Quiz Test",
                "Questions: 5",
                1,
                parentActivity::changeTab
            ),
            containerId = R.id.gotoQuizFrame
        )
    }

    private fun logAndShowError(e: Response.Error<List<Note>>) {
        Log.e("Questions",e.exception.toString())
        Toast.makeText(requireContext(), e.msg, Toast.LENGTH_SHORT).show()
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
            NotesFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}