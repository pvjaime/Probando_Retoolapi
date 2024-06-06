package perez.jaime.probandoretoolapi.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import perez.jaime.probandoretoolapi.databinding.FragmentDatalleEmpresaBinding
import perez.jaime.probandoretoolapi.viewmodel.EmpresasViewModel


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ID_EMPRESA = "idEmpresa"

/**
 * A simple [Fragment] subclass.
 * Use the [DatalleEmpresaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DatalleEmpresaFragment : Fragment() {
    private var idEmpresa: Int = 0

    private var _binding: FragmentDatalleEmpresaBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idEmpresa = it.getInt(ID_EMPRESA)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDatalleEmpresaBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * Configuraciones
         */
        //Configurar el ViewModel
        var viewModelEmpresa = ViewModelProvider(this).get(EmpresasViewModel::class.java)
        //Configurar el Observador
        viewModelEmpresa.detalleEmpresa.observe(
            this
        ) { datosEmpresa ->
            binding.txtEmpresa.text = datosEmpresa.nombre_empresa
            binding.txtUbicacionDetalle.text = datosEmpresa.ubicacion
            Picasso.get().load(datosEmpresa.logo).into(binding.imagenLogo)
        }
        viewModelEmpresa.errores.observe(this) {
            //
        }
        //Configurar el click del boton
        binding.haceAlgo.setOnClickListener {
            val url = "http://www.google.com"
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(url))
            startActivity(i)
        }
        //Ejecucion desde el ViewModel
        viewModelEmpresa.obtenerDetalleEmpresa(idEmpresa)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param idEmpresa Como el Id de la empresa.
         * @return A new instance of fragment DatalleEmpresaFragment.
         */
        @JvmStatic
        fun newInstance(idEmpresa: Int) =
            DatalleEmpresaFragment().apply {
                arguments = Bundle().apply {
                    putInt(ID_EMPRESA, idEmpresa)
                }
            }
    }
}