package io.ynov.rayziaxcorpproject

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.core.text.set
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment() {
    private var cal = Calendar.getInstance()
    private var btnValidate : Button? = null;
    private var txtDate : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onStart() {
        super.onStart()
        btnValidate = activity?.findViewById(R.id.btn_save_data)
        txtDate = activity?.findViewById(R.id.text_view_date)
        var txtPhone : EditText? = activity?.findViewById(R.id.editUserTextPhone);
        var txtName : EditText? = activity?.findViewById(R.id.editUserTextName);
        txtPhone?.setText(activity?.intent?.getStringExtra(DataObj.PHONE_USER));
        txtName?.setText(activity?.intent?.getStringExtra(DataObj.USER_NAME));
        txtDate!!.text = activity?.intent?.getStringExtra(DataObj.BIRTHDAY_USER)
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        txtDate!!.setOnClickListener {
            context?.let {
                DatePickerDialog(
                    it,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }

        btnValidate!!.setOnClickListener {
            context?.let {
                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra(DataObj.USER_NAME, txtName!!.text.toString())
                intent.putExtra(DataObj.PHONE_USER, txtPhone!!.text.toString())
                intent.putExtra(DataObj.BIRTHDAY_USER, txtDate!!.text)
                activity?.intent = intent
                (activity as MainActivity).replaceFragment(HomeFragment(),"Home")
            }
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)
        txtDate!!.text = sdf.format(cal.getTime())
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}