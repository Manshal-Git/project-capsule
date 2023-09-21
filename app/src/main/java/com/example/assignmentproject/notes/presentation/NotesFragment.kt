package com.example.assignmentproject.notes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.assignmentproject.CapsuleActivity
import com.example.assignmentproject.NextButtonFragment
import com.example.assignmentproject.R
import com.example.assignmentproject.data.Response
import com.example.assignmentproject.databinding.FragmentNotesBinding
import com.example.assignmentproject.notes.domain.Note
import com.example.assignmentproject.utils.extension.changeFragment
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
        viewModel.notesUIState.observe(viewLifecycleOwner){
            when(it){
                is Response.Error -> {
                }
                is Response.Loading -> {
                }
                is Response.Success -> {
                    setUpNotesContent(it.data!!)
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