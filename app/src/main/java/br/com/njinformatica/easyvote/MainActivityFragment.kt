package br.com.njinformatica.easyvote

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)

        login_button.setOnClickListener {
            val toast = Toast.makeText(
                    activity, "Custom Toast From Fragment", Toast.LENGTH_LONG
            )
            //toast.setGravity(Gravity.RELATIVE_LAYOUT_DIRECTION, 0, 0)
            toast.show()
        }

        clear_button.setOnClickListener {
            val toast = Toast.makeText(
                    activity, "Custom Toast From Fragment", Toast.LENGTH_LONG
            )
            //toast.setGravity(Gravity.RELATIVE_LAYOUT_DIRECTION, 0, 0)
            toast.show()
        }
    }
}
