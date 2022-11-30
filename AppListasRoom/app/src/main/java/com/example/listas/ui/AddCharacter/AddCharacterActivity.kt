package com.example.listas.ui.AddCharacter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.formulationapp.ui.Constantes
import com.example.formulationapp.ui.MainViewModel

import com.example.formulationapp.utils.StringProvider
import com.example.listas.R

import com.example.listas.databinding.ActivityMainBinding
import com.example.listas.ui.CharacterList.CharacterListActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels ()
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
        val stringBuild = StringProvider.instance(this@AddCharacterActivity)
        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)
            imageView2.isVisible = false

            buttonAccion.setOnClickListener {
                if (name.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()) {
                    viewModel.handleEvent(
                        AddEvent.AddCharacter(
                            com.example.appnobasica.domain.modelo.Character(
                                name.text.toString(),
                                password.text.toString(),
                                slider.value.toInt(),
                                switchMaterial.text.toString(),
                                comprobarRadioButtons(),
                                ponerUrlImagenDeAnime(),
                                null
                            )
                        )
                    )
                } else {
                    Toast.makeText(
                        this@AddCharacterActivity,
                        stringBuild.getString(R.string.faltanDatos),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            switchMaterial.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    switchMaterial.setText(R.string.switch_mujer)
                } else
                    switchMaterial.setText(R.string.switch_hombre)
            }
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                imageView2.isVisible = true
                val radio: RadioButton = findViewById(checkedId)
                when (radio.text) {
                    stringBuild.getString(R.string.radio1) -> {
                        imageView2.setImageResource(R.drawable.descarga)
                    }
                    stringBuild.getString(R.string.radio2) -> {
                        imageView2.setImageResource(R.drawable.naruto_logo)
                    }
                    else -> {
                        imageView2.setImageResource(R.drawable.dragon_ball_logo)
                    }
                }
            }


            viewModel.uiState.observe(this@AddCharacterActivity) { state ->
                state.error?.let { error ->
                    Toast.makeText(this@AddCharacterActivity, error, Toast.LENGTH_SHORT).show()
                }
            }

            eliminarButton.setOnClickListener {
                mensajeParaCambiarDeActivity()
            }
        }
    }

    private fun mensajeParaCambiarDeActivity() {
        val dialog = MaterialAlertDialogBuilder(this@AddCharacterActivity)
            .setTitle(Constantes.CONFIRMACION)
            .setMessage(Constantes.QUIERES_VER_PERSONAJE)
            .setNegativeButton(Constantes.NO) { view, _ ->
                view.dismiss()
            }
            .setPositiveButton(Constantes.SI) { view, _ ->
                val intent =
                    Intent(this@AddCharacterActivity, CharacterListActivity::class.java)
                startActivity(intent)
                view.dismiss()
            }
            .setCancelable(false)
            .create()

        dialog.show()
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