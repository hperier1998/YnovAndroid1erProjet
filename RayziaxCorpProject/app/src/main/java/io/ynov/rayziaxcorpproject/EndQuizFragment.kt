package io.ynov.rayziaxcorpproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import io.ynov.rayziaxcorpproject.databinding.FragmentEndQuizBinding
import io.ynov.rayziaxcorpproject.databinding.FragmentQuizBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EndQuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EndQuizFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var endQuizMainBinding: FragmentEndQuizBinding

    private var userName: String? = null
    private var correctAnswers: String? = null
    private var totalQuestions: String? = null
    private var score: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

            // Retrieve the informations from the DataObj
            userName = arguments?.getString(DataObj.USER_NAME)
            correctAnswers = arguments?.getString(DataObj.CORRECT_ANSWERS)
            totalQuestions = arguments?.getString(DataObj.TOTAL_QUESTIONS)
            score = resources.getString(R.string.score_message) + correctAnswers + "/" + totalQuestions
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        endQuizMainBinding = FragmentEndQuizBinding.inflate(inflater,container,false)

        // Retrieve the user name from arguments
        arguments?.let {
            userName = it.getString(DataObj.USER_NAME)
        }

        endQuizMainBinding.resultUsername.text = userName
        endQuizMainBinding.resultUserscore.text = score

        return endQuizMainBinding.root
    }

    override fun onStart() {
        super.onStart()
        view?.findViewById<Button>(R.id.finishBtn)?.setOnClickListener{
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.main_frameLayout,HomeFragment())
            fragmentTransaction?.commit()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EndQuizFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EndQuizFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}