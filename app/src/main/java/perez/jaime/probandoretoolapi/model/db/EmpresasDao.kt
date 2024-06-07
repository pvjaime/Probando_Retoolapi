package perez.jaime.probandoretoolapi.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Dao que va a implementar todos los metodos que vamos a usar en esta app
 */
@Dao
interface EmpresasDao {

    //Metodo para insertar toda la data
    @Insert
    fun insertarData(empresas : List<EmpresaEntidad>)

    //Metodo para traer toda la informacion
    @Query("Select * from empresas")
    fun obeterEmpresasDB() : List<EmpresaEntidad>

    //Metodo para boorar toda la data
    @Query("DELETE FROM empresas")
    fun borrarDB()
}