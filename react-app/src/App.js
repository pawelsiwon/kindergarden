import React, { Component } from "react";
import { Route, HashRouter as Router } from "react-router-dom";
import { BrowserRouter } from "react-router-dom";
import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import LoginForm from "./components/LoginForm";
import MainScreen from "./components/MainScreen";

import AddPerson from "./components/person/AddPerson";
import ShowAllParents from "./components/person/ShowAllParents";
import PersonDetails from "./components/person/PersonDetails";
import PersonEdit from "./components/person/PersonEdit";

class App extends Component {
  state = {
    apiHost: "http://localhost:8081",
    sessionData: {
      login: "psiwon",
      password: "psiwon@"
    },
    screens: [
      {
        img: "/img/person.png",
        title: "PERSONS",
        buttons: [
          { title: "ADD", path: "person/add" },
          { title: "SHOW ALL", path: "person/show" }
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
      <Router path="/" history={new BrowserRouter()}>
        <div className="content">
          <Route
            path="/app"
            component={() => <MainScreen screens={this.state.screens} />}
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
          <Route
            path="/person/show"
            component={() => (
              <ShowAllParents
                apiHost={this.state.apiHost}
                session={this.state.sessionData}
              />
            )}
          />
          <Route
            path="/person/details/:personId"
            component={() => (
              <PersonDetails
                apiHost={this.state.apiHost}
                session={this.state.sessionData}
              />
            )}
          />
          <Route
            path="/person/edit/:personId"
            component={() => (
              <PersonEdit
                apiHost={this.state.apiHost}
                session={this.state.sessionData}
              />
            )}
          />
          <Route
            path="//"
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
