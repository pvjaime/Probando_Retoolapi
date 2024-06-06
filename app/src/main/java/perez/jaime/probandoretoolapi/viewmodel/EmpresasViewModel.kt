package perez.jaime.probandoretoolapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import perez.jaime.probandoretoolapi.model.EmpresasDetalleResponse
import perez.jaime.probandoretoolapi.model.EmpresasResponse
import perez.jaime.probandoretoolapi.model.network.ApiService
import perez.jaime.probandoretoolapi.model.network.RetrofitClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Viwe model que va a traer la lista de datos y el detalle de la empresa
 */
class EmpresasViewModel : ViewModel() {
    //Declaraciones de LiveData dependiendo de lo que nececite la vistas
    //LiveData para la pantalla de lista de empresas
    val listaEmpresas = MutableLiveData<List<EmpresasResponse>>()

    //LiveData para el detalle e una empresa
    val detalleEmpresa = MutableLiveData<EmpresasDetalleResponse>()

    //LiveData para los errores de la API
    val errores = MutableLiveData<String>()

    //funcion que va a ir a buscar la lista de empresas a la API
    fun listarEmpresas() {
        //Corrutina que va a ir a buscar la informacion
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //Llamar API
                val retroIntancia = RetrofitClass.retrofit.create(ApiService::class.java)
                //quien va a llamar a la API
                val llamadaApi = retroIntancia.obtenerEmpresas()
                //Llamar a la api para que nos devuelva los datos
                llamadaApi.enqueue(object : Callback<List<EmpresasResponse>> {
                    //Aca va la respuesta de la llamada de la API
                    //Metodo que se va a ejecutar si esta bien
                    override fun onResponse(
                        call: Call<List<EmpresasResponse>>, //la llamada a la API
                        response: Response<List<EmpresasResponse>> //La respuesta de la API
                    ) {
                        //vamos aver si la api me respondio bien
                        if (response.isSuccessful) {
                            val respuesta = response.body()
                            listaEmpresas.postValue(respuesta)
                        } else {
                            //Mostrar mensaje de error
                            errores.postValue(
                                "Error En la API - ${
                                    response.errorBody().toString()
                                }"
                            )
                        }
                    }

                    //Metodo que se va a ejecuar si hay algun error
                    override fun onFailure(call: Call<List<EmpresasResponse>>, t: Throwable) {
                        //Mostrar mensaje de error
                        errores.postValue("Error De Falla - ${t.message}")
                    }

                })
            } catch (e: Exception) {
                //aqui si hay un error se ejecuta este codigo
                e.printStackTrace()
                errores.postValue("Error Interno - ${e.message}")
            }
        }
    }


    /**
     * Funcion que va a a llamar a la segunda API
     */
    fun obtenerDetalleEmpresa(idEmpresa: Int) {
        //Corrutina que va a ir a buscar la informacion
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //Llamar API
                val retroIntancia = RetrofitClass.retrofit.create(ApiService::class.java)
                //quien va a llamar a la API
                val llamadaApi = retroIntancia.detalleEmpresa(idEmpresa)
                //Llamar a la api para que nos devuelva los datos
                llamadaApi.enqueue(object : Callback<EmpresasDetalleResponse> {
                    //Aca va la respuesta de la llamada de la API
                    //Metodo que se va a ejecutar si esta bien
                    override fun onResponse(
                        call: Call<EmpresasDetalleResponse>, //la llamada a la API
                        response: Response<EmpresasDetalleResponse> //La respuesta de la API
                    ) {
                        //vamos aver si la api me respondio bien
                        if (response.isSuccessful) {
                            val respuesta = response.body()
                            detalleEmpresa.postValue(respuesta)
                        } else {
                            //Mostrar mensaje de error
                            errores.postValue(
                                "Error En la API - ${
                                    response.errorBody().toString()
                                }"
                            )
                        }
                    }

                    //Metodo que se va a ejecuar si hay algun error
                    override fun onFailure(call: Call<EmpresasDetalleResponse>, t: Throwable) {
                        //Mostrar mensaje de error
                        errores.postValue("Error De Falla - ${t.message}")
                    }

                })
            } catch (e: Exception) {
                //aqui si hay un error se ejecuta este codigo
                e.printStackTrace()
                errores.postValue("Error Interno - ${e.message}")
            }

        }
    }
}