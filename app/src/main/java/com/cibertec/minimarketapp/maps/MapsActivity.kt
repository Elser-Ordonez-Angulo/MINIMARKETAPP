package com.cibertec.minimarketapp.maps

import android.location.Geocoder
import android.os.Bundle
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.minimarketapp.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Locale

class MapsActivity: AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var centerMarker: ImageView
    private var isMapMoving = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        //se refiere a la flecha que regresa a la pantalla anterior
        val actionBar=supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Ubicanos"

        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

        centerMarker = findViewById(R.id.imgMarcador)
    }

    //Aca podemos codificar lo que queremos que haga el maps
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(this, R.raw.map_stily)
        )

        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isRotateGesturesEnabled = false

        val marketPlace = LatLng(-8.1049433, -79.009523)
        map.addMarker(MarkerOptions()
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mapa))
            .position(marketPlace)
            .title("Ciudad de Trujillo")
        )
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(marketPlace, 18f), 8000, null
        )
        map.setOnMapClickListener {
            map.clear()

            map.addMarker(MarkerOptions().position(it).title("Lugar"))
            // al hacer clic sobre otro lado la camara se moverá
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(it, 18f), 4000, null
            )
        }


        map.setOnCameraIdleListener{
            if (isMapMoving){
                isMapMoving = false
                animateMarkerDown()
                val centerLaLong = map.cameraPosition.target

                val latitude = centerLaLong.latitude
                val longitude = centerLaLong.longitude

                getStringName(latitude, longitude)
            }
        }

        map.setOnCameraMoveStartedListener {
            if(!isMapMoving){
                isMapMoving = true
                animateMarkerUp()
            }
        }
    }


    private  fun  getStringName(lat: Double, lon:Double){
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val address = geocoder.getFromLocation(lat, lon, 1)
            if (address != null && address.isNotEmpty()){
                val addressValue = address[0]
                val streetName  = addressValue.getAddressLine(0)
                Toast.makeText(this, streetName, Toast.LENGTH_SHORT).show()
            }

        }catch (e: Exception){
            e.printStackTrace()
        }
    }


    private fun animateMarkerUp(){
        val animation = TranslateAnimation(0f, 0f, 0f, -50f).apply {
            duration = 300
            fillAfter = true
        }
        centerMarker.startAnimation(animation)
    }
    private fun animateMarkerDown(){
        val animation = TranslateAnimation(0f, 0f, -50f, 0f).apply {
            duration = 300
            fillAfter = true
        }
        centerMarker.startAnimation(animation)
    }
}