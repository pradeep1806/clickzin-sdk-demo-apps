import React from 'react';
import { NativeModules, Text, TouchableOpacity, View } from 'react-native';

const {ClickzinAndroidTracker} = NativeModules;

const HelloWorldApp = () => {
  const handleButtonClick = () => {
    ClickzinAndroidTracker.trackEvent('apikey', 'event', (err, message) => {
      if (err) {
        return console.log(err);
      }
      console.log('message', message);
    });
  };

  return (
    <View
      style={{
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
      }}>
      <Text>Hello, world!</Text>
      <TouchableOpacity onPress={handleButtonClick} style={{marginTop: 20}}>
        <View style={{backgroundColor: 'blue', padding: 10, borderRadius: 5}}>
          <Text style={{color: 'white'}}>Click Me</Text>
        </View>
      </TouchableOpacity>
    </View>
  );
};

export default HelloWorldApp;
