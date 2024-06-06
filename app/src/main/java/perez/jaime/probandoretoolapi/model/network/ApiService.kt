package perez.jaime.probandoretoolapi.model.network

import perez.jaime.probandoretoolapi.model.EmpresasDetalleResponse
import perez.jaime.probandoretoolapi.model.EmpresasResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interfaz que va a tener las Apis que vamos a usar
 */
interface ApiService {
    //Listado de empresas
    @GET("empresas")
    fun obtenerEmpresas(): Call<List<EmpresasResponse>>

    //Detalle de empresas
    @GET("empresas/{id_empresa}") //https://retoolapi.dev/cluuwe/empresas/9
    fun detalleEmpresa(@Path("id_empresa") empresaCargar: Int): Call<EmpresasDetalleResponse>
}