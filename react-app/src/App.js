import React, { Component } from "react";
import { Route, NavLink, HashRouter as Router } from "react-router-dom";
import { withRouter } from "react-router";
import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import LoginForm from "./components/LoginForm";
import MainScreen from "./components/MainScreen";

class App extends Component {
  state = {
    apiHost: "http://localhost:8081",
    login: "",
    password: "",
    screens: [
      {
        img: "/img/person.png",
        buttonValue: "MANAGE PERSONS",
        chooseScreen: () => this.chooseScreen("persons")
      },
      {
        img: "/img/children.png",
        buttonValue: "MANAGE CHILDREN",
        chooseScreen: () => this.chooseScreen("children")
      },
      {
        img: "/img/classroom.png",
        buttonValue: "MANAGE CLASSROOMS",
        chooseScreen: () => this.chooseScreen("classrooms")
      },
      {
        img: "/img/news.png",
        buttonValue: "MANAGE NEWS",
        chooseScreen: () => this.chooseScreen("news")
      }
    ]
  };

  setSessionId = (login, password) => {
    this.setState({ login: login, password: password });
  };

  chooseScreen = screen => {
    console.log("Choose screen: " + screen);
  };

  render() {
    return (
      <Router path="/login">
        <div className="content">
          <Route
            path="/app"
            component={() => <MainScreen screens={this.state.screens} />}
          />
          <Route
            path="/login"
            component={() => (
              <LoginForm
                apiHost={this.state.apiHost}
                setSession={this.setSessionId}
              />
            )}
          />
        </div>
      </Router>
    );
  }
}

export default App;
