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
import ShowAllChildren from "./components/children/ShowAllChildren";
import AddChild from "./components/children/AddChild";
import ChildDetails from "./components/children/ChildDetails";
import AddNews from "./components/news/AddNews";
import ShowNews from "./components/news/ShowNews";
import ClassroomAdd from "./components/classroom/ClassroomAdd";
import ClassroomDetails from "./components/classroom/ClassroomDetails";
import ClassroomList from "./components/classroom/ClassroomList";
import ClassroomManageChildren from "./components/classroom/ClassroomManageChildren";
import AddPayment from "./components/Payments/AddPayment";

class App extends Component {
  state = {
    apiHost: "http://localhost:8081",
    sessionData: {
      login: "psiwon1",
      password: "psiwon1",
      message: ""
    },
    screens: [
      {
        img: "/img/children.png",
        title: "CHILDREN",
        buttons: [
          { title: "ADD", path: "children/add" },
          { title: "SHOW ALL", path: "children/show" }
        ]
      },
      {
        img: "/img/classroom.png",
        title: "CLASSROOMS",
        buttons: [
          { title: "ADD", path: "classroom/add" },
          { title: "SHOW ALL", path: "classroom/show" }
        ]
      },
      {
        img: "/img/news.png",
        title: "NEWS",
        buttons: [
          { title: "ADD", path: "/news/add" },
          { title: "SHOW ALL", path: "/news/show" }
        ]
      },
      {
        img: "/img/children.png",
        title: "PAYMENTS",
        buttons: [
          { title: "ADD", path: "payments/add" },
          { title: "SHOW ALL", path: "payments/show" }
        ]
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
            path="/parents/show"
            component={() => (
              <ShowAllParents
                apiLink="/person/list/parents"
                title="All parents"
                apiHost={this.state.apiHost}
                session={this.state.sessionData}
              />
            )}
          />

          <Route
            path="/teachers/show"
            component={() => (
              <ShowAllParents
                apiLink="/person/list/teachers"
                title="All teachers"
                apiHost={this.state.apiHost}
                session={this.state.sessionData}
              />
            )}
          />

          <Route
            path="/admins/show"
            component={() => (
              <ShowAllParents
                apiLink="/person/list/admins"
                title="All admins"
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
            path="/children/add"
            component={() => (
              <AddChild
                apiHost={this.state.apiHost}
                session={this.state.sessionData}
              />
            )}
          />
          <Route
            path="/children/show"
            component={() => (
              <ShowAllChildren
                apiHost={this.state.apiHost}
                session={this.state.sessionData}
              />
            )}
          />
          <Route
            path="/child/details/:childId"
            component={() => (
              <ChildDetails
                apiHost={this.state.apiHost}
                session={this.state.sessionData}
              />
            )}
          />

          <Route
            path="/classroom/show"
            component={() => (
              <ClassroomList
                apiHost={this.state.apiHost}
                session={this.state.sessionData}
              />
            )}
          />
          <Route
            path="/classroom/add"
            component={() => (
              <ClassroomAdd
                apiHost={this.state.apiHost}
                session={this.state.sessionData}
              />
            )}
          />
          <Route
            path="/classroom/details/:classId"
            component={() => (
              <ClassroomDetails
                apiHost={this.state.apiHost}
                session={this.state.sessionData}
              />
            )}
          />
          <Route
            path="/classroom/managechildren/:classId"
            component={() => (
              <ClassroomManageChildren
                apiHost={this.state.apiHost}
                session={this.state.sessionData}
              />
            )}
          />

          <Route
            path="/news/add"
            component={() => (
              <AddNews
                apiHost={this.state.apiHost}
                session={this.state.sessionData}
              />
            )}
          />
          <Route
            path="/news/show"
            component={() => (
              <ShowNews
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

          <Route
            path="/payments/add"
            component={() => (
              <AddPayment
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
