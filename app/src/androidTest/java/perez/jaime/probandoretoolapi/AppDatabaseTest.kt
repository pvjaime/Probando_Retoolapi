package perez.jaime.probandoretoolapi

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import perez.jaime.probandoretoolapi.model.db.AppDatabase
import perez.jaime.probandoretoolapi.model.db.EmpresaEntidad

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var miBaseDeDatos: AppDatabase

    @Before
    fun setup() {
        // Crear una instancia de la base de datos de prueba
        miBaseDeDatos = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun tearDown() {
        // Cerrar la base de datos después de cada prueba
        miBaseDeDatos.close()
    }

    @Test
    fun testInsertarDataRecuperar() {
        // Insertar datos en la base de datos
        val datos = ArrayList<EmpresaEntidad>()
        datos.add(
            EmpresaEntidad(
                id_api = 1,
                nombre_empresa = "Morgan Stanley",
                url_logo = "https://logo.clearbit.com/comcast.net",
                ubicacion_empresa = "Lodi, California, United States",
                fecha_fundacion = "Jul 4, 2024 12:47 PM"
            )
        )
        miBaseDeDatos.empresaDao().insertarData(datos)
        // Recuperar el dato insertado
        val datoRecuperado =
            miBaseDeDatos.empresaDao().obeterEmpresasDB()
        // Verificar que el dato recuperado sea el mismo que elinsertado
        assertEquals("Morgan Stanley", datoRecuperado[0].nombre_empresa)
    }

}