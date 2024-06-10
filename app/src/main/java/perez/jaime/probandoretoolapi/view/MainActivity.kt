package perez.jaime.probandoretoolapi.view

import android.R
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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
        var adaptadorEmpresas = EmpresaAdapter(listOf())
        binding.listaEmpresas.adapter = adaptadorEmpresas
        //Configurar el Observador
        viewModelEmpresa.listaEmpresas.observe(
            this
        ) { datosEmpresa ->
            adaptadorEmpresas = EmpresaAdapter(datosEmpresa)
            binding.listaEmpresas.adapter = adaptadorEmpresas
            binding.listaEmpresas.visibility = VISIBLE
            binding.progressBar.visibility = GONE
            //Vamos a configurar el Spinner
            val arrayAdapter: ArrayAdapter<*> = ArrayAdapter<Any?>(this, R.layout.simple_spinner_item, datosEmpresa)
            binding.spinnerEmpresas.adapter = arrayAdapter
            binding.spinnerEmpresas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    // You can define your actions as you want
                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    val selectedObject = datosEmpresa.get(position)
                    Toast.makeText(
                        this@MainActivity,
                        "ID: ${selectedObject.id} Name: ${selectedObject.nombre_empresa}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

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