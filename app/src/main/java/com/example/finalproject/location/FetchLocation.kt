package com.example.finalproject.location

import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.finalproject.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient

fun fetchLocation(context: MainActivity, fusedLocationProviderClient: FusedLocationProviderClient) {
    val task = fusedLocationProviderClient.lastLocation

    if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(context, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
        return
    }

    task.addOnSuccessListener {
        if (it != null) {
            Log.d("Success", "${it.latitude}, ${it.longitude}")

            context.viewModel.setLocation(Location(it.latitude, it.longitude))
            context.viewModel.setIsLocationLoad(true)
        }
        else
        {
            Log.d("Failure", "FetchLocation failed")
        }
    }

    task.addOnFailureListener {
        Log.d("Failure", "FetchLocation failed")
    }
}