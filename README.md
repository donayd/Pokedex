<h1 align="center">Retrodex</h1>

<p align="center">
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-green.svg?style=flat"/></a>
  <a href="https://www.java.com/"><img alt="Android" src="https://img.shields.io/badge/Android-Java-orange.svg?style=flat"/></a>
  <a href="https://github.com/donayd"><img alt="GitHub" src="https://img.shields.io/badge/GitHub-Donayd-blue.svg?style=flat"/></a> 
</p>

<p align="center">  
Pokedex Retro para mostrar todos los atributos de los primeros Pokémon usando esta API:
https://pokeapi.co/api/v2/
</p>

</br>

<div style="float:left;margin:0px 12px 0 12px">
    <img src="/previews/preview.gif" align="right" width="320"/>
</div>

## Tecnologías utilizadas
- Basado en [Kotlin](https://kotlinlang.org/)
- SDK mínimo 21
- SDK objetivo 31
- Jetpack
    - Ciclo de vida: Observa los ciclos de vida de Android y maneja los estados de la interfaz de usuario en los cambios del ciclo de vida.
   - ViewModel: Administra el titular de datos relacionados con la interfaz de usuario y el ciclo de vida. Permite que los datos sobrevivan a los cambios de configuración, como las rotaciones de pantalla.
   - DataBinding: Vincula los componentes de la interfaz de usuario en sus diseños a las fuentes de datos en su aplicación utilizando un formato declarativo en lugar de programación.
  - [Hilt](https://dagger.dev/hilt/): Para inyección de dependencia.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Construye las peticiones al API REST.
- [Glide](https://github.com/bumptech/glide), [GlidePalette](https://github.com/florent37/GlidePalette): Carga imágenes desde la red.
