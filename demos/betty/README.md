# Hi! Lets meet Betty...

Betty is actually an app, for checking Bookmakers best odds.
Android app, written in Kotlin and it uses Jetpack Compose for UI.
Right now Betty is only single screen, but in the future may add more features to it.

# Setup

To run app:

1. git clone repo to Android studio
2. Get your own Api Key from [Odds-Api](https://the-odds-api.com/)-
3. Add your own Api Key to gradle.properties in this format

   betty_api_access_key = "123456789012345678901234567890"

4. And run app in emulator or on physical device

## Stack

- ***MVVM*** architecture
- ***Retrofit*** for pulling data from network
- ***Room*** for persistence
- ***Coroutines*** for threading
- All *Remote* and *Local* logic handled in *Repository*
- *ViewModel* handles logic between UI and *Repository* using **Flows** and **Coroutines**
- **Hilt** for dependency injection
- Using ****Lottie**** for animations
- **Workmanager** fetches new list everyday
- **Notifications** when new data is fetched

# Screens - Features

## Main Screen

- Main Screen shows *Best Odds* form Ods-Api.
- All odds are displayed in LazyColumn.
- Using Sticky Header in the list. List is split in 3 parts: High, Medium and Low chance of winning
- **Swipe down to refresh** deletes all items from database and fetches new list
- Repository flattens this big 4 layered DTO to small single data class Odd
- Repository picks up best odds and those belonging to Williams Will
    (i use Williams Hill for betting if i do at all.. no time)
- Progress bar of fetching data showing in Home screen
- Snackbar shows if and error comes up
- All results are saved in local database.
- Workmanager constrains handles no internet connection

And i think that's all about Betty for now.
---------------------------------------------
## In the future will add:
- Bookmaker chooser - probably by filtering getOddsFromLocal() flow
- Sport chooser may ber useful for someone too
- Option to show odds as a fraction not only decimal
- Settings screen for managing notifications and work manager tasks

----------------------------------------

When all above is done i'll find Betty new home in Google Play Store

# Any suggestions let me know.

<img src="https://github.com/adrianwitaszak/Betty/raw/master/screenshots/betty.gif" width="400" height="900">

# Copyright

```
Copyright 2021 Adrian Witaszak

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
