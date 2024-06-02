package com.borys.moviles_examen

import android.app.Application
import com.borys.moviles_examen.data.AppContainer
import com.borys.moviles_examen.data.AppDataContainer

class EventoApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
