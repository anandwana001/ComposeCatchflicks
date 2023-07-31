# ComposeCatchflicks 🎬

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)

This is a Compose version of my earlier [Catchflicks](https://github.com/anandwana001/catchflicks) project. This will be my new Kitchen Sink Project for learning new Android concepts.

## Screenshot 📱
<p>
<img src="/art/Screenshot_20230729-150320.png" width="220" height="450"/>
<img src="/art/Screenshot_20230729-150337.png" width="220" height="450"/>
<img src="/art/Screenshot_20230729-150350.png" width="220" height="450"/>
<img src="/art/Screenshot_20230729-150355.png" width="220" height="450"/>
<img src="/art/Screenshot_20230729-150403.png" width="220" height="450"/>
<img src="/art/Screenshot_20230729-150429.png" width="220" height="450"/>
</p>

## Architecture



### Packing Structure
- data
  - model
  - remote
    - response
- di
- domain
  - model
  - repository
- ui
  - component
  - navigation
  - screens
  - theme
  - util
- util
- ComposeCatchflicksApplication.kt
- MainActivity.kt

## Tech-stack 🛠
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - UI creation library
- [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) - Navigate between Compose Screen
- [Compose Paging](https://developer.android.com/jetpack/androidx/releases/paging) - Managing multiple pages of data
- [Coil](https://coil-kt.github.io/coil/) - Image Loading Library
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - Async Programming
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - Observable Data holder
- [Compose Material3 for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design
- [AssistChip](https://m3.material.io/components/chips/specs) - Chips are for info
- [Hilt](https://dagger.dev/hilt/) - Dependency Injection Library
- [Retrofit](https://square.github.io/retrofit/) - Used for Networking
- [OkHTTP](https://square.github.io/okhttp/) - HTTP Client required by Retrofit Library for Networking
- [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) - Use to serialize and deserialize Kotlin objects to JSON
- [Kotlin Gradle](https://kotlinlang.org/docs/gradle-configure-project.html) - All the gradle files are writing in pure kotlin
- [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Manage activity and fragment lifecycle
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Observable data holder
- [AndroidX](https://developer.android.com/jetpack/androidx) - Android library for core functionalities
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage data in lifecycle aware fashion

## Development Setup ⚙️
This project uses the [TMDb API](https://developers.themoviedb.org/4/getting-started) to fetch movies data.<br>
To begin with the setup, firstly you need to create an API key.

1. Create an account at [themoviedb.org](https://www.themoviedb.org/documentation/api)
1. Go to settings from the profile icon
1. Click on API
1. Click on Create

```
# Insert at ~/local.properties
TMDB_API_KEY=<insert>
```
This project uses the Hilt for dependency Injection. After opening this project in your Android Studio you might get some error which is due unavailability of few classes.
You need to `make project` or try to build the project, this will generate all the required classes for Hilt.


## Learnings from this Repo
1.

## If this repository helps you in anyway, show your love :heart: by putting a :star: on this project :v:

## Credits
Author: Akshay Nandwana

<a href="https://twitter.com/akshay81844">
  <img align="left" alt="Akshay's Twitter" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/twitter.svg" />
</a>
<a href="https://www.linkedin.com/in/anandwana001/">
  <img align="left" alt="Akshay's Linkdein" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/linkedin.svg" />
</a>
<br><br>


## Support
<a href="https://www.buymeacoffee.com/anandwana001" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/v2/default-yellow.png" alt="Buy Me A Coffee" style="height: 60px !important;width: 217px !important;" ></a>


## Need a Mentor? [Click Here](https://topmate.io/anandwana001)
<img src="https://github.com/anandwana001/anandwana001/assets/25057618/6e1d507b-a5d5-409c-85a7-9b44838497ff" width=400 height=150/>


## License

    Copyright 2022 Akshay Nandwana

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
