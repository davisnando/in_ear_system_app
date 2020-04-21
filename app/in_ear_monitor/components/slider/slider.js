import React, { Component } from 'react';
import { View, Text, StyleSheet, PanResponder, findNodeHandle } from 'react-native';
export default class Slider extends Component {
  constructor(props) {
    super(props);
    this.state = {
        value: props.initialValue,
        rawValue: 0,
        MaxHeight: 0,
    };
    this.style = StyleSheet.create({
        container: {
            flexGrow: 1,
            alignSelf: "stretch",
            alignItems: "center",
            paddingTop: 10,
        },
        text: {
            fontSize: 15,
        },
        slidercontainer: {
            flex: 1,
            alignSelf: "stretch",
            justifyContent: "center",
            flexDirection: "row",
            backgroundColor: 'white',
        },
        bar: {
            backgroundColor: this.props.backgroundColor ? this.props.backgroundColor : "black",
            width: "100%",
            alignItems:'center',
            flexGrow: 1,
            alignSelf: "flex-end"
        }
    })
    this._panResponder = PanResponder.create({
        onStartShouldSetPanResponder: (evt, gestureState) => true,
        onStartShouldSetPanResponderCapture: (evt, gestureState) => true,
        onMoveShouldSetPanResponder: (evt, gestureState) => true,
        onMoveShouldSetPanResponderCapture: (evt, gestureState) => true,
        onPanResponderMove: (evt, gestureState) => this.onMove(evt, gestureState),
        onPanResponderRelease: (_, gestureState) => {
          var value = this.state.rawValue + -gestureState.dy
          this.setState({rawValue: value})
          this.callback(this.state.value)
        }
      });
    this.min = props.min
    this.max = props.max
    this.callback = props.callback
  }
  getValueFromBottomOffset(offset, barHeight, min, max){
      return ((max- min) * offset) / barHeight
  }

  onMove(evt, gestureState){
      const {rawValue } = this.state
      var height = rawValue + -gestureState.dy
      if(height > this.state.MaxHeight) height = this.state.MaxHeight
      if(height < 0) height = 0
      this.setState({value: Math.round(((this.max / this.state.MaxHeight) * height) + this.min)})
  }

  inRange(value, min, max){
      if (value < min) return min
      if (value > max) return max
      return value
  }
  getHeight(){
      return this.state.value + "%"
  }

  render() {
    return (
      <View style={this.style.container} >
        <Text style={this.style.text}>{this.state.value}</Text>
        <View style={this.style.slidercontainer} {...this._panResponder.panHandlers} onLayout={event => {
            const layout = event.nativeEvent.layout;
            this.state.MaxHeight = layout.height
            this.state.rawValue =  (this.state.value / this.max)  * layout.height
          }}>
            <View style={[this.style.bar, {height: this.getHeight()}]} />
        </View>
      </View>
    );
  }
}


