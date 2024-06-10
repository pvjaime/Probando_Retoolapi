package perez.jaime.probandoretoolapi

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import perez.jaime.probandoretoolapi.model.network.ApiService
import perez.jaime.probandoretoolapi.model.network.RetrofitClass
import perez.jaime.probandoretoolapi.viewmodel.EmpresasViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Probando la Api de Retrofit
 */
class RetrofitApiTest {
    //Prueba que va a llamar al detalle de la empresa
    @Test
    fun testPlacesService() {
        //Get an instance of PlacesService by proiving the Retrofit instance
        val service = RetrofitClass.retrofit.create(ApiService::class.java)
        //Execute the API call
        val response = service.detalleEmpresa(1).execute()
        //Check for error body
        val errorBody = response.errorBody()
        assert(errorBody == null)
        //Check for success body
        val responseWrapper = response.body()
        assert(responseWrapper != null)
        assert(response.code() == 200)
    }


}