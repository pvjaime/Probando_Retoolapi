package perez.jaime.probandoretoolapi.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Clase para generar la intancia de Retrofit
 */
class RetrofitClass {

    //Se crea una intancia de retrofit que se configura
    companion object {
        //Cajita que se conecta a la API
        val retrofit = Retrofit.Builder()
            .baseUrl("https://retoolapi.dev/cluuwe/") //url base de la API
            .addConverterFactory(GsonConverterFactory.create())//Convertidor de Json a Objetos
            .build()
    }
}