/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, { Component,  } from 'react';
import { ScrollView, View} from 'react-native';
import Channel from "./components/channel/channel";
import Master from "./components/master/master";
import UdpAudio from "react-native-udp-audio";

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };

  }
  componentDidMount(){
    fetch("http://192.168.178.34:5656/CreateChannel")
    UdpAudio.PlayAudioBuffer();
  }

  render() {
    return (
      <View style={{flex: 1, flexDirection: 'row', paddingBottom:10}}>
        <ScrollView style={{ flexDirection: 'column', }} contentContainerStyle={{ flexDirection: "row", flexWrap: "wrap", paddingBottom: 20}}>
          <Channel channel={1}/>
          <Channel/>
          <Channel/>
          <Channel/>
          <Channel/>
          <Channel/>
          <Channel/>
          <Channel/>
          <Channel/>
        </ScrollView>
        <Master/>
      </View>
    );
  }
}