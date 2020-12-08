package mike.machine.platziconf.view.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import mike.machine.platziconf.R
import mike.machine.platziconf.model.Ubication

class UbicationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val ubication = Ubication() //Para controlar la ubicación
        val zoom = 16f //Para dar un zoom
        val centerMap = LatLng(ubication.latitude, ubication.longitude) //Se centra

        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))

        val centerMark = LatLng(ubication.latitude,ubication.longitude)
        val markerOptions = MarkerOptions()
        markerOptions.position(centerMark)
        markerOptions.title("Platzi Conf 2020")

        //Para agregar el ícono
        val bitmapDraw = context?.applicationContext?.let { ContextCompat.getDrawable(it,R.drawable.logo_platzi) } as BitmapDrawable
        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw.bitmap,150,150,false) //Se le da un tamaño

        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker)) //se agrega el ícono
        googleMap?.addMarker(markerOptions) //Aquí se coloca el mapa

        googleMap?.setOnMarkerClickListener(this) //Se agrega un escuchador al ícono

        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context,R.raw.custom_map))

    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        findNavController().navigate(R.id.ubicationDetailFragmentDialog)
        return true;

    }

}