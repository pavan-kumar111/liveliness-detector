/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, {useEffect, useState} from 'react';
import {Text} from 'react-native';

import {Button} from 'react-native';
import CustomModule from './CustomModule';
import {DeviceEventEmitter} from 'react-native';

const onDataAvailable = event => {
  console.log(event);
};

DeviceEventEmitter.addListener('onDataAvailable', onDataAvailable);

function App() {
  const callSdk = () => {
    //   console.log('We will invoke the native module here!');
    CustomModule.loadSdk();
  };

  return (
    <Button
      title="Click to invoke your native module!"
      color="#841584"
      onPress={() => callSdk()}
    />
  );
}

export default App;
