import React, { Component } from "react";
import { Route, NavLink, HashRouter as Router } from "react-router-dom";
import { withRouter } from "react-router";
import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import LoginForm from "./components/LoginForm";
import MainScreen from "./components/MainScreen";

import AddPerson from "./components/person/AddPerson";

class App extends Component {
  state = {
    apiHost: "http://localhost:8081",
    sessionData: {
      login: "",
      password: ""
    },
    screens: [
      {
        img: "/img/person.png",
        title: "PERSONS",
        buttons: [
          { title: "ADD", path: "person/add" },
          { title: "SHOW ALL", path: "" }
        ]
      },
      {
        img: "/img/children.png",
        title: "CHILDREN",
        buttons: [{ title: "ADD", path: "" }, { title: "SHOW ALL", path: "" }]
      },
      {
        img: "/img/classroom.png",
        title: "CLASSROOMS",
        buttons: [{ title: "ADD", path: "" }, { title: "SHOW ALL", path: "" }]
      },
      {
        img: "/img/news.png",
        title: "NEWS",
        buttons: [{ title: "ADD", path: "" }, { title: "SHOW ALL", path: "" }]
      }
    ]
  };

  setSessionId = (login, password) => {
    this.setState({ sessionData: { login, password } });
  };

  chooseScreen = screen => {
    console.log("Choose screen: " + screen);
  };

  render() {
    return (
      <Router path="/">
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
          <Route
            path="/person/add"
            component={() => (
              <AddPerson
                apiHost={this.state.apiHost}
                session={this.state.sessionData}
              />
            )}
          />
        </div>
      </Router>
    );
  }
}

export default App;
