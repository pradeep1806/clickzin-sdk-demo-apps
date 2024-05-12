## Additional information

CLickzin react android tracking sdk

## Steps to integrate SDK

## Step 1: Add npm package using below command

    npm i clickzin_react

## Step 2: In main.dart, add imports

```javascript
import {NativeModules} from 'react-native';
```

## Step 3: During app start in a React Native application

```javascript
ClickzinAndroidTracker.startTracking('apikey', (err, message) => {
  if (err) return console.log(err);
  console.log('message', message);
});
```

## Step 4: Track events in respective events widget with respective event name.Events like register, sale, login and so on will be tracked using this api.

```javascript
ClickzinAndroidTracker.trackEvent('apikey', 'event', (err, message) => {
  if (err) return console.log(err);
  console.log('message', message);
});
```
