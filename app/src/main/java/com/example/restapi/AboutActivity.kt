package com.example.restapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Build
import android.view.MenuItem
import android.content.Context
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.widget.TextView
import java.net.NetworkInterface
import java.net.Inet4Address
import java.net.InetAddress
import java.util.*

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Povolit tlačítko zpět

        val textViewMacAddress: TextView = findViewById(R.id.textViewMacAddress)
        val textViewIpAddress: TextView = findViewById(R.id.textViewIpAddress)
        val textViewDeviceName: TextView = findViewById(R.id.textViewDeviceName)

        val macAddress = getMacAddress()
        val ipAddress = getIpAddress()
        val deviceName = getDeviceName()

        textViewMacAddress.text = "MAC Address: $macAddress"
        textViewIpAddress.text = "IP Address: $ipAddress"
        textViewDeviceName.text = "Device Name: $deviceName"

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // Zavolá metodu, která simuluje stisk tlačítka zpět
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getMacAddress(): String {
        try {
            val networkInterface: NetworkInterface? = NetworkInterface.getByName("wlan0")

            if (networkInterface != null) {
                val macAddressBytes: ByteArray? = networkInterface.hardwareAddress
                if (macAddressBytes != null) {
                    val macAddress = StringBuilder()
                    for (b in macAddressBytes) {
                        macAddress.append(String.format("%02X:", b))
                    }
                    if (macAddress.isNotEmpty()) {
                        macAddress.deleteCharAt(macAddress.length - 1)
                        return macAddress.toString()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return "N/A"
    }

    private fun getIpAddress(): String {
        var ipAddress = "N/A"
        try {
            val networkInterfaces: Enumeration<NetworkInterface> = NetworkInterface.getNetworkInterfaces()

            while (networkInterfaces.hasMoreElements()) {
                val networkInterface: NetworkInterface = networkInterfaces.nextElement()

                val inetAddresses: Enumeration<InetAddress> = networkInterface.inetAddresses

                while (inetAddresses.hasMoreElements()) {
                    val inetAddress: InetAddress = inetAddresses.nextElement()

                    // Příklad: Vracíte první nalezenou IP adresu
                    if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                        ipAddress = inetAddress.hostAddress
                        break
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ipAddress
    }

    private fun getDeviceName(): String {
        return Build.MODEL
    }
}