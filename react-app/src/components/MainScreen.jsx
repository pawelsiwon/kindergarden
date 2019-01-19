import React, { Component } from "react";
import { withRouter } from "react-router";
import ScreenElement from "./ScreenElement";
import "./../css/MainScreen.css";
import PersonScreenElement from "./PersonScreenElement";

class MainScreen extends Component {
  render() {
    return (
      <div className="container main-screen">
        <div className="row">
          <PersonScreenElement
            img="/img/person.png"
            screenTitle="PERSONS"
            buttonOnClick={e => console.log(e)}
          />
          {this.props.screens.map(screen => (
            <ScreenElement
              key={"screenElement_" + screen.title}
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
