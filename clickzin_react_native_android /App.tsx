/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, {useEffect} from 'react';
import ClickzinTracking from 'clickzin_tracking_react_native';

import {
  Button,
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';

import {Colors} from 'react-native/Libraries/NewAppScreen';

function App(): React.JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';

  useEffect(() => {
    async function trackInstall() {
      try {
        const response = await ClickzinTracking.trackInstall(
          '17d00f2b-208a-4f7e-aa82-7e5b82fd918d',
        );
        console.log('Clickzin Response : ', response);
      } catch (error) {
        console.error('Clickzin error :', error);
      }
    }

    trackInstall();
  }, []);

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  const changeMessage = async () => {
    //setMessage('You pressed the button!');

    try {
      const response = await ClickzinTracking.trackEvent(
        '17d00f2b-208a-4f7e-aa82-7e5b82fd918d',
        'register',
      );
      console.log('Clickzin event response : ', response);
    } catch (error) {
      console.error('Clickzin event error :', error);
    }
  };

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar
        barStyle={isDarkMode ? 'light-content' : 'dark-content'}
        backgroundColor={backgroundStyle.backgroundColor}
      />
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        style={backgroundStyle}>
        <View style={styles.container}>
          <Text style={styles.title}>
            Welcome to Clickzin React Native Demo App to test tracking
          </Text>

          <Button title="Track Register Event" onPress={changeMessage} />
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
  container: {
    paddingTop: 100,
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: 20,
  },
  message: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
});

export default App;
