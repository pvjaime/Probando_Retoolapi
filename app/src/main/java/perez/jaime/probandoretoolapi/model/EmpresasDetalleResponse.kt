package perez.jaime.probandoretoolapi.model

/**
 * Data que vamos a recibir de la API
 */
data class EmpresasDetalleResponse(
    //Se agregan todos las variables que nos va a traer la API
    val id : Int,
    val logo: String,
    val ubicacion: String,
    val nombre_empresa: String,
    val fecha_fundacion: String
)
