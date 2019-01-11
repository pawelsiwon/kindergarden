import React, { Component } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import LoginForm from "./components/LoginForm";

class App extends Component {

  render() {
    return (
      <React.Fragment>
        <LoginForm />
      </React.Fragment>
    );
  }
}

export default App;
