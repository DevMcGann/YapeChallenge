package com.gsoft.yapechallenge.presentation.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.gsoft.yapechallenge.R
import com.gsoft.yapechallenge.data.model.LocationData
import com.gsoft.yapechallenge.databinding.FragmentMapBinding


class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback {

    private lateinit var binding: FragmentMapBinding

    private val zoom : Float =  15f
    private val time : Int = 4000
    private lateinit var mMap: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapBinding.bind(view)
        createMapFragment()
    }

    private fun createMapFragment() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

    }

    override fun onMapReady(myMap: GoogleMap) {
        mMap = myMap
        drawMarkers(arguments?.get("location") as LocationData)
        val data = arguments?.get("location") as LocationData
        val latLng = LatLng(data.lat.toDouble(), data.long.toDouble())
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(latLng, zoom),
            time,
            null
        )
    }

    private fun drawMarkers(location:LocationData) {
            mMap.addMarker(MarkerOptions()
                .position(LatLng(location.lat.toDouble(), location.long.toDouble()))
            )
    }

}