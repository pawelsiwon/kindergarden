import React, { Component } from "react";
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
    Axois.post("http://localhost:8081/api/login", this.state)
      .then((res, req) => {
        console.log(res);
        this.setState({ message: res.data });
      })
      .catch(err => {
        console.log(err);
      });
  };

  render() {
    return (
      <div className="container">
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
          type="text"
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

export default LoginForm;
