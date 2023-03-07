package io.ynov.rayziaxcorpproject

import android.os.Bundle
import android.view.ActionProvider
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PreferencesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PreferencesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val language_spinner : Spinner? = activity?.findViewById(R.id.languages_spinner)
        this.context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.languages_spinner,
                android.R.layout.simple_spinner_item
                ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                language_spinner?.adapter = adapter
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val swDarkMode: Switch? = view?.findViewById(R.id.dark_mode_switch);
        val languageSpinner : Spinner? = view?.findViewById(R.id.languages_spinner)
        val btn : Button? = view?.findViewById(R.id.btn_validate_change_config)
        swDarkMode?.isChecked = App.darkMode;
        swDarkMode?.setOnCheckedChangeListener{_,isChecked ->
            if(swDarkMode.isChecked){
                (activity?.application as App).setDarkMode(true);
            }else {
                (activity?.application as App).setDarkMode(false);
            }
        }
        languageSpinner?.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View?, pos: Int, id: Long) {
                val lang : String = parent.getItemAtPosition(pos).toString()

                val languages :  Array<out String> = resources.getStringArray(R.array.languages_spinner);
                when(lang){
                    languages[0]-> {
                        (activity?.application as App).setLocaleLang(Locale("en"))
                    }
                    languages[1]-> {
                        (activity?.application as App).setLocaleLang(Locale("fr"))
                    }
                    else ->{
                        Toast.makeText(context,"aucun langage reconnu",Toast.LENGTH_SHORT).show()
                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code à exécuter lorsqu'aucun item n'est sélectionné
            }
        }

        btn?.setOnClickListener{view
            (activity?.application as App).apply()
            activity?.recreate()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_preferences, container, false)
        val languageSpinner : Spinner? = view.findViewById(R.id.languages_spinner)

        this.context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.languages_spinner,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                languageSpinner?.adapter = adapter
            }
        }
        return view;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment preferencesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PreferencesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}