package mike.machine.platziconf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.*
import mike.machine.platziconf.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val tvFragmentHomeSubtituloVolley = view?.findViewById<TextView>(R.id.tvFragmentHomeSubtituloVolley)
        val tvFragmentHomeContenidoTexto = view?.findViewById<TextView>(R.id.tvFragmentHomeContenidoTexto)

        val queue = Volley.newRequestQueue(activity)
        val url:String = "https://www.ces.tech/About-CES.aspx"

        val stringRequest = StringRequest(Request.Method.HEAD, url, Response.Listener {response ->
            tvFragmentHomeSubtituloVolley?.text = "HEAD del http"
        }, Response.ErrorListener { response ->
            tvFragmentHomeSubtituloVolley?.text = "Error al hacer la petición HEAD" })

        val bodyRequest = StringRequest(Request.Method.GET, url, Response.Listener { response ->
            tvFragmentHomeContenidoTexto?.text = "${response.substring(0,500)}"

        }, Response.ErrorListener { response ->
            tvFragmentHomeContenidoTexto?.text = "Error al hacer la petición GET"
        })
        queue.add(stringRequest)
        queue.add(bodyRequest)

        // Inflate the layout for this fragment

/*
*         Glide.with(view.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(profile_image)

* */

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}