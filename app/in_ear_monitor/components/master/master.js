import React, { Component } from 'react';
import { View, Text } from 'react-native';
import Slider from "../slider/slider"

export default class Master extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }
  valueUpdated(value){
      console.log(value)
  }
  render() {
    return (
    <View style={{ width: 80, borderColor: "black", borderWidth: 3, marginRight: 20, marginTop: 20}}>
        <Text style={{alignSelf: "center",}}>Master</Text>
        <View style={{width: 60, alignSelf: "center", flex: 1}}>
            <Slider min={0} max={100} initialValue={20} backgroundColor="red" callback={this.valueUpdated} />
        </View>
    </View>
    );
  }
}
