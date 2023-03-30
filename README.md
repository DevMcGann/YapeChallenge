# YapeChallenge
Challenge for Yape


## UI
Pude haberlo hecho 100% en Jetpack Compose,  pero para ir más hacia lo clásico, elegí XML.  Si tuviese que elegir,  iria por Jetpack Compose. 
De acuerdo a lo leido en diversos foros, el rendimiento es mejor que con XML,  y de paso es mucho mas simple trabajar con listas,  ya que no necesita Adapters. 

## Arquitectura
Para esta demo,  elegí la clásica arquitectura MVVM + Clean Architecture (data, domain, presentation).

## Librerias
Navigation components:  Una activity, hosteando tantos fragments como views necesite en la App,  orquestando la navegación con el Nav Graph y también los argumentos. 

## Libreria de terceros
* Retrofit para la llamada al "backend" 
* Glide para cargar imagenes desde la web a un container de la vista de XML
* Dagger Hilt para inyección de dependencias
* Google maps


## Patrones de diseño
* Observer -> El livedata principal que trae las recetas
* Adapter ->  Usado por ejemplo en el Recycler View Adapter
* Dependency Injections -> Dagger Hilt
* Singleton -> Para tenes una instancia común en todo el proyecto de Retrofit por ejemplo
* Builder -> Para crear la instancia de Retrofit 
* Facadde -> Repository interface 

## Testing
Para realizar las pruebas unitarias del UseCase y del ViewModel, utilicé Junit4 [Los test cases pasan satisfactoriamente]



## Funcionamiento de la App
MainScreen,  la cual al crearse escucha un livedata en el ViewModel,   este Livedata alimenta la Recycler View con las recetas traidas desde la API. 
Al hacer click sobre una receta,  pasa por Arguments la recipe seleccionada al fragmento de Detail,  este muestra la imagen y una descripción ficticia. 
Al hacer click sobre el ícono de Mapa,  lleva a una tercera Screen,  que recive por Argumentos un objeto la clase Location(val lat: String, y val long:String),  posteriormente parsea esto a LatLong,  y con eso dibuja el PIN en el Mapa.   



## Imagenes

![y1](https://user-images.githubusercontent.com/32915926/228726628-75b5eb5f-7319-4508-ba52-150b1a8dc637.png)
![y2](https://user-images.githubusercontent.com/32915926/228726657-b9647c00-3b2a-402f-a116-af1a361a506e.png)
![y3](https://user-images.githubusercontent.com/32915926/228726672-55d45589-fb89-4c84-a1ca-74afaa1369e4.png)
![y4](https://user-images.githubusercontent.com/32915926/228726993-d21e8dcb-0102-4cb3-af12-2b6a41d16103.png)




