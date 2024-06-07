package perez.jaime.probandoretoolapi.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad de la base de datos que vamos a crear
 */
@Entity(tableName = "empresas")
data class EmpresaEntidad(
    //aca van los campos que voy a guardar en la base de datos
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val id_api: Int,
    val nombre_empresa: String,
    val url_logo: String,
    val ubicacion_empresa: String,
    val fecha_fundacion: String
)
