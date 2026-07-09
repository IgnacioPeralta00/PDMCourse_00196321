package com.pdm.rankeuca

import android.app.Application
import com.pdm.rankeuca.data.AppProvider

class RankeUcaApplication : Application() {
    val appProvider by lazy { AppProvider(this) }
}