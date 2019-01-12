import React, { Component } from "react";
import { withRouter } from "react-router";
import ScreenElement from "./ScreenElement";
import "./../css/MainScreen.css";

class MainScreen extends Component {
  render() {
    return (
      <div className="container main-screen">
        <div className="row">
          {this.props.screens.map(screen => (
            <ScreenElement
              img={screen.img}
              screenTitle={screen.title}
              buttons={screen.buttons}
              buttonOnClick={screen.chooseScreen}
            />
          ))}
        </div>
      </div>
    );
  }
}

export default withRouter(MainScreen);
