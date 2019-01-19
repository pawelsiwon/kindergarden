import React, { Component } from "react";
import Axios from "axios";
import { withRouter } from "react-router";

class AddPayment extends Component {
  state = {
    login: "",
    password: ""
  };
  render() {
    return (
      <div className="container">
        <form className="needs=validation">
          <div className="row">
            <button
              className="btn btn-primary m-2"
              onClick={e => this.props.history.push("/app")}
            >
              Return
            </button>
          </div>
          <h4>Add new payment</h4>
          <br />
          <div className="row">
            <div className="col-md-4 mb-3">
              <label htmlFor="0002">Login</label>
              <input
                type="text"
                className="form-control"
                id="0002"
                value={this.state.login}
                onChange={e => this.setState({ login: e.target.value })}
              />
            </div>
            <div className="col-md-4 mb-3">
              <label htmlFor="0003">Password</label>
              <input
                type="password"
                className="form-control"
                id="0003"
                value={this.state.password}
                onChange={e => this.setState({ password: e.target.value })}
              />
            </div>

            <div className="col-md-4 mb-3">
              <label htmlFor="firstName">Person ID</label>
              <input
                id="firstName"
                className="form-control"
                type="text"
                value={this.state.name}
                onChange={e => this.setState({ name: e.target.value })}
              />
            </div>

            <div className="col-md-4 mb-3">
              <label htmlFor="firstName">Child ID</label>
              <input
                id="firstName"
                className="form-control"
                type="text"
                value={this.state.name}
                onChange={e => this.setState({ name: e.target.value })}
              />
            </div>

            <div className="col-md-4 mb-3">
              <label htmlFor="firstName">Name</label>
              <input
                id="firstName"
                className="form-control"
                type="text"
                value={this.state.name}
                onChange={e => this.setState({ name: e.target.value })}
              />
            </div>

            <div className="col-md-4 mb-3">
              <label htmlFor="firstName">Payment ID</label>
              <input
                id="firstName"
                className="form-control"
                type="text"
                value={this.state.name}
                onChange={e => this.setState({ name: e.target.value })}
              />
            </div>

            <button
              className="btn btn-danger col-md-1 m-2"
              onClick={this.resetForm}
            >
              Reset
            </button>
            <button
              className="btn btn-primary col-md-1 m-2"
              onClick={this.submitPayment}
            >
              Add
            </button>
          </div>
          <div
            className={"m-2 alert alert-" + this.state.alertType}
            role="alert"
          >
            {this.state.formMessage}
          </div>
        </form>
      </div>
    );
  }

  resetForm = () => {
    this.setState({
      name: "",
      surname: "",
      birthdate: "",

      pesel: "",
      phonenumber: "",
      role: "",

      email: "",
      login: "",
      password: "",

      city: "",
      postale: "",
      street: "",
      streetAddress: "",
      suite: "",

      lista: [
        { title: "Administrator", value: "admin" },
        { title: "Parent", value: "parent" },
        { title: "Teacher", value: "teacher" }
      ],
      formMessage: "",
      alertType: ""
    });
  };

  submitPayment = () => {
    console.log(this.props.apiHost + "/payment/add");
    const requestData = {
      loginData: this.props.session,
      person: this.state,
      personId: null
    };
    console.log(requestData);
    Axios.post(this.props.apiHost + "/payment/add", requestData)
      .then(
        this.setState({
          alertType: "success",
          formMessage: "Person added!"
        })
      )
      .catch(
        this.setState({
          alertType: "warning",
          formMessage:
            "An error occured, please try again or contact with admin!"
        })
      );
  };
}

export default withRouter(AddPayment);
