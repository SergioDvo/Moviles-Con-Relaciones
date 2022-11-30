package com.example.listas.ui.UpdateCharacter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.formulationapp.ui.Constantes
import com.example.formulationapp.utils.StringProvider
import com.example.listas.R
import com.example.listas.databinding.UpdateCharacterBinding
import com.example.listas.ui.CharacterList.CharacterListActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class UpdateCharacterActivity : AppCompatActivity() {

    private lateinit var binding: UpdateCharacterBinding

    private val viewModel: UpdateCharacterViewModel by viewModels()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Handle item selection
        return when (item.itemId) {
            R.id.action_action1 -> {
                val uri = Uri.parse(Constantes.URL)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val stringBuild = StringProvider.instance(this)
        binding = UpdateCharacterBinding.inflate(layoutInflater).apply {
            setContentView(root)
            val adapter = HabilidadesAdapter(ArrayList())
            habilidadList.adapter = adapter
            habilidadList.layoutManager = GridLayoutManager(this@UpdateCharacterActivity,2)
            val nombre = intent.getStringExtra(Constantes.NOMBRE)
            Timber.d(Constantes.NOMBRE_NOMBRE + "$nombre")
            if (nombre != null) {
                viewModel.handleEvent(UpdateEvent.GetCharacterWithHabilidades(nombre))
            }
            buttonAccion.setOnClickListener {
                if (name.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()) {
                    viewModel.handleEvent(
                        UpdateEvent.UpdateCharacter(
                            com.example.appnobasica.domain.modelo.Character(
                                name.text.toString(),
                                password.text.toString(),
                                slider.value.toInt(),
                                switchMaterial.text.toString(),
                                comprobarRadioButtons(),
                                ponerUrlImagenDeAnime(),
                                null,
                                //todo meter las habilidades al añadir
                            )
                        )
                    )
                } else {
                    Toast.makeText(
                        this@UpdateCharacterActivity,
                        stringBuild.getString(R.string.faltanDatos),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            buttonAddHabilidad.setOnClickListener {
                if (nombre != null) {
                    viewModel.handleEvent(UpdateEvent.AddRandomHabilidad(nombre))
                }
            }
            switchMaterial.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    switchMaterial.setText(R.string.switch_mujer)
                } else
                    switchMaterial.setText(R.string.switch_hombre)
            }

            viewModel.uiState.observe(this@UpdateCharacterActivity) { state ->
                state.error?.let { error ->
                    Toast.makeText(this@UpdateCharacterActivity, error, Toast.LENGTH_SHORT).show()
                }
                state.character.let {
                    name.setText(it.name)
                    password.setText(it.description)
                    slider.value = it.poder.toFloat()
                    if (it.sexo == stringBuild.getString(R.string.switch_mujer)) {
                        switchMaterial.setText(R.string.switch_mujer)
                        switchMaterial.isChecked = true
                    } else {
                        switchMaterial.setText(R.string.switch_hombre)
                        switchMaterial.isChecked = false
                    }
                    if (it.habilidades!=null) {
                        adapter.cambiarLista(it.habilidades)
                    }
                }
            }
            eliminarButton.setOnClickListener {
                val dialog = MaterialAlertDialogBuilder(this@UpdateCharacterActivity)
                    .setTitle(Constantes.CONFIRMACION)
                    .setMessage(Constantes.QUIERES_VER_PERSONAJE)
                    .setNegativeButton(Constantes.NO) { view, _ ->
                        view.dismiss()
                    }
                    .setPositiveButton(Constantes.SI) { view, _ ->
                        val intent =
                            Intent(this@UpdateCharacterActivity, CharacterListActivity::class.java)
                        startActivity(intent)
                        view.dismiss()
                    }
                    .setCancelable(false)
                    .create()

                dialog.show()
            }
        }
    }

    private fun comprobarRadioButtons(): String {

        var radioButton: String = ""
        with(binding) {
            if (radioButton1.isChecked) {
                radioButton = radioButton1.text.toString()
            } else if (radioButton2.isChecked) {
                radioButton = radioButton2.text.toString()
            } else if (radioButton3.isChecked) {
                radioButton = radioButton3.text.toString()
            }
        }
        return radioButton
    }

    private fun ponerUrlImagenDeAnime(): String {
        var urlImagen = ""
        with(binding) {
            if (radioButton1.isChecked) {
                urlImagen = Constantes.URL_IMAGEN_ANIME1
            } else if (radioButton2.isChecked) {
                urlImagen = Constantes.URL_IMAGEN_ANIME2
            } else if (radioButton3.isChecked) {
                urlImagen = Constantes.URL_IMAGEN_ANIME3
            }
        }
        return urlImagen
    }
}