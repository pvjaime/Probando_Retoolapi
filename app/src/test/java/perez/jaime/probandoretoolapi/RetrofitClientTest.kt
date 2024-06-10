package perez.jaime.probandoretoolapi

import org.junit.Test
import perez.jaime.probandoretoolapi.model.network.RetrofitClass
import retrofit2.Retrofit

/**
 * Test que prueba la instancia de Retrofit
 */
class RetrofitClientTest {
    //Test que verifica que la url de retrofit corresponde a la del servicio
    @Test
    fun testRetrofitInstance() {
        //Obtiene una intancia de nuestro retrofit
        val instance: Retrofit = RetrofitClass.retrofit
        //Verifica que la url configurada es la misma del servidor que estamos usando
        assert(instance.baseUrl().toUrl().toString() == "https://retoolapi.dev/cluuwe/")
    }
}