package perez.jaime.probandoretoolapi.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import perez.jaime.probandoretoolapi.databinding.ActivityMainBinding
import perez.jaime.probandoretoolapi.view.adapter.EmpresaAdapter
import perez.jaime.probandoretoolapi.viewmodel.EmpresasViewModel

class MainActivity : AppCompatActivity() {
    //Variable de Binding
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Configuraciones de Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Configurar vista
        setContentView(binding.root)
        /**
         * Configuraciones
         */
        //Configurar el ViewModel
        var viewModelEmpresa = ViewModelProvider(this).get(EmpresasViewModel::class.java)
        //configurando el Loader
        binding.listaEmpresas.visibility = GONE
        binding.progressBar.visibility = VISIBLE
        //Configurar el RecyclerView
        binding.listaEmpresas.layoutManager = LinearLayoutManager(this)
        //Configurar el Adapter
        var adaptadorEmpresas = EmpresaAdapter(listOf(), this)
        binding.listaEmpresas.adapter = adaptadorEmpresas
        //Configurar el Observador
        viewModelEmpresa.listaEmpresas.observe(
            this
        ) { datosEmpresa ->
            adaptadorEmpresas = EmpresaAdapter(datosEmpresa, this)
            binding.listaEmpresas.adapter = adaptadorEmpresas
            binding.listaEmpresas.visibility = VISIBLE
            binding.progressBar.visibility = GONE
        }
        viewModelEmpresa.errores.observe(this) {
            binding.listaEmpresas.visibility = GONE
            binding.progressBar.visibility = GONE
            val builder = AlertDialog.Builder(this)
            builder.setMessage(it)
                .setPositiveButton("OK") { dialog, id ->
                    dialog.dismiss()
                }
            // Create the AlertDialog object and return it.
            builder.create().show()
        }
        //Ejecucion desde el ViewModel
        viewModelEmpresa.listarEmpresas()
    }
}