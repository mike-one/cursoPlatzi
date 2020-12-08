package mike.machine.platziconf.view.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_ubication_detail_dialog.*
import mike.machine.platziconf.R
import mike.machine.platziconf.model.Ubication

class UbicationDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.fullScreenDialogStyle)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ubication_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarUbication.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolbarUbication.setTitleTextColor(Color.WHITE)
        toolbarUbication.setNavigationOnClickListener { dismiss() } //Aquí cierra el dialogo al momento de hacer click!

        val ubication = Ubication()
        toolbarUbication.title = ubication.name
        tvDetailUbicationName.text = ubication.name
        tvDetailUbicationAddress.text = ubication.address
        tvDetailUbicationPhone.text = ubication.phone
        tvDetailUbicationWebSite.text = ubication.website

        //Se agrega la opción de realizar una llamada
        llPhonePlace.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data= Uri.parse("tel:${ubication.phone}")
            }
            startActivity(intent)

        }
        //Se agrega la función de abrir el webSite en el navegador
        llWebSite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(ubication.website)
            startActivity(intent)

        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }


}