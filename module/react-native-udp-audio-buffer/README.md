# react-native-udp-audio-buffer

## Getting started

`$ npm install react-native-udp-audio-buffer --save`

### Mostly automatic installation

`$ react-native link react-native-udp-audio-buffer`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-udp-audio-buffer` and add `UdpAudioBuffer.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libUdpAudioBuffer.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.reactlibrary.UdpAudioBufferPackage;` to the imports at the top of the file
  - Add `new UdpAudioBufferPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-udp-audio-buffer'
  	project(':react-native-udp-audio-buffer').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-udp-audio-buffer/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-udp-audio-buffer')
  	```


## Usage
```javascript
import UdpAudioBuffer from 'react-native-udp-audio-buffer';

// TODO: What to do with the module?
UdpAudioBuffer;
```
