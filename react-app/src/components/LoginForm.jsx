import React, { Component } from "react";
import { withRouter } from "react-router";
import Input from "@material-ui/core/Input";
import Button from "@material-ui/core/Button";
import Axois from "axios";
import "./../css/LoginForm.css";

class LoginForm extends Component {
  state = {
    login: "",
    password: "",
    message: ""
  };

  submitData = () => {
    Axois.post(this.props.apiHost + "/api/login", this.state)
      .then((res, req) => {
        this.setState({ message: "" });
        this.props.setSession(this.state.login, this.state.password);
        this.props.history.push("/app");
      })
      .catch(err => {
        this.props.setSession(this.state.login, this.state.password);
        //this.setState({ message: "Plrease try again!" });
      });
  };

  render() {
    return (
      <div className="formContainer">
        <img src="/img/logo.png" alt="Logo przedszkola" />
        <Input
          className="m-2"
          name="login"
          placeholder="Username"
          type="text"
          value={this.state.login}
          onChange={e => {
            this.setState({ login: e.target.value });
          }}
        />
        <Input
          className="m-2 flex-item"
          name="password"
          placeholder="Password"
          type="password"
          value={this.state.password}
          onChange={e => {
            this.setState({ password: e.target.value });
          }}
        />
        <span className="message">{this.state.message}</span>
        <Button variant="contained" color="secondary" onClick={this.submitData}>
          Submit
        </Button>
      </div>
    );
  }
}

export default withRouter(LoginForm);
