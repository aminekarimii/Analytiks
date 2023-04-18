# Analytiks
![Group 54 (1)](https://user-images.githubusercontent.com/20410115/228402805-3309d17a-0bc5-4404-90f8-20c9b30e33a9.png)

## Description
An android library that centralizes analytics services in one place can be a useful tool for developers who want to track the usage and performance of their app. 
Should be easy as it sounds, a single implementation to start with the base analytics core features, and then you can add each analytic service separately (to preserve library size).
A debug analytics mode that can log the same properties in the debug console.

![Scheme of the library logic](https://user-images.githubusercontent.com/20410115/225161402-d3a7d24f-da0d-4360-abab-fe86c68f0214.png)

## Download
```Groovy
dependencies {
    implementation 'com.analytiks:core:1.0.0'
    implementation 'com.analytiks:firebase:1.0.0' // Optional Firebase Analytics addon
    implementation 'com.analytiks:amplitude:1.0.0' // Optional Amplitude addon
    // Add any other optional addons here
}
```

## Features [🚧 wip]
The list of features provided by the library  
- **Initialization:** `init` Initialize the "analytiks" library, along with its sub-libraries, during the initialization process.  
- **Log event:** `event` send/save an event with a name and an optional set of properties.
- **Identify user:** `identify` Identify the current user by the given id or a random uuid in case of an empty one.
- **Set user property:** `setUserProperty` Sets a key value property to the identified user.
- **Reset:** `reset` the plugins and remove the default users configuration.
- **Flush events** `flush` send the recorded local data to the service servers on call.

## Supported analytics SDKs
Here's a list of the most known Analytic services that we will supoort in our library.  
- [x] Google/Firebase Analytics
- [x] Segment
- [x] Mixpanel
- [ ] Flurry Analytics
- [ ] Amplitude
- [ ] App Annie
- [ ] Localytics
- [ ] AppsFlyer
- [ ] Sensor Tower
- [ ] Onesignal
- [x] Timber - For local event logging   

➕ Can't find your service? [open an issue](https://github.com/aminekarimii/analytiks/issues/new) with the name and the direct documentation link in the comment section.

## License 🔖

```
    Apache 2.0 License

    Copyright 2022 KARIMI Amine

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
