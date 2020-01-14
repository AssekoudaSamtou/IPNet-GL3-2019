package com.samtou.ipnet_gl3_2019.Permissions

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import butterknife.ButterKnife
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.samtou.ipnet_gl3_2019.R

class GPSActivity : AppCompatActivity(), LocationListener {

    lateinit var latitudeEdt: EditText
    lateinit var longitudeEdt: EditText
    lateinit var altitudeEdt: EditText
    lateinit var precisionEdt: EditText

//    lateinit var mFusedLocationClient: FusedLocationProviderClient
    lateinit var locationManager: LocationManager

    val PERMISSION_REQUEST_LOCATION = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gps)
//        ButterKnife.bind(this)
        latitudeEdt = findViewById(R.id.latitudeEdt)
        longitudeEdt = findViewById(R.id.longitudeEdt)
        altitudeEdt = findViewById(R.id.altitudeEdt)
        precisionEdt = findViewById(R.id.precisionEdt)

//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @SuppressLint("SetTextI18n")
    fun getPosition(view: View) {
        if (
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, this)
        }else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), PERMISSION_REQUEST_LOCATION
            )
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_LOCATION) {
//            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                mFusedLocationClient.lastLocation.addOnSuccessListener(this) {
//                    latitudeEdt.setText("LATITUDE : " + it.latitude.toString())
//                    longitudeEdt.setText("LONGITUDE : " + it.longitude.toString())
//                    altitudeEdt.setText("ALTITUDE : " + it.altitude.toString())
//                    precisionEdt.setText("PRECISION : " + it.accuracy.toString())
//                }
//            }
        }
    }

    override fun onLocationChanged(location: Location?) {
        location?.longitude

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
