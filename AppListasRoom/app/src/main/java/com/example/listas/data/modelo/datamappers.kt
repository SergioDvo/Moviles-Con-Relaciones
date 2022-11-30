package com.example.listas.data.modelo

import com.example.appnobasica.domain.modelo.Character
import com.example.listas.modelo.Habilidad

fun CharacterEntity.toCharacter() : Character{
    when(this.anime){
        "Naruto" -> return Character(this.name, this.description, this.poder, this.sexo, this.anime, "https://www.freepnglogos.com/uploads/naruto/image-naruto-shippuden-logo-gamefactory-wiki-14.png",null)
        "One Piece" -> return Character(this.name, this.description, this.poder, this.sexo, this.anime, "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/4153eaf9-97b2-4c99-be24-1129b1af0e7b/df4mgfb-476cf6ee-d982-47ae-a5bc-2d286089b532.png/v1/fill/w_1280,h_452,strp/one_piece_logo_png_by_foxboxnostalgic101_df4mgfb-fullview.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9NDUyIiwicGF0aCI6IlwvZlwvNDE1M2VhZjktOTdiMi00Yzk5LWJlMjQtMTEyOWIxYWYwZTdiXC9kZjRtZ2ZiLTQ3NmNmNmVlLWQ5ODItNDdhZS1hNWJjLTJkMjg2MDg5YjUzMi5wbmciLCJ3aWR0aCI6Ijw9MTI4MCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.J4Ax-YsBuXR2kokIxHv1mpdOHQoqa0moUDoAIr_UoeQ",null)
        "Dragon Ball" -> return Character(this.name, this.description, this.poder, this.sexo, this.anime, "https://upload.wikimedia.org/wikipedia/commons/2/22/Dragon_Ball_Super.png",null)
    }
return Character(this.name, this.description, this.poder, this.sexo, this.anime, "",null)
}
fun Character.toCharacterEntity() : CharacterEntity{
    return CharacterEntity(this.name,this.description,this.poder,this.sexo,this.anime)
}
fun CharacterWithHabilidadesEntity.toCharacter() : Character{
    return Character(this.character.name,this.character.description,this.character.poder,this.character.sexo,this.character.anime,"",
        this.habilidades?.map { it.toHabilidad() })
}
fun HabilidadEntity.toHabilidad() : Habilidad{
    return Habilidad(this.nombre,this.mana,this.characterName)
}
fun Habilidad.toHabilidadEntity() : HabilidadEntity{
    return HabilidadEntity(this.nombre,this.mana,this.characterName)
}