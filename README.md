## Truck Monitor

[![CI](https://github.com/njarun/TruckMonitor/actions/workflows/android.yml/badge.svg)](https://github.com/njarun/TruckMonitor/actions/workflows/android.yml) [![codecov](https://codecov.io/gh/njarun/TruckMonitor/graph/badge.svg?token=1HH0U55YGD)](https://codecov.io/gh/njarun/TruckMonitor)

### Implementation:
A tracking application to monitor the location of company trucks. Truck details are showed in List and Map views.

**Links:**    
Download APK from [here](https://drive.neptunelabs.xyz/s/TruckMonitorApp/download?path=%2F&files=TruckMonitor.apk)   
Screens from [here](https://drive.neptunelabs.xyz/s/TruckMonitorApp)
<br>[Demo Video<br><img src="https://drive.neptunelabs.xyz/s/TruckMonitorApp/download?path=%2F&files=List.jpeg" width="180" height="380">](https://drive.neptunelabs.xyz/s/TruckMonitorAppDemo)

### Project Breakdown
**Project Architecture:**  
Clean - MVVM

**Project Language:**  
Kotlin

**Project Highlights:**
1. 73% code coverage - [[Details]](https://codecov.io/gh/njarun/TruckMonitor)
2. Focussed on SOLID principles | Clean - MVVM Architecture
3. Object oriented programming approach
4. DI using Hilt
5. Usage of Jetpack components such as Room, NavGraph, View/DataBinding, ViewModel, LiveData...
6. Kotlin Coroutines/Flow, Rx
7. Kotlin List filters and sorting
8. Room query operations
9. Unit, UI and Database tests
10. Optional Obfuscation
11. Scalability

**Database used:**   
[Room](https://developer.android.com/jetpack/androidx/releases/room)

**SCM/Git:**   
[GitHub](https://github.com/njarun/TruckMonitor)

### Screens
1. Splash page
2. List page
3. Map page

### Application flow:
1. When the app is launched, a Splash page is presented with App icon
2. Once the user lands on the Dashboard, the app loads truck data from local database, also pulls the latest data from server.
3. In successful retrieval of data from server, the local cache (DB) is flushed and the new data is stored as cache.
4. The List and Map fragments observer on the shared view model truck live data.
5. When there is a data update in the shared view model live data, its loaded to the List and Map UI
6. The sort button in the action bar orders the list/map data in ascending or descending order.
7. Users can search for the trucks with plate number, name and location.
8. Up on search, the relevant results are queried from database to the List and Map views.
9. Listeners are attached to the truck items to process the user selections.

### Capabilities
1. Clean-MVVM architecture, components are easily replaceable and focussed on SOLID principles
2. Coroutines, flow, live data to pass or observer events/data to parts of application
3. The Dashboard transition is with NavGraph component and BottomBar
4. UI is loaded by View/Data binding
5. Functional/Method bindings for views
6. Factory class structure for Local/Network data sources
7. Room database is updated with API results for persistence
8. Search data from local Database with multiple conditions
9. Hilt for dependency injection
10. Code coverage with CodeCov, check the codecov badge at the top to see the results.

### Screenshots:
<br>

**Splash Page:**
<br>
<img src="https://drive.neptunelabs.xyz/s/TruckMonitorApp/download?path=%2F&files=Splash.jpeg" width="360" height="780">
<br>
<br>

**List Page:**
<br>
<br>
<img src="https://drive.neptunelabs.xyz/s/TruckMonitorApp/download?path=%2F&files=List.jpeg" width="360" height="780">
<br>
<br>

**Map Page:**
<br>    
<img src="https://drive.neptunelabs.xyz/s/TruckMonitorApp/download?path=%2F&files=Map.jpeg" width="360" height="780">  
<br>
<br>

**Search:**
<br>    
<img src="https://drive.neptunelabs.xyz/s/TruckMonitorApp/download?path=%2F&files=Search.jpeg" width="360" height="780">
<br>

### Credits:
App icon from [FlatIcon](https://www.flaticon.com/)
<br>
<br>