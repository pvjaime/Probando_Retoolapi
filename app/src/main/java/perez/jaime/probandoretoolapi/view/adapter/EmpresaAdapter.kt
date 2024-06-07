package perez.jaime.probandoretoolapi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import perez.jaime.probandoretoolapi.R
import perez.jaime.probandoretoolapi.databinding.FilaListaEmpresaBinding
import perez.jaime.probandoretoolapi.model.db.EmpresaEntidad
import perez.jaime.probandoretoolapi.view.DetalleEmpresaFragment

class EmpresaAdapter(private val listaEmpresas: List<EmpresaEntidad>) :
    RecyclerView.Adapter<EmpresaAdapter.EmpresaViewHolder>() {


    class EmpresaViewHolder(val binding: FilaListaEmpresaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpresaViewHolder {
        val binding =
            FilaListaEmpresaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmpresaViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listaEmpresas.size
    }

    override fun onBindViewHolder(holder: EmpresaViewHolder, position: Int) {
        val empresa = listaEmpresas[position]
        holder.binding.txtNombreEmpresa.text = empresa.nombre_empresa
        holder.binding.txtUbicacion.text = empresa.ubicacion_empresa
        holder.binding.txtFechaFundacion.text = empresa.fecha_fundacion
        //Imagen
        Picasso.get()
            .load(empresa.url_logo)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.logoEmpresa)
        //Configurar el click
        holder.binding.root.setOnClickListener {
            var detalle = DetalleEmpresaFragment.newInstance(empresa.id_api)
            val activity = it.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.main, detalle)
                .addToBackStack(null).commit()
        }
    }

}